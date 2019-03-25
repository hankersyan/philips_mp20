package io.hankers.mp20;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Models {

	// About unsigned byte
	// https://stackoverflow.com/questions/4266756/can-we-make-unsigned-byte-in-java
	// println((byte) 200) // output -56
	// println(((byte) 200) && 0xFF) // output 200
	// public static int byteToUnsigned(byte b) {
	// return b & 0xFF;
	// }

	public static class Ubyte {
		private byte b;

		public int value() {
			return b;
		}

		public void setValue(int i) {
			b = (byte) (i & 0xFF);
		}

		public void read(InputStream ins) throws IOException {
			b = (byte) ins.read();
		}

		public void write(DataOutputStream ous) throws IOException {
			ous.writeByte(b);
		}
	}

	public static class Ushort {
		private byte l;
		private byte h;

		public int value() {
			return Byte.toUnsignedInt(h) << 8 + Byte.toUnsignedInt(l);
		}

		public void setValue(int i) {
			l = (byte) (i & 0xFF);
			h = (byte) (i >> 8 & 0xFF);
		}

		public void read(InputStream ins, boolean bigEndian) throws IOException {
			if (bigEndian) {
				h = (byte) ins.read();
				l = (byte) ins.read();
			} else {
				l = (byte) ins.read();
				h = (byte) ins.read();
			}
		}

		public void write(DataOutputStream ous, boolean bigEndian) throws IOException {
			if (bigEndian) {
				ous.writeByte(h);
				ous.writeByte(l);
			} else {
				ous.writeByte(l);
				ous.writeByte(h);
			}
		}
	}

	public static class Uint {
		private byte l;
		private byte ml;
		private byte mh;
		private byte h;

		public long value() {
			return Byte.toUnsignedLong(h) << 24 + Byte.toUnsignedLong(mh) << 16 + Byte.toUnsignedLong(ml) << 8
					+ Byte.toUnsignedLong(l);
		}

		public void read(InputStream ins, boolean bigEndian) throws IOException {
			if (bigEndian) {
				h = (byte) ins.read();
				mh = (byte) ins.read();
				ml = (byte) ins.read();
				l = (byte) ins.read();
			} else {
				l = (byte) ins.read();
				ml = (byte) ins.read();
				mh = (byte) ins.read();
				h = (byte) ins.read();
			}
		}

		public void write(DataOutputStream ous, boolean bigEndian) throws IOException {
			if (bigEndian) {
				ous.writeByte(h);
				ous.writeByte(mh);
				ous.writeByte(ml);
				ous.writeByte(l);
			} else {
				ous.writeByte(l);
				ous.writeByte(ml);
				ous.writeByte(mh);
				ous.writeByte(h);
			}
		}
	}

	public static class Float32 {
		private byte l;
		private byte ml;
		private byte mh;
		private byte h;
		private double value;

		public double value() {
			return value;
		}

		public void read(InputStream ins, boolean bigEndian) throws IOException {
			if (bigEndian) {
				h = (byte) ins.read();
				mh = (byte) ins.read();
				ml = (byte) ins.read();
				l = (byte) ins.read();
			} else {
				l = (byte) ins.read();
				ml = (byte) ins.read();
				mh = (byte) ins.read();
				h = (byte) ins.read();
			}

			int exponent = h;
			int mantissa = mh << 16 + ml << 8 + l;
			value = mantissa * Math.pow((double) 10, (double) exponent);
		}

		public void write(DataOutputStream ous, boolean bigEndian) throws IOException {
			if (bigEndian) {
				ous.writeByte(h);
				ous.writeByte(mh);
				ous.writeByte(ml);
				ous.writeByte(l);
			} else {
				ous.writeByte(l);
				ous.writeByte(ml);
				ous.writeByte(mh);
				ous.writeByte(h);
			}
		}
	}

	public static class Nomenclature {
		private Ushort magic;
		private byte major;
		private byte minor;

		public void read(InputStream ins, boolean bigEndian) throws IOException {
			magic.read(ins, bigEndian);
			major = (byte) ins.read();
			minor = (byte) ins.read();
		}

		public void write(DataOutputStream ous, boolean bigEndian) throws IOException {
			magic.write(ous, bigEndian);
			ous.writeByte(major);
			ous.writeByte(minor);
		}
	}

	public static class ROapdus {
		Ushort ro_type;
		Ushort length;

		public void read(InputStream ins, boolean bigEndian) throws IOException {
			ro_type.read(ins, bigEndian);
			length.read(ins, bigEndian);
		}

		public void write(DataOutputStream ous, boolean bigEndian) throws IOException {
			ro_type.write(ous, bigEndian);
			length.write(ous, bigEndian);
		}
	}

	public static class ROIVapdu {
		Ushort invoke_id;
		Ushort command_type;
		Ushort length;

		public void read(InputStream ins, boolean bigEndian) throws IOException {
			invoke_id.read(ins, bigEndian);
			command_type.read(ins, bigEndian);
			length.read(ins, bigEndian);
		}

		public void write(DataOutputStream ous, boolean bigEndian) throws IOException {
			invoke_id.write(ous, bigEndian);
			command_type.write(ous, bigEndian);
			length.write(ous, bigEndian);
		}
	}

	public static class GlbHandle {
		private Ushort context_id;
		private Ushort handle;

		public void read(InputStream ins, boolean bigEndian) throws IOException {
			context_id.read(ins, bigEndian);
			handle.read(ins, bigEndian);
		}

		public void write(DataOutputStream ous, boolean bigEndian) throws IOException {
			context_id.write(ous, bigEndian);
			handle.write(ous, bigEndian);
		}
	}

	public static class ManagedObjectId {
		private Ushort obj_class;
		private GlbHandle obj_inst;

		public void read(InputStream ins, boolean bigEndian) throws IOException {
			obj_class.read(ins, bigEndian);
			obj_inst.read(ins, bigEndian);
		}

		public void write(DataOutputStream ous, boolean bigEndian) throws IOException {
			obj_class.write(ous, bigEndian);
			obj_inst.write(ous, bigEndian);
		}
	}

	public static class EventReportArgument {
		private ManagedObjectId managed_object;
		private Uint relative_time;
		private Ushort event_type;
		private Ushort length;

		public void read(InputStream ins, boolean bigEndian) throws IOException {
			managed_object.read(ins, bigEndian);
			relative_time.read(ins, bigEndian);
			event_type.read(ins, bigEndian);
			length.read(ins, bigEndian);
		}

		public void write(DataOutputStream ous, boolean bigEndian) throws IOException {
			managed_object.write(ous, bigEndian);
			relative_time.write(ous, bigEndian);
			event_type.write(ous, bigEndian);
			length.write(ous, bigEndian);
		}
	}

	public static class AVAType {
		Ushort attribute_id;
		Ushort length; // length in bytes
		byte[] buf;
		private Object value;

		public void read(InputStream ins, boolean bigEndian) throws IOException {
			attribute_id.read(ins, bigEndian);
			length.read(ins, bigEndian);
			buf = new byte[length.value()];
			ins.read(buf);

			value = convertToAttributeValue(buf, bigEndian);
		}

		private Object convertToAttributeValue(byte[] value, boolean bigEndian) throws IOException {
			Object ret = null;
			switch (attribute_id.value()) {
			case DataConstants.NOM_ATTR_ID_HANDLE:
				// ReadIDHandle(avaattribobjects);
				ret = b2us(value, 0, bigEndian);
				break;

			case DataConstants.NOM_ATTR_ID_LABEL:
//                    ReadIDLabel(avaattribobjects);
				ret = b2ui(value, 0, bigEndian);
				break;

			case DataConstants.NOM_ATTR_NU_VAL_OBS:
//                    ReadNumericObservationValue(avaattribobjects, timeModel, numericValResult);
				NuObsValue tmp = new NuObsValue();
				InputStream ins = new ByteArrayInputStream(value);
				tmp.read(ins, bigEndian);
				ins.close();
				ret = tmp;
				break;

			case DataConstants.NOM_ATTR_NU_CMPD_VAL_OBS:
//                    ReadCompoundNumericObsValue(avaattribobjects, timeModel, numericValResult);
				NuObsValueCmp tmp2 = new NuObsValueCmp();
				InputStream ins2 = new ByteArrayInputStream(value);
				tmp2.read(ins2, bigEndian);
				ins2.close();
				ret = tmp2;
				break;

			case DataConstants.NOM_ATTR_METRIC_SPECN:
				break;

			case DataConstants.NOM_ATTR_ID_LABEL_STRING:
//                    ReadIDLabelString(avaattribobjects);
				ret = new String(value, StandardCharsets.UTF_8);
				break;

			case DataConstants.NOM_ATTR_SA_VAL_OBS:
//                    using (new PerformanceTimer("NOM_ATTR_SA_VAL_OBS"))
//                        ReadWaveSaObservationValueObject(avaattribobjects, timeModel, waveValResult);
				break;

			case DataConstants.NOM_ATTR_SA_CMPD_VAL_OBS:
//                    ReadCompoundWaveSaObservationValue(avaattribobjects, timeModel, waveValResult);
				break;

			case DataConstants.NOM_ATTR_SA_SPECN:
				ret = ReadSaSpecifications(value, bigEndian);
				break;

			case DataConstants.NOM_ATTR_SCALE_SPECN_I16:
				ret = ReadSaScaleSpecifications(value, bigEndian);
				break;

			case DataConstants.NOM_ATTR_SA_CALIB_I16:
				ret = ReadSaCalibrationSpecifications(value, bigEndian);
				break;

			default:
				// unknown attribute -> do nothing
				break;
			}
			return ret;
		}

		public void write(DataOutputStream ous, boolean bigEndian) throws IOException {
			attribute_id.write(ous, bigEndian);
			length.write(ous, bigEndian);
			ous.write(buf);
		}
	}

	public static class AttributeList {
		private Ushort count;
		private Ushort length; // length in bytes
		private ArrayList<AVAType> value;

		public void read(InputStream ins, boolean bigEndian) throws IOException {
			count.read(ins, bigEndian);
			length.read(ins, bigEndian);
			value = new ArrayList<AVAType>();
			for (int i = 0; i < count.value(); i++) {
				AVAType ava = new AVAType();
				ava.read(ins, bigEndian);
				value.add(ava);
			}
		}

		public void write(DataOutputStream ous, boolean bigEndian) throws IOException {
			count.write(ous, bigEndian);
			length.write(ous, bigEndian);
			for (int i = 0; i < count.value(); i++) {
				value.get(i).write(ous, bigEndian);
			}
		}
	}

	public static class ConnectIndication {
		private Nomenclature nomenclature;
		private ROapdus roapdus;
		private ROIVapdu roivapdu;
		private EventReportArgument eventReportArgument;
		private AttributeList connectIndInfo;

		public void read(InputStream ins, boolean bigEndian) throws IOException {
			nomenclature.read(ins, bigEndian);
			roapdus.read(ins, bigEndian);
			roivapdu.read(ins, bigEndian);
			eventReportArgument.read(ins, bigEndian);
			connectIndInfo.read(ins, bigEndian);
		}

		public void write(DataOutputStream ous, boolean bigEndian) throws IOException {
			nomenclature.write(ous, bigEndian);
			roapdus.write(ous, bigEndian);
			roivapdu.write(ous, bigEndian);
			eventReportArgument.write(ous, bigEndian);
			connectIndInfo.write(ous, bigEndian);
		}
	}

	public static class LI {
		private byte l;
		private byte m;
		private byte h;

		public void read(InputStream ins, boolean bigEndian) throws IOException {
			h = (byte) ins.read();
			if (h == 0xff) {
				m = (byte) ins.read();
				l = (byte) ins.read();
			} else {
				l = h;
				h = 0;
				m = 0;
			}
		}

		public void write(DataOutputStream ous, boolean bigEndian) throws IOException {
			if (h == 0xff) {
				ous.writeByte(h);
				ous.writeByte(m);
				ous.writeByte(l);
			} else {
				ous.writeByte(l);
			}
		}
	}

	// BCD encoded, decimal 99 coded as 0x99, 0xff as invalid
	public static class AbsoluteTime {
		private byte century;
		private byte year;
		private byte month;
		private byte day;
		private byte hour;
		private byte minute;
		private byte second;
		@SuppressWarnings("unused")
		private byte sec_fractions;
		private Date date;

		public void read(InputStream ins) throws IOException {
			century = (byte) ins.read();
			year = (byte) ins.read();
			month = (byte) ins.read();
			day = (byte) ins.read();
			hour = (byte) ins.read();
			minute = (byte) ins.read();
			second = (byte) ins.read();
			sec_fractions = (byte) ins.read();

			Calendar c = Calendar.getInstance();
			c.set(century * 100 + year, month - 1, day, hour, minute, second);
			date = c.getTime();
		}

		public void write(DataOutputStream ous) throws IOException {
			ous.writeByte(century);
			ous.writeByte(year);
			ous.writeByte(month);
			ous.writeByte(day);
			ous.writeByte(hour);
			ous.writeByte(minute);
			ous.writeByte(second);
			ous.writeByte(sec_fractions);
		}

		public Date getDate() {
			return date;
		}
	}

	// BCD decode
//	private static int BinaryCodedDecimalToInteger(int value) {
//		if (value != 0xFF) {
//			int lowerNibble = value & 0x0F;
//			int upperNibble = value >> 4;
//
//			int multipleOfOne = lowerNibble;
//			int multipleOfTen = upperNibble * 10;
//
//			return (multipleOfOne + multipleOfTen);
//		} else {
//			return 0;
//		}
//	}

	public static class SessionHeader {
		// PIPG 67
		public static enum SessionHeader_TYPE {
			CN_SPDU_SI(0x0D), AC_SPDU_SI(0x0E), RF_SPDU_SI(0x0C), FN_SPDU_SI(0x09), DN_SPDU_SI(0x0A), AB_SPDU_SI(0x19);

			// declaring private variable for getting values
			private byte action;

			// getter method
			public byte getAction() {
				return this.action;
			}

			// enum constructor - cannot be public or protected
			private SessionHeader_TYPE(int action) {
				this.action = (byte) action;
			}
		}

		private byte type;
		private LI length;

		public void read(InputStream ins, boolean bigEndian) throws IOException {
			type = (byte) ins.read();
			length.read(ins, bigEndian);
		}

		public void write(DataOutputStream ous, boolean bigEndian) throws IOException {
			ous.writeByte(type);
			length.write(ous, bigEndian);
		}
	}

	public static class SPpdu {
		private Ushort session_id;
		private Ushort p_context_id;

		public void read(InputStream ins, boolean bigEndian) throws IOException {
			session_id.read(ins, bigEndian);
			p_context_id.read(ins, bigEndian);
		}

		public void write(DataOutputStream ous, boolean bigEndian) throws IOException {
			session_id.write(ous, bigEndian);
			p_context_id.write(ous, bigEndian);
		}
	}

	// typedef struct {
	// ManagedObjectId managed_object;
	// AttributeList attribute_list;
	// } MdsCreateInfo;
	public static class MdsCreateInfo {
		private ManagedObjectId managed_object;
		private AttributeList attribute_list;

		public void read(InputStream ins, boolean bigEndian) throws IOException {
			managed_object.read(ins, bigEndian);
			attribute_list.read(ins, bigEndian);
		}

		public void write(DataOutputStream ous, boolean bigEndian) throws IOException {
			managed_object.write(ous, bigEndian);
			attribute_list.write(ous, bigEndian);
		}
	}

	// MDSCreateEventReport ::=
	// <SPpdu>
	// <ROapdus (ro_type := ROIV_APDU)>
	// <ROIVapdu (command_type := CMD_CONFIRMED_EVENT_REPORT) >
	// <EventReportArgument (event_type := NOM_NOTI_MDS_CREAT)>
	// <MDSCreateInfo>
	public static class MDSCreateEventReport {
		private SPpdu sppdu;
		private ROapdus roapdus;
		private ROIVapdu roivapdu;
		private EventReportArgument eventReportArgument;
		private MdsCreateInfo mdsCreateInfo;
		private AbsoluteTime absoluteTime;
		private long relativeTime;

		public void read(InputStream ins, boolean bigEndian) throws IOException {
			sppdu.read(ins, bigEndian);
			roapdus.read(ins, bigEndian);
			roivapdu.read(ins, bigEndian);
			eventReportArgument.read(ins, bigEndian);
			mdsCreateInfo.read(ins, bigEndian);

			for (int i = 0; i < mdsCreateInfo.attribute_list.count.value(); i++) {
				AVAType ava = mdsCreateInfo.attribute_list.value.get(i);
				if (ava.attribute_id.value() == DataConstants.NOM_ATTR_TIME_ABS) {
					absoluteTime = new AbsoluteTime();
					InputStream instream = new ByteArrayInputStream(ava.buf);
					absoluteTime.read(instream);
					ins.close();
				} else if (ava.attribute_id.value() == DataConstants.NOM_ATTR_TIME_REL) {
					if (ava.length.value() == 4) {
						relativeTime = java.nio.ByteBuffer.wrap(ava.buf)
								.order(bigEndian ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN).getInt();
					} else {
						System.out.println("ERROR: invalid relative tiime");
					}
				}
			}
			// Get Patient demographics
			// DataConstants.NOM_ATTR_PT_ID
			// DataConstants.NOM_ATTR_PT_NAME_GIVEN
			// DataConstants.NOM_ATTR_PT_NAME_FAMILY
			// DataConstants.NOM_ATTR_PT_DOB
		}

		public void write(DataOutputStream ous, boolean bigEndian) throws IOException {
			sppdu.write(ous, bigEndian);
			roapdus.write(ous, bigEndian);
			roivapdu.write(ous, bigEndian);
			eventReportArgument.write(ous, bigEndian);
			mdsCreateInfo.write(ous, bigEndian);
		}

		public AbsoluteTime getAbsoluteTime() throws IOException {
			return absoluteTime;
		}

		public long getRelativeTime() throws IOException {
			return relativeTime;
		}
	}

	// typedef struct {
	// u_16 invoke_id; /* mirrored back from op. invoke */
	// CMDType command_type; /* identifies type of command */
	// u_16 length; /* no of bytes in rest of message */
	// } RORSapdu;
	public static class RORSapdu {
		Ushort invoke_id;
		Ushort command_type;
		Ushort length;

		public void read(InputStream ins, boolean bigEndian) throws IOException {
			invoke_id.read(ins, bigEndian);
			command_type.read(ins, bigEndian);
			length.read(ins, bigEndian);
		}

		public void write(DataOutputStream ous, boolean bigEndian) throws IOException {
			invoke_id.write(ous, bigEndian);
			command_type.write(ous, bigEndian);
			length.write(ous, bigEndian);
		}
	}

	// typedef struct {
	// ManagedObjectId managed_object;
	// OIDType action_type; /* identification of method */
	// u_16 length; /* size of appended data */
	// } ActionResult;
	public static class ActionResult {
		ManagedObjectId managed_object;
		Ushort action_type; // NOM_ACT_POLL_MDIB_DATA NOM_ACT_POLL_MDIB_DATA_EXT
		Ushort length;

		public void read(InputStream ins, boolean bigEndian) throws IOException {
			managed_object.read(ins, bigEndian);
			action_type.read(ins, bigEndian);
			length.read(ins, bigEndian);
		}

		public void write(DataOutputStream ous, boolean bigEndian) throws IOException {
			managed_object.write(ous, bigEndian);
			action_type.write(ous, bigEndian);
			length.write(ous, bigEndian);
		}
	}

	// typedef struct {
	// u_16 poll_number;
	// RelativeTime rel_time_stamp;
	// AbsoluteTime abs_time_stamp;
	// TYPE polled_obj_type;
	// OIDType polled_attr_grp;
	// PollInfoList poll_info_list;
	// } PollMdibDataReply;
	public static class PollMdibDataReply {
		Ushort poll_number;
		Uint rel_time_stamp;
		AbsoluteTime abs_time_stamp;
		Ushort polled_obj_type;
		Ushort polled_attr_grp;
		PollInfoList poll_info_list;

		public void read(InputStream ins, boolean bigEndian) throws IOException {
			poll_number.read(ins, bigEndian);
			rel_time_stamp.read(ins, bigEndian);
			abs_time_stamp.read(ins);
			polled_obj_type.read(ins, bigEndian);
			polled_attr_grp.read(ins, bigEndian);
			poll_info_list.read(ins, bigEndian);
		}

		public void write(DataOutputStream ous, boolean bigEndian) throws IOException {
			poll_number.write(ous, bigEndian);
			rel_time_stamp.write(ous, bigEndian);
			abs_time_stamp.write(ous);
			polled_obj_type.write(ous, bigEndian);
			polled_attr_grp.write(ous, bigEndian);
			poll_info_list.write(ous, bigEndian);
		}
	}

	// typedef struct {
	// u_16 count;
	// u_16 length;
	// SingleContextPoll value[1];
	// } PollInfoList;
	public static class PollInfoList {
		Ushort count;
		Ushort length;
		List<SingleContextPoll> value;

		public void read(InputStream ins, boolean bigEndian) throws IOException {
			count.read(ins, bigEndian);
			length.read(ins, bigEndian);
			value = new ArrayList<SingleContextPoll>();
			for (int i = 0; i < count.value(); i++) {
				SingleContextPoll ava = new SingleContextPoll();
				ava.read(ins, bigEndian);
				value.add(ava);
			}
		}

		public void write(DataOutputStream ous, boolean bigEndian) throws IOException {
			count.write(ous, bigEndian);
			length.write(ous, bigEndian);
			for (int i = 0; i < count.value(); i++) {
				value.get(i).write(ous, bigEndian);
			}
		}
	}

	// typedef struct {
	// Handle obj_handle;
	// AttributeList attributes;
	// } ObservationPoll;
	public static class ObservationPoll {
		Ushort obj_handle;
		AttributeList attributes;

		public void read(InputStream ins, boolean bigEndian) throws IOException {
			obj_handle.read(ins, bigEndian);
			attributes.read(ins, bigEndian);
		}

		public void write(DataOutputStream ous, boolean bigEndian) throws IOException {
			obj_handle.write(ous, bigEndian);
			attributes.write(ous, bigEndian);
		}
	}

	// typedef struct {
	// MdsContext context_id;
	// struct {
	// u_16 count;
	// u_16 length;
	// ObservationPoll value[1];
	// } poll_info;
	// } SingleContextPoll;
	public static class SingleContextPoll {
		Ushort context_id;
		PollInfo poll_info;

		public void read(InputStream ins, boolean bigEndian) throws IOException {
			context_id.read(ins, bigEndian);
			poll_info.read(ins, bigEndian);
		}

		public void write(DataOutputStream ous, boolean bigEndian) throws IOException {
			context_id.write(ous, bigEndian);
			poll_info.write(ous, bigEndian);
		}
	}

	public static class PollInfo {
		Ushort count;
		Ushort length;
		List<ObservationPoll> value;

		public void read(InputStream ins, boolean bigEndian) throws IOException {
			count.read(ins, bigEndian);
			length.read(ins, bigEndian);
			value = new ArrayList<ObservationPoll>();
			for (int i = 0; i < count.value(); i++) {
				ObservationPoll ava = new ObservationPoll();
				ava.read(ins, bigEndian);
				value.add(ava);
			}
		}

		public void write(DataOutputStream ous, boolean bigEndian) throws IOException {
			count.write(ous, bigEndian);
			length.write(ous, bigEndian);
			for (int i = 0; i < count.value(); i++) {
				value.get(i).write(ous, bigEndian);
			}
		}
	}

	private static int b2us(byte[] buf, int offset, boolean bigEndian) {
		int us = ByteBuffer.wrap(buf).order(bigEndian ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN).getInt();
		return us;
	}

	private static long b2ui(byte[] buf, int offset, boolean bigEndian) {
		long ui = ByteBuffer.wrap(buf).order(bigEndian ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN).getLong();
		return ui;
	}

//	MDSPollActionResult ::=
//            <SPpdu>
//            <ROapdus (ro_type := RORS_APDU)>
//            <RORSapdu (invoke_id := "mirrored from request message"
//                       command_type := CMD_CONFIRMED_ACTION)>
//            <ActionResult
//              (managed_object := {NOM_MOC_VMS_MDS, 0, 0},
//               action_type := NOM_ACT_POLL_MDIB_DATA)>
//            <PollMdibDataReply>
	public static class MDSPollActionResult {
		private SPpdu sppdu; // 4 bytes
		private ROapdus roapdus; // 4 bytes
		private RORSapdu rorsapdu; // 6 bytes
		private ActionResult actionResult; // 10 bytes
		private PollMdibDataReply pollMdibDataReply;

		public void read(InputStream ins, boolean bigEndian) throws IOException {
			sppdu.read(ins, bigEndian);
			roapdus.read(ins, bigEndian);
			rorsapdu.read(ins, bigEndian);
			actionResult.read(ins, bigEndian);
			pollMdibDataReply.read(ins, bigEndian);
		}

		public void write(DataOutputStream ous, boolean bigEndian) throws IOException {
			sppdu.write(ous, bigEndian);
			roapdus.write(ous, bigEndian);
			rorsapdu.write(ous, bigEndian);
			actionResult.write(ous, bigEndian);
			pollMdibDataReply.write(ous, bigEndian);
		}

		public static boolean isValidType(byte[] buf, boolean bigEndian) {
			int ro_type = b2us(buf, 4, bigEndian);
			int action_type = b2us(buf, 20, bigEndian);
			return ro_type == DataConstants.RORS_APDU && action_type == DataConstants.NOM_ACT_POLL_MDIB_DATA;
		}
	}

//	typedef struct PollMdibDataReplyExt {
//	    u_16 			poll_number
//	    u_16 			sequence_no
//	    RelativeTime 	rel_time_stamp
//	    AbsoluteTime 	abs_time_stamp
//	    TYPE 			polled_obj_type
//	    OIDType 		polled_attr_grp
//	    PollInfoList 	poll_info_list
//	} PollMdibDataReplyExt;
	public static class PollMdibDataReplyExt {
		Ushort poll_number;
		Ushort sequence_no;
		Uint rel_time_stamp;
		AbsoluteTime abs_time_stamp;
		Ushort polled_obj_type;
		Ushort polled_attr_grp;
		PollInfoList poll_info_list;

		public void read(InputStream ins, boolean bigEndian) throws IOException {
			poll_number.read(ins, bigEndian);
			sequence_no.read(ins, bigEndian);
			rel_time_stamp.read(ins, bigEndian);
			abs_time_stamp.read(ins);
			polled_obj_type.read(ins, bigEndian);
			polled_attr_grp.read(ins, bigEndian);
			poll_info_list.read(ins, bigEndian);
		}

		public void write(DataOutputStream ous, boolean bigEndian) throws IOException {
			poll_number.write(ous, bigEndian);
			sequence_no.write(ous, bigEndian);
			rel_time_stamp.write(ous, bigEndian);
			abs_time_stamp.write(ous);
			polled_obj_type.write(ous, bigEndian);
			polled_attr_grp.write(ous, bigEndian);
			poll_info_list.write(ous, bigEndian);
		}
	}

//	 MDSPollActionResultExt ::=
//		     <SPpdu>
//		     <ROapdus (ro_type := RORS_APDU)>
//		     <RORSapdu (invoke_id := "mirrored from request message"
//		                command_type := CMD_CONFIRMED_ACTION)>
//		     <ActionResult
//		        (managed_object := {NOM_MOC_VMS_MDS, 0, 0},
//		         action_type := NOM_ACT_POLL_MDIB_DATA_EXT)>
//		     <PollMdibDataReplyExt>
	public static class MDSPollActionResultExt {
		private SPpdu sppdu; // 4 bytes
		private ROapdus roapdus; // 4 bytes
		private RORSapdu rorsapdu; // 6 bytes
		private ActionResult actionResult; // 10 bytes
		private PollMdibDataReply pollMdibDataReply;

		public void read(InputStream ins, boolean bigEndian) throws IOException {
			sppdu.read(ins, bigEndian);
			roapdus.read(ins, bigEndian);
			rorsapdu.read(ins, bigEndian);
			actionResult.read(ins, bigEndian);
			pollMdibDataReply.read(ins, bigEndian);
		}

		public void write(DataOutputStream ous, boolean bigEndian) throws IOException {
			sppdu.write(ous, bigEndian);
			roapdus.write(ous, bigEndian);
			rorsapdu.write(ous, bigEndian);
			actionResult.write(ous, bigEndian);
			pollMdibDataReply.write(ous, bigEndian);
		}

		public static boolean isValidType(byte[] buf, boolean bigEndian) {
			int ro_type = b2us(buf, 4, bigEndian);
			int action_type = b2us(buf, 22, bigEndian); // skip 22 bytes
			return ro_type == DataConstants.RORS_APDU && action_type == DataConstants.NOM_ACT_POLL_MDIB_DATA_EXT;
		}
	}

//	typedef struct  {
//	    OIDType physio_id;
//	    MeasurementState state;
//	    OIDType unit_code;
//	    FLOATType value; // uint32
//	} NuObsValue;
	public static class NuObsValue {
		Ushort physio_id;
		Ushort state; // eg: DEMO_DATA
		Ushort unit_code;
		Float32 value;
		String physio_str;

		public void read(InputStream ins, boolean bigEndian) throws IOException {
			physio_id.read(ins, bigEndian);
			state.read(ins, bigEndian);
			unit_code.read(ins, bigEndian);
			value.read(ins, bigEndian);

			physio_str = DataConstants.AlertSource.GetValue(physio_id.value()).name();
		}

		public void write(DataOutputStream ous, boolean bigEndian) throws IOException {
			physio_id.write(ous, bigEndian);
			state.write(ous, bigEndian);
			unit_code.write(ous, bigEndian);
			value.write(ous, bigEndian);
		}
	}

//	typedef struct {
//        u_16           count;
//        u_16           length;
//        NuObsValue     value[1];
//    } NuObsValueCmp;
	public static class NuObsValueCmp {
		Ushort count;
		Ushort length; // eg: DEMO_DATA
		List<NuObsValue> value;

		public void read(InputStream ins, boolean bigEndian) throws IOException {
			count.read(ins, bigEndian);
			length.read(ins, bigEndian);
			value = new ArrayList<NuObsValue>();
			for (int i = 0; i < count.value(); i++) {
				NuObsValue ava = new NuObsValue();
				ava.read(ins, bigEndian);
				value.add(ava);
			}
		}

		public void write(DataOutputStream ous, boolean bigEndian) throws IOException {
			count.write(ous, bigEndian);
			length.write(ous, bigEndian);
			for (int i = 0; i < count.value(); i++) {
				value.get(i).write(ous, bigEndian);
			}
		}

	}

	private static SaSpec ReadSaSpecifications(byte[] buf, boolean bigEndian) throws IOException {
		InputStream ins = new ByteArrayInputStream(buf);

		SaSpec saspec = new SaSpec();
		saspec.read(ins, bigEndian);

		ins.close();

		return saspec;
	}

	public static ScaleRangeSpec16 ReadSaScaleSpecifications(byte[] buf, boolean bigEndian) throws IOException {
		InputStream ins = new ByteArrayInputStream(buf);

		ScaleRangeSpec16 srspec = new ScaleRangeSpec16();
		srspec.read(ins, bigEndian);

		ins.close();

		return srspec;
	}

	public static SaCalData16 ReadSaCalibrationSpecifications(byte[] buf, boolean bigEndian) throws IOException {
		InputStream ins = new ByteArrayInputStream(buf);

		SaCalData16 sacal = new SaCalData16();
		sacal.read(ins, bigEndian);

		ins.close();

		return sacal;
	}

//    typedef struct {
//    	u_8 sample_size;
//    	u_8 significant_bits;
//    } SampleType;
	public static class SampleType {
		Ubyte sample_size;
		Ubyte significant_bits;

		public void read(InputStream ins) throws IOException {
			sample_size.read(ins);
			significant_bits.read(ins);
		}

		public void write(DataOutputStream ous) throws IOException {
			sample_size.write(ous);
			significant_bits.write(ous);
		}

	}

// typedef struct {
//        u_16 array_size;
//        SampleType sample_type;
//        SaFlags flags;
// } SaSpec;
	public static class SaSpec {
		Ushort array_size;
		SampleType sample_type;
		Ushort flags;

		// SaFlags
		public static final int SMOOTH_CURVE = 0x8000;
		public static final int DELAYED_CURVE = 0x4000;
		public static final int STATIC_SCALE = 0x2000;
		public static final int SA_EXT_VAL_RANGE = 0x1000;

		public void read(InputStream ins, boolean bigEndian) throws IOException {
			array_size.read(ins, bigEndian);
			sample_type.read(ins);
			flags.read(ins, bigEndian);
		}

		public void write(DataOutputStream ous, boolean bigEndian) throws IOException {
			array_size.write(ous, bigEndian);
			sample_type.write(ous);
			flags.write(ous, bigEndian);
		}
	}

//    typedef struct {
//    	FLOATType lower_absolute_value; 
//    	FLOATType upper_absolute_value; 
//    	u_16 lower_scaled_value; 
//    	u_16 upper_scaled_value;
//    } ScaleRangeSpec16;
	public static class ScaleRangeSpec16 {
		Float32 lower_absolute_value;
		Float32 upper_absolute_value;
		Ushort lower_scaled_value;
		Ushort upper_scaled_value;

		public void read(InputStream ins, boolean bigEndian) throws IOException {
			lower_absolute_value.read(ins, bigEndian);
			upper_absolute_value.read(ins, bigEndian);
			lower_scaled_value.read(ins, bigEndian);
			upper_scaled_value.read(ins, bigEndian);
		}

		public void write(DataOutputStream ous, boolean bigEndian) throws IOException {
			lower_absolute_value.write(ous, bigEndian);
			upper_absolute_value.write(ous, bigEndian);
			lower_scaled_value.write(ous, bigEndian);
			upper_scaled_value.write(ous, bigEndian);
		}
	}

//typedef struct {
//    FLOATType lower_absolute_value;        
//    FLOATType upper_absolute_value;
//    u_16 lower_scaled_value;
//    u_16 upper_scaled_value;
//    u_16 increment;
//    u_16 cal_type;
//#define BAR  0
//#define STAIR 1
//} SaCalData16;
	public static class SaCalData16 {
		Float32 lower_absolute_value;
		Float32 upper_absolute_value;
		Ushort lower_scaled_value;
		Ushort upper_scaled_value;
		Ushort increment;
		Ushort cal_type;

		// Cal_Type
		public static final int BAR = 0;
		public static final int STAIR = 1;

		public void read(InputStream ins, boolean bigEndian) throws IOException {
			lower_absolute_value.read(ins, bigEndian);
			upper_absolute_value.read(ins, bigEndian);
			lower_scaled_value.read(ins, bigEndian);
			upper_scaled_value.read(ins, bigEndian);
			increment.read(ins, bigEndian);
			cal_type.read(ins, bigEndian);
		}

		public void write(DataOutputStream ous, boolean bigEndian) throws IOException {
			lower_absolute_value.write(ous, bigEndian);
			upper_absolute_value.write(ous, bigEndian);
			lower_scaled_value.write(ous, bigEndian);
			upper_scaled_value.write(ous, bigEndian);
			increment.write(ous, bigEndian);
			cal_type.write(ous, bigEndian);
		}
	}

}
