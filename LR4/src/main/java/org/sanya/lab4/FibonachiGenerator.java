package org.sanya.lab4;

public class FibonachiGenerator implements RandomGenerator<Integer> {
    Integer lagA, lagB;
    RandomGenerator<Integer> randomGenerator;

    public FibonachiGenerator(Integer lagA, Integer lagB, RandomGenerator<Integer> randomGenerator) {
        this.randomGenerator = randomGenerator;
        this.lagA = lagA;
        this.lagB = lagB;


    }

    @Override
    public Integer next() {
        //Генерируем случ. величины
        var startSequence = generateSequence();
        Integer Res = generateValue(startSequence);
        return Res;
    }

    @Override
    public void setSeed(Integer seed) {
        randomGenerator.setSeed(seed);
    }

    private Integer generateValue(Integer[] startSequence) {
        int Res;
        if (startSequence[Math.max(lagA, lagB) - lagA] >= startSequence[Math.max(lagA, lagB) - lagB]) {
            Res = startSequence[Math.max(lagA, lagB) - lagA] - startSequence[Math.max(lagA, lagB) - lagB];
        } else
            Res = startSequence[Math.max(lagA, lagB) - lagB] - startSequence[Math.max(lagA, lagB) - lagA];
        return Res;
    }


    private Integer[] generateSequence() {
        Integer[] sequence = new Integer[Math.max(lagA, lagB)];
        for (int i = 0; i < Math.max(lagA, lagB); i++) {
            sequence[i] = randomGenerator.next();
        }
        return sequence;
    }
}
