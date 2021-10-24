package org.sanya.utils;

import java.nio.charset.Charset;

public class ByteConverter {
    Charset charset;

    public ByteConverter(Charset charset) {
        this.charset = charset;
    }

    public byte[] toBytes(String data) {
        return data.getBytes(charset);
    }

    public String fromBytes(byte[] bytes) {
        return new String(bytes, charset);
    }
}
