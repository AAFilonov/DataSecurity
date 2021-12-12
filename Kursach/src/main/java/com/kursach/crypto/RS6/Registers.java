package com.kursach.crypto.RS6;

public class Registers {
    public int A, B, C, D;
    Registers(byte[] block){
        init(block);
    }

    public void init(byte[] block) {
        int index = 0;
        this.A = ((block[index++] & 0xff) | (block[index++] & 0xff) << 8 | (block[index++] & 0xff) << 16 | (block[index++] & 0xff) << 24);
        this.B = ((block[index++] & 0xff) | (block[index++] & 0xff) << 8 | (block[index++] & 0xff) << 16 | (block[index++] & 0xff) << 24);
        this.C = ((block[index++] & 0xff) | (block[index++] & 0xff) << 8 | (block[index++] & 0xff) << 16 | (block[index++] & 0xff) << 24);
        this.D = ((block[index++] & 0xff) | (block[index++] & 0xff) << 8 | (block[index++] & 0xff) << 16 | (block[index++] & 0xff) << 24);
    }

    public byte[] toByteArray(int wordSize) {
        int[] data = new int[4];
        byte[] text = new byte[wordSize / 2];

        data[0] = this.A;
        data[1] = this.B;
        data[2] = this.C;
        data[3] = this.D;

        for (int i = 0; i < text.length; i++) {
            text[i] = (byte) ((data[i / 4] >>> (i % 4) * 8) & 0xff);
        }
        return text;
    }

}
