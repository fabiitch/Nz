package com.github.fabiitch.nz.gdx.pools;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public abstract class SynchronizedPool<T> {
    private final int max;
    private int peak;
    private final Queue<T> pool = new ConcurrentLinkedQueue<>();

    public SynchronizedPool() {
        this(16, Integer.MAX_VALUE);
    }

    public SynchronizedPool(int initialCapacity) {
        this(initialCapacity, Integer.MAX_VALUE);
    }

    public SynchronizedPool(int initialCapacity, int max) {
        this.max = max;
        for (int i = 0; i < initialCapacity && i < max; i++) {
            pool.offer(newObject());
        }
    }

    protected abstract T newObject();


    public T obtain() {
        T obj = pool.poll();
        return (obj != null) ? obj : newObject();
    }

    public void free(T obj) {
        if (pool.size() < max) {
            pool.offer(obj);
            if (pool.size() > peak) {
                peak = pool.size();
            }
        }
        // else drop object
    }

    public int size() {
        return pool.size();
    }

    public void clear() {
        pool.clear();
        peak = 0;
    }
}
