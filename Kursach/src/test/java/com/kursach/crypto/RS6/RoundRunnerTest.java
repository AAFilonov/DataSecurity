package com.kursach.crypto.RS6;

import com.kursach.utils.ByteUtils;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;


class RoundRunnerTest {
    private final RoundRunner roundRunner = new RoundRunner();
    private final String expectedResult = "10010101 11010010 10001110 01101111 00011001 10100111 10001110 00011110 01000101 00111011 10010000 10010101 10110011 01001000 10111100 00111000 ";

    @Test
    void testRunRound() {
        var input_data = "some really long string with many many bytes";
        //  var input_bytes = StringToByteConverter.toBytes(input_data);
        var input_bytes = new byte[]{
                (byte) 255, (byte) 255, (byte) 255, (byte) 255,
                (byte) 255, (byte) 255, (byte) 255, (byte) 255,
                (byte) 0, (byte) 0, (byte) 0, (byte) 0,
                (byte) 0, (byte) 0, (byte) 0, (byte) 0
        };
        var round_key = new int[]{(byte) 255,(byte) 127};

        var round_key_bytes = new byte[]{(byte) 255,(byte) 127};

        System.out.println("Вход массив : "+Arrays.toString(input_bytes));
        System.out.println("Вход биты   : "+ByteUtils.toUnSingedBinaryString(input_bytes));
        System.out.println("Ключ массив : "+Arrays.toString(round_key));
        System.out.println("Ключ биты   : "+ByteUtils.toUnSingedBinaryString(round_key_bytes));

        var output_bytes = roundRunner.runRoundCommented(new Registers(input_bytes),round_key[0],round_key[1]).toByteArray(32);
        var output_bytes_str = ByteUtils.toUnSingedBinaryString(output_bytes);


         System.out.println("Выход биты  : "+output_bytes_str);
         System.out.println("Выход массив: "+Arrays.toString(output_bytes));

      //  assertEquals(expectedResult,output_bytes_str);
    }


}