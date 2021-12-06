package org.sanya.lab4;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FibonachiGenerator implements RandomGenerator<Double,Integer> {
    Integer lagA, lagB;
    Double multiplicationConstant;
    RandomGenerator<Integer,Integer> randomGenerator;

    public FibonachiGenerator(Integer lagA, Integer lagB, RandomGenerator<Integer,Integer> randomGenerator, Double multiplicationConstant) {
        this.randomGenerator = randomGenerator;
        this.lagA = lagA;
        this.lagB = lagB;
        this.multiplicationConstant = multiplicationConstant;
    }

    public FibonachiGenerator(Integer lagA, Integer lagB, RandomGenerator<Integer,Integer> randomGenerator) {
        this.randomGenerator = randomGenerator;
        this.lagA = lagA;
        this.lagB = lagB;
        this.multiplicationConstant = Math.pow(10,9);//multiplicationConstant;
    }

    @Override
    public Double next() {
        //Генерируем случ. величины
        var startSequence = generateSequence();
        List<Double> startSequenceDouble = Arrays.stream(startSequence)
                .map(value -> value / Math.pow(10,9))
                .collect(Collectors.toList());
        Double Res = generateValue(startSequenceDouble);
        return Res;
    }

    public double nextDouble() {
        return next();
    }
    public int nextInt() {
        return (int)(next()*multiplicationConstant);
    }
    @Override
    public void setSeed(Integer seed) {
        randomGenerator.setSeed(seed);
    }

    private Double generateValue(List<Double> startSequence) {
        Double Res;
        if (startSequence.get(Math.max(lagA, lagB) - lagA) >= startSequence.get(Math.max(lagA, lagB) - lagB)) {
            Res = startSequence.get(Math.max(lagA, lagB) - lagA) - startSequence.get(Math.max(lagA, lagB) - lagB);
        } else
            Res = startSequence.get(Math.max(lagA, lagB) - lagB) - startSequence.get(Math.max(lagA, lagB) - lagA);
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
