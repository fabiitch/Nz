package com.github.fabiitch.nz.java.time.timers;

import com.github.fabiitch.nz.java.math.percent.Percentage;

public class DT_Timer {
    public float duration;//seconds

    public DT_Timer() {
    }

    public float update(float dt) {
        duration += dt;
        return duration;
    }

    public float getAlpha(float time) {
        return Percentage.alpha(time, duration);
    }

    public void reset() {
        duration = 0;
    }

}
