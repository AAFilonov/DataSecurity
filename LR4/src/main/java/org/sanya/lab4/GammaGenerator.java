package org.sanya.lab4;

import org.sanya.utils.ByteUtils;

public class GammaGenerator {
    RandomGenerator<Double,Integer> randomGenerator;
    private int gammaIndex = 0;
    private byte[] roundGamma;

    public GammaGenerator(RandomGenerator<Double,Integer> randomGenerator) {
        this.randomGenerator = randomGenerator;
        startNewRound();
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
        roundGamma = getNewGamma();
    }

    private  byte[] getNewGamma(){
        double generatedValue = randomGenerator.next();
        int integerValue =(int)(generatedValue*Math.pow(10,7));
        return ByteUtils.toBytes(integerValue);
    }
}
