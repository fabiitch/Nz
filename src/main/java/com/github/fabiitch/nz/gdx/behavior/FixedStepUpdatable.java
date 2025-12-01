package com.github.fabiitch.nz.gdx.behavior;

import lombok.Getter;
import lombok.Setter;

public class FixedStepUpdatable implements Updatable {

    @Getter
    @Setter
    private float stepTime;
    @Getter
    @Setter
    private Updatable updatable;

    private float accumulator;

    public FixedStepUpdatable(float stepTime, Updatable updatable) {
        this.stepTime = stepTime;
        this.updatable = updatable;
    }

    @Override
    public void update(float dt) {
        accumulator += dt;
        while (accumulator >= stepTime) {
            updatable.update(stepTime);
            accumulator -= stepTime;
        }
    }

}
