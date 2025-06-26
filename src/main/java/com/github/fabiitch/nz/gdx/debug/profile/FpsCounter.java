package com.github.fabiitch.nz.gdx.debug.profile;

import com.badlogic.gdx.Gdx;
import com.github.fabiitch.nz.java.utils.IntCounter;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class FpsCounter {

    private final IntCounter counter;
    private int minBound ;

    public FpsCounter() {
        this(50);
    }

    public FpsCounter(int minBound) {
        this.minBound = minBound;
        this.counter = new IntCounter();
    }

    public void update(){
        final int fps = Gdx.graphics.getFramesPerSecond();
        counter.put(fps);
        if (fps < minBound) {
            Gdx.app.debug("FPS LOW", "FSP=" + fps);
        }
    }

    public int getAverage() {
        return counter.average;
    }


}
