package com.kursach.utils;

import java.nio.charset.Charset;

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
}
