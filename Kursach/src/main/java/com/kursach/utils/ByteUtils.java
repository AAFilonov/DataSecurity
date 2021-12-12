package com.kursach.utils;

import java.io.ByteArrayOutputStream;

public class ByteUtils {
    public static String toUnSingedBinaryString(byte[] bytes) {
        String output = "";
        for (byte aByte : bytes) {
            output += toUnSingedBinaryString(aByte) + " ";
        }
        return output;
    }
    public static String toSingedBinaryString(byte[] bytes) {
        String output = "";
        for (byte aByte : bytes) {
            output += toSingedBinaryString(aByte) + " ";
        }
        return output;
    }

    private static String toUnSingedBinaryString(byte i) {
        if (i < 0) {
            i = (byte) (i & (byte) 127);
            return "1" + String.format("%7s", Integer.toBinaryString(i)).replace(' ', '0');
        } else
            return String.format("%8s", Integer.toBinaryString(i)).replace(' ', '0');
    }

    private static String toSingedBinaryString(byte i) {

        return String.format("%8s", Integer.toBinaryString(i)).replace(' ', '0');
    }

    public static byte[] toBytes(Integer val) {

        var str = Integer.toBinaryString(val);
        var reversedStr = new StringBuilder(str).reverse().toString();
        String[] substrings = reversedStr.split("(?<=\\G.{7})");

        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        for (String s : substrings) {
            byte b = Byte.parseByte(s, 2);
            byteBuffer.write(b);
        }

        var bytes = byteBuffer.toByteArray();
        reverse(bytes);
        String check = toUnSingedBinaryString(bytes);
        return bytes;
    }

    public static void reverse(byte[] array) {
        if (array == null) {
            return;
        }
        int i = 0;
        int j = array.length - 1;
        byte tmp;
        while (j > i) {
            tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
            i++;
        }
    }
}
