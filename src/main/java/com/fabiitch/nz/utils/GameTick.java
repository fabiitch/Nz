package com.fabiitch.nz.utils;

import com.badlogic.gdx.utils.TimeUtils;

public class GameTick {
    public long currentNano;
    public long currentMillis;
    public float dt;

    public void update(float dt) {
        currentNano = TimeUtils.nanoTime();
        currentMillis = TimeUtils.millis();
        this.dt = dt;
    }
}
