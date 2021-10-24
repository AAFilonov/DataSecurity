package org.sanya.lab4;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;

class GammaGeneratorTest {
    private static GammaGenerator gammaGenerator;

    @BeforeAll
    public static void setUp() {
        CongruentGenerator congruentGenerator = new CongruentGenerator(1664525, 1, (int) Math.pow(2, 24), 1);
        FibonachiGenerator fibonachiGenerator = new FibonachiGenerator(17, 5, congruentGenerator);

        gammaGenerator = new GammaGenerator(fibonachiGenerator);
    }

    @RepeatedTest(10)
    public void testGenerate() {
        System.out.println("index:" +gammaGenerator.gammaIndex);
        System.out.println("byte:" +gammaGenerator.getGammaByte());
    }
}