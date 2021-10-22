package org.sanya.lab4;

public class CongruentGenerator implements RandomGenerator<Integer> {
    private Integer a, b, m, Y0;
    private Integer lastValue;

    public CongruentGenerator(Integer a, Integer b, Integer m, Integer y0) {
        this.a = a;
        this.b = b;
        this.m = m;
        Y0 = y0;
        lastValue = Y0;
    }



    @Override
    public Integer next() {
        lastValue = (a * lastValue + b) % m;
        return lastValue;
    }
}
