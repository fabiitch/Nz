package com.fabiitch.nz.java.event;
@FunctionalInterface
public interface Listener<T> {
    void onEvent(T t);
}
