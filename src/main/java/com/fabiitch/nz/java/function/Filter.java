package com.fabiitch.nz.java.function;

@FunctionalInterface
public interface Filter<T> {

    boolean accept(T t);
}
