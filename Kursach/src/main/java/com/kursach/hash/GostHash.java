package com.kursach.hash;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

import static com.kursach.hash.HashUtils.*;

public class GostHash implements Hash {
    private final static Logger log = LoggerFactory.getLogger(GostHash.class);
    private final GostContext context;

    public GostHash(){
        context = new GostContext(GostR3411_94_CryptoProParamSet);
    }

    /* Substitution blocks for hash function 1.2.643.2.9.1.6.1  */
    private final static SubstitutionBlock GostR3411_94_CryptoProParamSet = new SubstitutionBlock();

    static {
        byte[] k8 = new byte[]{0x1, 0x3, 0xA, 0x9, 0x5, 0xB, 0x4, 0xF, 0x8, 0x6, 0x7, 0xE, 0xD, 0x0, 0x2, 0xC};
        byte[] k7 = new byte[]{0xD, 0xE, 0x4, 0x1, 0x7, 0x0, 0x5, 0xA, 0x3, 0xC, 0x8, 0xF, 0x6, 0x2, 0x9, 0xB};
        byte[] k6 = new byte[]{0x7, 0x6, 0x2, 0x4, 0xD, 0x9, 0xF, 0x0, 0xA, 0x1, 0x5, 0xB, 0x8, 0xE, 0xC, 0x3};
        byte[] k5 = new byte[]{0x7, 0x6, 0x4, 0xB, 0x9, 0xC, 0x2, 0xA, 0x1, 0x8, 0x0, 0xE, 0xF, 0xD, 0x3, 0x5};
        byte[] k4 = new byte[]{0x4, 0xA, 0x7, 0xC, 0x0, 0xF, 0x2, 0x8, 0xE, 0x1, 0x6, 0x5, 0xD, 0xB, 0x9, 0x3};
        byte[] k3 = new byte[]{0x7, 0xF, 0xC, 0xE, 0x9, 0x4, 0x1, 0x0, 0x3, 0xB, 0x5, 0x2, 0x6, 0xA, 0x8, 0xD};
        byte[] k2 = new byte[]{0x5, 0xF, 0x4, 0x0, 0x2, 0xD, 0xB, 0x9, 0x1, 0x7, 0x6, 0x3, 0xC, 0xE, 0xA, 0x8};
        byte[] k1 = new byte[]{0xA, 0x4, 0x5, 0x6, 0x8, 0x1, 0x3, 0x7, 0xD, 0xC, 0xE, 0x0, 0x9, 0x2, 0xB, 0xF};

        System.arraycopy(k8, 0, GostR3411_94_CryptoProParamSet.k8, 0, 16);
        System.arraycopy(k7, 0, GostR3411_94_CryptoProParamSet.k7, 0, 16);
        System.arraycopy(k6, 0, GostR3411_94_CryptoProParamSet.k6, 0, 16);
        System.arraycopy(k5, 0, GostR3411_94_CryptoProParamSet.k5, 0, 16);
        System.arraycopy(k4, 0, GostR3411_94_CryptoProParamSet.k4, 0, 16);
        System.arraycopy(k3, 0, GostR3411_94_CryptoProParamSet.k3, 0, 16);
        System.arraycopy(k2, 0, GostR3411_94_CryptoProParamSet.k2, 0, 16);
        System.arraycopy(k1, 0, GostR3411_94_CryptoProParamSet.k1, 0, 16);
    }


    public byte[] calcHash(InputStream input) {
        try {
            final byte[] buf = new byte[1024];
            context.startHash();
            while (true) {
                final int len = input.read(buf);
                if (len < 1)
                    break;
                context.hashBlock(buf, 0, len);
            }
            return context.finishHash();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to read bytes from input", e);
        }
    }
}