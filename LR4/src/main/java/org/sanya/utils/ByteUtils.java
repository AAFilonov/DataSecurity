package org.sanya.utils;

import java.io.ByteArrayOutputStream;
import java.util.BitSet;

public class ByteUtils {
    public static String toBinaryString(byte[] bytes) {
        String output = "";
        for (byte aByte : bytes) {
            output += Integer.toBinaryString(aByte) + " ";
        }
        return output;
    }


    public  static  String toBinaryString(byte i){
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
        String check = toBinaryString(bytes);
        return bytes;
        //return new byte[]{
        //        (byte) ((val >> 24) & 0xff),
        //        (byte) ((val >> 16) & 0xff),
        //        (byte) ((val >> 8) & 0xff),
        //        (byte) ((val >> 0) & 0xff),
        //};
        //return
    }

    private static BitSet convertIntToBitset(String binary) {

        char[] bits = binary.toCharArray();
        BitSet bitSet = new BitSet();
        for (int i = 0; i < bits.length; i++) {
            bitSet.set(i, bits[i] == '0');
        }
        return bitSet;
    }

    public static byte[] toByteArray(BitSet bits) {
        byte[] bytes = new byte[(bits.length() + 7) / 8];
        for (int i = 0; i < bits.length(); i++) {
            if (bits.get(i)) {
                bytes[bytes.length - i / 8 - 1] |= 1 << (i % 8);
            }
        }
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
