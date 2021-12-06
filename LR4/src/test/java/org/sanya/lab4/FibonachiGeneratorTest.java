package org.sanya.lab4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.sanya.utils.ByteUtils;

import static java.lang.Math.pow;

class FibonachiGeneratorTest {
    static FibonachiGenerator fibonachiGenerator;
    static GammaGenerator gammaGenerator;

    @BeforeAll
    public static void setUp() {
        CongruentGenerator congruentGenerator = new CongruentGenerator(0);
        fibonachiGenerator = new FibonachiGenerator(17, 5, congruentGenerator);

    }

    @RepeatedTest(100)
    public void testGenerate() {
        var value = fibonachiGenerator.next();
        System.out.println(value);
        Assertions.assertTrue(value<1.0);
    }

    @RepeatedTest(40)
    public void testGenerateBytes() {
        int value = fibonachiGenerator.nextInt();
        System.out.println(value);
        var byteValue = ByteUtils.toBytes(value);
        System.out.println(ByteUtils.toBinaryString(byteValue));

    }
}