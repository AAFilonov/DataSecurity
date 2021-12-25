package com.kursach.kursach.service;

import com.kursach.hash.GostHashJava;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final static Logger log = LoggerFactory.getLogger(HashService.class);

    public String calcHash(String data) {
        log.info("Data to hash: {}", data);
        var data_bytes = data.getBytes(StandardCharsets.UTF_8);
        javaHash.init();
        var bytes = javaHash.calcHash(new ByteArrayInputStream(data_bytes));
        javaHash.finishHash();
        log.info("Hashing result: {}", bytesToHex(bytes));
        return bytesToHex(bytes);
    }

    public static byte[] calcHash(File file) {
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            javaHash.init();
            var output = javaHash.calcHash(fileInputStream);
            javaHash.finishHash();
            return output;
        } catch (IOException x) {
            throw new RuntimeException("Failed to create FileInputStream", x);
        }
    }


}
