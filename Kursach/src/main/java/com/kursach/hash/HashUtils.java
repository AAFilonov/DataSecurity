package com.kursach.hash;

public class HashUtils {
     final static long TOP_UINT = 0xFFFFFFFFL + 1L;
     final static int TOP_UBYTE = 0xFF + 1;

    static long int2ulong(int n) {
        if (n >= 0)
            return (long) n;
        return TOP_UINT + n;
    }



    static int byte2uint(byte n) {
        if (n >= 0)
            return (int) n;
        return TOP_UBYTE + n;
    }

    //----------------------------------------------------------------------------------

    /* Xor two sequences of bytes */
    static void xor_blocks(byte[] result, byte[] a, byte[] b, int bstart, int len) {
        int i;
        for (i = 0; i < len; ++i)
            result[i] = (byte) (a[i] ^ b[bstart + i]);
    }

    static void swap_bytes(byte[] w, byte[] k) {
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 8; ++j)
                k[i + 4 * j] = w[8 * i + j];
        }
    }

    /* was A_A */
    static void circle_xor8(byte[] w, byte[] k) {
        circle_xor8(w, 0, k);
    }

    static void circle_xor8(byte[] w, int wstart, byte[] k) {
        final byte[] buf = new byte[8];
        System.arraycopy(w, wstart, buf, 0, 8);
        System.arraycopy(w, wstart + 8, k, 0, 24);
        for (int i = 0; i < 8; ++i)
            k[i + 24] = (byte) (buf[i] ^ k[i]);
    }

    /* was R_R */
    static void transform_3(byte[] data) {
        final int acc =
                (byte2uint(data[0]) ^ byte2uint(data[2]) ^
                        byte2uint(data[4]) ^ byte2uint(data[6]) ^
                        byte2uint(data[24]) ^ byte2uint(data[30])) |
                        ((byte2uint(data[1]) ^ byte2uint(data[3]) ^
                                byte2uint(data[5]) ^ byte2uint(data[7]) ^
                                byte2uint(data[25]) ^ byte2uint(data[31])) << 8);
        System.arraycopy(data, 2, data, 0, 30);
        data[30] = (byte) (acc & 0xff);
        data[31] = (byte) (acc >>> 8);
    }

    /* Adds blocks of N bytes modulo 2**(8*n). Returns carry*/
    static int addBlocks(int n, byte[] left, byte[] right, int rightPos) {
        int i;
        int carry = 0;
        int sum;
        for (i = 0; i < n; i++) {
            sum = byte2uint(left[i]) + byte2uint(right[rightPos + i]) + carry;
            left[i] = (byte) (sum & 0xff);
            carry = sum >>> 8;
        }
        return carry;
    }

}
