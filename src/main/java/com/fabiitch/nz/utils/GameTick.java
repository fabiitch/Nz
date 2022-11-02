package com.fabiitch.nz.utils;

import com.badlogic.gdx.utils.TimeUtils;

public class GameTick {
    public long nanoStart;
    public long millisStart;
    public float dt;

    public void update(float dt) {
        nanoStart = TimeUtils.nanoTime();
        millisStart = TimeUtils.millis();
        this.dt = dt;
    }
}
