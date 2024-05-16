package com.github.fabiitch.nz.java.data;

public class Pair<T, K> {
    public T key;
    public K value;

    public Pair(T key, K value) {
        this.key = key;
        this.value = value;
    }

    public Pair() {
    }

    public static <T, K> Pair of(T key, K value) {
       return new Pair(key, value);
    }

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public K getValue() {
        return value;
    }

    public void setValue(K value) {
        this.value = value;
    }
}
