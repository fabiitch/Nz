package com.github.fabiitch.nz.java.time;

import com.badlogic.gdx.utils.Pool;

public class Chrono implements Pool.Poolable {

    public long millisStart;
    public long millisEnd;

    public Chrono() {
        start();
    }

    public void start() {
        millisStart = System.currentTimeMillis();
        millisEnd = 0;
    }

    public void end() {
        millisEnd = System.currentTimeMillis();
    }

    public long getMillis() {
        long end = millisEnd == 0 ? System.currentTimeMillis() : millisEnd;
        return end - millisStart;
    }

    public float getSeconds() {
        return TimeConverter.millisToSecond(getMillis());
    }

    @Override
    public void reset() {
        start();
    }
}
