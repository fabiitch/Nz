package com.github.fabiitch.nz.java.function;

@FunctionalInterface
public interface Filter<T> {

    boolean accept(T t);
}
