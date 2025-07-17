package com.github.fabiitch.nz.java.thread;

import lombok.experimental.UtilityClass;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@UtilityClass
public class ExecutorsUtils {

    public static ScheduledExecutorService newScheduledThreadPool(String name, int nThreads) {
        return Executors.newScheduledThreadPool(nThreads, new NamedIncThreadFactory(name));
    }

    public static ScheduledExecutorService newSingleThreadScheduledExecutor(String name) {
        return Executors.newSingleThreadScheduledExecutor(new NamedThreadFactory(name));
    }

    public static ExecutorService newFixedThreadPool(int nThreads, String name) {
        return Executors.newFixedThreadPool(nThreads, new NamedThreadFactory(name));
    }

    public static ExecutorService newSingleThreadExecutor(String name) {
        return Executors.newSingleThreadExecutor(new NamedThreadFactory(name));
    }

    public static ExecutorService newCachedThreadPool(String name) {
        return Executors.newCachedThreadPool(new NamedThreadFactory(name));
    }
}
