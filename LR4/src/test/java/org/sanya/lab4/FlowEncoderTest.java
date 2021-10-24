package org.sanya.lab4;

import org.junit.jupiter.api.Test;
import org.sanya.utils.ByteConverter;

import java.nio.charset.Charset;

class FlowEncoderTest {

    @Test
    public void translateTest() {
        CongruentGenerator congruentGenerator = new CongruentGenerator(1664525, 1, (int) Math.pow(2, 24), 0);
        FibonachiGenerator fibonachiGenerator = new FibonachiGenerator(17, 5, congruentGenerator);
        ByteEncoder byteEncoder = new ByteEncoder(new GammaGenerator(fibonachiGenerator));
        var flowEncoder = new FlowEncoder(byteEncoder, new ByteConverter(Charset.defaultCharset()));
        flowEncoder.runTranslation();

    }

}