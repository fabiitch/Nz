package com.fabiitch.nz.scene2D.nz;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.fabiitch.nz.math.utils.Percentage;

public class NzActorPositionner {
    private float width;
    private float height;

    public NzActorPositionner(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public void resize(float width, float height) {
        this.width = width;
        this.height = height;
    }

    Actor actor;
    boolean centerActor;

    public NzActorPositionner giveActor(Actor actor, boolean centerActor) {
        this.actor = actor;
        this.centerActor = centerActor;
        return this;
    }

    public Vector2 getPosition() {
        return getPosition(new Vector2());
    }

    public Vector2 getPosition(Vector2 pos) {
        if (centerActor) {
            return pos.set(actor.getX() + actor.getWidth() / 2, actor.getY() + actor.getHeight() / 2);
        } else {
            return pos.set(actor.getX(), actor.getY());
        }
    }

    public NzActorPositionner setPosition(float x, float y) {
        if (centerActor) {
            StagePlacementUtils.placeCenter(actor, x, y);
        } else {
            actor.setX(x);
            actor.setY(y);
        }

        return this;
    }

    public NzActorPositionner setPosition(Vector2 pos) {
        setPosition(pos.x, pos.y);
        return this;
    }

    public NzActorPositionner setPositionByPercent(Vector2 positionByPercent) {
        setPositionByPercent(positionByPercent.x, positionByPercent.y);
        return this;
    }

    public NzActorPositionner setPositionByPercent(float percentWitdh, float percentHeight) {
        setXPercent(percentWitdh);
        setYPercent(percentHeight);
        return this;
    }

    public NzActorPositionner setSizeByWitdhPercent(float percentWitdh) {
        float value = Percentage.getValue(percentWitdh, this.width);
        actor.setWidth(value);
        actor.setHeight(value);
        return this;
    }

    public NzActorPositionner setSizeByHeightPercent(float percentHeight) {
        float value = Percentage.getValue(percentHeight, this.height);
        actor.setWidth(value);
        actor.setHeight(value);
        return this;
    }

    public NzActorPositionner setSizeByPercent(float percentWitdh, float percentHeight) {
        setWidthPercent(percentWitdh);
        setHeightPercent(percentHeight);
        return this;
    }

    public NzActorPositionner setWidthPercent(float percentWitdh) {
        actor.setWidth(Percentage.getValue(percentWitdh, this.width));
        return this;
    }

    public NzActorPositionner setHeightPercent(float percentHeight) {
        actor.setHeight(Percentage.getValue(percentHeight, this.height));
        return this;
    }

    public NzActorPositionner setXPercent(float percentWitdh) {
        if (centerActor) {
            float percentXValue = Percentage.getValue(percentWitdh, this.width);
            StagePlacementUtils.placeCenterX(actor, percentXValue);
        } else {
            actor.setX(Percentage.getValue(percentWitdh, this.width));
        }
        return this;
    }

    public NzActorPositionner setYPercent(float percentHeight) {
        if (centerActor) {
            float percentYValue = Percentage.getValue(percentHeight, this.height);
            StagePlacementUtils.placeCenterY(actor, percentYValue);
        } else {
            actor.setY(Percentage.getValue(percentHeight, this.height));
        }
        return this;
    }

}
