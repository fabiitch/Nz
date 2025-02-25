package com.github.fabiitch.nz.gdx.scene2D.nz.neww;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class NzPos extends Actor{

    private Actor actor;
    private Actor reference;
    private final Rectangle bounds = new Rectangle();
    private NzPlacementType placementType;
    private boolean center;


    public void act() {
        actor.setPosition();
    }
}
