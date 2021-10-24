package org.sanya.lab4;

public class ByteEncoder {
    RandomGenerator<Integer> gammaGenerator;

    private byte getGamma() {
        return gammaGenerator.next().byteValue();
    }


    public byte[] encode(byte[] bytes) {
        System.out.println(Integer.toBinaryString(5));

        return bytes;
    }

    public byte[] decode(byte[] bytes) {
        return bytes;
    }
}
