package com.xiaoshujing.kid.common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;


/**
 * A class with support of various methods for writing different data types to
 * the underlying byte array.
 * 
 * @author Zhiping Zeng, Mozat(Singapore) Pte Ltd. clipse.zeng@gmail.com
 * 
 */
public class BytesWriter extends ByteArrayOutputStream {

	public BytesWriter() {
		super();
	}

	public BytesWriter(int size) {
		super(size);
	}

	/**
	 * Write an integer to the byte array occupying 4-bytes.
	 * 
	 * @param num
	 *            the input integer
	 */
	public void writeInt(int num) {
		write((byte) (0xff & (num >> 24)));
		write((byte) (0xff & (num >> 16)));
		write((byte) (0xff & (num >> 8)));
		write((byte) (0xff & num));
	}

	/**
	 * Write a long integer to the byte array occupying 8-bytes.
	 * 
	 * @param num
	 *            the input long integer
	 */
	public void writeLong(long num) {
		write((byte) (0xff & (num >> 56)));
		write((byte) (0xff & (num >> 48)));
		write((byte) (0xff & (num >> 40)));
		write((byte) (0xff & (num >> 32)));
		write((byte) (0xff & (num >> 24)));
		write((byte) (0xff & (num >> 16)));
		write((byte) (0xff & (num >> 8)));
		write((byte) (0xff & num));
	}

//    private void writeDouble(double num) throws IOException {
//		ByteBuffer buff = ByteBuffer.allocate(Double.SIZE / 8);
//		buff.putDouble(num);
//		write(buff.array());
//	}

	/**
	 * Write a short integer to the byte array occupying 2-bytes. Note that if
	 * num is greater than 65535, then only its lower 16 bits will be written to
	 * the byte array
	 * 
	 * @param num
	 *            the input integer
	 */
	public void writeShort(int num) {
		write((byte) (0xff & (num >> 8)));
		write((byte) (0xff & num));
	}

	/**
	 * Write a byte array to the underlying byte array with the length of this
	 * bytes before the bytes. Note that the length information is written as a
	 * 4-bytes integer. So do not misuse it to write data according to SmartView
	 * protocol, because in SmartView the length of information is represented
	 * by short integer.
	 * 
	 * 
	 * @param bts
	 *            the input byte array
	 */
//    private void writeWithLen(byte[] bts) {
//		this.writeInt(bts.length);
//		write(bts, 0, bts.length);
//	}

	/**
	 * Write a byte array occupying a fixed length of len. If the length of bts
	 * is greater than len, then the bytes with index larger than len will be
	 * ignored. If the length of bts is smaller than len, then 0 is written to
	 * the rest space.
	 * 
	 * @param bts
	 *            the input byte array
	 * @param len
	 *            length should be occupied
	 */
	public void writeFixedLen(byte[] bts, int len) {
		int i;

		if (null == bts) {
			i = len;
			while (i-- > 0)
				write(0);
		} else {
			write(bts, 0, bts.length);
			i = len - bts.length;
			if (i > 0) {
				while (i-- > 0)
					this.write(0);
			}
		}
	}

	/**
	 * The same as writeFixedLen, except the input parameter is a string.
	 * 
	 * @param str
	 *            the input String
	 * @param len
	 *            length should be occupied
	 */
	public void writeFixedLen(String str, int len) {
		byte[] buf = Util.toBytes(str);
		writeFixedLen(buf, len);
    }

	/**
	 * Write the str as a short string format defined in SmartView2
	 * 
	 * @param str
	 *            the string sequence
	 * @throws IOException
	 */
    @SuppressWarnings("unused")
    public void writeShortString(String str) throws IOException {
		if (str == null) {
			this.writeShort(0);
		} else {
			byte[] buf = Util.toBytes(str);
			writeShort(buf.length);
			write(buf);
        }
	}

//    private void writeTinyBytes(byte[] data) throws IOException {
//		if(data == null){
//			write(0);
//		} else {
//			write(data.length);
//			write(data);
//		}
//	}

//    private void writeShortBytes(byte[] data) throws IOException {
//		if (data == null) {
//			this.writeShort(0);
//		} else {
//			this.writeShort(data.length);
//			this.write(data);
//		}
//	}

	public void writeIntBytes(byte[] data) throws IOException {
		if (data == null) {
			this.writeInt(0);
		} else {
			this.writeInt(data.length);
			this.write(data);
		}
	}

    @SuppressWarnings("unused")
    public void writeIntString(String str) throws IOException {
		if (str == null) {
			writeInt(0);
		} else {
			byte[] buf = Util.toBytes(str);
			writeInt(buf.length);
			write(buf);
        }
	}

//	/**
//	 * Write the bytes as a varchar format defined in SmartView2
//	 *
//	 * @param buf
//	 *            the input data
//	 * @throws IOException
//	 */
//    private void writeVarChar(byte[] buf) throws IOException {
//		if (buf == null) {
//			this.write(0);
//		} else {
//			this.write(buf.length);
//			this.write(buf);
//		}
//	}

//	/**
//	 * The same as writeByte. But to make it more clear.
//	 *
//	 * @param cnt
//	 *            the count value
//	 */
//    private void writeCount(int cnt) {
//		this.write(cnt);
//	}

//	/**
//	 * The same as writeByte. But to make it more clear.
//	 *
//	 * @param tag
//	 *            the tag value
//	 */
//    private void writeTag(int tag) {
//		this.write(tag);
//	}

//    private void writeTLVLen(int len) {
//		this.writeShort(len);
//	}

	public void writeByte(int num) {
		this.write(num);
	}

	/**
	 * Write the string as a varchar format defined in SmartView2
	 * 
	 * @param str
	 *            the input string
	 * @throws IOException
	 */
    @SuppressWarnings("unused")
    public void writeVarChar(String str) throws IOException {
		if (str == null) {
			this.write(0);
		} else {
			byte[] buf = Util.toBytes(str);
			write(buf.length);
			write(buf);
        }
	}

	public void finish() {
		try {
			close();
		} catch (IOException e) {
			// e.printStackTrace();
		}
	}

//	/**
//	 * Write option fields according the format of TLVGroup
//	 * 
//	 * @param opts
//	 * @throws IOException
//	 */
//	public void writeTLVOptions(OptionFields opts) throws IOException {
//		if (opts == null) {
//			this.writeByte(0);
//			return;
//		}
//		int i = 0, num = opts.size();
//		OptionFields.OptionItem item = null;
//
//		write(num);
//		while (i < num) {
//			item = opts.options.get(i++);
//
//			writeTag(item.tag);
//			writeTLVLen(item.value.length);
//			write(item.value);
//			// switch (item.dataType) {
//			// case C.DATA_TYPE.DATA_BYTE:
//			// write(item.value);
//			// break;
//			// case C.DATA_TYPE.DATA_SHORT:
//			// this.writeShort(item.value);
//			// break;
//			// case C.DATA_TYPE.DATA_INTEGER:
//			// break;
//			//
//			// case C.DATA_TYPE.DATA_RAW_BYTES:
//			// break;
//			// case C.DATA_TYPE.DATA_RAW_STRING:
//			// break;
//			//
//			// case C.DATA_TYPE.DATA_SHORT_BYTES:
//			// break;
//			// case C.DATA_TYPE.DATA_SHORT_STRING:
//			// break;
//			//
//			// case C.DATA_TYPE.DATA_VARCHAR:
//			// break;
//			// case C.DATA_TYPE.DATA_STRING:
//			// break;
//			// }
//		}
//	}

//	public void writePairValues(MoPairValues values) throws IOException {
//		if (values == null)
//			return;
//		int i = 0, num = values.size();
//		MoPair item = null;
//
//		// write(num);
//		while (i < num) {
//			item = values.get(i++);
//
//			writeShortString(item.name);
//			// the following will not been invoked.
//			if (item.name.startsWith("mosnapshot:")
//					|| item.name.startsWith("mobinary:"))
//				write(item.value.getBytes());
//			else
//				writeShortString(item.value);
//		}
//	}
}
