package org.sanya.lab4;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.sanya.utils.ByteUtils;

class CongruentGeneratorTest {
    static CongruentGenerator congruentGenerator;

    @BeforeAll
    public static void setUp() {
        congruentGenerator = new CongruentGenerator(0);


    }

    @RepeatedTest(10)
    public void testGenerate() {
        System.out.println(congruentGenerator.next());
    }

    @RepeatedTest(40)
    public void testGenerateBytes() {
        var value = congruentGenerator.next();
        System.out.println(value);
        var byteValue = ByteUtils.toBytes(value);
        System.out.println(ByteUtils.toBinaryString(byteValue));

    }
}