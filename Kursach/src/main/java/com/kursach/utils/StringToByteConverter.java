package com.kursach.utils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class StringToByteConverter {
    static Charset charset = Charset.defaultCharset();

    public StringToByteConverter(Charset charset) {
        this.charset = charset;
    }

    public static byte[] toBytes(String data) {
        return data.getBytes(charset);
    }

    public static  String fromBytes(byte[] bytes) {
        return new String(bytes, charset);
    }



    private static final byte[] HEX_ARRAY = "0123456789abcdef".getBytes(StandardCharsets.US_ASCII);
    public static String bytesToHex(byte[] bytes) {
        byte[] hexChars = new byte[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars, StandardCharsets.UTF_8);
    }
}
