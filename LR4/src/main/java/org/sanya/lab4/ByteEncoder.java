package org.sanya.lab4;

import org.sanya.utils.ByteUtils;

public class ByteEncoder {
    GammaGenerator gammaGenerator;

    public ByteEncoder(GammaGenerator gammaGenerator) {
        this.gammaGenerator = gammaGenerator;
    }

    public byte[] encode(byte[] bytes) {
        byte[] encoded_bytes = new byte[bytes.length];
        for (int i = 0, bytesLength = bytes.length; i < bytesLength; i++) {
            encoded_bytes[i] = encodeByte(bytes[i], gammaGenerator.getGammaByte());
        }
        return encoded_bytes;
    }


    private byte encodeByte(byte aByte, byte gamma) {

        var result = (byte) (aByte ^ gamma);
        System.out.println(
                ByteUtils.toBinaryString(aByte)
                        + "^"
                        + ByteUtils.toBinaryString(gamma)
                        + "=="
                        + ByteUtils.toBinaryString(result));
        return result;


    }


    public byte[] decode(byte[] bytes) {
        return bytes;
    }
}
