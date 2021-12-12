package com.kursach.crypto.RS6;

import com.kursach.utils.StringToByteConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;

@Component
class KeyInitializer {
    KeyInitializer() {
        initSecretKey();
    }
    @Value("${crypto.Pw}")
    private static int P_W = 0xB7E15163;

    @Value("${crypto.Qw}")
    private static int Q_W = 0x9E3779b9;

    @Value("${crypto.secretKey.value}")
    private String SECRET_KEY_VALUE = "someText";

    @Value("${crypto.secretKey.lengthByte}")
    private Integer SECRET_KEY_LENGTH = 4;


    @Value("${crypto.wordSize}")
    private static Integer wordSize=32;


    private byte[] secretKeyBytes = new byte[SECRET_KEY_LENGTH];


    byte[] getKey() {
        return secretKeyBytes;
    }

    private void initSecretKey() {
        var bytes = StringToByteConverter.toBytes(SECRET_KEY_VALUE);
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        bis.readNBytes(secretKeyBytes, 0, SECRET_KEY_LENGTH);
    }

    public  int[] keyGen(byte[] key, int amountOfRounds){
        int bytesInWord = wordSize / 8;
        int count = key.length / bytesInWord;
        int[] L = new int[count];
        int[] RoundKeys = new int[2 * amountOfRounds + 4];
        int index = 0;

        for(int i = 0; i < count; i++){
            L[i] = ((key[index++]) & 0xff | (key[index++] & 0xff) << 8 | (key[index++] & 0xff) << 16 | (key[index++] & 0xff) << 24);
        }
        RoundKeys[0] = P_W;

        for(int i = 1; i <= 2*amountOfRounds+3; i++){
            RoundKeys[i] = RoundKeys[i-1] + Q_W;
        }

        int A = 0, B = 0, i = 0,j =0;
        int v = 3 * Math.max(count, 2*amountOfRounds+4);

        for(int k = 1;k <= v; k++){
            A = RoundKeys[i] = rotateL(RoundKeys[i] + A + B, 3);
            B = L[j] = rotateL(L[j] + A + B, A+B);
            i = (i + 1) % (2 * amountOfRounds + 4);
            j = (j + 1) % count;
        };
        return RoundKeys;
    }
    private static int rotateL(int n, int x) {
        return ((n << x) | (n >>> (wordSize - x)));
    }

}

