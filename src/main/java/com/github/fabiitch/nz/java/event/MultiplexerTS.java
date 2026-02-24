package com.github.fabiitch.nz.java.event;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MultiplexerTS<T> {

    protected final List<T> listeners = new CopyOnWriteArrayList<>();

    public void addListener(T t) {
        listeners.add(t);
    }

    public void removeListener(T t) {
        listeners.remove(t);
    }

    public void clear(){
        listeners.clear();
    }
}
