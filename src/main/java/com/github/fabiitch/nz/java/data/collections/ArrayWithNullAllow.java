package com.github.fabiitch.nz.java.data.collections;

//TODO a voir si utile

/**
 * Remove set index to null, and dont resize array
 * size dont count null values, for each dont give null values
 * @param <T>
 */
public class ArrayWithNullAllow<T> {
    public T[] array;
    public int size;

    public ArrayWithNullAllow() {
        this(16);
    }

    /**
     * Creates an ordered array with the specified capacity.
     */
    public ArrayWithNullAllow(int capacity) {
        array = (T[]) new Object[capacity];
    }
    public void add(T value){

    }
}
