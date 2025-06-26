package com.github.fabiitch.nz.gdx.debug.profile;

import com.badlogic.gdx.math.FloatCounter;
import lombok.Getter;

public class RenderTimeCounter {

    @Getter
    private final FloatCounter counter = new FloatCounter(0);

    private long millisStart;

    public float getAverage(){
        return counter.average;
    }

    public void startFrame() {
        millisStart = System.currentTimeMillis();
    }

    public void endFrame() {
        counter.put(System.currentTimeMillis() - millisStart);
    }

    public void clear() {
        counter.reset();
    }
}
