package com.kursach.hash;

import static com.kursach.hash.HashUtils.*;

public final class GostContext {
    long len = 0;
    Encryptor encryptor = null;
    int left = 0;
    final byte H[] = new byte[32];
    final byte S[] = new byte[32];
    final byte remainder[] = new byte[32];


    public  GostContext(SubstitutionBlock subst_block) {
        len = 0;
        encryptor = null;
        left = 0;
        java.util.Arrays.fill(H, (byte) 0);
        java.util.Arrays.fill(S, (byte) 0);
        java.util.Arrays.fill(remainder, (byte) 0);

        encryptor = new Encryptor(subst_block);

    }


    final void startHash() {
        java.util.Arrays.fill(H, (byte) 0);
        java.util.Arrays.fill(S, (byte) 0);
        len = 0L;
        left = 0;
    }


    final void hashBlock(byte[] block, int pos, int length) {
        final int lastPos = pos + length;
        if (left > 0) {
            int addBytes = 32 - left;
            if (addBytes > length)
                addBytes = length;
            System.arraycopy(block, pos, remainder, left, addBytes);
            left += addBytes;
            if (left < 32)
                return;
            pos += addBytes;
            hashStep(H, remainder, 0);
            addBlocks(32, S, remainder, 0);
            len += 32;
            left = 0;
        }

        while (lastPos - pos >= 32) {
            hashStep(H, block, pos);
            addBlocks(32, S, block, pos);
            len += 32;
            pos += 32;
        }

        if (pos != length) {
            left = lastPos - pos;
            System.arraycopy(block, pos, remainder, 0, left);
        }
    }

    /**
     * Compute hash value from current state of ctx
     * state of hash ctx becomes invalid and cannot be used for further
     * hashing.
     */
    final byte[] finishHash() {
        final byte[] buf = new byte[32];
        final byte[] xH = new byte[32];
        final byte[] xS = new byte[32];
        long fin_len = len;

        System.arraycopy(H, 0, xH, 0, 32);
        System.arraycopy(S, 0, xS, 0, 32);
        java.util.Arrays.fill(buf, (byte) 0);

        if (left > 0) {
            System.arraycopy(remainder, 0, buf, 0, left);
            hashStep(xH, buf, 0);
            addBlocks(32, xS, buf, 0);
            fin_len += left;
            java.util.Arrays.fill(buf, (byte) 0);
        }

        fin_len <<= 3; /* Hash length in BITS!!*/
        int bptr = 0;
        while (fin_len > 0) {
            buf[bptr++] = (byte) (fin_len & 0xFF);
            fin_len >>>= 8;
        }

        hashStep(xH, buf, 0);
        hashStep(xH, xS, 0);
        return xH;
    }

    final void hashStep(byte[] H, byte[] m, int mstart) {
        final byte[] xU = new byte[32];
        final byte[] xW = new byte[32];
        final byte[] xV = new byte[32];
        final byte[] xS = new byte[32];
        final byte[] Key = new byte[32];

        computeFirstKey(H, m, mstart, xW, Key);
        encryptor.gostEncrypt(Key, H, 0, xS, 0);

        computeSecondKey(H, m, mstart, xU, xW, xV, Key);
        encryptor.gostEncrypt(Key, H, 8, xS, 8);

        computeThirdKey(xU, xW, xV, Key);
        encryptor.gostEncrypt(Key, H, 16, xS, 16);

        computeFourthKey(xU, xW, xV, Key);
        encryptor.gostEncrypt(Key, H, 24, xS, 24);

        confoundTransform(H, m, mstart, xS);
    }

    private void computeFirstKey(byte[] xH, byte[] xM, int mstart, byte[] xW, byte[] key) {

        xor_blocks(xW, xH, xM, mstart, 32);
        swap_bytes(xW, key);
    }

    private void computeSecondKey(byte[] xH, byte[] xM, int mstart, byte[] xU, byte[] xW, byte[] xV, byte[] Key) {
        circle_xor8(xH, xU);
        circle_xor8(xM, mstart, xV);
        circle_xor8(xV, xV);
        xor_blocks(xW, xU, xV, 0, 32);
        swap_bytes(xW, Key);
    }

    private void computeThirdKey(byte[] xU, byte[] xW, byte[] xV, byte[] Key) {
        circle_xor8(xU, xU);

        xU[31] = (byte) ~xU[31];
        xU[29] = (byte) ~xU[29];
        xU[28] = (byte) ~xU[28];
        xU[24] = (byte) ~xU[24];
        xU[23] = (byte) ~xU[23];
        xU[20] = (byte) ~xU[20];
        xU[18] = (byte) ~xU[18];
        xU[17] = (byte) ~xU[17];
        xU[14] = (byte) ~xU[14];
        xU[12] = (byte) ~xU[12];
        xU[10] = (byte) ~xU[10];
        xU[8] = (byte) ~xU[8];
        xU[7] = (byte) ~xU[7];
        xU[5] = (byte) ~xU[5];
        xU[3] = (byte) ~xU[3];
        xU[1] = (byte) ~xU[1];

        circle_xor8(xV, xV);
        circle_xor8(xV, xV);
        xor_blocks(xW, xU, xV, 0, 32);
        swap_bytes(xW, Key);
    }

    private void computeFourthKey(byte[] xU, byte[] xW, byte[] xV, byte[] Key) {
        circle_xor8(xU, xU);
        circle_xor8(xV, xV);
        circle_xor8(xV, xV);
        xor_blocks(xW, xU, xV, 0, 32);
        swap_bytes(xW, Key);
    }

    private void confoundTransform(byte[] xH, byte[] xM, int mstart, byte[] xS) {
        int i;
        for (i = 0; i < 12; i++)
            transform_3(xS);
        xor_blocks(xS, xS, xM, mstart, 32);
        transform_3(xS);
        xor_blocks(xS, xS, xH, 0, 32);
        for (i = 0; i < 61; i++)
            transform_3(xS);
        System.arraycopy(xS, 0, xH, 0, 32);
    }
}
