package org.sanya.lab4;

public interface RandomGenerator<T,S> {
    public T next();
    public void setSeed(S seed);
}
