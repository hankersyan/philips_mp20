package io.hankers.mp20;

public class DataConstants {
	// private static final byte DataExportID = 0x11;
	public static final byte BOFCHAR = (byte) 0xC0;
	public static final byte EOFCHAR = (byte) 0xC1;
	public static final byte ESCAPECHAR = 0x7D;

	// public static final byte BIT5 = 0x7C;
	public static final byte BIT5COMPL = 0x20;

	// private byte[] FrameAbort = { 0x7d, (byte) 0xc1 };

	public static byte[] trimByteArray(int[] shortArray) {
		byte[] buf = new byte[shortArray.length];
		for (int i = 0; i < shortArray.length; i++) {
			buf[i] = (byte) shortArray[i];
		}
		return buf;
	}

	public static byte[] aarq_msg = trimByteArray(new int[] { 0x0D, 0xEC, 0x05, 0x08, 0x13, 0x01, 0x00, 0x16, 0x01,
			0x02, 0x80, 0x00, 0x14, 0x02, 0x00, 0x02, 0xC1, 0xDC, 0x31, 0x80, 0xA0, 0x80, 0x80, 0x01, 0x01, 0x00, 0x00,
			0xA2, 0x80, 0xA0, 0x03, 0x00, 0x00, 0x01, 0xA4, 0x80, 0x30, 0x80, 0x02, 0x01, 0x01, 0x06, 0x04, 0x52, 0x01,
			0x00, 0x01, 0x30, 0x80, 0x06, 0x02, 0x51, 0x01, 0x00, 0x00, 0x00, 0x00, 0x30, 0x80, 0x02, 0x01, 0x02, 0x06,
			0x0C, 0x2A, 0x86, 0x48, 0xCE, 0x14, 0x02, 0x01, 0x00, 0x00, 0x00, 0x01, 0x01, 0x30, 0x80, 0x06, 0x0C, 0x2A,
			0x86, 0x48, 0xCE, 0x14, 0x02, 0x01, 0x00, 0x00, 0x00, 0x02, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x61,
			0x80, 0x30, 0x80, 0x02, 0x01, 0x01, 0xA0, 0x80, 0x60, 0x80, 0xA1, 0x80, 0x06, 0x0C, 0x2A, 0x86, 0x48, 0xCE,
			0x14, 0x02, 0x01, 0x00, 0x00, 0x00, 0x03, 0x01, 0x00, 0x00, 0xBE, 0x80, 0x28, 0x80, 0x06, 0x0C, 0x2A, 0x86,
			0x48, 0xCE, 0x14, 0x02, 0x01, 0x00, 0x00, 0x00, 0x01, 0x01, 0x02, 0x01, 0x02, 0x81, 0x48, 0x80, 0x00, 0x00,
			0x00, 0x40, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x80, 0x00, 0x00, 0x00, 0x20, 0x00, 0x00, 0x00, 0x00,
			0x00, 0x00, 0x00, 0x00, 0x01, 0x00, 0x2C, 0x00, 0x01, 0x00, 0x28, 0x80, 0x00, 0x00, 0x00, 0x00, 0x00, 0x09,
			0xC4, 0x00, 0x00, 0x09, 0xC4, 0x00, 0x00, 0x03, 0xE8, 0xFF, 0xFF, 0xFF, 0xFF, 0x60, 0x00, 0x00, 0x00, 0x00,
			0x01, 0x00, 0x0C, 0xF0, 0x01, 0x00, 0x08, 0x8C, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
			0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 });

	public static byte[] aarq_msg_ext_poll = trimByteArray(new int[] { 0x0D, 0xEC, 0x05, 0x08, 0x13, 0x01, 0x00, 0x16,
			0x01, 0x02, 0x80, 0x00, 0x14, 0x02, 0x00, 0x02, 0xC1, 0xDC, 0x31, 0x80, 0xA0, 0x80, 0x80, 0x01, 0x01, 0x00,
			0x00, 0xA2, 0x80, 0xA0, 0x03, 0x00, 0x00, 0x01, 0xA4, 0x80, 0x30, 0x80, 0x02, 0x01, 0x01, 0x06, 0x04, 0x52,
			0x01, 0x00, 0x01, 0x30, 0x80, 0x06, 0x02, 0x51, 0x01, 0x00, 0x00, 0x00, 0x00, 0x30, 0x80, 0x02, 0x01, 0x02,
			0x06, 0x0C, 0x2A, 0x86, 0x48, 0xCE, 0x14, 0x02, 0x01, 0x00, 0x00, 0x00, 0x01, 0x01, 0x30, 0x80, 0x06, 0x0C,
			0x2A, 0x86, 0x48, 0xCE, 0x14, 0x02, 0x01, 0x00, 0x00, 0x00, 0x02, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
			0x61, 0x80, 0x30, 0x80, 0x02, 0x01, 0x01, 0xA0, 0x80, 0x60, 0x80, 0xA1, 0x80, 0x06, 0x0C, 0x2A, 0x86, 0x48,
			0xCE, 0x14, 0x02, 0x01, 0x00, 0x00, 0x00, 0x03, 0x01, 0x00, 0x00, 0xBE, 0x80, 0x28, 0x80, 0x06, 0x0C, 0x2A,
			0x86, 0x48, 0xCE, 0x14, 0x02, 0x01, 0x00, 0x00, 0x00, 0x01, 0x01, 0x02, 0x01, 0x02, 0x81, 0x48, 0x80, 0x00,
			0x00, 0x00, 0x40, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x80, 0x00, 0x00, 0x00, 0x20, 0x00, 0x00, 0x00,
			0x00, 0x00, 0x00, 0x00, 0x00, 0x01, 0x00, 0x2C, 0x00, 0x01, 0x00, 0x28, 0x80, 0x00, 0x00, 0x00, 0x00, 0x00,
			0x09, 0xC4, 0x00, 0x00, 0x09, 0xC4, 0x00, 0x00, 0x03, 0xE8, 0xFF, 0xFF, 0xFF, 0xFF, 0x60, 0x00, 0x00, 0x00,
			0x00, 0x01, 0x00, 0x0C, 0xF0, 0x01, 0x00, 0x08, 0x8C, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
			0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 });

	public static byte[] aarq_msg_ext_poll2 = trimByteArray(new int[] { 0x0D, 0xEC, 0x05, 0x08, 0x13, 0x01, 0x00, 0x16,
			0x01, 0x02, 0x80, 0x00, 0x14, 0x02, 0x00, 0x02, 0xC1, 0xDC, 0x31, 0x80, 0xA0, 0x80, 0x80, 0x01, 0x01, 0x00,
			0x00, 0xA2, 0x80, 0xA0, 0x03, 0x00, 0x00, 0x01, 0xA4, 0x80, 0x30, 0x80, 0x02, 0x01, 0x01, 0x06, 0x04, 0x52,
			0x01, 0x00, 0x01, 0x30, 0x80, 0x06, 0x02, 0x51, 0x01, 0x00, 0x00, 0x00, 0x00, 0x30, 0x80, 0x02, 0x01, 0x02,
			0x06, 0x0C, 0x2A, 0x86, 0x48, 0xCE, 0x14, 0x02, 0x01, 0x00, 0x00, 0x00, 0x01, 0x01, 0x30, 0x80, 0x06, 0x0C,
			0x2A, 0x86, 0x48, 0xCE, 0x14, 0x02, 0x01, 0x00, 0x00, 0x00, 0x02, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
			0x61, 0x80, 0x30, 0x80, 0x02, 0x01, 0x01, 0xA0, 0x80, 0x60, 0x80, 0xA1, 0x80, 0x06, 0x0C, 0x2A, 0x86, 0x48,
			0xCE, 0x14, 0x02, 0x01, 0x00, 0x00, 0x00, 0x03, 0x01, 0x00, 0x00, 0xBE, 0x80, 0x28, 0x80, 0x06, 0x0C, 0x2A,
			0x86, 0x48, 0xCE, 0x14, 0x02, 0x01, 0x00, 0x00, 0x00, 0x01, 0x01, 0x02, 0x01, 0x02, 0x81, 0x48, 0x80, 0x00,
			0x00, 0x00, 0x40, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x80, 0x00, 0x00, 0x00, 0x20, 0x00, 0x00, 0x00,
			0x00, 0x00, 0x00, 0x00, 0x00, 0x01, 0x00, 0x2C, 0x00, 0x01, 0x00, 0x28, 0x80, 0x00, 0x00, 0x00, 0x00, 0x00,
			0x03, 0xE8, 0x00, 0x00, 0x09, 0xC4, 0x00, 0x00, 0x03, 0xE8, 0xFF, 0xFF, 0xFF, 0xFF, 0x60, 0x00, 0x00, 0x00,
			0x00, 0x01, 0x00, 0x0C, 0xF0, 0x01, 0x00, 0x08, 0x8C, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
			0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 });

	public static byte[] aarq_msg_wave_ext_poll = trimByteArray(new int[] { 0x0D, 0xEC, 0x05, 0x08, 0x13, 0x01, 0x00,
			0x16, 0x01, 0x02, 0x80, 0x00, 0x14, 0x02, 0x00, 0x02, 0xC1, 0xDC, 0x31, 0x80, 0xA0, 0x80, 0x80, 0x01, 0x01,
			0x00, 0x00, 0xA2, 0x80, 0xA0, 0x03, 0x00, 0x00, 0x01, 0xA4, 0x80, 0x30, 0x80, 0x02, 0x01, 0x01, 0x06, 0x04,
			0x52, 0x01, 0x00, 0x01, 0x30, 0x80, 0x06, 0x02, 0x51, 0x01, 0x00, 0x00, 0x00, 0x00, 0x30, 0x80, 0x02, 0x01,
			0x02, 0x06, 0x0C, 0x2A, 0x86, 0x48, 0xCE, 0x14, 0x02, 0x01, 0x00, 0x00, 0x00, 0x01, 0x01, 0x30, 0x80, 0x06,
			0x0C, 0x2A, 0x86, 0x48, 0xCE, 0x14, 0x02, 0x01, 0x00, 0x00, 0x00, 0x02, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00,
			0x00, 0x61, 0x80, 0x30, 0x80, 0x02, 0x01, 0x01, 0xA0, 0x80, 0x60, 0x80, 0xA1, 0x80, 0x06, 0x0C, 0x2A, 0x86,
			0x48, 0xCE, 0x14, 0x02, 0x01, 0x00, 0x00, 0x00, 0x03, 0x01, 0x00, 0x00, 0xBE, 0x80, 0x28, 0x80, 0x06, 0x0C,
			0x2A, 0x86, 0x48, 0xCE, 0x14, 0x02, 0x01, 0x00, 0x00, 0x00, 0x01, 0x01, 0x02, 0x01, 0x02, 0x81, 0x48, 0x80,
			0x00, 0x00, 0x00, 0x40, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x80, 0x00, 0x00, 0x00, 0x20, 0x00, 0x00,
			0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x01, 0x00, 0x2C, 0x00, 0x01, 0x00, 0x28, 0x80, 0x00, 0x00, 0x00, 0x00,
			0x00, 0x03, 0xE8, 0x00, 0x00, 0x09, 0xC4, 0x00, 0x00, 0x03, 0xE8, 0xFF, 0xFF, 0xFF, 0xFF, 0x60, 0x00, 0x00,
			0x00, 0x00, 0x01, 0x00, 0x0C, 0xF0, 0x01, 0x00, 0x08, 0x88, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
			0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 });

	public static byte[] aarq_msg_wave_ext_poll2 = trimByteArray(new int[] { 0x0D, 0xFF, 0x01, 0x28, 0x05, 0x08, 0x13,
			0x01, 0x00, 0x16, 0x01, 0x02, 0x80, 0x00, 0x14, 0x02, 0x00, 0x02, 0xC1, 0xFF, 0x01, 0x16, 0x31, 0x80, 0xA0,
			0x80, 0x80, 0x01, 0x01, 0x00, 0x00, 0xA2, 0x80, 0xA0, 0x03, 0x00, 0x00, 0x01, 0xA4, 0x80, 0x30, 0x80, 0x02,
			0x01, 0x01, 0x06, 0x04, 0x52, 0x01, 0x00, 0x01, 0x30, 0x80, 0x06, 0x02, 0x51, 0x01, 0x00, 0x00, 0x00, 0x00,
			0x30, 0x80, 0x02, 0x01, 0x02, 0x06, 0x0C, 0x2A, 0x86, 0x48, 0xCE, 0x14, 0x02, 0x01, 0x00, 0x00, 0x00, 0x01,
			0x01, 0x30, 0x80, 0x06, 0x0C, 0x2A, 0x86, 0x48, 0xCE, 0x14, 0x02, 0x01, 0x00, 0x00, 0x00, 0x02, 0x01, 0x00,
			0x00, 0x00, 0x00, 0x00, 0x00, 0x61, 0x80, 0x30, 0x80, 0x02, 0x01, 0x01, 0xA0, 0x80, 0x60, 0x80, 0xA1, 0x80,
			0x06, 0x0C, 0x2A, 0x86, 0x48, 0xCE, 0x14, 0x02, 0x01, 0x00, 0x00, 0x00, 0x03, 0x01, 0x00, 0x00, 0xBE, 0x80,
			0x28, 0x80, 0x06, 0x0C, 0x2A, 0x86, 0x48, 0xCE, 0x14, 0x02, 0x01, 0x00, 0x00, 0x00, 0x01, 0x01, 0x02, 0x01,
			0x02, 0x81, 0x82, 0x00, 0x80, 0x80, 0x00, 0x00, 0x00, 0x40, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x80,
			0x00, 0x00, 0x00, 0x20, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x02, 0x00, 0x64, 0x00, 0x01, 0x00,
			0x28, 0x80, 0x00, 0x00, 0x00, 0x00, 0x00, 0x0F, 0xA0, 0x00, 0x00, 0x05, 0xB0, 0x00, 0x00, 0x05, 0xB0, 0xFF,
			0xFF, 0xFF, 0xFF, 0x60, 0x00, 0x00, 0x00, 0x00, 0x01, 0x00, 0x0C, 0xF0, 0x01, 0x00, 0x08, 0x8E, 0x00, 0x00,
			0x00, 0x00, 0x00, 0x00, 0x00, 0x01, 0x02, 0x00, 0x34, 0x00, 0x06, 0x00, 0x30, 0x00, 0x01, 0x00, 0x21, 0x00,
			0x00, 0x00, 0x01, 0x00, 0x01, 0x00, 0x06, 0x00, 0x00, 0x00, 0xC9, 0x00, 0x01, 0x00, 0x09, 0x00, 0x00, 0x00,
			0x3C, 0x00, 0x01, 0x00, 0x05, 0x00, 0x00, 0x00, 0x10, 0x00, 0x01, 0x00, 0x2A, 0x00, 0x00, 0x00, 0x01, 0x00,
			0x01, 0x00, 0x36, 0x00, 0x00, 0x00, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
			0x00, 0x00, 0x00, 0x00, 0x00 });

	public static byte[] mds_create_resp_msg = trimByteArray(
			new int[] { 0xE1, 0x00, 0x00, 0x02, 0x00, 0x02, 0x00, 0x14, 0x00, 0x01, 0x00, 0x01, 0x00, 0x0E, 0x00, 0x21,
					0x00, 0x00, 0x00, 0x00, 0x00, 0x0A, 0xBD, 0x00, 0x0D, 0x06, 0x00, 0x00 });

	public static byte[] poll_request_msg = trimByteArray(new int[] { 0xE1, 0x00, 0x00, 0x02, 0x00, 0x01, 0x00, 0x1C,
			0x00, 0x00, 0x00, 0x07, 0x00, 0x16, 0x00, 0x21, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x0C, 0x16,
			0x00, 0x08, 0x00, 0x01, 0x00, 0x01, 0x00, 0x06, 0x00, 0x00 });

	public static byte[] poll_request_msg2 = trimByteArray(new int[] { 0xE1, 0x00, 0x00, 0x02, 0x00, 0x01, 0x00, 0x1C,
			0x00, 0x01, 0x00, 0x07, 0x00, 0x16, 0x00, 0x21, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x0C, 0x16,
			0x00, 0x08, 0x00, 0x01, 0x00, 0x01, 0x00, 0x21, 0x08, 0x0C });

	public static byte[] poll_request_msg3 = trimByteArray(new int[] { 0xe1, 0x00, 0x00, 0x02, 0x00, 0x01, 0x00, 0x1c,
			0x00, 0x06, 0x00, 0x07, 0x00, 0x16, 0x00, 0x21, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x0c, 0x16,
			0x00, 0x08, 0x00, 0x05, 0x00, 0x01, 0x00, 0x2a, 0x08, 0x07 });

	public static byte[] poll_request_msg4 = trimByteArray(new int[] { 0xe1, 0x00, 0x00, 0x02, 0x00, 0x01, 0x00, 0x1c,
			0x00, 0x10, 0x00, 0x07, 0x00, 0x16, 0x00, 0x21, 0x00, 0x10, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
			0x0c, 0x16, 0x00, 0x08, 0x00, 0x0f, 0x00, 0x01, 0x00, 0x20, 0x00, 0x36, 0x08, 0x11 });

	public static byte[] ext_poll_request_msg3 = trimByteArray(
			new int[] { 0xe1, 0x00, 0x00, 0x02, 0x00, 0x01, 0x00, 0x28, 0x00, 0x04, 0x00, 0x07, 0x00, 0x22, 0x00, 0x21,
					0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0xf1, 0x3b, 0x00, 0x14, 0x00, 0x03, 0x00, 0x01,
					0x00, 0x09, 0x00, 0x00, 0x00, 0x01, 0x00, 0x08, 0xf1, 0x3e, 0x00, 0x04, 0x00, 0x49, 0x3e, 0x00 });

	public static byte[] ext_poll_request_msg = trimByteArray(new int[] { 0xE1, 0x00, 0x00, 0x02, 0x00, 0x01, 0x00,
			0x20, 0x00, 0x01, 0x00, 0x07, 0x00, 0x1A, 0x00, 0x21, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0xF1,
			0x3B, 0x00, 0x0C, 0x00, 0x01, 0x00, 0x01, 0x00, 0x06, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 });

	public static byte[] ext_poll_request_msg2 = trimByteArray(new int[] { 0xE1, 0x00, 0x00, 0x02, 0x00, 0x01, 0x00,
			0x20, 0x00, 0x01, 0x00, 0x07, 0x00, 0x1A, 0x00, 0x21, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0xF1,
			0x3B, 0x00, 0x0E, 0x00, 0x01, 0x00, 0x01, 0x00, 0x06, 0x00, 0x01, 0x00, 0x04, 0x80, 0x00, 0x00, 0x00 });

	public static byte[] ext_poll_request_msg4 = trimByteArray(
			new int[] { 0xe1, 0x00, 0x00, 0x02, 0x00, 0x01, 0x00, 0x28, 0x00, 0x03, 0x00, 0x07, 0x00, 0x22, 0x00, 0x21,
					0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0xf1, 0x3b, 0x00, 0x14, 0x00, 0x02, 0x00, 0x01,
					0x00, 0x06, 0x00, 0x00, 0x00, 0x01, 0x00, 0x08, 0xf1, 0x3e, 0x00, 0x04, 0x00, 0x49, 0x3e, 0x00 });

	public static byte[] ext_poll_request_msg5 = trimByteArray(
			new int[] { 0xE1, 0x00, 0x00, 0x02, 0x00, 0x01, 0x00, 0x28, 0x00, 0x01, 0x00, 0x07, 0x00, 0x22, 0x00, 0x21,
					0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0xF1, 0x3B, 0x00, 0x14, 0x00, 0x01, 0x00, 0x01,
					0x00, 0x06, 0x00, 0x00, 0x00, 0x01, 0x00, 0x08, 0xF1, 0x3E, 0x00, 0x04, 0x00, 0x03, 0xA9, 0x80 });

	public static byte[] ext_poll_request_wave_msg3 = trimByteArray(
			new int[] { 0xE1, 0x00, 0x00, 0x02, 0x00, 0x01, 0x00, 0x28, 0x00, 0x01, 0x00, 0x07, 0x00, 0x22, 0x00, 0x21,
					0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0xF1, 0x3B, 0x00, 0x14, 0x00, 0x02, 0x00, 0x01,
					0x00, 0x09, 0x00, 0x00, 0x00, 0x01, 0x00, 0x08, 0xF1, 0x3E, 0x00, 0x04, 0x00, 0x03, 0xA9, 0x80 });

	public static byte[] ext_poll_request_wave_msg = trimByteArray(
			new int[] { 0xe1, 0x00, 0x00, 0x02, 0x00, 0x01, 0x00, 0x28, 0x00, 0x04, 0x00, 0x07, 0x00, 0x22, 0x00, 0x21,
					0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0xf1, 0x3b, 0x00, 0x14, 0x00, 0x03, 0x00, 0x01,
					0x00, 0x09, 0x00, 0x00, 0x00, 0x01, 0x00, 0x08, 0xf1, 0x3e, 0x00, 0x04, 0x00, 0x49, 0x3e, 0x00 });

	public static byte[] ext_poll_request_wave_msg2 = trimByteArray(
			new int[] { 0xe1, 0x00, 0x00, 0x02, 0x00, 0x01, 0x00, 0x28, 0x00, 0x04, 0x00, 0x07, 0x00, 0x22, 0x00, 0x21,
					0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0xf1, 0x3b, 0x00, 0x14, 0x00, 0x03, 0x00, 0x01,
					0x00, 0x09, 0x00, 0x00, 0x00, 0x01, 0x00, 0x08, 0xf1, 0x3e, 0x00, 0x04, 0x00, 0x49, 0x3e, 0x00 });

	public static byte[] ext_poll_request_alert_msg = trimByteArray(
			new int[] { 0xe1, 0x00, 0x00, 0x02, 0x00, 0x01, 0x00, 0x28, 0x00, 0x05, 0x00, 0x07, 0x00, 0x22, 0x00, 0x21,
					0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0xf1, 0x3b, 0x00, 0x14, 0x00, 0x04, 0x00, 0x01,
					0x00, 0x36, 0x08, 0x01, 0x00, 0x01, 0x00, 0x08, 0xf1, 0x3e, 0x00, 0x04, 0x00, 0x49, 0x3e, 0x00 });

	public static byte[] get_rtsa_prio_msg = trimByteArray(
			new int[] { 0xE1, 0x00, 0x00, 0x02, 0x00, 0x01, 0x00, 0x16, 0x00, 0x00, 0x00, 0x03, 0x00, 0x10, 0x00, 0x21,
					0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x01, 0x00, 0x02, 0xF2, 0x3A });

	public static byte[] keep_alive_msg = trimByteArray(new int[] { 0xe1, 0x00, 0x00, 0x02, 0x00, 0x01, 0x00, 0x1c,
			0x00, 0x01, 0x00, 0x07, 0x00, 0x16, 0x00, 0x21, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x0c, 0x16,
			0x00, 0x08, 0x00, 0x01, 0x00, 0x01, 0x00, 0x21, 0x08, 0x0c });

	public static byte[] set_rtsa_prio_msg = trimByteArray(new int[] { 0xE1, 0x00, 0x00, 0x02, 0x00, 0x01, 0x00, 0x22,
			0x00, 0x00, 0x00, 0x05, 0x00, 0x1C, 0x00, 0x21, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x01,
			0x00, 0x0E, 0x00, 0x00, 0xF2, 0x3A, 0x00, 0x08, 0x00, 0x01, 0x00, 0x04, 0x00, 0x02, 0x01, 0x02 });

	public static byte[] keep_alive_msg2 = trimByteArray(new int[] { 0xe1, 0x00, 0x00, 0x02, 0x00, 0x01, 0x00, 0x1c,
			0x00, 0x08, 0x00, 0x07, 0x00, 0x16, 0x00, 0x21, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x0c, 0x16,
			0x00, 0x08, 0x00, 0x07, 0x00, 0x01, 0x00, 0x36, 0x08, 0x11 });

	public static byte[] rlrq_msg = trimByteArray(new int[] { 0x09, 0x18, 0xC1, 0x16, 0x61, 0x80, 0x30, 0x80, 0x02,
			0x01, 0x01, 0xA0, 0x80, 0x62, 0x80, 0x80, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 });

	public static byte[] rlrq_resp_msg = trimByteArray(new int[] { 0x0A, 0x18, 0xC1, 0x16, 0x61, 0x80, 0x30, 0x80, 0x02,
			0x01, 0x01, 0xA0, 0x80, 0x63, 0x80, 0x80, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 });

	public static byte[] assoc_abort_resp_msg = trimByteArray(
			new int[] { 0x19, 0x2E, 0x11, 0x01, 0x03, 0xC1, 0x29, 0xA0, 0x80, 0xA0, 0x80, 0x30, 0x80, 0x02, 0x01, 0x01,
					0x06, 0x02, 0x51, 0x01, 0x00, 0x00, 0x00, 0x00, 0x61, 0x80, 0x30, 0x80, 0x02, 0x01, 0x01, 0xA0,
					0x80, 0x64, 0x80, 0x80, 0x01, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 });

	public static byte[] assoc_release_resp_msg = trimByteArray(new int[] { 0x09, 0x18, 0x61, 0x80, 0x30, 0x80, 0x02,
			0x01, 0x01, 0xA0, 0x80, 0x62, 0x80, 0x80, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 });

	// SessionHeader
	public static final byte CN_SPDU_SI = 0x0D;

	public static final byte AC_SPDU_SI = 0x0E;
	public static final byte RF_SPDU_SI = 0x0C;
	public static final byte FN_SPDU_SI = 0x09;
	public static final byte DN_SPDU_SI = 0x0A;
	public static final byte AB_SPDU_SI = 0x19;
	public static final byte DA_SPDU_SI = (byte) 0xE1;

	// ROapdus
	public static final byte ROIV_APDU = 1;

	public static final byte RORS_APDU = 2;
	public static final byte ROER_APDU = 3;
	public static final byte RORLS_APDU = 5;

	// RORSapdu
	public static final byte CMD_EVENT_REPORT = 0;

	public static final byte CMD_CONFIRMED_EVENT_REPORT = 1;
	public static final byte CMD_GET = 3;
	public static final byte CMD_SET = 4;
	public static final byte CMD_CONFIRMED_SET = 5;
	public static final byte CMD_CONFIRMED_ACTION = 7;

	// RORLSapdu
	public static final byte RORLS_FIRST = 1; /* set in the first message */

	public static final byte RORLS_NOT_FIRST_NOT_LAST = 2;
	public static final byte RORLS_LAST = 3; /* last RORLSapdu, one RORSapdu to follow */

	// ActionType
	public static final int NOM_ACT_POLL_MDIB_DATA = 3094; // Single poll, ushort

	public static final int NOM_ACT_POLL_MDIB_DATA_EXT = 61755; // Extended poll, ushort

	// ReceiveState
	public static final byte STATE_SEARCH_FRAME = 1;

	public static final byte STATE_READ_FRAME = 2;
	public static final byte STATE_FINISHED_FRAME = 3;

	// MeasurementState
	public static final int INVALID = 0x8000;

	public static final int QUESTIONABLE = 0x4000;
	public static final int UNAVAILABLE = 0x2000;
	public static final int CALIBRATION_ONGOING = 0x1000;
	public static final int TEST_DATA = 0x0800;
	public static final int DEMO_DATA = 0x0400;
	public static final int VALIDATED_DATA = 0x0080;
	public static final int EARLY_INDICATION = 0x0040;
	public static final int MSMT_ONGOING = 0x0020;
	public static final int MSMT_STATE_IN_ALARM = 0x0002;
	public static final int MSMT_STATE_AL_INHIBITED = 0x0001;

	public static final int FLOATTYPE_NAN = 0x007FFFFF;

	// MetricCategory
	public static final byte MCAT_UNSPEC = 0;

	public static final byte AUTO_MEASUREMENT = 1;
	public static final byte MANUAL_MEASUREMENT = 2;
	public static final byte AUTO_SETTING = 3;
	public static final byte MANUAL_SETTING = 4;
	public static final byte AUTO_CALCULATION = 5;
	public static final byte MANUAL_CALCULATION = 6;
	public static final byte MULTI_DYNAMIC_CAPABILITIES = 50;
	public static final byte AUTO_ADJUST_PAT_TEMP = (byte) 128;
	public static final byte MANUAL_ADJUST_PAT_TEMP = (byte) 129;
	public static final byte AUTO_ALARM_LIMIT_SETTING = (byte) 130;

	public static final int AVAIL_INTERMITTEND = 0x8000;
	public static final int UPD_PERIODIC = 0x4000;
	public static final int UPD_EPISODIC = 0x2000;
	public static final int MSMT_NONCONTINUOUS = 0x1000;

	// PollProfileExtOptions
	public static final long POLL_EXT_PERIOD_NU_1SEC = 0x80000000; // uint

	public static final int POLL_EXT_PERIOD_NU_AVG_12SEC = 0x40000000;
	public static final int POLL_EXT_PERIOD_NU_AVG_60SEC = 0x20000000;
	public static final int POLL_EXT_PERIOD_NU_AVG_300SEC = 0x10000000;
	public static final int POLL_EXT_PERIOD_RTSA = 0x08000000;
	public static final int POLL_EXT_ENUM = 0x04000000;
	public static final int POLL_EXT_NU_PRIO_LIST = 0x02000000;
	public static final int POLL_EXT_DYN_MODALITIES = 0x01000000;

	// -----------------------------------------------------------------------------

	public static final int NOM_ATTR_METRIC_SPECN = 2367;
	public static final int NOM_ATTR_ID_HANDLE = 2337;
	public static final int NOM_ATTR_ID_LABEL = 2340;
	public static final int NOM_ATTR_ID_LABEL_STRING = 2343;
	public static final int NOM_ATTR_NU_CMPD_VAL_OBS = 2379;
	public static final int NOM_ATTR_NU_VAL_OBS = 2384;
	public static final int NOM_ATTR_SYS_TYPE = 0x986;
	public static final int NOM_ATTR_SA_CALIB_I16 = 0x964;

	// Compound Sample Array Observed Value
	public static final int NOM_ATTR_SA_CMPD_VAL_OBS = 0x967;

	// Sample Array Physiological Range
	public static final int NOM_ATTR_SA_RANGE_PHYS_I16 = 0x96a;

	// Sample Array Specification
	public static final int NOM_ATTR_SA_SPECN = 0x96d;

	// Sample Array Observed Value
	public static final int NOM_ATTR_SA_VAL_OBS = 0x96e;

	// Scale and Range Specification
	public static final int NOM_ATTR_SCALE_SPECN_I16 = 0x96f;

	// Date and Time
	public static final int NOM_ATTR_TIME_ABS = 0x987;

	// Sample Period
	public static final int NOM_ATTR_TIME_PD_SAMP = 0x98d;

	// Relative Time
	public static final int NOM_ATTR_TIME_REL = 0x98f;

	// Absolute Time Stamp
	public static final int NOM_ATTR_TIME_STAMP_ABS = 0x990;

	// Relative Time Stamp
	public static final int NOM_ATTR_TIME_STAMP_REL = 0x991;

	// Patient Date of Birth
	public static final int NOM_ATTR_PT_DOB = 0x958;

	// Patient ID
	public static final int NOM_ATTR_PT_ID = 0x95a;

	// Family Name
	public static final int NOM_ATTR_PT_NAME_FAMILY = 0x95c;

	// Given Name
	public static final int NOM_ATTR_PT_NAME_GIVEN = 0x95d;

	// Patient Sex
	public static final int NOM_ATTR_PT_SEX = 0x961;

	// -----------------------------------------------------------------------------

	public static final long MDDL_VERSION1 = 0x80000000; // uint
	public static final int NOM_ATTR_POLL_PROFILE_EXT = 61441;
	public static final byte NOM_POLL_PROFILE_SUPPORT = 1;

	public static final byte VAL_METRIC_SPEC = 1;
	public static final byte VAL_LABEL = 2;
	public static final byte VAL_LABEL_STRING = 4;
	public static final byte VAL_VALUE = 8;

	public static enum WavesIDLabels { // uint32
		NLS_NOM_ECG_ELEC_POTL(0x00020100), NLS_NOM_ECG_ELEC_POTL_I(0x00020101), NLS_NOM_ECG_ELEC_POTL_II(0x00020102),
		NLS_NOM_ECG_ELEC_POTL_III(0x0002013D), NLS_NOM_ECG_ELEC_POTL_AVR(0x0002013E),
		NLS_NOM_ECG_ELEC_POTL_AVL(0x0002013F), NLS_NOM_ECG_ELEC_POTL_AVF(0x00020140),
		NLS_NOM_ECG_ELEC_POTL_V1(0x00020103), NLS_NOM_ECG_ELEC_POTL_V2(0x00020104),
		NLS_NOM_ECG_ELEC_POTL_V3(0x00020105), NLS_NOM_ECG_ELEC_POTL_V4(0x00020106),
		NLS_NOM_ECG_ELEC_POTL_V5(0x00020107), NLS_NOM_ECG_ELEC_POTL_V6(0x00020108), NLS_NOM_PULS_OXIM_PLETH(0x00024BB4),
		NLS_NOM_PRESS_BLD_ART(0x00024A10), NLS_NOM_PRESS_BLD_ART_ABP(0x00024A14),
		NLS_NOM_PRESS_BLD_VEN_CENT(0x00024A44), NLS_NOM_RESP(0x00025000), NLS_NOM_AWAY_CO2(0x000250AC),
		NLS_NOM_PRESS_AWAY(0x000250F0), NLS_NOM_FLOW_AWAY(0x000250D4), NLS_EEG_NAMES_EEG_CHAN1_LBL(0x800F5401),
		NLS_EEG_NAMES_EEG_CHAN2_LBL(0x800F5402), NLS_EEG_NAMES_EEG_CHAN3_LBL(0x800F5432),
		NLS_EEG_NAMES_EEG_CHAN4_LBL(0x800F5434);

		// declaring private variable for getting values
		private int v;

		// getter method
		public int getValue() {
			return this.v;
		}

		// enum constructor - cannot be public or protected
		private WavesIDLabels(int value) {
			this.v = (int) value;
		}
	}

	public static enum AttributeIDs {
		// Device P-Alarm List
		NOM_ATTR_AL_MON_P_AL_LIST(0x902),

		// Device T-Alarm List
		NOM_ATTR_AL_MON_T_AL_LIST(0x904),

		// Altitude
		NOM_ATTR_ALTITUDE(0x90c),

		// Application Area
		NOM_ATTR_AREA_APPL(0x90d),

		// Color
		NOM_ATTR_COLOR(0x911),

		// Device Alert Condition
		NOM_ATTR_DEV_AL_COND(0x916),

		// Display Resolution
		NOM_ATTR_DISP_RES(0x917),

		// Visual Grid
		NOM_ATTR_GRID_VIS_I16(0x91a),

		// Association Invoke Id
		NOM_ATTR_ID_ASSOC_NO(0x91d),

		// Bed Label
		NOM_ATTR_ID_BED_LABEL(0x91e),

		// Object Handle
		NOM_ATTR_ID_HANDLE(0x921),

		// Label
		NOM_ATTR_ID_LABEL(0x924),

		// Label String
		NOM_ATTR_ID_LABEL_STRING(0x927),

		// System Model
		NOM_ATTR_ID_MODEL(0x928),

		// Product Specification
		NOM_ATTR_ID_PROD_SPECN(0x92d),

		// Object Type
		NOM_ATTR_ID_TYPE(0x92f),

		// Line Frequency
		NOM_ATTR_LINE_FREQ(0x935),

		// System Localization
		NOM_ATTR_LOCALIZN(0x937),

		// Metric Info Label
		NOM_ATTR_METRIC_INFO_LABEL(0x93c),

		// Metric Info Label String
		NOM_ATTR_METRIC_INFO_LABEL_STR(0x93d),

		// Metric Specification
		NOM_ATTR_METRIC_SPECN(0x93f),

		// Metric State
		NOM_ATTR_METRIC_STAT(0x940),

		// Measure Mode
		NOM_ATTR_MODE_MSMT(0x945),

		// Operating Mode
		NOM_ATTR_MODE_OP(0x946),

		// Nomenclature Version
		NOM_ATTR_NOM_VERS(0x948),

		// Compound Numeric Observed Value
		NOM_ATTR_NU_CMPD_VAL_OBS(0x94b),

		// Numeric Observed Value
		NOM_ATTR_NU_VAL_OBS(0x950),

		// Patient BSA
		NOM_ATTR_PT_BSA(0x956),

		// Pat Demo State
		NOM_ATTR_PT_DEMOG_ST(0x957),

		// Patient Date of Birth
		NOM_ATTR_PT_DOB(0x958),

		// Patient ID
		NOM_ATTR_PT_ID(0x95a),

		// Family Name
		NOM_ATTR_PT_NAME_FAMILY(0x95c),

		// Given Name
		NOM_ATTR_PT_NAME_GIVEN(0x95d),

		// Patient Sex
		NOM_ATTR_PT_SEX(0x961),

		// Patient Type
		NOM_ATTR_PT_TYPE(0x962),

		// Sample Array Calibration Specification
		NOM_ATTR_SA_CALIB_I16(0x964),

		// Compound Sample Array Observed Value
		NOM_ATTR_SA_CMPD_VAL_OBS(0x967),

		// Sample Array Physiological Range
		NOM_ATTR_SA_RANGE_PHYS_I16(0x96a),

		// Sample Array Specification
		NOM_ATTR_SA_SPECN(0x96d),

		// Sample Array Observed Value
		NOM_ATTR_SA_VAL_OBS(0x96e),

		// Scale and Range Specification
		NOM_ATTR_SCALE_SPECN_I16(0x96f),

		// Safety Standard
		NOM_ATTR_STD_SAFETY(0x982),

		// System ID
		NOM_ATTR_SYS_ID(0x984),

		// System Specification
		NOM_ATTR_SYS_SPECN(0x985),

		// System Type
		NOM_ATTR_SYS_TYPE(0x986),

		// Date and Time
		NOM_ATTR_TIME_ABS(0x987),

		// Sample Period
		NOM_ATTR_TIME_PD_SAMP(0x98d),

		// Relative Time
		NOM_ATTR_TIME_REL(0x98f),

		// Absolute Time Stamp
		NOM_ATTR_TIME_STAMP_ABS(0x990),

		// Relative Time Stamp
		NOM_ATTR_TIME_STAMP_REL(0x991),

		// Unit Code
		NOM_ATTR_UNIT_CODE(0x996),

		// Enumeration Observed Value
		NOM_ATTR_VAL_ENUM_OBS(0x99e),

		// MDS Status
		NOM_ATTR_VMS_MDS_STAT(0x9a7),

		// Patient Age
		NOM_ATTR_PT_AGE(0x9d8),

		// Patient Height
		NOM_ATTR_PT_HEIGHT(0x9dc),

		// Patient Weight
		NOM_ATTR_PT_WEIGHT(0x9df),

		// Sample Array Fixed Values Specification
		NOM_ATTR_SA_FIXED_VAL_SPECN(0xa16),

		// Patient Paced Mode
		NOM_ATTR_PT_PACED_MODE(0xa1e),

		// Internal Patient ID
		NOM_ATTR_PT_ID_INT(0xf001),

		// Private Attribute
		NOM_SAT_O2_TONE_FREQ(0xf008),

		// Private Attribute
		NOM_ATTR_CMPD_REF_LIST(0xf009),

		// IP Address Information
		NOM_ATTR_NET_ADDR_INFO(0xf100),

		// Protocol Support
		NOM_ATTR_PCOL_SUPPORT(0xf101),

		// Notes1
		NOM_ATTR_PT_NOTES1(0xf129),

		// Notes2
		NOM_ATTR_PT_NOTES2(0xf12a),

		// Time for Periodic Polling
		NOM_ATTR_TIME_PD_POLL(0xf13e),

		// Patient BSA Formula
		NOM_ATTR_PT_BSA_FORMULA(0xf1ec),

		// Mds General System Info
		NOM_ATTR_MDS_GEN_INFO(0xf1fa),

		// no of prioritized objects for poll request
		NOM_ATTR_POLL_OBJ_PRIO_NUM(0xf228),

		// Numeric Object Priority List
		NOM_ATTR_POLL_NU_PRIO_LIST(0xf239),

		// Wave Object Priority List
		NOM_ATTR_POLL_RTSA_PRIO_LIST(0xf23a),

		// Metric Modality
		NOM_ATTR_METRIC_MODALITY(0xf294),

		// The attributes are arranged in the following attribute groups:
		// Alert Monitor Group
		NOM_ATTR_GRP_AL_MON(0x801),

		// Metric Observed Value Group
		NOM_ATTR_GRP_METRIC_VAL_OBS(0x803),

		// Patient Demographics Attribute Group
		NOM_ATTR_GRP_PT_DEMOG(0x807),

		// System Application Attribute Group
		NOM_ATTR_GRP_SYS_APPL(0x80a),

		// System Identification Attribute Group
		NOM_ATTR_GRP_SYS_ID(0x80b),

		// System Production Attribute Group
		NOM_ATTR_GRP_SYS_PROD(0x80c),

		// VMO Dynamic Attribute Group
		NOM_ATTR_GRP_VMO_DYN(0x810),

		// VMO Static Attribute Group
		NOM_ATTR_GRP_VMO_STATIC(0x811);

		// declaring private variable for getting values
		private int v;

		// getter method
		public int getValue() {
			return this.v;
		}

		// enum constructor - cannot be public or protected
		private AttributeIDs(int value) {
			this.v = value;
		}
		
        public boolean Compare(int i){return v == i;}
        public static AttributeIDs GetValue(int id)
        {
        	AttributeIDs[] As = AttributeIDs.values();
            for(int i = 0; i < As.length; i++)
            {
                if(As[i].Compare(id))
                    return As[i];
            }
            return null;
        }
	}

	public static enum ObjectClasses // UInt16
	{
		// non-CLS), but required -also used for Alert Source
		NOM_MOC_VMO(1),

		// VMO
		NOM_MOC_VMO_METRIC_NU(6),

		// Numeric
		NOM_MOC_VMO_METRIC_SA_RT(9),

		// Realtime Sample Array
		NOM_MOC_VMS_MDS(33),

		// MDS
		NOM_MOC_VMS_MDS_COMPOS_SINGLE_BED(35),

		// Composit Single Bed MDS
		NOM_MOC_VMS_MDS_SIMP(37),

		// Simple MDS
		NOM_MOC_BATT(41),

		// Battery
		NOM_MOC_PT_DEMOG(42),

		// Patient Demographics
		NOM_MOC_VMO_AL_MON(54),

		// Alert Monitor
		NOM_ACT_POLL_MDIB_DATA(3094),

		// Poll Action
		NOM_NOTI_MDS_CREAT(3334),

		// MDS Create
		NOM_NOTI_CONN_INDIC(3351),

		// Connect Indication
		NOM_DEV_METER_CONC_SKIN_GAS(4264),

		// Skin Gas
		NOM_DEV_METER_FLOW_BLD(4284),

		// Blood Flow
		NOM_DEV_ANALY_CONC_GAS_MULTI_PARAM_MDS(4113),

		// Gas Analyzer
		NOM_DEV_ANALY_CONC_GAS_MULTI_PARAM_VMD(4114),

		// Gas
		NOM_DEV_METER_CONC_SKIN_GAS_MDS(4265),

		// Skin Gas
		NOM_DEV_MON_PHYSIO_MULTI_PARAM_MDS(4429),

		// Multi-Param
		NOM_DEV_PUMP_INFUS_MDS(4449),

		// Pump Infus
		NOM_DEV_SYS_PT_VENT_MDS(4465),

		// Ventilator
		NOM_DEV_SYS_PT_VENT_VMD(4466),

		// Ventilator
		NOM_DEV_SYS_MULTI_MODAL_MDS(4493),

		// Multi-Modal MDS
		NOM_DEV_SYS_MULTI_MODAL_VMD(4494),

		// Multi-Modal
		NOM_DEV_SYS_VS_CONFIG_MDS(5209),

		// config MDS
		NOM_DEV_SYS_VS_UNCONFIG_MDS(5213),

		// unconfig MDS
		NOM_DEV_ANALY_SAT_O2_VMD(4106),

		// sat O2
		NOM_DEV_ANALY_FLOW_AWAY_VMD(4130),

		// Flow Away
		NOM_DEV_ANALY_CARD_OUTPUT_VMD(4134),

		// CO
		NOM_DEV_ANALY_PRESS_BLD_VMD(4174),

		// Press
		NOM_DEV_ANALY_RESP_RATE_VMD(4186),

		// RR
		NOM_DEV_CALC_VMD(4206),

		// Calculation
		NOM_DEV_ECG_VMD(4262),

		// ECG
		NOM_DEV_METER_CONC_SKIN_GAS_VMD(4266),

		// Skin Gas
		NOM_DEV_EEG_VMD(4274),

		// EEG
		NOM_DEV_METER_TEMP_BLD_VMD(4350),

		// Blood Temp
		NOM_DEV_METER_TEMP_VMD(4366),

		// Temp
		NOM_DEV_MON_BLD_CHEM_MULTI_PARAM_VMD(4398),

		// Bld Chem
		NOM_DEV_SYS_ANESTH_VMD(4506),

		// Aneshesia
		NOM_DEV_GENERAL_VMD(5122),

		// General
		NOM_DEV_ECG_RESP_VMD(5130),

		// ECG-Resp
		NOM_DEV_ARRHY_VMD(5134),

		// Arrythmia
		NOM_DEV_PULS_VMD(5138),

		// Pulse
		NOM_DEV_ST_VMD(5142),

		// ST
		NOM_DEV_CO2_VMD(5146),

		// CO2
		NOM_DEV_PRESS_BLD_NONINV_VMD(5150),

		// Noninv Press
		NOM_DEV_CEREB_PERF_VMD(5154),

		// Cereb Perf
		NOM_DEV_CO2_CTS_VMD(5158),

		// CO2 CTS
		NOM_DEV_CO2_TCUT_VMD(5162),

		// TcCO2
		NOM_DEV_O2_VMD(5166),

		// O2
		NOM_DEV_O2_CTS_VMD(5170),

		// CTS
		NOM_DEV_O2_TCUT_VMD(5174),

		// Tc02
		NOM_DEV_TEMP_DIFF_VMD(5178),

		// Diff Temp
		NOM_DEV_CNTRL_VMD(5182),

		// Control
		NOM_DEV_WEDGE_VMD(5190),

		// Wedge
		NOM_DEV_O2_VEN_SAT_VMD(5194),

		// O2 Vent Sat
		NOM_DEV_CARD_RATE_VMD(5202),

		// HR
		NOM_DEV_PLETH_VMD(5238),

		// Pleth
		NOM_SAT_O2_TONE_FREQ(61448),

		// Private Attribute
		NOM_OBJ_HIF_KEY(61584),

		// Key
		NOM_OBJ_DISP(61616),

		// Display
		NOM_OBJ_SOUND_GEN(61648),

		// Sound Generator
		NOM_OBJ_SETTING(61649),

		// Setting
		NOM_OBJ_PRINTER(61650),

		// Printer
		NOM_OBJ_EVENT(61683),

		// Event
		NOM_OBJ_BATT_CHARGER(61690),

		// Battery Charger
		NOM_OBJ_ECG_OUT(61691),

		// ECG out
		NOM_OBJ_INPUT_DEV(61692),

		// Input Device
		NOM_OBJ_NETWORK(61693),

		// Network
		NOM_OBJ_QUICKLINK(61694),

		// Quicklink Bar
		NOM_OBJ_SPEAKER(61695),

		// Speaker
		NOM_OBJ_PUMP(61716),

		// Pump
		NOM_OBJ_IR(61717),

		// IR
		NOM_ACT_POLL_MDIB_DATA_EXT(61755),

		// Extended Poll Action
		NOM_DEV_ANALY_PULS_CONT(61800),

		// Puls Cont
		NOM_DEV_ANALY_BISPECTRAL_INDEX_VMD(61806),

		// BIS
		NOM_DEV_HIRES_TREND(61820),

		// Hires Trend
		NOM_DEV_HIRES_TREND_MDS(61821),

		// Hires Trend
		NOM_DEV_HIRES_TREND_VMD(61822),

		// Hires Trend
		NOM_DEV_MON_PT_EVENT_VMD(61826),

		// Events
		NOM_DEV_DERIVED_MSMT(61828),

		// Derived Measurement
		NOM_DEV_DERIVED_MSMT_MDS(61829),

		// Derived Measurement
		NOM_DEV_DERIVED_MSMT_VMD(61830),

		// Derived Measurement
		NOM_OBJ_SENSOR(61902),

		// Sensor
		NOM_OBJ_XDUCR(61903),

		// Transducer
		NOM_OBJ_CHAN_1(61916),

		// Channel 1
		NOM_OBJ_CHAN_2(61917),

		// Channel 2
		NOM_OBJ_AWAY_AGENT_1(61918),

		// Agent 1
		NOM_OBJ_AWAY_AGENT_2(61919),

		// Agent 2
		NOM_OBJ_HIF_MOUSE(61983),

		// MOUSE
		NOM_OBJ_HIF_TOUCH(61984),

		// TOUCH
		NOM_OBJ_HIF_SPEEDPOINT(61985),

		// Speedpoint
		NOM_OBJ_HIF_ALARMBOX(61986),

		// Alarmbox
		NOM_OBJ_BUS_I2C(61987),

		// I2C Bus
		NOM_OBJ_CPU_SEC(61988),

		// 2nd CPU
		NOM_OBJ_LED(61990),

		// LED
		NOM_OBJ_RELAY(61991),

		// Relay
		NOM_OBJ_BATT_1(61996),

		// Battery 1
		NOM_OBJ_BATT_2(61997),

		// Battery 2
		NOM_OBJ_DISP_SEC(61998),

		// 2nd Display
		NOM_OBJ_AGM(61999),

		// AGM
		NOM_OBJ_TELEMON(62014),

		// TeleMon
		NOM_OBJ_XMTR(62015),

		// Transmitter
		NOM_OBJ_CABLE(62016),

		// Cable
		NOM_OBJ_TELEMETRY_XMTR(62053),

		// Telemetry Transmitter
		NOM_OBJ_MMS(62070),

		// MMS
		NOM_OBJ_DISP_THIRD(62073),

		// Third Display
		NOM_OBJ_BATT(62078),

		// Battery
		NOM_OBJ_BATT_TELE(62091),

		// Battery Tele
		NOM_DEV_PROT_WATCH_CHAN(62095),

		// Protocol Watch generic
		NOM_OBJ_PROT_WATCH_1(62097),

		// Protocol Watch Protocol No. 1
		NOM_OBJ_PROT_WATCH_2(62098),

		// Protocol Watch Protocol No. 2
		NOM_OBJ_PROT_WATCH_3(62099),

		// Protocol Watch Protocol No. 3
		NOM_OBJ_ECG_SYNC(62147),

		// ECG Sync
		NOM_DEV_METAB_VMD(62162),

		// Metabolism
		NOM_OBJ_SENSOR_O2_CO2(62165),

		// SENSOR O2 CO2
		NOM_OBJ_SRR_IF_1(62208),

		// SRR Interface 1
		NOM_OBJ_DISP_REMOTE(62228);

		// REMOTE DISPLAY

		// declaring private variable for getting values
		private int v;

		// getter method
		public int getValue() {
			return this.v;
		}

		// enum constructor - cannot be public or protected
		private ObjectClasses(int value) {
			this.v = value;
		}

	}

	public static enum AlertSource // UInt16
	{
		NOM_ECG_LEAD_IG_LEAD_I(1), NOM_ECG_LEAD_II(2), NOM_ECG_LEAD_LA(21), NOM_ECG_LEAD_RA(22), NOM_ECG_LEAD_LL(23),
		NOM_ECG_LEAD_fI(24), NOM_ECG_LEAD_fE(25), NOM_ECG_LEAD_fA(27), NOM_ECG_LEAD_C(66), NOM_ECG_LEAD_C1FR(82),
		NOM_ECG_LEAD_C2FR(83), NOM_ECG_LEAD_C3FR(84), NOM_ECG_LEAD_C4FR(85), NOM_ECG_LEAD_C5FR(87),
		NOM_ECG_LEAD_C6FR(88), NOM_ECG_LEAD_C7FR(89), NOM_ECG_LEAD_C8FR(90), NOM_ECG_LEAD_ES(100), NOM_ECG_LEAD_AS(101),
		NOM_ECG_LEAD_AI(102), NOM_ECG_LEAD_RL(115), NOM_ECG_LEAD_EASI_S(116), NOM_ECG_ELEC_POTL(256),
		NOM_ECG_ELEC_POTL_I(257), NOM_ECG_ELEC_POTL_II(258), NOM_ECG_ELEC_POTL_V1(259), NOM_ECG_ELEC_POTL_V2(260),
		NOM_ECG_ELEC_POTL_V3(261), NOM_ECG_ELEC_POTL_V4(262), NOM_ECG_ELEC_POTL_V5(263), NOM_ECG_ELEC_POTL_V6(264),
		NOM_ECG_ELEC_POTL_III(317), NOM_ECG_ELEC_POTL_AVR(318), NOM_ECG_ELEC_POTL_AVL(319), NOM_ECG_ELEC_POTL_AVF(320),
		NOM_ECG_ELEC_POTL_V(323), NOM_ECG_ELEC_POTL_MCL(331), NOM_ECG_ELEC_POTL_MCL1(332), NOM_ECG_AMPL_ST(768),
		NOM_ECG_AMPL_ST_I(769), NOM_ECG_AMPL_ST_II(770), NOM_ECG_AMPL_ST_V1(771), NOM_ECG_AMPL_ST_V2(772),
		NOM_ECG_AMPL_ST_V3(773), NOM_ECG_AMPL_ST_V4(774), NOM_ECG_AMPL_ST_V5(775), NOM_ECG_AMPL_ST_V6(776),
		NOM_ECG_AMPL_ST_III(829), NOM_ECG_AMPL_ST_AVR(830), NOM_ECG_AMPL_ST_AVL(831), NOM_ECG_AMPL_ST_AVF(832),
		NOM_ECG_AMPL_ST_V(835), NOM_ECG_AMPL_ST_MCL(843), NOM_ECG_AMPL_ST_ES(868), NOM_ECG_AMPL_ST_AS(869),
		NOM_ECG_AMPL_ST_AI(870), NOM_ECG_TIME_PD_QT_GL(16160), NOM_ECG_TIME_PD_QTc(16164),
		NOM_ECG_CARD_BEAT_RATE(16770), NOM_ECG_CARD_BEAT_RATE_BTB(16778), NOM_ECG_V_P_C_CNT(16993),
		NOM_ECG_V_P_C_RATE(16994), NOM_ECG_V_P_C_FREQ(17000), NOM_PULS_RATE(18442), NOM_PLETH_PULS_RATE(18466),
		NOM_RES_VASC_SYS_INDEX(18688), NOM_WK_LV_STROKE_INDEX(18692), NOM_WK_RV_STROKE_INDEX(18696),
		NOM_OUTPUT_CARD_INDEX(18700), NOM_PRESS_BLD(18944), NOM_PRESS_BLD_SYS(18945), NOM_PRESS_BLD_DIA(18946),
		NOM_PRESS_BLD_MEAN(18947), NOM_PRESS_BLD_NONINV(18948), NOM_PRESS_BLD_NONINV_SYS(18949),
		NOM_PRESS_BLD_NONINV_DIA(18950), NOM_PRESS_BLD_NONINV_MEAN(18951), NOM_PRESS_BLD_AORT(18956),
		NOM_PRESS_BLD_AORT_SYS(18957), NOM_PRESS_BLD_AORT_DIA(18958), NOM_PRESS_BLD_AORT_MEAN(18959),
		NOM_PRESS_BLD_ART(18960), NOM_PRESS_BLD_ART_SYS(18961), NOM_PRESS_BLD_ART_DIA(18962),
		NOM_PRESS_BLD_ART_MEAN(18963), NOM_PRESS_BLD_ART_ABP(18964), NOM_PRESS_BLD_ART_ABP_SYS(18965),
		NOM_PRESS_BLD_ART_ABP_DIA(18966), NOM_PRESS_BLD_ART_ABP_MEAN(18967), NOM_PRESS_BLD_ART_PULM(18972),
		NOM_PRESS_BLD_ART_PULM_SYS(18973), NOM_PRESS_BLD_ART_PULM_DIA(18974), NOM_PRESS_BLD_ART_PULM_MEAN(18975),
		NOM_PRESS_BLD_ART_PULM_WEDGE(18980), NOM_PRESS_BLD_ART_UMB(18984), NOM_PRESS_BLD_ART_UMB_SYS(18985),
		NOM_PRESS_BLD_ART_UMB_DIA(18986), NOM_PRESS_BLD_ART_UMB_MEAN(18987), NOM_PRESS_BLD_ATR_LEFT(18992),
		NOM_PRESS_BLD_ATR_LEFT_SYS(18993), NOM_PRESS_BLD_ATR_LEFT_DIA(18994), NOM_PRESS_BLD_ATR_LEFT_MEAN(18995),
		NOM_PRESS_BLD_ATR_RIGHT(18996), NOM_PRESS_BLD_ATR_RIGHT_SYS(18997), NOM_PRESS_BLD_ATR_RIGHT_DIA(18998),
		NOM_PRESS_BLD_ATR_RIGHT_MEAN(18999), NOM_PRESS_BLD_VEN_CENT(19012), NOM_PRESS_BLD_VEN_CENT_SYS(19013),
		NOM_PRESS_BLD_VEN_CENT_DIA(19014), NOM_PRESS_BLD_VEN_CENT_MEAN(19015), NOM_PRESS_BLD_VEN_UMB(19016),
		NOM_PRESS_BLD_VEN_UMB_SYS(19017), NOM_PRESS_BLD_VEN_UMB_DIA(19018), NOM_PRESS_BLD_VEN_UMB_MEAN(19019),
		NOM_SAT_O2_CONSUMP(19200), NOM_OUTPUT_CARD(19204), NOM_RES_VASC_PULM(19236), NOM_RES_VASC_SYS(19240),
		NOM_SAT_O2(19244), NOM_SAT_O2_ART(19252), NOM_SAT_O2_VEN(19260), NOM_SAT_DIFF_O2_ART_ALV(19264),
		NOM_TEMP(19272), NOM_TEMP_ART(19280), NOM_TEMP_AWAY(19284), NOM_TEMP_CORE(19296), NOM_TEMP_ESOPH(19300),
		NOM_TEMP_INJ(19304), NOM_TEMP_NASOPH(19308), NOM_TEMP_SKIN(19316), NOM_TEMP_TYMP(19320), NOM_TEMP_VEN(19324),
		NOM_VOL_BLD_STROKE(19332), NOM_WK_CARD_LEFT(19344), NOM_WK_CARD_RIGHT(19348), NOM_WK_LV_STROKE(19356),
		NOM_WK_RV_STROKE(19364), NOM_PULS_OXIM_PERF_REL(19376), NOM_PLETH(19380), NOM_PULS_OXIM_SAT_O2(19384),
		NOM_PULS_OXIM_SAT_O2_DIFF(19396), NOM_PULS_OXIM_SAT_O2_ART_LEFT(19400), NOM_PULS_OXIM_SAT_O2_ART_RIGHT(19404),
		NOM_OUTPUT_CARD_CTS(19420), NOM_VOL_VENT_L_END_SYS(19460), NOM_GRAD_PRESS_BLD_AORT_POS_MAX(19493),
		NOM_RESP(20480), NOM_RESP_RATE(20490), NOM_AWAY_RESP_RATE(20498), NOM_CAPAC_VITAL(20608), NOM_COMPL_LUNG(20616),
		NOM_COMPL_LUNG_DYN(20620), NOM_COMPL_LUNG_STATIC(20624), NOM_CONC_AWAY_CO2(20628), NOM_CONC_AWAY_CO2_ET(20636),
		NOM_CONC_AWAY_CO2_INSP_MIN(20646), NOM_AWAY_CO2(20652), NOM_AWAY_CO2_ET(20656), NOM_AWAY_CO2_INSP_MIN(20666),
		NOM_CO2_TCUT(20684), NOM_O2_TCUT(20688), NOM_FLOW_AWAY(20692), NOM_FLOW_AWAY_EXP_MAX(20697),
		NOM_FLOW_AWAY_INSP_MAX(20701), NOM_FLOW_CO2_PROD_RESP(20704), NOM_IMPED_TTHOR(20708),
		NOM_PRESS_RESP_PLAT(20712), NOM_PRESS_AWAY(20720), NOM_PRESS_AWAY_MIN(20722), NOM_PRESS_AWAY_CTS_POS(20724),
		NOM_PRESS_AWAY_NEG_MAX(20729), NOM_PRESS_AWAY_END_EXP_POS_INTRINSIC(20736), NOM_PRESS_AWAY_INSP(20744),
		NOM_PRESS_AWAY_INSP_MAX(20745), NOM_PRESS_AWAY_INSP_MEAN(20747), NOM_RATIO_IE(20760),
		NOM_RATIO_AWAY_DEADSP_TIDAL(20764), NOM_RES_AWAY(20768), NOM_RES_AWAY_EXP(20772), NOM_RES_AWAY_INSP(20776),
		NOM_TIME_PD_APNEA(20784), NOM_VOL_AWAY_TIDAL(20796), NOM_VOL_MINUTE_AWAY(20808), NOM_VOL_MINUTE_AWAY_EXP(20812),
		NOM_VOL_MINUTE_AWAY_INSP(20816), NOM_CONC_AWAY_O2(20836), NOM_VENT_CONC_AWAY_O2_DELTA(20840),
		NOM_VENT_CONC_AWAY_O2_EXP(20844), NOM_VENT_AWAY_CO2_EXP(20860), NOM_VENT_PRESS_AWAY_END_EXP_POS(20904),
		NOM_VENT_VOL_AWAY_DEADSP(20912), NOM_VENT_VOL_LUNG_TRAPD(20920), NOM_VENT_CONC_AWAY_O2_INSP(29848),
		NOM_VENT_FLOW_RATIO_PERF_ALV_INDEX(20880), NOM_VENT_FLOW_INSP(20876), NOM_VENT_CONC_AWAY_CO2_INSP(20832),
		NOM_VENT_PRESS_OCCL(20892), NOM_VENT_VOL_AWAY_DEADSP_REL(20916), NOM_VENT_VOL_MINUTE_AWAY_MAND(20940),
		NOM_COEF_GAS_TRAN(20948), NOM_CONC_AWAY_DESFL(20952), NOM_CONC_AWAY_ENFL(20956), NOM_CONC_AWAY_HALOTH(20960),
		NOM_CONC_AWAY_SEVOFL(20964), NOM_CONC_AWAY_ISOFL(20968), NOM_CONC_AWAY_N2O(20976),
		NOM_CONC_AWAY_DESFL_ET(21012), NOM_CONC_AWAY_ENFL_ET(21016), NOM_CONC_AWAY_HALOTH_ET(21020),
		NOM_CONC_AWAY_SEVOFL_ET(21024), NOM_CONC_AWAY_ISOFL_ET(21028), NOM_CONC_AWAY_N2O_ET(21036),
		NOM_CONC_AWAY_DESFL_INSP(21096), NOM_CONC_AWAY_ENFL_INSP(21100), NOM_CONC_AWAY_HALOTH_INSP(21104),
		NOM_CONC_AWAY_SEVOFL_INSP(21108), NOM_CONC_AWAY_ISOFL_INSP(21112), NOM_CONC_AWAY_N2O_INSP(21120),
		NOM_CONC_AWAY_O2_INSP(21124), NOM_VENT_TIME_PD_PPV(21344), NOM_VENT_PRESS_RESP_PLAT(21352),
		NOM_VENT_VOL_LEAK(21360), NOM_VENT_VOL_LUNG_ALV(21364), NOM_CONC_AWAY_O2_ET(21368), NOM_CONC_AWAY_N2(21372),
		NOM_CONC_AWAY_N2_ET(21376), NOM_CONC_AWAY_N2_INSP(21380), NOM_CONC_AWAY_AGENT(21384),
		NOM_CONC_AWAY_AGENT_ET(21388), NOM_CONC_AWAY_AGENT_INSP(21392), NOM_PRESS_CEREB_PERF(22532),
		NOM_PRESS_INTRA_CRAN(22536), NOM_PRESS_INTRA_CRAN_SYS(22537), NOM_PRESS_INTRA_CRAN_DIA(22538),
		NOM_PRESS_INTRA_CRAN_MEAN(22539), NOM_SCORE_GLAS_COMA(22656), NOM_SCORE_EYE_SUBSC_GLAS_COMA(22658),
		NOM_SCORE_MOTOR_SUBSC_GLAS_COMA(22659), NOM_SCORE_SUBSC_VERBAL_GLAS_COMA(22660), NOM_CIRCUM_HEAD(22784),
		NOM_TIME_PD_PUPIL_REACT_LEFT(22820), NOM_TIME_PD_PUPIL_REACT_RIGHT(22824), NOM_EEG_ELEC_POTL_CRTX(22828),
		NOM_EMG_ELEC_POTL_MUSCL(22844), NOM_EEG_FREQ_PWR_SPEC_CRTX_DOM_MEAN(22908),
		NOM_EEG_FREQ_PWR_SPEC_CRTX_PEAK(22916), NOM_EEG_FREQ_PWR_SPEC_CRTX_SPECTRAL_EDGE(22920),
		NOM_EEG_PWR_SPEC_TOT(22968), NOM_EEG_PWR_SPEC_ALPHA_REL(22996), NOM_EEG_PWR_SPEC_BETA_REL(23000),
		NOM_EEG_PWR_SPEC_DELTA_REL(23004), NOM_EEG_PWR_SPEC_THETA_REL(23008), NOM_FLOW_URINE_INSTANT(26636),
		NOM_VOL_URINE_BAL_PD(26660), NOM_VOL_URINE_COL(26672), NOM_VOL_INFUS_ACTUAL_TOTAL(26876),
		NOM_CONC_PH_ART(28676), NOM_CONC_PCO2_ART(28680), NOM_CONC_PO2_ART(28684), NOM_CONC_HB_ART(28692),
		NOM_CONC_HB_O2_ART(28696), NOM_CONC_PO2_VEN(28732), NOM_CONC_PH_VEN(28724), NOM_CONC_PCO2_VEN(28728),
		NOM_CONC_HB_O2_VEN(28744), NOM_CONC_PH_URINE(28772), NOM_CONC_NA_URINE(28780), NOM_CONC_NA_SERUM(28888),
		NOM_CONC_PH_GEN(28932), NOM_CONC_HCO3_GEN(28936), NOM_CONC_NA_GEN(28940), NOM_CONC_K_GEN(28944),
		NOM_CONC_GLU_GEN(28948), NOM_CONC_CA_GEN(28952), NOM_CONC_PCO2_GEN(28992), NOM_CONC_CHLORIDE_GEN(29032),
		NOM_BASE_EXCESS_BLD_ART(29036), NOM_CONC_PO2_GEN(29044), NOM_CONC_HCT_GEN(29060),
		NOM_VENT_MODE_MAND_INTERMIT(53290), NOM_TEMP_RECT(57348), NOM_TEMP_BLD(57364), NOM_TEMP_DIFF(57368),
		NOM_METRIC_NOS(61439), NOM_ECG_AMPL_ST_INDEX(61501), NOM_TIME_TCUT_SENSOR(61502), NOM_TEMP_TCUT_SENSOR(61503),
		NOM_VOL_BLD_INTRA_THOR(61504), NOM_VOL_BLD_INTRA_THOR_INDEX(61505), NOM_VOL_LUNG_WATER_EXTRA_VASC(61506),
		NOM_VOL_LUNG_WATER_EXTRA_VASC_INDEX(61507), NOM_VOL_GLOBAL_END_DIA(61508), NOM_VOL_GLOBAL_END_DIA_INDEX(61509),
		NOM_CARD_FUNC_INDEX(61510), NOM_OUTPUT_CARD_INDEX_CTS(61511), NOM_VOL_BLD_STROKE_INDEX(61512),
		NOM_VOL_BLD_STROKE_VAR(61513), NOM_EEG_RATIO_SUPPRN(61514), NOM_ELECTRODE_IMPED(61515),
		NOM_EEG_BIS_SIG_QUAL_INDEX(61517), NOM_EEG_BISPECTRAL_INDEX(61518), NOM_GAS_TCUT(61521),
		NOM_CONC_AWAY_SUM_MAC(61533), NOM_CONC_AWAY_SUM_MAC_ET(61534), NOM_CONC_AWAY_SUM_MAC_INSP(61535),
		NOM_RES_VASC_PULM_INDEX(61543), NOM_WK_CARD_LEFT_INDEX(61544), NOM_WK_CARD_RIGHT_INDEX(61545),
		NOM_SAT_O2_CONSUMP_INDEX(61546), NOM_PRESS_AIR_AMBIENT(61547), NOM_SAT_DIFF_O2_ART_VEN(61548),
		NOM_SAT_O2_DELIVER(61549), NOM_SAT_O2_DELIVER_INDEX(61550), NOM_RATIO_SAT_O2_CONSUMP_DELIVER(61551),
		NOM_RATIO_ART_VEN_SHUNT(61552), NOM_AREA_BODY_SURFACE(61553), NOM_INTENS_LIGHT(61554),
		NOM_HEATING_PWR_TCUT_SENSOR(61558), NOM_RATE_DIFF_CARD_BEAT_PULSE(61560), NOM_VOL_INJ(61561),
		NOM_VOL_THERMO_EXTRA_VASC_INDEX(61562), NOM_NUM_CATHETER_CONST(61564), NOM_PULS_OXIM_PERF_REL_LEFT(61578),
		NOM_PULS_OXIM_PERF_REL_RIGHT(61579), NOM_PULS_OXIM_PLETH_RIGHT(61580), NOM_PULS_OXIM_PLETH_LEFT(61581),
		NOM_CONC_BLD_UREA_NITROGEN(61583), NOM_CONC_BASE_EXCESS_ECF(61584), NOM_VENT_VOL_MINUTE_AWAY_SPONT(61585),
		NOM_CONC_DIFF_HB_O2_ATR_VEN(61586), NOM_PAT_WEIGHT(61587), NOM_PAT_HEIGHT(61588), NOM_CONC_AWAY_MAC(61593),
		NOM_PULS_OXIM_PLETH_TELE(61595), NOM_PULS_OXIM_SAT_O2_TELE(61596), NOM_PULS_OXIM_PULS_RATE_TELE(61597),
		NOM_PRESS_BLD_NONINV_TELE(61600), NOM_PRESS_BLD_NONINV_TELE_SYS(61601), NOM_PRESS_BLD_NONINV_TELE_DIA(61602),
		NOM_PRESS_BLD_NONINV_TELE_MEAN(61603), NOM_PRESS_GEN_1(61604), NOM_PRESS_GEN_1_SYS(61605),
		NOM_PRESS_GEN_1_DIA(61606), NOM_PRESS_GEN_1_MEAN(61607), NOM_PRESS_GEN_2(61608), NOM_PRESS_GEN_2_SYS(61609),
		NOM_PRESS_GEN_2_DIA(61610), NOM_PRESS_GEN_2_MEAN(61611), NOM_PRESS_GEN_3(61612), NOM_PRESS_GEN_3_SYS(61613),
		NOM_PRESS_GEN_3_DIA(61614), NOM_PRESS_GEN_3_MEAN(61615), NOM_PRESS_GEN_4(61616), NOM_PRESS_GEN_4_SYS(61617),
		NOM_PRESS_GEN_4_DIA(61618), NOM_PRESS_GEN_4_MEAN(61619), NOM_PRESS_INTRA_CRAN_1(61620),
		NOM_PRESS_INTRA_CRAN_1_SYS(61621), NOM_PRESS_INTRA_CRAN_1_DIA(61622), NOM_PRESS_INTRA_CRAN_1_MEAN(61623),
		NOM_PRESS_INTRA_CRAN_2(61624), NOM_PRESS_INTRA_CRAN_2_SYS(61625), NOM_PRESS_INTRA_CRAN_2_DIA(61626),
		NOM_PRESS_INTRA_CRAN_2_MEAN(61627), NOM_PRESS_BLD_ART_FEMORAL(61628), NOM_PRESS_BLD_ART_FEMORAL_SYS(61629),
		NOM_PRESS_BLD_ART_FEMORAL_DIA(61630), NOM_PRESS_BLD_ART_FEMORAL_MEAN(61631), NOM_PRESS_BLD_ART_BRACHIAL(61632),
		NOM_PRESS_BLD_ART_BRACHIAL_SYS(61633), NOM_PRESS_BLD_ART_BRACHIAL_DIA(61634),
		NOM_PRESS_BLD_ART_BRACHIAL_MEAN(61635), NOM_TEMP_VESICAL(61636), NOM_TEMP_CEREBRAL(61637),
		NOM_TEMP_AMBIENT(61638), NOM_TEMP_GEN_1(61639), NOM_TEMP_GEN_2(61640), NOM_TEMP_GEN_3(61641),
		NOM_TEMP_GEN_4(61642), NOM_USOUND_CARD_BEAT_RATE_FETAL(61643), NOM_USOUND_CARD_BEAT_RATE_FETAL_BTB(61644),
		NOM_USOUND_CARD_BEAT_FETAL_SIG_QUAL_INDEX(61645), NOM_ECG_CARD_BEAT_FETAL(61646),
		NOM_ECG_CARD_BEAT_RATE_FETAL(61647), NOM_ECG_CARD_BEAT_RATE_FETAL_BTB(61648),
		NOM_ECG_CARD_BEAT_FETAL_SIG_QUAL_INDEX(61649), NOM_TRIG_BEAT_FETAL(61650), NOM_ECG_ELEC_POTL_FETAL(61651),
		NOM_TOCO(61652), NOM_STAT_COINCIDENCE(61653), NOM_PRESS_INTRA_UTERAL(61656), NOM_VOL_AWAY(61663),
		NOM_VOL_AWAY_INSP_TIDAL(61664), NOM_VOL_AWAY_EXP_TIDAL(61665), NOM_AWAY_RESP_RATE_SPIRO(61666),
		NOM_PULS_PRESS_VAR(61667), NOM_PRESS_BLD_NONINV_PULS_RATE(61669), NOM_RATIO_FETAL_MVMT_TOTAL(61680),
		NOM_VENT_RESP_RATE_MAND(61681), NOM_VENT_VOL_TIDAL_MAND(61682), NOM_VENT_VOL_TIDAL_SPONT(61683),
		NOM_CARDIAC_TROPONIN_I(61684), NOM_CARDIO_PULMONARY_BYPASS_MODE(61685), NOM_BNP(61686),
		NOM_TIME_PD_RESP_PLAT(61695), NOM_SAT_O2_VEN_CENT(61696), NOM_SNR(61697), NOM_HUMID(61699),
		NOM_FRACT_EJECT(61701), NOM_PERM_VASC_PULM_INDEX(61702), NOM_TEMP_ORAL(61704), NOM_TEMP_AXIL(61708),
		NOM_TEMP_ORAL_PRED(61712), NOM_TEMP_RECT_PRED(61716), NOM_TEMP_AXIL_PRED(61720), NOM_TEMP_AIR_INCUB(61738),
		NOM_PULS_OXIM_PERF_REL_TELE(61740), NOM_TEMP_PRED(61760), NOM_SHUNT_RIGHT_LEFT(61770),
		NOM_ECG_TIME_PD_QT_HEART_RATE(61780), NOM_ECG_TIME_PD_QT_BASELINE(61781), NOM_ECG_TIME_PD_QTc_DELTA(61782),
		NOM_ECG_TIME_PD_QT_BASELINE_HEART_RATE(61783), NOM_CONC_PH_CAP(61784), NOM_CONC_PCO2_CAP(61785),
		NOM_CONC_PO2_CAP(61786), NOM_SAT_O2_CAP(61793), NOM_CONC_MG_ION(61787), NOM_CONC_MG_SER(61788),
		NOM_CONC_tCA_SER(61789), NOM_CONC_P_SER(61790), NOM_CONC_CHLOR_SER(61791), NOM_CONC_FE_GEN(61792),
		NOM_CONC_AN_GAP(61794), NOM_CONC_AN_GAP_CALC(61857), NOM_CONC_ALB_SER(61795), NOM_SAT_O2_ART_CALC(61796),
		NOM_SAT_O2_VEN_CALC(61798), NOM_SAT_O2_CAP_CALC(61856), NOM_CONC_HB_CO_GEN(29056), NOM_CONC_HB_FETAL(61797),
		NOM_CONC_HB_MET_GEN(29052), NOM_PLTS_CNT(61799), NOM_WB_CNT(61800), NOM_RB_CNT(61801), NOM_RET_CNT(61802),
		NOM_PLASMA_OSM(61803), NOM_CONC_CREA_CLR(61804), NOM_NSLOSS(61805), NOM_CONC_CHOLESTEROL(61806),
		NOM_CONC_TGL(61807), NOM_CONC_HDL(61808), NOM_CONC_LDL(61809), NOM_CONC_UREA_GEN(61810), NOM_CONC_CREA(61811),
		NOM_CONC_LACT(61812), NOM_CONC_BILI_TOT(61815), NOM_CONC_PROT_SER(61816), NOM_CONC_PROT_TOT(61817),
		NOM_CONC_BILI_DIRECT(61818), NOM_CONC_LDH(61819), NOM_ES_RATE(61820), NOM_CONC_PCT(61821),
		NOM_CONC_CREA_KIN_MM(61823), NOM_CONC_CREA_KIN_SER(61824), NOM_CONC_CREA_KIN_MB(61825), NOM_CONC_CHE(61826),
		NOM_CONC_CRP(61827), NOM_CONC_AST(61828), NOM_CONC_AP(61829), NOM_CONC_ALPHA_AMYLASE(61830),
		NOM_CONC_GPT(61831), NOM_CONC_GOT(61832), NOM_CONC_GGT(61833), NOM_TIME_PD_ACT(61834), NOM_TIME_PD_PT(61835),
		NOM_PT_INTL_NORM_RATIO(61836), NOM_TIME_PD_aPTT_WB(61837), NOM_TIME_PD_aPTT_PE(61838), NOM_TIME_PD_PT_WB(61839),
		NOM_TIME_PD_PT_PE(61840), NOM_TIME_PD_THROMBIN(61841), NOM_TIME_PD_COAGULATION(61842),
		NOM_TIME_PD_THROMBOPLAS(61843), NOM_FRACT_EXCR_NA(61844), NOM_CONC_UREA_URINE(61845),
		NOM_CONC_CREA_URINE(61846), NOM_CONC_K_URINE(61847), NOM_CONC_K_URINE_EXCR(61848), NOM_CONC_OSM_URINE(61849),
		NOM_CONC_GLU_URINE(61855), NOM_CONC_CHLOR_URINE(61850), NOM_CONC_PRO_URINE(61851), NOM_CONC_CA_URINE(61852),
		NOM_FLUID_DENS_URINE(61853), NOM_CONC_HB_URINE(61854), NOM_ENERGY_BAL(61861),
		NOM_PULS_OXIM_SAT_O2_PRE_DUCTAL(61888), NOM_PULS_OXIM_PERF_REL_PRE_DUCTAL(61996),
		NOM_PULS_OXIM_SAT_O2_POST_DUCTAL(61908), NOM_PULS_OXIM_PERF_REL_POST_DUCTAL(61916), NOM_PRESS_GEN_5(62452),
		NOM_PRESS_GEN_5_SYS(62453), NOM_PRESS_GEN_5_DIA(62454), NOM_PRESS_GEN_5_MEAN(62455), NOM_PRESS_GEN_6(62456),
		NOM_PRESS_GEN_6_SYS(62457), NOM_PRESS_GEN_6_DIA(62458), NOM_PRESS_GEN_6_MEAN(62459), NOM_PRESS_GEN_7(62460),
		NOM_PRESS_GEN_7_SYS(62461), NOM_PRESS_GEN_7_DIA(62462), NOM_PRESS_GEN_7_MEAN(62463), NOM_PRESS_GEN_8(62464),
		NOM_PRESS_GEN_8_SYS(62465), NOM_PRESS_GEN_8_DIA(62466), NOM_PRESS_GEN_8_MEAN(62467),
		NOM_ECG_AMPL_ST_BASELINE_I(62481), NOM_ECG_AMPL_ST_BASELINE_II(62482), NOM_ECG_AMPL_ST_BASELINE_V1(62483),
		NOM_ECG_AMPL_ST_BASELINE_V2(62484), NOM_ECG_AMPL_ST_BASELINE_V3(62485), NOM_ECG_AMPL_ST_BASELINE_V4(62486),
		NOM_ECG_AMPL_ST_BASELINE_V5(62487), NOM_ECG_AMPL_ST_BASELINE_V6(62488), NOM_ECG_AMPL_ST_BASELINE_III(62541),
		NOM_ECG_AMPL_ST_BASELINE_AVR(62542), NOM_ECG_AMPL_ST_BASELINE_AVL(62543), NOM_ECG_AMPL_ST_BASELINE_AVF(62544),
		NOM_AGE(63504), NOM_AGE_GEST(63505), NOM_AWAY_CORR_COEF(63508), NOM_AWAY_RESP_RATE_SPONT(63509),
		NOM_AWAY_TC(63510), NOM_BIRTH_LENGTH(63512), NOM_BREATH_RAPID_SHALLOW_INDEX(63513), NOM_C20_PER_C_INDEX(63514),
		NOM_CARD_CONTRACT_HEATHER_INDEX(63516), NOM_CONC_ALP(63517), NOM_CONC_CA_GEN_NORM(63522),
		NOM_CONC_CA_SER(63524), NOM_CONC_CO2_TOT(63525), NOM_CONC_CO2_TOT_CALC(63526), NOM_CONC_CREA_SER(63527),
		NOM_RESP_RATE_SPONT(63528), NOM_CONC_GLO_SER(63529), NOM_CONC_GLU_SER(63530), NOM_CONC_HB_CORP_MEAN(63532),
		NOM_CONC_K_SER(63535), NOM_CONC_NA_EXCR(63536), NOM_CONC_PCO2_ART_ADJ(63538), NOM_CONC_PCO2_CAP_ADJ(63539),
		NOM_CONC_PH_CAP_ADJ(63543), NOM_CONC_PH_GEN_ADJ(63544), NOM_CONC_PO2_ART_ADJ(63547),
		NOM_CONC_PO2_CAP_ADJ(63548), NOM_CREA_OSM(63551), NOM_EEG_BURST_SUPPRN_INDEX(63552),
		NOM_EEG_ELEC_POTL_CRTX_GAIN_LEFT(63553), NOM_EEG_ELEC_POTL_CRTX_GAIN_RIGHT(63554),
		NOM_EEG_FREQ_PWR_SPEC_CRTX_MEDIAN_LEFT(63563), NOM_EEG_FREQ_PWR_SPEC_CRTX_MEDIAN_RIGHT(63564),
		NOM_EEG_PWR_SPEC_ALPHA_ABS_LEFT(63573), NOM_EEG_PWR_SPEC_ALPHA_ABS_RIGHT(63574),
		NOM_EEG_PWR_SPEC_BETA_ABS_LEFT(63579), NOM_EEG_PWR_SPEC_BETA_ABS_RIGHT(63580),
		NOM_EEG_PWR_SPEC_DELTA_ABS_LEFT(63587), NOM_EEG_PWR_SPEC_DELTA_ABS_RIGHT(63588),
		NOM_EEG_PWR_SPEC_THETA_ABS_LEFT(63593), NOM_EEG_PWR_SPEC_THETA_ABS_RIGHT(63594),
		NOM_ELEC_EVOK_POTL_CRTX_ACOUSTIC_AAI(63603), NOM_EXTRACT_O2_INDEX(63605), NOM_FLOW_AWAY_AIR(63607),
		NOM_FLOW_AWAY_EXP_ET(63610), NOM_FLOW_AWAY_MAX_SPONT(63613), NOM_FLOW_AWAY_TOT(63617),
		NOM_FLOW_CO2_PROD_RESP_TIDAL(63618), NOM_FLOW_URINE_PREV_24HR(63619), NOM_FREE_WATER_CLR(63620),
		NOM_HB_CORP_MEAN(63621), NOM_HEATING_PWR_INCUBATOR(63622), NOM_OUTPUT_CARD_INDEX_ACCEL(63625),
		NOM_PTC_CNT(63627), NOM_PULS_OXIM_PLETH_GAIN(63629), NOM_RATIO_AWAY_RATE_VOL_AWAY(63630),
		NOM_RATIO_BUN_CREA(63631), NOM_RATIO_CONC_BLD_UREA_NITROGEN_CREA_CALC(63632),
		NOM_RATIO_CONC_URINE_CREA_CALC(63633), NOM_RATIO_CONC_URINE_CREA_SER(63634), NOM_RATIO_CONC_URINE_NA_K(63635),
		NOM_RATIO_PaO2_FIO2(63636), NOM_RATIO_TIME_PD_PT(63637), NOM_RATIO_TIME_PD_PTT(63638),
		NOM_RATIO_TRAIN_OF_FOUR(63639), NOM_RATIO_URINE_SER_OSM(63640), NOM_RES_AWAY_DYN(63641),
		NOM_RESP_BREATH_ASSIST_CNT(63642), NOM_RIGHT_HEART_FRACT_EJECT(63643), NOM_TIME_PD_EVOK_REMAIN(63648),
		NOM_TIME_PD_EXP(63649), NOM_TIME_PD_FROM_LAST_MSMT(63650), NOM_TIME_PD_INSP(63651),
		NOM_TIME_PD_KAOLIN_CEPHALINE(63652), NOM_TIME_PD_PTT(63653), NOM_TRAIN_OF_FOUR_1(63655),
		NOM_TRAIN_OF_FOUR_2(63656), NOM_TRAIN_OF_FOUR_3(63657), NOM_TRAIN_OF_FOUR_4(63658),
		NOM_TRAIN_OF_FOUR_CNT(63659), NOM_TWITCH_AMPL(63660), NOM_UREA_SER(63661), NOM_VENT_ACTIVE(63664),
		NOM_VENT_AMPL_HFV(63665), NOM_VENT_CONC_AWAY_AGENT_DELTA(63666), NOM_VENT_CONC_AWAY_DESFL_DELTA(63667),
		NOM_VENT_CONC_AWAY_ENFL_DELTA(63668), NOM_VENT_CONC_AWAY_HALOTH_DELTA(63669),
		NOM_VENT_CONC_AWAY_ISOFL_DELTA(63670), NOM_VENT_CONC_AWAY_N2O_DELTA(63671),
		NOM_VENT_CONC_AWAY_O2_CIRCUIT(63672), NOM_VENT_CONC_AWAY_SEVOFL_DELTA(63673),
		NOM_VENT_PRESS_AWAY_END_EXP_POS_LIMIT_LO(63674), NOM_VENT_PRESS_AWAY_PV(63676), NOM_VENT_TIME_PD_RAMP(63677),
		NOM_VENT_VOL_AWAY_INSP_TIDAL_HFV(63678), NOM_VENT_VOL_TIDAL_HFV(63679), NOM_VOL_AWAY_EXP_TIDAL_SPONT(63682),
		NOM_VOL_AWAY_TIDAL_PSV(63683), NOM_VOL_CORP_MEAN(63684), NOM_VOL_FLUID_THORAC(63685),
		NOM_VOL_FLUID_THORAC_INDEX(63686), NOM_VOL_LVL_LIQUID_BOTTLE_AGENT(63687),
		NOM_VOL_LVL_LIQUID_BOTTLE_DESFL(63688), NOM_VOL_LVL_LIQUID_BOTTLE_ENFL(63689),
		NOM_VOL_LVL_LIQUID_BOTTLE_HALOTH(63690), NOM_VOL_LVL_LIQUID_BOTTLE_ISOFL(63691),
		NOM_VOL_LVL_LIQUID_BOTTLE_SEVOFL(63692), NOM_VOL_MINUTE_AWAY_INSP_HFV(63693),
		NOM_VOL_URINE_BAL_PD_INSTANT(63694), NOM_VOL_URINE_SHIFT(63695), NOM_VOL_VENT_L_END_SYS_INDEX(63697),
		NOM_WEIGHT_URINE_COL(63699), NOM_SAT_O2_TISSUE(63840), NOM_CEREB_STATE_INDEX(63841), NOM_SAT_O2_GEN_1(63842),
		NOM_SAT_O2_GEN_2(63843), NOM_SAT_O2_GEN_3(63844), NOM_SAT_O2_GEN_4(63845), NOM_TEMP_CORE_GEN_1(63846),
		NOM_TEMP_CORE_GEN_2(63847), NOM_PRESS_BLD_DIFF(63848), NOM_PRESS_BLD_DIFF_GEN_1(63852),
		NOM_PRESS_BLD_DIFF_GEN_2(63856), NOM_FLOW_PUMP_HEART_LUNG_MAIN(63860), NOM_FLOW_PUMP_HEART_LUNG_SLAVE(63861),
		NOM_FLOW_PUMP_HEART_LUNG_SUCTION(63862), NOM_FLOW_PUMP_HEART_LUNG_AUX(63863),
		NOM_FLOW_PUMP_HEART_LUNG_CARDIOPLEGIA_MAIN(63864), NOM_FLOW_PUMP_HEART_LUNG_CARDIOPLEGIA_SLAVE(63865),
		NOM_TIME_PD_PUMP_HEART_LUNG_AUX_SINCE_START(63866), NOM_TIME_PD_PUMP_HEART_LUNG_AUX_SINCE_STOP(63867),
		NOM_VOL_DELIV_PUMP_HEART_LUNG_AUX(63868), NOM_VOL_DELIV_TOTAL_PUMP_HEART_LUNG_AUX(63869),
		NOM_TIME_PD_PLEGIA_PUMP_HEART_LUNG_AUX(63870), NOM_TIME_PD_PUMP_HEART_LUNG_CARDIOPLEGIA_MAIN_SINCE_START(63871),
		NOM_TIME_PD_PUMP_HEART_LUNG_CARDIOPLEGIA_MAIN_SINCE_STOP(63872),
		NOM_VOL_DELIV_PUMP_HEART_LUNG_CARDIOPLEGIA_MAIN(63873),
		NOM_VOL_DELIV_TOTAL_PUMP_HEART_LUNG_CARDIOPLEGIA_MAIN(63874),
		NOM_TIME_PD_PLEGIA_PUMP_HEART_LUNG_CARDIOPLEGIA_MAIN(63875),
		NOM_TIME_PD_PUMP_HEART_LUNG_CARDIOPLEGIA_SLAVE_SINCE_START(63876),
		NOM_TIME_PD_PUMP_HEART_LUNG_CARDIOPLEGIA_SLAVE_SINCE_STOP(63877),
		NOM_VOL_DELIV_PUMP_HEART_LUNG_CARDIOPLEGIA_SLAVE(63878),
		NOM_VOL_DELIV_TOTAL_PUMP_HEART_LUNG_CARDIOPLEGIA_SLAVE(63879),
		NOM_TIME_PD_PLEGIA_PUMP_HEART_LUNG_CARDIOPLEGIA_SLAVE(63880), NOM_RATIO_INSP_TOTAL_BREATH_SPONT(63888),
		NOM_VENT_PRESS_AWAY_END_EXP_POS_TOTAL(63889), NOM_COMPL_LUNG_PAV(63890), NOM_RES_AWAY_PAV(63891),
		NOM_RES_AWAY_EXP_TOTAL(63892), NOM_ELAS_LUNG_PAV(63893), NOM_BREATH_RAPID_SHALLOW_INDEX_NORM(63894);

		// declaring private variable for getting values
		private int v;

		// getter method
		public int getValue() {
			return this.v;
		}

		// enum constructor - cannot be public or protected
		private AlertSource(int value) {
			this.v = value;
		}
		
        public boolean Compare(int i){return v == i;}
        public static AlertSource GetValue(int id)
        {
        	AlertSource[] As = AlertSource.values();
            for(int i = 0; i < As.length; i++)
            {
                if(As[i].Compare(id))
                    return As[i];
            }
            return null;
        }
	}

}