package com.fabiitch.nz.utils.time;

import com.fabiitch.nz.math.utils.Percentage;

public class DT_Timer {
    public float duration;//seconds

    public DT_Timer() {
    }

    public float update(float dt) {
        duration += dt;
        return duration;
    }

    public float getAlpha(float time) {
        return Percentage.getAlpha(time, duration);
    }

    public void reset() {
        duration = 0;
    }

}
