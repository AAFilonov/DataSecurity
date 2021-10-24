package org.sanya.lab4;

import org.sanya.utils.ByteUtils;

public class GammaGenerator {
    RandomGenerator<Integer> randomGenerator;
    private int gammaIndex = 0;
    private byte[] roundGamma;

    public GammaGenerator(RandomGenerator<Integer> randomGenerator) {
        this.randomGenerator = randomGenerator;
        startNewRound();
        roundGamma = ByteUtils.toBytes(randomGenerator.next());
    }

    public byte getGammaByte() {
        if (gammaIndex < roundGamma.length ) {

            return roundGamma[gammaIndex++];

        } else {
            startNewRound();
            return roundGamma[gammaIndex++];
        }

    }

    private void startNewRound() {
        gammaIndex = 0;
        roundGamma = ByteUtils.toBytes(randomGenerator.next());
    }
}
