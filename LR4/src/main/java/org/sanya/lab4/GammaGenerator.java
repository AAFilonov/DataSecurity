package org.sanya.lab4;

import org.sanya.utils.ByteUtils;

public class GammaGenerator {
    RandomGenerator<Integer> randomGenerator;
    int gammaIndex = 0;
    private byte[] roundGamma;

    public GammaGenerator(RandomGenerator<Integer> randomGenerator) {
        this.randomGenerator = randomGenerator;
        startNewRound();
        roundGamma = ByteUtils.toBytes(randomGenerator.next());
    }

    public byte getGammaByte() {
        if (gammaIndex < roundGamma.length ) {
            var gammaByte = roundGamma[gammaIndex];
            gammaIndex++;
            return gammaByte;

        } else {
            startNewRound();
            var gammaByte = roundGamma[gammaIndex];
            gammaIndex++;
            return gammaByte;
        }

    }

    private void startNewRound() {
        gammaIndex = 0;
       // roundGamma = ByteUtils.toBytes(randomGenerator.next());
    }
}
