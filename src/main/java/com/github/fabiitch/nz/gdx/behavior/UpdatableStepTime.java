package com.github.fabiitch.nz.gdx.behavior;

import lombok.Getter;
import lombok.Setter;

public class UpdatableStepTime implements Updatable {

    @Getter
    @Setter
    private float stepTime;
    @Getter
    @Setter
    public Updatable updatable;

    private float accumulator;

    public UpdatableStepTime(float stepTime, Updatable updatable) {
        this.stepTime = stepTime;
        this.updatable = updatable;
    }

    @Override
    public void update(float dt) {
        accumulator += dt;
        int count = 0;
        while (accumulator >= stepTime) {
            accumulator -= stepTime;
            count++;
        }
        if(count > 0){
            updatable.update(count * stepTime);
        }
    }

}
