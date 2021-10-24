package org.sanya.lab4;


import org.junit.jupiter.api.Test;
import org.sanya.utils.IOUtils;

import java.nio.charset.Charset;

class StringEncoderTest {

    @Test
    public void testEncode() {
        ByteEncoder byteEncoder = new ByteEncoder();
        StringEncoder encoder = new StringEncoder(byteEncoder, Charset.defaultCharset());

        var bytes = encoder.encodeToBytes("someData");
        System.out.println(IOUtils.toBinaryString(bytes));


        //Assertions.assertEquals(bytes, "someData");
    }

}