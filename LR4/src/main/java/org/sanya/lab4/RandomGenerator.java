package org.sanya.lab4;

public interface RandomGenerator<T> {
    public T next();
    public void setSeed(T seed);
}
