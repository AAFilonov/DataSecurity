package com.kursach.kursach.service;

import com.kursach.hash.GostHashJava;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static com.kursach.utils.StringToByteConverter.bytesToHex;

@Service
public class HashService {
    private static GostHashJava javaHash = new GostHashJava();


    public String calcHash(String data) {
        var data_bytes = data.getBytes(StandardCharsets.UTF_8);
        javaHash.init();
        var bytes = javaHash.calcHash(new ByteArrayInputStream(data_bytes));
        javaHash.finishHash();
        return bytesToHex(bytes);
    }

    public static byte[] calcHash(File file) {
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            javaHash.init();
            var output =  javaHash.calcHash(fileInputStream);
            javaHash.finishHash();
            return output;
        } catch (IOException x) {
            throw new RuntimeException("Failed to create FileInputStream", x);
        }
    }


}
