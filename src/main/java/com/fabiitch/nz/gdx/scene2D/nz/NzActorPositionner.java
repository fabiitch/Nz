package com.fabiitch.nz.gdx.scene2D.nz;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.fabiitch.nz.gdx.scene2D.data.PosSize;
import com.fabiitch.nz.java.math.utils.Percentage;

public class NzActorPositionner {
    private float stageWitdh;
    private float stageHeight;

    public NzActorPositionner(float stageWitdh, float stageHeight) {
        this.stageWitdh = stageWitdh;
        this.stageHeight = stageHeight;
    }

    public void resize(float width, float height) {
        this.stageWitdh = width;
        this.stageHeight = height;
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

    public NzActorPositionner set(PosSize posSize) {
        setPosition(posSize.getX(), posSize.getY());
        actor.setSize(posSize.getWitdh(), posSize.getHeight());
        return this;
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

    public NzActorPositionner setByPercent(PosSize posSize) {
        setPositionByPercent(posSize.getX(), posSize.getY());
        setSizePercent(posSize.getWitdh(), posSize.getHeight());
        return this;
    }

    public NzActorPositionner setSize(float witdh, float height) {
        actor.setSize(witdh, height);
        return this;
    }

    public NzActorPositionner setSize(float size) {
        actor.setSize(size, size);
        return this;
    }

    public NzActorPositionner setSizeByWitdhPercent(float percentW, float percentH) {
        actor.setWidth(Percentage.getValue(percentW, this.stageWitdh));
        actor.setHeight(Percentage.getValue(percentH, this.stageWitdh));
        return this;
    }

    public NzActorPositionner setSizeByWitdhPercent(float percentWitdh) {
        float value = Percentage.getValue(percentWitdh, this.stageWitdh);
        actor.setWidth(value);
        actor.setHeight(value);
        return this;
    }

    public NzActorPositionner setSizeByHeightPercent(float percentHeight) {
        float value = Percentage.getValue(percentHeight, this.stageHeight);
        actor.setWidth(value);
        actor.setHeight(value);
        return this;
    }

    public NzActorPositionner setSizeByHeightPercent(float percentW, float percentH) {
        actor.setWidth(Percentage.getValue(percentW, this.stageHeight));
        actor.setHeight(Percentage.getValue(percentH, this.stageHeight));
        return this;
    }

    public NzActorPositionner setSizePercent(float percent) {
        setSizePercent(percent, percent);
        return this;
    }

    public NzActorPositionner setSizePercent(float percentWitdh, float percentHeight) {
        setWidthPercent(percentWitdh);
        setHeightPercent(percentHeight);
        return this;
    }

    public NzActorPositionner setWidthPercent(float percentWitdh) {
        actor.setWidth(Percentage.getValue(percentWitdh, this.stageWitdh));
        return this;
    }

    public NzActorPositionner setHeightPercent(float percentHeight) {
        actor.setHeight(Percentage.getValue(percentHeight, this.stageHeight));
        return this;
    }

    public NzActorPositionner setXPercent(float percentWitdh) {
        if (centerActor) {
            float percentXValue = Percentage.getValue(percentWitdh, this.stageWitdh);
            StagePlacementUtils.placeCenterX(actor, percentXValue);
        } else {
            actor.setX(Percentage.getValue(percentWitdh, this.stageWitdh));
        }
        return this;
    }

    public NzActorPositionner setYPercent(float percentHeight) {
        if (centerActor) {
            float percentYValue = Percentage.getValue(percentHeight, this.stageHeight);
            StagePlacementUtils.placeCenterY(actor, percentYValue);
        } else {
            actor.setY(Percentage.getValue(percentHeight, this.stageHeight));
        }
        return this;
    }

    public NzActorPositionner set(NzPlacement placement) {
        if (placement.sizePercent) {
            if (placement.witdhAsSizePercentTotal)
                setSizeByWitdhPercent(placement.posSize.getWitdh(), placement.posSize.getHeight());
            else if (placement.heightAsSizePercentTotal)
                setSizeByHeightPercent(placement.posSize.getWitdh(), placement.posSize.getHeight());
            else
                setSizePercent(placement.posSize.getWitdh(), placement.posSize.getHeight());

        } else {
            actor.setSize(placement.posSize.getWitdh(), placement.posSize.getHeight());
        }

        if (placement.posPercent)
            setPositionByPercent(placement.posSize.getX(), placement.posSize.getY());
        else
            setPosition(placement.posSize.getX(), placement.posSize.getY());

        return this;
    }

    public NzActorPositionner glueRight() {
        if (centerActor)
            setXPercent(100 - Percentage.getPercent(actor.getWidth(), stageWitdh) / 2);
        else
            setXPercent(100 - Percentage.getPercent(actor.getWidth(), stageWitdh));
        return this;
    }

    public NzActorPositionner glueLeft() {
        if (centerActor)
            setXPercent(Percentage.getPercent(actor.getWidth(), stageWitdh) / 2);
        else
            setXPercent(0);
        return this;
    }

    public NzActorPositionner glueBottom() {
        if (centerActor)
            setYPercent(Percentage.getPercent(actor.getHeight(), stageHeight) / 2);
        else
            setYPercent(0);
        return this;
    }

    public NzActorPositionner glueTop() {
        if (centerActor)
            setYPercent(100 - Percentage.getPercent(actor.getHeight(), stageHeight) / 2);
        else
            setYPercent(100 - Percentage.getPercent(actor.getHeight(), stageHeight));
        return this;
    }

    public NzActorPositionner addX(float percent) {
        float posPercent = Percentage.getPercent(actor.getX(), stageWitdh);
        setXPercent(posPercent + percent);
        return this;
    }

    public NzActorPositionner addY(float percent) {
        float posPercent = Percentage.getPercent(actor.getY(), stageHeight);
        setYPercent(posPercent + percent);
        return this;
    }

    public NzActorPositionner add(float percentX, float percentY) {
        return addX(percentX).addY(percentY);
    }

    public void visible(boolean b) {
        actor.setVisible(b);
    }
}
