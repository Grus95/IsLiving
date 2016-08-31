package com.example.grus95.utilslibrary.array;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Created by grus95 on 16/8/31
 */
public class ArrayUtil {
	public static short[] byte2short(byte[] data) {
		ByteBuffer bb = ByteBuffer.wrap(data);
		short[] shorts = new short[data.length / 2];
		bb.order(ByteOrder.LITTLE_ENDIAN).asShortBuffer().get(shorts);
		return shorts;
	}

	public static byte[] short2byte(short[] data) {
		int length = data.length;
		ByteBuffer buffer = ByteBuffer.allocate(2 * length);
		for (int i = 0; i < length; i++) {
			buffer.putShort(data[i]);
		}
		buffer.flip();
		return buffer.array();
	}
}
