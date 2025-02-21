package com.github.fabiitch.nz.gdx.scene2D.nz;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.github.fabiitch.nz.gdx.scene2D.data.PosSize;
import com.github.fabiitch.nz.java.math.percent.Percentage;

public class NzActorPositionner {
    private Stage stage;

    private Actor actor;
    private boolean centerActor;

    public NzActorPositionner(Stage stage) {
        this.stage = stage;
    }

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

    public NzActorPositionner add() {
        stage.addActor(actor);
        return this;
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
        actor.setWidth(Percentage.value(percentW, stage.getWidth()));
        actor.setHeight(Percentage.value(percentH, stage.getWidth()));
        return this;
    }

    public NzActorPositionner setSizeByWitdhPercent(float percentWitdh) {
        float value = Percentage.value(percentWitdh, stage.getWidth());
        actor.setWidth(value);
        actor.setHeight(value);
        return this;
    }

    public NzActorPositionner setSizeByHeightPercent(float percentHeight) {
        float value = Percentage.value(percentHeight, stage.getHeight());
        actor.setWidth(value);
        actor.setHeight(value);
        return this;
    }

    public NzActorPositionner setSizeByHeightPercent(float percentW, float percentH) {
        actor.setWidth(Percentage.value(percentW, stage.getHeight()));
        actor.setHeight(Percentage.value(percentH, stage.getHeight()));
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
        actor.setWidth(Percentage.value(percentWitdh, stage.getWidth()));
        return this;
    }

    public NzActorPositionner squareHeightPercent(float percentHeight) {
        setHeightPercent(percentHeight);
        actor.setWidth(actor.getHeight());
        return this;
    }

    public NzActorPositionner xFix(float x) {
        actor.setX(x);
        return this;
    }

    public NzActorPositionner squareWidthPercent(float percentWitdh) {
        setWidthPercent(percentWitdh);
        actor.setHeight(actor.getWidth());
        return this;
    }

    public NzActorPositionner setHeightPercent(float percentHeight) {
        actor.setHeight(Percentage.value(percentHeight, stage.getHeight()));
        return this;
    }

    public NzActorPositionner setXPercent(float percentWitdh) {
        if (centerActor) {
            float percentXValue = Percentage.value(percentWitdh, stage.getWidth());
            StagePlacementUtils.placeCenterX(actor, percentXValue);
        } else {
            actor.setX(Percentage.value(percentWitdh, stage.getWidth()));
        }
        return this;
    }

    public NzActorPositionner setYPercent(float percentHeight) {
        if (centerActor) {
            float percentYValue = Percentage.value(percentHeight, stage.getHeight());
            StagePlacementUtils.placeCenterY(actor, percentYValue);
        } else {
            actor.setY(Percentage.value(percentHeight, stage.getHeight()));
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
            setXPercent(100 - Percentage.percentage(actor.getWidth(), stage.getWidth()) / 2);
        else
            setXPercent(100 - Percentage.percentage(actor.getWidth(), stage.getWidth()));
        return this;
    }

    public NzActorPositionner glueLeft() {
        if (centerActor)
            setXPercent(Percentage.percentage(actor.getWidth(), stage.getWidth()) / 2);
        else
            setXPercent(0);
        return this;
    }

    public NzActorPositionner glueBottom() {
        if (centerActor)
            setYPercent(Percentage.percentage(actor.getHeight(), stage.getHeight()) / 2);
        else
            setYPercent(0);
        return this;
    }

    public NzActorPositionner glueTop() {
        if (centerActor)
            setYPercent(100 - Percentage.percentage(actor.getHeight(), stage.getHeight()) / 2);
        else
            setYPercent(100 - Percentage.percentage(actor.getHeight(), stage.getHeight()));
        return this;
    }

    public NzActorPositionner addX(float percent) {
        float posPercent = Percentage.percentage(actor.getX(), stage.getWidth());
        setXPercent(posPercent + percent);
        return this;
    }

    public NzActorPositionner addY(float percent) {
        float posPercent = Percentage.percentage(actor.getY(), stage.getHeight());
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
