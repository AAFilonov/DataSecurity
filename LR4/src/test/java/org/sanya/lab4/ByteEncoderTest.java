package org.sanya.lab4;


import org.junit.jupiter.api.Test;

import java.nio.charset.Charset;

class ByteEncoderTest {



    @Test
    public void testEncode() {
        CongruentGenerator congruentGenerator = new CongruentGenerator(1664525, 1, (int) Math.pow(2, 24), 0);
        FibonachiGenerator fibonachiGenerator = new FibonachiGenerator(17, 5, congruentGenerator);
        ByteEncoder byteEncoder = new ByteEncoder(new GammaGenerator(fibonachiGenerator));
        byte[] bytes = new byte[]{Integer.valueOf(32).byteValue()};
        byteEncoder.encode(bytes);


        //Assertions.assertEquals(bytes, "someData");
    }

}