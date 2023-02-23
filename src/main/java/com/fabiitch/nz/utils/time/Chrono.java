package com.fabiitch.nz.utils.time;

import com.badlogic.gdx.utils.Pool;

public class Chrono implements Pool.Poolable {

    public long millisStart;
    public long millisEnd;

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
        long end = millisEnd == 0 ? System.currentTimeMillis() : millisEnd;
        return TimeConverter.millisToSecond(end - millisStart);
    }

    @Override
    public void reset() {
        millisStart = 0;
        millisEnd = 0;
    }
}
