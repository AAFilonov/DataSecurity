package com.kursach.hash;

import static com.kursach.hash.HashUtils.*;
import static com.kursach.hash.HashUtils.byte2uint;

//низкоуровневое шифронвание блока заданным ключем
public final class Encryptor {
    final int k[] = new int[8];
    /* Constant s-boxes -- set up in gost_init(). */
    final int k87[] = new int[256];
    final int k65[] = new int[256];
    final int k43[] = new int[256];
    final int k21[] = new int[256];

    Encryptor (SubstitutionBlock b) {
        for (int i = 0; i < 256; i++) {
            k87[i] = (b.k8[i >>> 4] << 4 | b.k7[i & 15]) << 24;
            k65[i] = (b.k6[i >>> 4] << 4 | b.k5[i & 15]) << 16;
            k43[i] = (b.k4[i >>> 4] << 4 | b.k3[i & 15]) << 8;
            k21[i] = b.k2[i >>> 4] << 4 | b.k1[i & 15];
        }
    }

    /* Part of GOST 28147 algorithm moved into separate function */
    final int f(int n, int x) {
        long tmp = int2ulong(n) + int2ulong(x);
        if (tmp >= TOP_UINT)
            tmp -= TOP_UINT;
        x = k87[((int) (tmp >>> 24)) & 255] |
                k65[((int) (tmp >>> 16)) & 255] |
                k43[((int) (tmp >>> 8)) & 255] |
                k21[((int) tmp) & 255];
        /* Rotate left 11 bits */
        return ((int) (int2ulong(x) << 11)) | x >>> (32 - 11);
    }

    /* Low-level encryption routine - encrypts one 64 bit block*/
    final void gostcrypt(byte[] in, int inpos, byte[] out, int outpos) {
        int n1, n2; /* As named in the GOST */
        n1 = byte2uint(in[inpos + 0]) |
                (byte2uint(in[inpos + 1]) << 8) |
                (byte2uint(in[inpos + 2]) << 16) |
                (byte2uint(in[inpos + 3]) << 24);
        n2 = byte2uint(in[inpos + 4]) |
                (byte2uint(in[inpos + 5]) << 8) |
                (byte2uint(in[inpos + 6]) << 16) |
                (byte2uint(in[inpos + 7]) << 24);
        /* Instead of swapping halves, swap names each round */

        n2 ^= f(n1, k[0]);
        n1 ^= f(n2, k[1]);
        n2 ^= f(n1, k[2]);
        n1 ^= f(n2, k[3]);
        n2 ^= f(n1, k[4]);
        n1 ^= f(n2, k[5]);
        n2 ^= f(n1, k[6]);
        n1 ^= f(n2, k[7]);

        n2 ^= f(n1, k[0]);
        n1 ^= f(n2, k[1]);
        n2 ^= f(n1, k[2]);
        n1 ^= f(n2, k[3]);
        n2 ^= f(n1, k[4]);
        n1 ^= f(n2, k[5]);
        n2 ^= f(n1, k[6]);
        n1 ^= f(n2, k[7]);

        n2 ^= f(n1, k[0]);
        n1 ^= f(n2, k[1]);
        n2 ^= f(n1, k[2]);
        n1 ^= f(n2, k[3]);
        n2 ^= f(n1, k[4]);
        n1 ^= f(n2, k[5]);
        n2 ^= f(n1, k[6]);
        n1 ^= f(n2, k[7]);

        n2 ^= f(n1, k[7]);
        n1 ^= f(n2, k[6]);
        n2 ^= f(n1, k[5]);
        n1 ^= f(n2, k[4]);
        n2 ^= f(n1, k[3]);
        n1 ^= f(n2, k[2]);
        n2 ^= f(n1, k[1]);
        n1 ^= f(n2, k[0]);

        out[outpos + 0] = (byte) (n2 & 0xff);
        out[outpos + 1] = (byte) ((n2 >>> 8) & 0xff);
        out[outpos + 2] = (byte) ((n2 >>> 16) & 0xff);
        out[outpos + 3] = (byte) (n2 >>> 24);
        out[outpos + 4] = (byte) (n1 & 0xff);
        out[outpos + 5] = (byte) ((n1 >>> 8) & 0xff);
        out[outpos + 6] = (byte) ((n1 >>> 16) & 0xff);
        out[outpos + 7] = (byte) (n1 >>> 24);
    }

    /* Set 256 bit  key into context */
    final void gostSetKey(byte[] xk) {
        int i, j;
        for (i = 0, j = 0; i < 8; i++, j += 4) {
            k[i] = byte2uint(xk[j]) |
                    (byte2uint(xk[j + 1]) << 8) |
                    (byte2uint(xk[j + 2]) << 16) |
                    (byte2uint(xk[j + 3]) << 24);
        }
    }

    /* Encrypts one block using specified key */
    final void gostEncrypt(byte[] key, byte[] inblock, int inpos, byte[] outblock, int outpos) {
        gostSetKey(key);
        gostcrypt(inblock, inpos, outblock, outpos);
    }
}
