package com.fabiitch.nz.scene2D;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public abstract class SimpleCLickListener extends InputListener {

    public SimpleCLickListener() {
    }

    public abstract void onClick();

    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        onClick();
        return false;
    }

    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        onClick();
    }
}
