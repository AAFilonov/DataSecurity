package org.sanya.lab4;

import java.nio.charset.Charset;

public class StringEncoder {
    public StringEncoder(ByteEncoder byteEncoder, Charset charset) {
        this.byteEncoder = byteEncoder;
        this.charset = charset;
    }

    ByteEncoder byteEncoder;
    Charset charset;

    public String encode(String data) {
        byte[] encodedBytes = byteEncoder.encode(data.getBytes(charset));
        return new String(encodedBytes, charset);
    }

    public byte[] encodeToBytes(String data) {
        return byteEncoder.encode(data.getBytes(charset));
    }

    public String decode(String data) {
        byte[] decodedBytes = byteEncoder.decode(data.getBytes(charset));
        return new String(decodedBytes, charset);
    }

    public byte[] decodeToBytes(String data) {
        return byteEncoder.decode(data.getBytes(charset));
    }
}
