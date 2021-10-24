package org.sanya.lab4;

import org.junit.jupiter.api.Test;
import org.sanya.utils.ByteConverter;

import java.nio.charset.Charset;

class FlowEncoderTest {

    @Test
    public void translateTest() {
        var flowEncoder = new FlowEncoder(new ByteEncoder(), new ByteConverter(Charset.defaultCharset()));
        flowEncoder.runTranslation();

    }

}