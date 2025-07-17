package com.github.fabiitch.nz.java.thread;

import lombok.AllArgsConstructor;

import java.util.concurrent.ThreadFactory;

@AllArgsConstructor
public class NamedThreadFactory implements ThreadFactory {

    private final String name;

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setName(name);
        return t;
    }
}
