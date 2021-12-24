package com.kursach.hash;

import lombok.SneakyThrows;

import java.nio.charset.StandardCharsets;

import static com.kursach.utils.StringToByteConverter.bytesToHex;

public class GostHashJavaWrapper {
    private static GostHashJava javaHash = new GostHashJava();

    public static byte[] hash(byte[] bytes) {
        try {
            javaHash.init();
            javaHash.startHash();
            javaHash.hashBlock(bytes, 0, bytes.length);
            return javaHash.finishHash();
        } finally {
            javaHash.done();
        }
    }
//TODO покрыть шаги вычсления хэша логированием
    @SneakyThrows
    public static String hash(String input) {
        var bytes = hash(input.getBytes("UTF-8"));
        return bytesToHex(bytes);
    }

}
