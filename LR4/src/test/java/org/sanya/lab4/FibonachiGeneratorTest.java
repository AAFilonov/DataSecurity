package org.sanya.lab4;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.sanya.utils.ByteUtils;

class FibonachiGeneratorTest {
    static FibonachiGenerator fibonachiGenerator;

    @BeforeAll
    public static void setUp() {
        CongruentGenerator congruentGenerator = new CongruentGenerator(0);
        fibonachiGenerator = new FibonachiGenerator(17, 5, congruentGenerator);

    }

    @RepeatedTest(10)
    public void testGenerate() {
        System.out.println(fibonachiGenerator.next());
    }

    @RepeatedTest(40)
    public void testGenerateBytes() {
        var value = fibonachiGenerator.next();
        System.out.println(value);
        var byteValue = ByteUtils.toBytes(value);
        System.out.println(ByteUtils.toBinaryString(byteValue));

    }
}