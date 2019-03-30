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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.hankers.mp20.DataConstants.AttributeIDs;

public class Models {

	static final Logger logger = LogManager.getLogger(Models.class.getName());

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
			return Byte.toUnsignedInt(b);
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
			return (Byte.toUnsignedInt(h) << 8) + Byte.toUnsignedInt(l);
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
			return (Byte.toUnsignedLong(h) << 24) + (Byte.toUnsignedLong(mh) << 16) + (Byte.toUnsignedLong(ml) << 8)
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
		private Ubyte l = new Ubyte();
		private Ubyte ml = new Ubyte();
		private Ubyte mh = new Ubyte();
		private byte h;
		private double value = Double.NaN;
		static final double MIN = -(Math.pow(2, 23) - 3);
		static final double MAX = +(Math.pow(2, 23) - 3);

		public double value() {
			return value;
		}
		
		public String toString() {
			if (Double.isNaN(value)) {
				return "NaN";
			}
			return String.valueOf(value);
		}

		public void read(InputStream ins, boolean bigEndian) throws IOException {
			if (bigEndian) {
				h = (byte) ins.read();
				mh.read(ins);
				ml.read(ins);
				l.read(ins);
			} else {
				l.read(ins);
				ml.read(ins);
				mh.read(ins);
				h = (byte) ins.read();
			}

			int exponent = h; // signed
			int mantissa = (mh.value() << 16) + (ml.value() << 8) + l.value(); // signed
			// -128 <= exponent <= 127
			// -(2^23-3) <= mantissa <= +(2^23-3)
			if (MIN <= mantissa && mantissa <= MAX) {
				value = mantissa * Math.pow((double) 10, (double) exponent);
			} else {
				value = Double.NaN;
			}
			logger.trace("Float32,{},{},{},{},{}={}", h, mh.value(), ml.value(), l.value(), mantissa, value);
		}

		public void write(DataOutputStream ous, boolean bigEndian) throws IOException {
			if (bigEndian) {
				ous.write(h);
				mh.write(ous);
				ml.write(ous);
				l.write(ous);
			} else {
				l.write(ous);
				ml.write(ous);
				mh.write(ous);
				ous.write(h);
			}
		}
	}

	public static class Nomenclature {
		private Ushort magic = new Ushort();
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
		Ushort ro_type = new Ushort(); // ROIV_APDU 1, RORS_APDU 2, ROER_APDU 3, ROLRS_APDU 5
		Ushort length = new Ushort();

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
		Ushort invoke_id = new Ushort();
		Ushort command_type = new Ushort();
		Ushort length = new Ushort();

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
		private Ushort context_id = new Ushort();
		private Ushort handle = new Ushort();

		public void read(InputStream ins, boolean bigEndian) throws IOException {
			context_id.read(ins, bigEndian);
			handle.read(ins, bigEndian);
		}

		public void write(DataOutputStream ous, boolean bigEndian) throws IOException {
			context_id.write(ous, bigEndian);
			handle.write(ous, bigEndian);
		}
	}

	public static class ManagedObjectId { // 6 bytes
		private Ushort obj_class = new Ushort();
		private GlbHandle obj_inst = new GlbHandle(); // 4 bytes

		public void read(InputStream ins, boolean bigEndian) throws IOException {
			obj_class.read(ins, bigEndian);
			obj_inst.read(ins, bigEndian);
		}

		public void write(DataOutputStream ous, boolean bigEndian) throws IOException {
			obj_class.write(ous, bigEndian);
			obj_inst.write(ous, bigEndian);
		}
	}

//	typedef struct {
//		ManagedObjectId managed_object;
//		AttributeList attributeList;
//	} GetResult;
//	typedef struct {
//		ManagedObjectId managed_object; 
//		AttributeList attributeList;
//	} SetResult;
	public static class GetResult {
		ManagedObjectId managed_object = new ManagedObjectId();
		AttributeList attributeList = new AttributeList();
	}

	public static class EventReportArgument { // 14 bytes
		private ManagedObjectId managed_object = new ManagedObjectId(); // 6 bytes
		private Uint relative_time = new Uint();
		private Ushort event_type = new Ushort();
		private Ushort length = new Ushort();

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
		Ushort attribute_id = new Ushort();
		Ushort length = new Ushort(); // length in bytes
		byte[] buf;
		private Object value;

		public void read(InputStream ins, boolean bigEndian) throws IOException {
			attribute_id.read(ins, bigEndian);
			length.read(ins, bigEndian);
			buf = new byte[length.value()];
			ins.read(buf);

			value = convertToAttributeValue(buf, bigEndian);
			AttributeIDs attrId = DataConstants.AttributeIDs.GetValue(attribute_id.value());
			logger.debug("AVA={},\t\t{}", attrId != null ? attrId.name() : attribute_id.value(), value);
		}

		public Object getValue() {
			return value;
		}

		private Object convertToAttributeValue(byte[] value, boolean bigEndian) throws IOException {
			Object ret = null;
			switch (attribute_id.value()) {
			case DataConstants.NOM_ATTR_ID_HANDLE:
				// ReadIDHandle(avaattribobjects);
				ret = b2us(value, 0, bigEndian);
				break;

			case DataConstants.NOM_ATTR_ID_LABEL:
//              ReadIDLabel(avaattribobjects);
				ret = b2ui(value, 0, bigEndian);
				break;

			case DataConstants.NOM_ATTR_NU_VAL_OBS:
//              ReadNumericObservationValue(avaattribobjects, timeModel, numericValResult);
				NuObsValue tmp = new NuObsValue();
				InputStream ins = new ByteArrayInputStream(value);
				tmp.read(ins, bigEndian);
				ins.close();
				ret = tmp;
				break;

			case DataConstants.NOM_ATTR_NU_CMPD_VAL_OBS:
//              ReadCompoundNumericObsValue(avaattribobjects, timeModel, numericValResult);
				NuObsValueCmp tmp2 = new NuObsValueCmp();
				InputStream ins2 = new ByteArrayInputStream(value);
				tmp2.read(ins2, bigEndian);
				ins2.close();
				ret = tmp2;
				break;

			case DataConstants.NOM_ATTR_METRIC_SPECN:
				break;

			case DataConstants.NOM_ATTR_ID_LABEL_STRING:
//              ReadIDLabelString(avaattribobjects);
				ret = new String(value, StandardCharsets.UTF_8);
				break;

			case DataConstants.NOM_ATTR_SA_VAL_OBS:
//              using (new PerformanceTimer("NOM_ATTR_SA_VAL_OBS"))
//              	ReadWaveSaObservationValueObject(avaattribobjects, timeModel, waveValResult);
				SaObsValue saobsvalue = new SaObsValue();
				InputStream ins3 = new ByteArrayInputStream(value);
				saobsvalue.read(ins3, bigEndian);
				ins3.close();
				ret = saobsvalue;
				break;

			case DataConstants.NOM_ATTR_SA_CMPD_VAL_OBS:
//              ReadCompoundWaveSaObservationValue(avaattribobjects, timeModel, waveValResult);
				SaObsValueCmp saobsvalueCmp = new SaObsValueCmp();
				InputStream ins4 = new ByteArrayInputStream(value);
				saobsvalueCmp.read(ins4, bigEndian);
				ins4.close();
				ret = saobsvalueCmp;
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

	public static class AttributeList { // 4+length bytes
		private Ushort count = new Ushort();
		private Ushort length = new Ushort(); // length in bytes
		private List<AVAType> value;

		public void read(InputStream ins, boolean bigEndian) throws IOException {
			count.read(ins, bigEndian);
			length.read(ins, bigEndian);
			if (count.value() > 0) {
				value = new ArrayList<AVAType>();
				for (int i = 0; i < count.value(); i++) {
					AVAType ava = new AVAType();
					ava.read(ins, bigEndian);
					value.add(ava);
				}
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
		private Nomenclature nomenclature = new Nomenclature();
		private ROapdus roapdus = new ROapdus();
		private ROIVapdu roivapdu = new ROIVapdu();
		private EventReportArgument eventReportArgument = new EventReportArgument();
		private AttributeList connectIndInfo = new AttributeList();

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
	public static class AbsoluteTime { // 8 bytes
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

			if (Byte.toUnsignedInt(century) != 0xff) {
				Calendar c = Calendar.getInstance();
				c.set(Integer.parseInt(Integer.toHexString(century), 10) * 100
						+ Integer.parseInt(Integer.toHexString(year), 10),
						Integer.parseInt(Integer.toHexString(month), 10) - 1,
						Integer.parseInt(Integer.toHexString(day), 10), Integer.parseInt(Integer.toHexString(hour), 10),
						Integer.parseInt(Integer.toHexString(minute), 10),
						Integer.parseInt(Integer.toHexString(second), 10));
				date = c.getTime();
			}
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
		private Ushort session_id = new Ushort(); // 0xE1 0x00
		private Ushort p_context_id = new Ushort();

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
	// AttributeList attribute_list = new AttributeList();
	// } MdsCreateInfo;
	public static class MdsCreateInfo {
		private ManagedObjectId managed_object = new ManagedObjectId();
		private AttributeList attribute_list = new AttributeList();

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
		private SPpdu sppdu = new SPpdu();
		private ROapdus roapdus = new ROapdus();
		private ROIVapdu roivapdu = new ROIVapdu();
		private EventReportArgument eventReportArgument = new EventReportArgument();
		private MdsCreateInfo mdsCreateInfo = new MdsCreateInfo();
		private AbsoluteTime absoluteTime;
		private long relativeTime;

		public void read(InputStream ins, boolean bigEndian) throws IOException {
			sppdu.read(ins, bigEndian);
			roapdus.read(ins, bigEndian);
			roivapdu.read(ins, bigEndian);
			eventReportArgument.read(ins, bigEndian);
			mdsCreateInfo.read(ins, bigEndian);

			int count = mdsCreateInfo.attribute_list.count.value();
			for (int i = 0; i < count; i++) {
				AVAType ava = mdsCreateInfo.attribute_list.value.get(i);
				if (ava.attribute_id.value() == DataConstants.NOM_ATTR_TIME_ABS) {
					absoluteTime = new AbsoluteTime();
					InputStream instream = new ByteArrayInputStream(ava.buf);
					absoluteTime.read(instream);
					ins.close();
					System.out.println("AbsoluteTime=" + absoluteTime.getDate());
					logger.info("AbsoluteTime=" + absoluteTime.getDate());
				} else if (ava.attribute_id.value() == DataConstants.NOM_ATTR_TIME_REL) {
					if (ava.length.value() == 4) {
						relativeTime = java.nio.ByteBuffer.wrap(ava.buf)
								.order(bigEndian ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN).getInt();
						logger.debug("relativeTime=" + relativeTime);
					} else {
						logger.warn("ERROR: invalid relative tiime");
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
		Ushort invoke_id = new Ushort();
		Ushort command_type = new Ushort();
		// CMD_EVENT_REPORT 0,
		// CMD_CONFIRMED_EVENT_REPORT 1,
		// CMD_GET 3,
		// CMD_SET 4,
		// CMD_CONFIRMED_SET 5,
		// CMD_CONFIRMED_ACTION 7
		Ushort length = new Ushort();

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
	// u_8 state;
	// //define RORLS_FIRST 1 /* set in the first message */
	// //define RORLS_NOT_FIRST_NOT_LAST 2
	// //define RORLS_LAST 3 /* last RORLSapdu, one RORSapdu to follow */
	// u_8 count; /* counter starts with 1 */
	// } RorlsId;
	public static class RorlsId {
		Ubyte state = new Ubyte();
		Ubyte count = new Ubyte();

		public void read(InputStream ins) throws IOException {
			state.read(ins);
			count.read(ins);
		}

		public void write(DataOutputStream ous) throws IOException {
			state.write(ous);
			count.write(ous);
		}
	}

// typedef struct {
// 		RorlsId linked_id; /* see below */
// 		u_16 invoke_id; /* see below */
// 		CMD_Type command_type;
// 		u_16 length;
// } ROLRSapdu;
	public static class ROLRSapdu {
		RorlsId linked_id = new RorlsId();
		Ushort invoke_id = new Ushort();
		Ushort command_type = new Ushort();
		Ushort length = new Ushort();

		public void read(InputStream ins, boolean bigEndian) throws IOException {
			linked_id.read(ins);
			invoke_id.read(ins, bigEndian);
			command_type.read(ins, bigEndian);
			length.read(ins, bigEndian);
		}

		public void write(DataOutputStream ous, boolean bigEndian) throws IOException {
			linked_id.write(ous);
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
	public static class ActionResult { // 10 bytes
		ManagedObjectId managed_object = new ManagedObjectId(); // 6 bytes
		Ushort action_type = new Ushort(); // NOM_ACT_POLL_MDIB_DATA 0x0c 0x16, NOM_ACT_POLL_MDIB_DATA_EXT 0xf1 0x3b
		Ushort length = new Ushort();

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
//   NomPartition    partition;
//                              /*
//                               #define NOM_PART_OBJ          1
//                               #define NOM_PART_SCADA        2
//                               #define NOM_PART_EVT          3
//                               #define NOM_PART_DIM          4
//                               #define NOM_PART_PGRP         6
//                               #define NOM_PART_INFRASTRUCT  8 */
//   OIDType         code;
// } TYPE;
	public static class TYPE {
		Ushort partition = new Ushort();
		Ushort code = new Ushort();

		public void read(InputStream ins, boolean bigEndian) throws IOException {
			partition.read(ins, bigEndian);
			code.read(ins, bigEndian);
		}

		public void write(DataOutputStream ous, boolean bigEndian) throws IOException {
			partition.write(ous, bigEndian);
			code.write(ous, bigEndian);
		}
	}

// typedef struct {
// 		u_16 poll_number;
// 		RelativeTime rel_time_stamp;
// 		AbsoluteTime abs_time_stamp;
// 		TYPE polled_obj_type;
// 		OIDType polled_attr_grp;
// 		PollInfoList poll_info_list;
// } PollMdibDataReply;
	public static class PollMdibDataReply { // 24+
		Ushort poll_number = new Ushort();
		Uint rel_time_stamp = new Uint();
		AbsoluteTime abs_time_stamp = new AbsoluteTime(); // 8 bytes
		TYPE polled_obj_type = new TYPE(); // 4 bytes, skip 16 bytes to code
		Ushort polled_attr_grp = new Ushort();
		PollInfoList poll_info_list = new PollInfoList(); // 4+length bytes

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
	public static class PollInfoList { // 4+length bytes
		Ushort count = new Ushort();
		Ushort length = new Ushort();
		List<SingleContextPoll> value;

		public void read(InputStream ins, boolean bigEndian) throws IOException {
			count.read(ins, bigEndian);
			length.read(ins, bigEndian);
			if (count.value() > 0) {
				value = new ArrayList<SingleContextPoll>();
				for (int i = 0; i < count.value(); i++) {
					SingleContextPoll scp = new SingleContextPoll();
					scp.read(ins, bigEndian);
					value.add(scp);
				}
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
// 		Handle obj_handle;
// 		AttributeList attributes;
// } ObservationPoll;
	public static class ObservationPoll {
		Ushort obj_handle = new Ushort();
		AttributeList attributes = new AttributeList();

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
// 		MdsContext context_id;
// 		struct {
// 			u_16 count;
// 			u_16 length;
// 			ObservationPoll value[1];
// 		} poll_info;
// } SingleContextPoll;
	public static class SingleContextPoll {
		Ushort context_id = new Ushort();
		PollInfo poll_info = new PollInfo();

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
		Ushort count = new Ushort();
		Ushort length = new Ushort();
		List<ObservationPoll> value;

		public void read(InputStream ins, boolean bigEndian) throws IOException {
			count.read(ins, bigEndian);
			length.read(ins, bigEndian);
			if (count.value() > 0) {
				value = new ArrayList<ObservationPoll>();
				for (int i = 0; i < count.value(); i++) {
					ObservationPoll op = new ObservationPoll();
					op.read(ins, bigEndian);
					value.add(op);
				}
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
		// way 1, The bitmask is to make the conversion give a value in the range
		// 0-65535 rather than -32768-32767.
		// short s = ...;
		// int i = s & 0xffff;
		// way 2
		// Short.toUnsignedInt(s)
		short ss = ByteBuffer.wrap(buf, offset, 2).order(bigEndian ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN)
				.getShort();
		int us = Short.toUnsignedInt(ss);
		return us;
	}

	private static long b2ui(byte[] buf, int offset, boolean bigEndian) {
		int si = ByteBuffer.wrap(buf, offset, 4).order(bigEndian ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN)
				.getInt();
		long ui = Integer.toUnsignedLong(si);
		return ui;
	}

//   typedef struct {
//     ManagedObjectId managed_object;/* addressed object */
//     u_32 scope;/* fixed value 0 */
//     OIDType action_type; /* identification of method 
//                           #define NOM_ACT_POLL_MDIB_DATA     3094  0x0c 0x16
//                           #define NOM_ACT_POLL_MDIB_DATA_EXT 61755 0xf1 0x3b */
//     u_16           length;        /* size of appended data */
//  } ActionArgument;
	public static class ActionArgument { // 14 bytes
		ManagedObjectId managed_object = new ManagedObjectId(); // 6 bytes
		Uint scope = new Uint();
		Ushort action_type = new Ushort();
		Ushort length = new Ushort();

		public void read(InputStream ins, boolean bigEndian) throws IOException {
			managed_object.read(ins, bigEndian);
			scope.read(ins, bigEndian);
			action_type.read(ins, bigEndian);
			length.read(ins, bigEndian);
		}

		public void write(DataOutputStream ous, boolean bigEndian) throws IOException {
			managed_object.write(ous, bigEndian);
			scope.write(ous, bigEndian);
			action_type.write(ous, bigEndian);
			length.write(ous, bigEndian);
		}
	}

// typedef struct{
//   u_16           poll_number;
//   TYPE           polled_obj_type;
//   OIDType        polled_attr_grp;
// } PollMdibDataReq;
	public static class PollMdibDataReq { // 8 bytes
		Ushort poll_number = new Ushort();
		TYPE polled_obj_type = new TYPE(); // 4 bytes
		Ushort polled_attr_grp = new Ushort();

		public void read(InputStream ins, boolean bigEndian) throws IOException {
			poll_number.read(ins, bigEndian);
			polled_obj_type.read(ins, bigEndian);
			polled_attr_grp.read(ins, bigEndian);
		}

		public void write(DataOutputStream ous, boolean bigEndian) throws IOException {
			poll_number.write(ous, bigEndian);
			polled_obj_type.write(ous, bigEndian);
			polled_attr_grp.write(ous, bigEndian);
		}
	}

// MDSPollAction ::=
//    <SPpdu>
//    <ROapdus (ro_type := ROIV_APDU)>
//    <ROIVapdu (command_type := CMD_CONFIRMED_ACTION)>
//    <ActionArgument (managed_object := {NOM_MOC_VMS_MDS, 0, 0},
//                    action_type := NOM_ACT_POLL_MDIB_DATA)>
//    <PollMdibDataReq>  
	public static class SimplePollRequest { // 36 bytes
		private SPpdu sppdu = new SPpdu(); // 4 bytes
		private ROapdus roapdus = new ROapdus(); // 4 bytes
		private ROIVapdu roivapdu = new ROIVapdu(); // 6 bytes
		private ActionArgument actionArgument = new ActionArgument(); // 14 bytes
		private PollMdibDataReq pollMdibDataReq = new PollMdibDataReq(); // 8 bytes

		public void read(InputStream ins, boolean bigEndian) throws IOException {
			sppdu.read(ins, bigEndian);
			roapdus.read(ins, bigEndian);
			roivapdu.read(ins, bigEndian);
			actionArgument.read(ins, bigEndian);
			pollMdibDataReq.read(ins, bigEndian);
		}

		public void write(DataOutputStream ous, boolean bigEndian) throws IOException {
			sppdu.write(ous, bigEndian);
			roapdus.write(ous, bigEndian);
			roivapdu.write(ous, bigEndian);
			actionArgument.write(ous, bigEndian);
			pollMdibDataReq.write(ous, bigEndian);
		}

		public static boolean isValidType(byte[] buf, boolean bigEndian) {
			int ro_type = b2us(buf, 4, bigEndian);
			int action_type = b2us(buf, 24, bigEndian);
			return ro_type == DataConstants.ROIV_APDU && action_type == DataConstants.NOM_ACT_POLL_MDIB_DATA;
		}
	}

// typedef struct{
//   u_16           poll_number;
//   TYPE           polled_obj_type;
//   OIDType        polled_attr_grp;
//   AttributeList  poll_ext_attr;
// } PollMdibDataReqExt;
	public static class PollMdibDataReqExt { // 12+ bytes
		Ushort poll_number = new Ushort();
		TYPE polled_obj_type = new TYPE(); // 4 bytes
		Ushort polled_attr_grp = new Ushort();
		AttributeList poll_ext_attr = new AttributeList(); // 4+length bytes

		public void read(InputStream ins, boolean bigEndian) throws IOException {
			poll_number.read(ins, bigEndian);
			polled_obj_type.read(ins, bigEndian);
			polled_attr_grp.read(ins, bigEndian);
			poll_ext_attr.read(ins, bigEndian);
		}

		public void write(DataOutputStream ous, boolean bigEndian) throws IOException {
			poll_number.write(ous, bigEndian);
			polled_obj_type.write(ous, bigEndian);
			polled_attr_grp.write(ous, bigEndian);
			poll_ext_attr.write(ous, bigEndian);
		}
	}

// MDSPollAction ::=
//     <SPpdu>
//     <ROapdus (ro_type := ROIV_APDU)>
//     <ROIVapdu (command_type := CMD_CONFIRMED_ACTION)>
//     <ActionArgument
//       (managed_object := {NOM_MOC_VMS_MDS, 0, 0},
//       action_type := NOM_ACT_POLL_MDIB_DATA_EXT)>
//     <PollMdibDataReqExt>
	public static class ExtPollRequest { // 40+ bytes
		private SPpdu sppdu = new SPpdu(); // 4 bytes
		private ROapdus roapdus = new ROapdus(); // 4 bytes
		private ROIVapdu roivapdu = new ROIVapdu(); // 6 bytes
		private ActionArgument actionArgument = new ActionArgument(); // 14 bytes
		private PollMdibDataReqExt pollMdibDataReqExt = new PollMdibDataReqExt(); // 12+ bytes

		public void read(InputStream ins, boolean bigEndian) throws IOException {
			sppdu.read(ins, bigEndian);
			roapdus.read(ins, bigEndian);
			roivapdu.read(ins, bigEndian);
			actionArgument.read(ins, bigEndian);
			pollMdibDataReqExt.read(ins, bigEndian);
		}

		public void write(DataOutputStream ous, boolean bigEndian) throws IOException {
			sppdu.write(ous, bigEndian);
			roapdus.write(ous, bigEndian);
			roivapdu.write(ous, bigEndian);
			actionArgument.write(ous, bigEndian);
			pollMdibDataReqExt.write(ous, bigEndian);
		}

		public static boolean isValidType(byte[] buf, boolean bigEndian) {
			int ro_type = b2us(buf, 4, bigEndian);
			int action_type = b2us(buf, 24, bigEndian);
			return ro_type == DataConstants.ROIV_APDU && action_type == DataConstants.NOM_ACT_POLL_MDIB_DATA_EXT;
		}

		public static boolean isWave(byte[] buf, boolean bigEndian) {
			int polled_obj_type_code = b2us(buf, 32, bigEndian);
			return isValidType(buf, bigEndian)
					&& polled_obj_type_code == DataConstants.ObjectClasses.NOM_MOC_VMO_METRIC_SA_RT.getValue();
		}
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
	public static class MDSPollActionResult { // 48+
		private SPpdu sppdu = new SPpdu(); // 4 bytes
		private ROapdus roapdus = new ROapdus(); // 4 bytes
		private RORSapdu rorsapdu = new RORSapdu(); // 6 bytes
		private ActionResult actionResult = new ActionResult(); // 10 bytes
		private PollMdibDataReply pollMdibDataReply = new PollMdibDataReply(); // 24+, skip 16 bytes to code

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

		public static boolean isWave(byte[] buf, boolean bigEndian) {
			if (buf.length >= 48 && isValidType(buf, bigEndian)) {
				int polled_obj_type_code = b2us(buf, 40, bigEndian);
				return polled_obj_type_code == DataConstants.ObjectClasses.NOM_MOC_VMO_METRIC_SA_RT.getValue();
			}
			return false;
		}
	}

	public static class MDSPollActionResultLinked { // 48+
		private SPpdu sppdu = new SPpdu(); // 4 bytes
		private ROapdus roapdus = new ROapdus(); // 4 bytes
		private ROLRSapdu rolrsapdu = new ROLRSapdu(); // 8 bytes
		private ActionResult actionResult = new ActionResult(); // 10 bytes
		private PollMdibDataReply pollMdibDataReply = new PollMdibDataReply(); // 24+, skip 16 bytes to code

		public void read(InputStream ins, boolean bigEndian) throws IOException {
			sppdu.read(ins, bigEndian);
			roapdus.read(ins, bigEndian);
			rolrsapdu.read(ins, bigEndian);
			actionResult.read(ins, bigEndian);
			pollMdibDataReply.read(ins, bigEndian);
		}

		public void write(DataOutputStream ous, boolean bigEndian) throws IOException {
			sppdu.write(ous, bigEndian);
			roapdus.write(ous, bigEndian);
			rolrsapdu.write(ous, bigEndian);
			actionResult.write(ous, bigEndian);
			pollMdibDataReply.write(ous, bigEndian);
		}

		public static boolean isValidType(byte[] buf, boolean bigEndian) {
			int ro_type = b2us(buf, 4, bigEndian);
			int action_type = b2us(buf, 20, bigEndian);
			return ro_type == DataConstants.RORS_APDU && action_type == DataConstants.NOM_ACT_POLL_MDIB_DATA;
		}

		public static boolean isWave(byte[] buf, boolean bigEndian) {
			if (buf.length >= 48 && isValidType(buf, bigEndian)) {
				int polled_obj_type_code = b2us(buf, 42, bigEndian);
				return polled_obj_type_code == DataConstants.ObjectClasses.NOM_MOC_VMO_METRIC_SA_RT.getValue();
			}
			return false;
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
	public static class PollMdibDataReplyExt { // 26+, skip 18 bytes to code
		Ushort poll_number = new Ushort();
		Ushort sequence_no = new Ushort();
		Uint rel_time_stamp = new Uint();
		AbsoluteTime abs_time_stamp = new AbsoluteTime(); // 8
		TYPE polled_obj_type = new TYPE(); // 4, skip 18 bytes to code
		Ushort polled_attr_grp = new Ushort();
		PollInfoList poll_info_list = new PollInfoList(); // 4+

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
	public static class MDSPollActionResultExt { // 50+
		private SPpdu sppdu = new SPpdu(); // 4 bytes
		private ROapdus roapdus = new ROapdus(); // 4 bytes
		private RORSapdu rorsapdu = new RORSapdu(); // 6 bytes
		private ActionResult actionResult = new ActionResult(); // 10 bytes
		private PollMdibDataReplyExt pollMdibDataReplyExt = new PollMdibDataReplyExt(); // 26+, skip 18 bytes to code

		public void read(InputStream ins, boolean bigEndian) throws IOException {
			sppdu.read(ins, bigEndian);
			roapdus.read(ins, bigEndian);
			rorsapdu.read(ins, bigEndian);
			actionResult.read(ins, bigEndian);
			pollMdibDataReplyExt.read(ins, bigEndian);
		}

		public void write(DataOutputStream ous, boolean bigEndian) throws IOException {
			sppdu.write(ous, bigEndian);
			roapdus.write(ous, bigEndian);
			rorsapdu.write(ous, bigEndian);
			actionResult.write(ous, bigEndian);
			pollMdibDataReplyExt.write(ous, bigEndian);
		}

		public static boolean isValidType(byte[] buf, boolean bigEndian) {
			int ro_type = b2us(buf, 4, bigEndian);
			int action_type = b2us(buf, 20, bigEndian); // skip 20 bytes
			return ro_type == DataConstants.RORS_APDU && action_type == DataConstants.NOM_ACT_POLL_MDIB_DATA_EXT;
		}

		public static boolean isWave(byte[] buf, boolean bigEndian) {
			if (buf.length >= 50 && isValidType(buf, bigEndian)) {
				int polled_obj_type_code = b2us(buf, 42, bigEndian);
				return polled_obj_type_code == DataConstants.ObjectClasses.NOM_MOC_VMO_METRIC_SA_RT.getValue();
			}
			return false;
		}
	}

	public static class MDSPollActionResultExtLinked { // 52+
		private SPpdu sppdu = new SPpdu(); // 4 bytes
		private ROapdus roapdus = new ROapdus(); // 4 bytes
		private ROLRSapdu rolrsapdu = new ROLRSapdu(); // 8 bytes
		private ActionResult actionResult = new ActionResult(); // 10 bytes
		private PollMdibDataReplyExt pollMdibDataReplyExt = new PollMdibDataReplyExt(); // 26+, skip 18 bytes to code

		public void read(InputStream ins, boolean bigEndian) throws IOException {
			sppdu.read(ins, bigEndian);
			roapdus.read(ins, bigEndian);
			rolrsapdu.read(ins, bigEndian);
			actionResult.read(ins, bigEndian);
			pollMdibDataReplyExt.read(ins, bigEndian);
		}

		public void write(DataOutputStream ous, boolean bigEndian) throws IOException {
			sppdu.write(ous, bigEndian);
			roapdus.write(ous, bigEndian);
			rolrsapdu.write(ous, bigEndian);
			actionResult.write(ous, bigEndian);
			pollMdibDataReplyExt.write(ous, bigEndian);
		}

		public static boolean isValidType(byte[] buf, boolean bigEndian) {
			int ro_type = b2us(buf, 4, bigEndian);
			int action_type = b2us(buf, 22, bigEndian); // skip 22 bytes
			return ro_type == DataConstants.RORLS_APDU && action_type == DataConstants.NOM_ACT_POLL_MDIB_DATA_EXT;
		}

		public static boolean isWave(byte[] buf, boolean bigEndian) {
			if (buf.length >= 52 && isValidType(buf, bigEndian)) {
				int polled_obj_type_code = b2us(buf, 44, bigEndian);
				return polled_obj_type_code == DataConstants.ObjectClasses.NOM_MOC_VMO_METRIC_SA_RT.getValue();
			}
			return false;
		}
	}

//	typedef struct  {
//	    OIDType physio_id;
//	    MeasurementState state;
//	    OIDType unit_code;
//	    FLOATType value; // uint32
//	} NuObsValue;
	public static class NuObsValue {
		Ushort physio_id = new Ushort();
		Ushort state = new Ushort(); // eg: DEMO_DATA
		Ushort unit_code = new Ushort();
		private Float32 value = new Float32();
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

		public String toString() {
			return "{" + (physio_str != null ? physio_str : "NULL") + ":" + value.toString() + "}";
		}
	}

//	typedef struct {
//        u_16           count;
//        u_16           length;
//        NuObsValue     value[1];
//    } NuObsValueCmp;
	public static class NuObsValueCmp {
		Ushort count = new Ushort();
		Ushort length = new Ushort(); // eg: DEMO_DATA
		List<NuObsValue> value;

		public void read(InputStream ins, boolean bigEndian) throws IOException {
			count.read(ins, bigEndian);
			length.read(ins, bigEndian);
			if (count.value() > 0) {
				value = new ArrayList<NuObsValue>();
				for (int i = 0; i < count.value(); i++) {
					NuObsValue nov = new NuObsValue();
					nov.read(ins, bigEndian);
					value.add(nov);
				}
			}
		}

		public void write(DataOutputStream ous, boolean bigEndian) throws IOException {
			count.write(ous, bigEndian);
			length.write(ous, bigEndian);
			for (int i = 0; i < count.value(); i++) {
				value.get(i).write(ous, bigEndian);
			}
		}

		public String toString() {
			if (value != null) {
				String ret = "[";
				for (int i = 0; i < value.size(); i++) {
					ret += value.get(i).toString() + ",";
				}
				ret = ret.replaceAll(",$", "") + "]";
				return ret;
			}
			return "";
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
		Ubyte sample_size = new Ubyte();
		Ubyte significant_bits = new Ubyte();

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
		Ushort array_size = new Ushort();
		SampleType sample_type = new SampleType();
		Ushort flags = new Ushort();

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
		Float32 lower_absolute_value = new Float32();
		Float32 upper_absolute_value = new Float32();
		Ushort lower_scaled_value = new Ushort();
		Ushort upper_scaled_value = new Ushort();

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
		Float32 lower_absolute_value = new Float32();
		Float32 upper_absolute_value = new Float32();
		Ushort lower_scaled_value = new Ushort();
		Ushort upper_scaled_value = new Ushort();
		Ushort increment = new Ushort();
		Ushort cal_type = new Ushort();

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

//typedef struct {
//	OIDType	physio_id;
//	MeasurementState state;
//	struct {
//		u_16 length;
//		u_8 value[1];
//	} array;
//} SaObsValue;
	public static class SaObsValue {
		Ushort physio_id = new Ushort();
		Ushort state = new Ushort();
		Ushort length = new Ushort();
		byte[] value;
		String physio_str;

		public void read(InputStream ins, boolean bigEndian) throws IOException {
			physio_id.read(ins, bigEndian);
			state.read(ins, bigEndian);
			length.read(ins, bigEndian);
			value = new byte[length.value()];
			ins.read(value);

			physio_str = DataConstants.AlertSource.GetValue(physio_id.value()).name();
		}

		public void write(DataOutputStream ous, boolean bigEndian) throws IOException {
			physio_id.write(ous, bigEndian);
			state.write(ous, bigEndian);
			length.write(ous, bigEndian);
			ous.write(value);
		}

		public String toString() {
			return "{" + (physio_str != null ? physio_str : "NULL") + ":" + value.length + " BYTES}";
		}

	}

//	typedef struct {
//		u_16 count;
//		u_16 length;
//		SaObsValue value[1]; 
//	} SaObsValueCmp;
	public static class SaObsValueCmp {
		Ushort count = new Ushort();
		Ushort length = new Ushort();
		List<SaObsValue> value;

		public void read(InputStream ins, boolean bigEndian) throws IOException {
			count.read(ins, bigEndian);
			length.read(ins, bigEndian);
			if (count.value() > 0) {
				value = new ArrayList<SaObsValue>();
				for (int i = 0; i < count.value(); i++) {
					SaObsValue tmp = new SaObsValue();
					tmp.read(ins, bigEndian);
					value.add(tmp);
				}
			}
		}

		public void write(DataOutputStream ous, boolean bigEndian) throws IOException {
			count.write(ous, bigEndian);
			length.write(ous, bigEndian);
			for (int i = 0; i < count.value(); i++) {
				value.get(i).write(ous, bigEndian);
			}
		}

		public String toString() {
			if (count.value() > 0 && value != null) {
				String ret = "[";
				for (int i = 0; i < count.value(); i++) {
					ret += value.get(i).toString() + ",";
				}
				return ret.replaceAll(",$", "") + "]";
			}
			return "NULL";
		}
	}

}
