package org.sanya.lab4;


import org.junit.jupiter.api.Test;
import org.sanya.utils.ByteUtils;
import org.sanya.utils.IOUtils;

import java.nio.charset.Charset;

class StringEncoderTest {

    @Test
    public void testEncode() {
        CongruentGenerator congruentGenerator = new CongruentGenerator(1664525, 1, (int) Math.pow(2, 24), 0);
        FibonachiGenerator fibonachiGenerator = new FibonachiGenerator(17, 5, congruentGenerator);
        ByteEncoder byteEncoder = new ByteEncoder(new GammaGenerator(fibonachiGenerator));
        StringEncoder encoder = new StringEncoder(byteEncoder, Charset.defaultCharset());

        var bytes = encoder.encodeToBytes("someData");
        System.out.println(ByteUtils.toBinaryString(bytes));


        //Assertions.assertEquals(bytes, "someData");
    }

}