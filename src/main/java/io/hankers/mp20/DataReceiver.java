package io.hankers.mp20;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.hankers.mp20.Models.AVAType;
import io.hankers.mp20.Models.MDSPollActionResult;
import io.hankers.mp20.Models.MDSPollActionResultExt;
import io.hankers.mp20.Models.MDSPollActionResultExtLinked;
import io.hankers.mp20.Models.MDSPollActionResultLinked;
import io.hankers.mp20.Models.ROIVapdu;
import io.hankers.mp20.Models.ROapdus;

public class DataReceiver extends Thread {
	private DatagramSocket _socket;
	private byte[] _buf = new byte[2048];
	private final static int PORT = 24105;
	private static InetAddress ADDR;
	private Models.AbsoluteTime _absoluteTime;
	private SimpleDateFormat _sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz");
	private Thread _pollThread;
	private boolean _bigEndian = true;
	static final Logger _logger = LogManager.getLogger(DataReceiver.class.getName());

	public DataReceiver() throws SocketException, UnknownHostException {
		_socket = new DatagramSocket();
		ADDR = InetAddress.getByName("127.0.0.1");
	}

	public void run() {
		DatagramPacket packet = new DatagramPacket(_buf, _buf.length);
		boolean succeedToAssociate = false;
		while (!Thread.currentThread().isInterrupted()) {
			try {
				try {
					if (!succeedToAssociate) {
						_socket.connect(ADDR, PORT);
						succeedToAssociate = sendAssociationRequest();
					}

					Arrays.fill(_buf, (byte) 0);
					_socket.receive(packet);

					ProcessPacket(_buf, packet.getLength());
				} catch (IOException e) {
					succeedToAssociate = false;
					Thread.sleep(3000);
					e.printStackTrace();
					_logger.error(e);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
				_logger.error(e);
				break;
			}
		}
		if (_pollThread != null && !_pollThread.isInterrupted()) {
			_pollThread.interrupt();
		}
		_socket.close();
	}

	private boolean sendAssociationRequest() throws IOException, InterruptedException {
		byte[] sendData = DataConstants.aarq_msg_wave_ext_poll2;
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length);
		_socket.send(sendPacket);

		java.util.Arrays.fill(_buf, (byte) 0);

		DatagramPacket receivePacket = new DatagramPacket(_buf, _buf.length);
		_socket.receive(receivePacket);
		String receivedSentence = new String(receivePacket.getData());
		_logger.debug("AssociationResult FROM SERVER:" + receivedSentence);

		java.util.Arrays.fill(_buf, (byte) 0);

		_socket.receive(receivePacket);
		receivedSentence = new String(receivePacket.getData());
		_logger.debug("MDSCreateEventReport FROM SERVER:" + receivedSentence);

		Models.MDSCreateEventReport mdsCreateEventReport = new Models.MDSCreateEventReport();
		InputStream ins = new ByteArrayInputStream(_buf, 0, receivePacket.getLength());
		mdsCreateEventReport.read(ins, true);
		ins.close();

		_absoluteTime = mdsCreateEventReport.getAbsoluteTime();
		long relativeTime = mdsCreateEventReport.getRelativeTime();
		_logger.debug("MDS Date,abs={},rel={}", _sdf.format(_absoluteTime.getDate()), relativeTime);

		_logger.debug(">>SendMDSCreateEventResult");
		sendData = DataConstants.mds_create_resp_msg;
		sendPacket = new DatagramPacket(sendData, sendData.length);
		_socket.send(sendPacket);

		sendData = DataConstants.get_rtsa_prio_msg;
		sendPacket = new DatagramPacket(sendData, sendData.length);
		_socket.send(sendPacket);

		SendRTSAPriorityMessage(CreateWaveformSet(9, _bigEndian));

		if (_pollThread != null && !_pollThread.isInterrupted()) {
			_pollThread.interrupt();
			_pollThread.join();
		}
		(_pollThread = new PollRequestThread(_socket)).start();

		return true;
	}

	public static class PollRequestThread extends Thread {
		private DatagramSocket _socket;
		private DatagramPacket dataPollPacket, wavePollPacket;

		public PollRequestThread(DatagramSocket socket) {
			_socket = socket;

			dataPollPacket = new DatagramPacket(DataConstants.ext_poll_request_msg,
					DataConstants.ext_poll_request_msg.length);
			wavePollPacket = new DatagramPacket(DataConstants.ext_poll_request_wave_msg,
					DataConstants.ext_poll_request_wave_msg.length);
		}

		public void run() {
			while (!Thread.currentThread().isInterrupted()) {
				try {
					_socket.send(dataPollPacket);
					_socket.send(wavePollPacket);

					Thread.sleep(1000);
				} catch (IOException e) {
					e.printStackTrace();
					_logger.error(e);
				} catch (InterruptedException e2) {
					e2.printStackTrace();
					_logger.error(e2);
				}
			}
		}
	}

	public static class ReceiveThread extends Thread {
		private DatagramSocket _socket;
		private byte[] _buf = new byte[2048];
		private DatagramPacket _revPacket;

		public ReceiveThread(DatagramSocket socket) {
			_socket = socket;
			_revPacket = new DatagramPacket(_buf, _buf.length);
		}

		public void run() {
			while (!Thread.currentThread().isInterrupted()) {
				try {
					Arrays.fill(_buf, (byte) 0);
					_socket.receive(_revPacket);
				} catch (IOException e) {
					e.printStackTrace();
					_logger.error(e);
				}
			}
		}
	}

	private static byte[] s2b(short s, boolean bigEndian) {
		return ByteBuffer.allocate(2).putShort(s).order(bigEndian ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN)
				.array();
	}

	private static byte[] i2b(int i, boolean bigEndian) {
		return ByteBuffer.allocate(4).putInt(i).order(bigEndian ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN)
				.array();
	}

	public static byte[] CreateWaveformSet(int nWaveSetType, boolean bigEndian) {
		// Upto 3 ECG and/or 8 non-ECG waveforms can be polled by selecting the
		// appropriate labels
		// in the Wave object priority list
		byte[] WaveTrtype = new byte[0];
		switch (nWaveSetType) {
		case 0:
			break;

		case 1:
			WaveTrtype = ArrayUtils.addAll(WaveTrtype, s2b((short) 0x03, bigEndian)); // count
			WaveTrtype = ArrayUtils.addAll(WaveTrtype, s2b((short) 0x0C, bigEndian)); // length
			WaveTrtype = ArrayUtils.addAll(WaveTrtype,
					i2b((int) DataConstants.WavesIDLabels.NLS_NOM_ECG_ELEC_POTL_II.getValue(), bigEndian));
			WaveTrtype = ArrayUtils.addAll(WaveTrtype,
					i2b((int) DataConstants.WavesIDLabels.NLS_NOM_ECG_ELEC_POTL_I.getValue(), bigEndian));
			WaveTrtype = ArrayUtils.addAll(WaveTrtype,
					i2b((int) DataConstants.WavesIDLabels.NLS_NOM_ECG_ELEC_POTL_III.getValue(), bigEndian));
			break;

		case 2:
			WaveTrtype = ArrayUtils.addAll(WaveTrtype, s2b((short) 0x09, bigEndian)); // count
			WaveTrtype = ArrayUtils.addAll(WaveTrtype, s2b((short) 0x24, bigEndian)); // length
			WaveTrtype = ArrayUtils.addAll(WaveTrtype,
					i2b((int) DataConstants.WavesIDLabels.NLS_NOM_ECG_ELEC_POTL_II.getValue(), bigEndian));
			WaveTrtype = ArrayUtils.addAll(WaveTrtype,
					i2b((int) DataConstants.WavesIDLabels.NLS_NOM_ECG_ELEC_POTL_V5.getValue(), bigEndian));
			WaveTrtype = ArrayUtils.addAll(WaveTrtype,
					i2b((int) DataConstants.WavesIDLabels.NLS_NOM_RESP.getValue(), bigEndian));
			WaveTrtype = ArrayUtils.addAll(WaveTrtype,
					i2b((int) DataConstants.WavesIDLabels.NLS_NOM_PULS_OXIM_PLETH.getValue(), bigEndian));
			WaveTrtype = ArrayUtils.addAll(WaveTrtype,
					i2b((int) DataConstants.WavesIDLabels.NLS_NOM_PRESS_BLD_ART.getValue(), bigEndian));
			WaveTrtype = ArrayUtils.addAll(WaveTrtype,
					i2b((int) DataConstants.WavesIDLabels.NLS_NOM_PRESS_BLD_VEN_CENT.getValue(), bigEndian));
			WaveTrtype = ArrayUtils.addAll(WaveTrtype,
					i2b((int) DataConstants.WavesIDLabels.NLS_NOM_AWAY_CO2.getValue(), bigEndian));
			WaveTrtype = ArrayUtils.addAll(WaveTrtype,
					i2b((int) DataConstants.WavesIDLabels.NLS_NOM_PRESS_AWAY.getValue(), bigEndian));
			WaveTrtype = ArrayUtils.addAll(WaveTrtype,
					i2b((int) DataConstants.WavesIDLabels.NLS_NOM_FLOW_AWAY.getValue(), bigEndian));
			break;

		case 3:
			WaveTrtype = ArrayUtils.addAll(WaveTrtype, s2b((short) 0x03, bigEndian)); // count
			WaveTrtype = ArrayUtils.addAll(WaveTrtype, s2b((short) 0x0C, bigEndian)); // length
			WaveTrtype = ArrayUtils.addAll(WaveTrtype,
					i2b((int) DataConstants.WavesIDLabels.NLS_NOM_ECG_ELEC_POTL_AVR.getValue(), bigEndian));
			WaveTrtype = ArrayUtils.addAll(WaveTrtype,
					i2b((int) DataConstants.WavesIDLabels.NLS_NOM_ECG_ELEC_POTL_AVL.getValue(), bigEndian));
			WaveTrtype = ArrayUtils.addAll(WaveTrtype,
					i2b((int) DataConstants.WavesIDLabels.NLS_NOM_ECG_ELEC_POTL_AVF.getValue(), bigEndian));
			break;

		case 4:
			WaveTrtype = ArrayUtils.addAll(WaveTrtype, s2b((short) 0x03, bigEndian)); // count
			WaveTrtype = ArrayUtils.addAll(WaveTrtype, s2b((short) 0x0C, bigEndian)); // length
			WaveTrtype = ArrayUtils.addAll(WaveTrtype,
					i2b((int) DataConstants.WavesIDLabels.NLS_NOM_ECG_ELEC_POTL_V1.getValue(), bigEndian));
			WaveTrtype = ArrayUtils.addAll(WaveTrtype,
					i2b((int) DataConstants.WavesIDLabels.NLS_NOM_ECG_ELEC_POTL_V2.getValue(), bigEndian));
			WaveTrtype = ArrayUtils.addAll(WaveTrtype,
					i2b((int) DataConstants.WavesIDLabels.NLS_NOM_ECG_ELEC_POTL_V3.getValue(), bigEndian));
			break;

		case 5:
			WaveTrtype = ArrayUtils.addAll(WaveTrtype, s2b((short) 0x03, bigEndian)); // count
			WaveTrtype = ArrayUtils.addAll(WaveTrtype, s2b((short) 0x0C, bigEndian)); // length
			WaveTrtype = ArrayUtils.addAll(WaveTrtype,
					i2b((int) DataConstants.WavesIDLabels.NLS_NOM_ECG_ELEC_POTL_V4.getValue(), bigEndian));
			WaveTrtype = ArrayUtils.addAll(WaveTrtype,
					i2b((int) DataConstants.WavesIDLabels.NLS_NOM_ECG_ELEC_POTL_V5.getValue(), bigEndian));
			WaveTrtype = ArrayUtils.addAll(WaveTrtype,
					i2b((int) DataConstants.WavesIDLabels.NLS_NOM_ECG_ELEC_POTL_V6.getValue(), bigEndian));
			break;

		case 6:
			WaveTrtype = ArrayUtils.addAll(WaveTrtype, s2b((short) 0x04, bigEndian)); // count
			WaveTrtype = ArrayUtils.addAll(WaveTrtype, s2b((short) 0x10, bigEndian)); // length
			WaveTrtype = ArrayUtils.addAll(WaveTrtype,
					i2b((int) DataConstants.WavesIDLabels.NLS_EEG_NAMES_EEG_CHAN1_LBL.getValue(), bigEndian));
			WaveTrtype = ArrayUtils.addAll(WaveTrtype,
					i2b((int) DataConstants.WavesIDLabels.NLS_EEG_NAMES_EEG_CHAN2_LBL.getValue(), bigEndian));
			WaveTrtype = ArrayUtils.addAll(WaveTrtype,
					i2b((int) DataConstants.WavesIDLabels.NLS_EEG_NAMES_EEG_CHAN3_LBL.getValue(), bigEndian));
			WaveTrtype = ArrayUtils.addAll(WaveTrtype,
					i2b((int) DataConstants.WavesIDLabels.NLS_EEG_NAMES_EEG_CHAN4_LBL.getValue(), bigEndian));
			break;

		case 7:
			WaveTrtype = ArrayUtils.addAll(WaveTrtype, s2b((short) 0x01, bigEndian)); // count
			WaveTrtype = ArrayUtils.addAll(WaveTrtype, s2b((short) 0x04, bigEndian)); // length
			WaveTrtype = ArrayUtils.addAll(WaveTrtype,
					i2b((int) DataConstants.WavesIDLabels.NLS_NOM_PRESS_BLD_ART.getValue(), bigEndian));
			break;

		case 8:
			WaveTrtype = ArrayUtils.addAll(WaveTrtype, s2b((short) 0x05, bigEndian)); // count
			WaveTrtype = ArrayUtils.addAll(WaveTrtype, s2b((short) 0x14, bigEndian)); // length
			WaveTrtype = ArrayUtils.addAll(WaveTrtype,
					i2b((int) DataConstants.WavesIDLabels.NLS_NOM_ECG_ELEC_POTL.getValue(), bigEndian));
			WaveTrtype = ArrayUtils.addAll(WaveTrtype,
					i2b((int) DataConstants.WavesIDLabels.NLS_NOM_PULS_OXIM_PLETH.getValue(), bigEndian));
			WaveTrtype = ArrayUtils.addAll(WaveTrtype,
					i2b((int) DataConstants.WavesIDLabels.NLS_NOM_PRESS_BLD_ART.getValue(), bigEndian));
			WaveTrtype = ArrayUtils.addAll(WaveTrtype,
					i2b((int) DataConstants.WavesIDLabels.NLS_NOM_PRESS_BLD_VEN_CENT.getValue(), bigEndian));
			WaveTrtype = ArrayUtils.addAll(WaveTrtype,
					i2b((int) DataConstants.WavesIDLabels.NLS_NOM_AWAY_CO2.getValue(), bigEndian));
			break;

		case 9:
			WaveTrtype = ArrayUtils.addAll(WaveTrtype, s2b((short) 0x04, bigEndian)); // count
			WaveTrtype = ArrayUtils.addAll(WaveTrtype, s2b((short) 0x10, bigEndian)); // length
			WaveTrtype = ArrayUtils.addAll(WaveTrtype,
					i2b((int) DataConstants.WavesIDLabels.NLS_NOM_ECG_ELEC_POTL_II.getValue(), bigEndian));
			WaveTrtype = ArrayUtils.addAll(WaveTrtype,
					i2b((int) DataConstants.WavesIDLabels.NLS_NOM_ECG_ELEC_POTL_I.getValue(), bigEndian));
			WaveTrtype = ArrayUtils.addAll(WaveTrtype,
					i2b((int) DataConstants.WavesIDLabels.NLS_NOM_ECG_ELEC_POTL_III.getValue(), bigEndian));
			WaveTrtype = ArrayUtils.addAll(WaveTrtype,
					i2b((int) DataConstants.WavesIDLabels.NLS_NOM_PULS_OXIM_PLETH.getValue(), bigEndian));
			break;
		}
		return WaveTrtype;
	}

	public void SendRTSAPriorityMessage(byte[] WaveTrType) throws IOException {
		byte[] tempbufflist = new byte[0];

		// Assemble request in reverse order first to calculate lengths
		// Insert TextIdList
		tempbufflist = ArrayUtils.addAll(tempbufflist, WaveTrType);

		AVAType avatype = new AVAType();
		avatype.attribute_id.setValue(DataConstants.AttributeIDs.NOM_ATTR_POLL_RTSA_PRIO_LIST.getValue());
		avatype.length.setValue(WaveTrType.length);

		tempbufflist = ArrayUtils.addAll(tempbufflist, s2b((short) avatype.length.value(), _bigEndian));
		tempbufflist = ArrayUtils.addAll(tempbufflist, s2b((short) avatype.attribute_id.value(), _bigEndian));

		byte[] AttributeModEntry = { 0x00, 0x00 };
		tempbufflist = ArrayUtils.addAll(tempbufflist, AttributeModEntry);

		byte[] ModListlength = s2b((short) tempbufflist.length, _bigEndian);
		byte[] ModListCount = { 0x00, 0x01 };

		tempbufflist = ArrayUtils.addAll(tempbufflist, ModListlength);
		tempbufflist = ArrayUtils.addAll(tempbufflist, ModListCount);

		byte[] ManagedObjectID = { 0x00, 0x21, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 };
		tempbufflist = ArrayUtils.addAll(tempbufflist, ManagedObjectID);

		ROIVapdu rovi = new ROIVapdu();
		rovi.length.setValue(tempbufflist.length);
		rovi.command_type.setValue(DataConstants.CMD_CONFIRMED_SET);
		rovi.invoke_id.setValue(0x0000);
		tempbufflist = ArrayUtils.addAll(tempbufflist, s2b((short) rovi.length.value(), _bigEndian));
		tempbufflist = ArrayUtils.addAll(tempbufflist, s2b((short) rovi.command_type.value(), _bigEndian));
		tempbufflist = ArrayUtils.addAll(tempbufflist, s2b((short) rovi.invoke_id.value(), _bigEndian));

		ROapdus roap = new ROapdus();
		roap.length.setValue(tempbufflist.length);
		roap.ro_type.setValue(DataConstants.ROIV_APDU);
		tempbufflist = ArrayUtils.addAll(tempbufflist, s2b((short) roap.length.value(), _bigEndian));
		tempbufflist = ArrayUtils.addAll(tempbufflist, s2b((short) roap.ro_type.value(), _bigEndian));

		byte[] Spdu = DataConstants.trimByteArray(new int[] { 0xE1, 0x00, 0x00, 0x02 });
		tempbufflist = ArrayUtils.addAll(tempbufflist, Spdu);

		byte[] finaltxbuff = tempbufflist;
		DatagramPacket sendPkt = new DatagramPacket(finaltxbuff, finaltxbuff.length);
		_socket.send(sendPkt);
	}

	private void ProcessPacket(byte[] packetbuffer, int length) throws IOException {
		InputStream ins = new ByteArrayInputStream(_buf, 0, length);
		if (MDSPollActionResult.isValidType(packetbuffer, _bigEndian)) {
			MDSPollActionResult result = new MDSPollActionResult();
			result.read(ins, _bigEndian);
		} else if (MDSPollActionResultLinked.isValidType(packetbuffer, _bigEndian)) {
			MDSPollActionResultLinked result = new MDSPollActionResultLinked();
			result.read(ins, _bigEndian);
		} else if (MDSPollActionResultExt.isValidType(packetbuffer, _bigEndian)) {
			MDSPollActionResultExt resultEx = new MDSPollActionResultExt();
			resultEx.read(ins, _bigEndian);
		} else if (MDSPollActionResultExtLinked.isValidType(packetbuffer, _bigEndian)) {
			MDSPollActionResultExtLinked resultEx = new MDSPollActionResultExtLinked();
			resultEx.read(ins, _bigEndian);
		} else {
			_logger.debug("Process other type packet");
		}
		ins.close();
	}

}
