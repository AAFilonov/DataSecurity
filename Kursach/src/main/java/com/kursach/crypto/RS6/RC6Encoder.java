package com.kursach.crypto.RS6;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RC6Encoder {
    private final RoundRunner roundRunner;
    private final KeyInitializer keyInitializer;


    @Value("${crypto.roundCount}")
    private Integer ROUND_COUNT;



    private static int r = 1;
    private static int w = 32;

    public RC6Encoder(RoundRunner roundRunner, KeyInitializer keyInitializer) {
        this.roundRunner = roundRunner;
        this.keyInitializer = keyInitializer;
    }


    public byte[] convertBlock(byte[] plainText) {

        Registers reg = new Registers(plainText);

        byte[] key = keyInitializer.getKey();
        int[] RoundKeys = keyInitializer.keyGen(key, r);

        reg.B += RoundKeys[0];
        reg.D += RoundKeys[1];

        for (int i = 1; i <= r; i++) {

            reg = roundRunner.runRound(reg, RoundKeys[2 * i], RoundKeys[2 * i + 1]);
        }
        reg.B += RoundKeys[2 * r + 2];
        reg.D += RoundKeys[2 * r + 3];
        return reg.toByteArray(w);
    }


}
