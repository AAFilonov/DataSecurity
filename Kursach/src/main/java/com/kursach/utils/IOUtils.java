package com.kursach.utils;

import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.function.Supplier;

public class IOUtils {

    @SneakyThrows
    public static String read(String filename) {
        Path path = Paths.get(filename);
        var maybeS = Files.lines(path).reduce((s, s2) -> s + s2);

        return maybeS.orElseThrow((Supplier<Throwable>) () -> new Exception("Ошибка при чтении файла " + filename));
    }

    @SneakyThrows
    public static void write(String filename, String data) {
        Path path = Paths.get(filename);
        Files.write(path, Collections.singleton(data));
    }


}
