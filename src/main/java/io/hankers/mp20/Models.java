package io.hankers.mp20;

import java.io.InputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Models {
	
	public static class Ushort {
		private byte l;
		private byte h;
		public int value() {
			return h<<8 + l;
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
			return h<<24 + mh<<16 + ml<<8 + l;
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

	public static class ROadpus {
		private Ushort ro_type;
		private Ushort length;
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
		private Ushort invoke_id;
		private Ushort command_type;
		private Ushort length;
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

    public static class EventReportArgument
    {
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
    	private Ushort attribute_id;
    	private Ushort length; // length in bytes
    	private byte[] value;
		public void read(InputStream ins, boolean bigEndian) throws IOException {
			attribute_id.read(ins, bigEndian);
			length.read(ins, bigEndian);
			value = new byte[length.value()];
			ins.read(value);
		}
		public void write(DataOutputStream ous, boolean bigEndian) throws IOException {
			attribute_id.write(ous, bigEndian);
			length.write(ous, bigEndian);
			ous.write(value);
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
			for(int i=0; i<count.value(); i++) {
				AVAType ava = new AVAType();
				ava.read(ins, bigEndian);
				value.add(ava);
			}
		}
		public void write(DataOutputStream ous, boolean bigEndian) throws IOException {
			count.write(ous, bigEndian);
			length.write(ous, bigEndian);
			for(int i=0; i<count.value(); i++) {
				value.get(i).write(ous, bigEndian);
			}
		}
    }
    
    public static class ConnectIndication {
    	private Nomenclature nomenclature;
    	private ROadpus roapdus;
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
}
