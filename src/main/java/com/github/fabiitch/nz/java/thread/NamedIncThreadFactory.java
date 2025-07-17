package com.github.fabiitch.nz.java.thread;

import lombok.AllArgsConstructor;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

@AllArgsConstructor
public class NamedIncThreadFactory implements ThreadFactory {

    private final String name;
    private final AtomicInteger threadNumber = new AtomicInteger(1);

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setName(name + "-" + threadNumber.getAndIncrement());
        return t;
    }
}
