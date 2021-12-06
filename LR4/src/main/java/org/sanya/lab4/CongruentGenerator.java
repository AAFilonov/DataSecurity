package org.sanya.lab4;

public class CongruentGenerator implements RandomGenerator<Integer,Integer> {
    private Integer a, b, m;
    private Integer lastValue;

    public CongruentGenerator(Integer seed) {
        this.a = 1140671485;
        this.b = 12820163;
        this.m = (int) Math.pow(2, 24);
        lastValue = seed;
    }

    public CongruentGenerator(Integer a, Integer b, Integer m, Integer seed) {
        this.a = a;
        this.b = b;
        this.m = m;
        lastValue = seed;
    }

    @Override
    public Integer next() {
        lastValue = (a * lastValue + b) % m;
        return lastValue;
    }

    @Override
    public void setSeed(Integer seed) {
        lastValue = seed;
    }

}
