package org.sanya.utils;

import java.nio.ByteBuffer;

public class ByteUtils {
    public static String toBinaryString(byte[] bytes) {
        String output = "";
        for (byte aByte : bytes) {
            output += Integer.toBinaryString(aByte) + " ";
        }
        return output;
    }

    public static byte[] toBytes(Integer val) {
     //   return ByteBuffer.allocate(4).putInt(val).array();
        return new byte[] {
                (byte)((val >> 24) & 0xff),
                (byte)((val >> 16) & 0xff),
                (byte)((val >> 8) & 0xff),
                (byte)((val >> 0) & 0xff),
        };
    }
}
