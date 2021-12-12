package com.kursach.crypto.RS6;

import com.kursach.utils.ByteUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class RC6EncoderTest {
    private final RC6Encoder encoder =new RC6Encoder(new RoundRunner(),new KeyInitializer());

    private  String expectedResult = "10010101 11010010 10001110 01101111 00011001 10100111 10001110 00011110 01000101 00111011 10010000 10010101 10110011 01001000 10111100 00111000 ";

    @Test
    void testConvertBlock() {
        var input_data = "some really long string with many many bytes";
        //  var input_bytes = StringToByteConverter.toBytes(input_data);
        var input_bytes = new byte[]{
                (byte) 255, (byte) 127, (byte) 63, (byte) 63,
                (byte) 127, (byte) 127, (byte) 63, (byte) 63,
                (byte) 127, (byte) 127, (byte) 63, (byte) 63,
                (byte) 127, (byte) 127, (byte) 63, (byte) 63
        };


        System.out.println(Arrays.toString(input_bytes));
        System.out.println(ByteUtils.toUnSingedBinaryString(input_bytes));

        var output_bytes = encoder.convertBlock(input_bytes);
        var output_bytes_str = ByteUtils.toUnSingedBinaryString(output_bytes);


        System.out.println(output_bytes_str);
        System.out.println(Arrays.toString(output_bytes));

      //  assertEquals(expectedResult,output_bytes_str);
    }
}