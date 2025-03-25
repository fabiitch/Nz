package com.github.fabiitch.nz.gdx.scene2D.nz;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.github.fabiitch.nz.gdx.scene2D.StageUtils;
import com.github.fabiitch.nz.gdx.scene2D.nz.sver.NzStagePosSaver;
import com.github.fabiitch.nz.gdx.scene2D.nz.utils.StagePlacementUtils;
import com.github.fabiitch.nz.gdx.scene2D.nz.value.NzPosType;
import com.github.fabiitch.nz.gdx.scene2D.nz.value.NzPosValue;
import com.github.fabiitch.nz.java.math.percent.Percentage;

public class NzActorPositionner {
    private final NzStage stage;
    private final NzStagePosSaver posSaver;

    private Actor actor;
    private boolean centerActor;

    private final Vector2 tmp = new Vector2();

    public NzActorPositionner(NzStage stage) {
        this.stage = stage;
        this.posSaver = stage.getPosSaver();
    }

    public NzActorPositionner giveActor(Actor actor, boolean centerActor) {
        this.actor = actor;
        this.centerActor = centerActor;
        return this;
    }

    public Vector2 getPositionFix() {
        return getPositionFix(new Vector2(), this.centerActor);
    }

    public Vector2 getPositionFix(Vector2 pos) {
        return getPositionFix(pos, this.centerActor);
    }

    public Vector2 getPositionFix(Vector2 result, boolean centerActor) {
        if (centerActor) {
            return result.set(actor.getX() + actor.getWidth() / 2, actor.getY() + actor.getHeight() / 2);
        } else {
            return result.set(actor.getX(), actor.getY());
        }
    }

    public Vector2 getPositionPercent() {
        return getPositionPercent(new Vector2(), this.centerActor);
    }

    public Vector2 getPositionPercent(Vector2 result, boolean centerActor) {
        Vector2 positionFix = getPositionFix(tmp, centerActor);
        return result.set(Percentage.percentage(positionFix.x, getParentWidth()), Percentage.percentage(positionFix.y, getParentHeight()));
    }

    private float getParentWidth() {
        return actor.getParent() != null ? actor.getParent().getWidth() : stage.getWidth();
    }

    private float getParentHeight() {
        return actor.getParent() != null ? actor.getParent().getHeight() : stage.getHeight();
    }


    public float getWidthPercent() {
        return Percentage.percentage(actor.getWidth(), getParentWidth());
    }

    public float getHeightPercent() {
        return Percentage.percentage(actor.getHeight(), getParentHeight());
    }

    public Vector2 getSizePercent(Vector2 pos) {
        return pos.set(getWidthPercent(), getHeightPercent());
    }

    public Vector2 getSizeFix(Vector2 pos) {
        return pos.set(Percentage.percentage(actor.getWidth(), getParentWidth()), Percentage.percentage(actor.getHeight(), getParentHeight()));
    }

    public Rectangle getBoundsPercent() {
        return getBoundsPercent(this.centerActor);
    }

    public Rectangle getBoundsPercent(boolean centerActor) {
        Rectangle rect = new Rectangle();

        Vector2 positionPercent = getPositionPercent(tmp, centerActor);
        rect.x = positionPercent.x;
        rect.y = positionPercent.y;

        Vector2 sizePercent = getSizePercent(tmp);
        rect.width = sizePercent.x;
        rect.height = sizePercent.y;
        return rect;
    }

    public Rectangle getBoundsFix() {
        return getBoundsFix(this.centerActor);
    }

    public Rectangle getBoundsFix(boolean centerActor) {
        Rectangle rect = new Rectangle();

        Vector2 positionPercent = getPositionFix(tmp, centerActor);
        rect.x = positionPercent.x;
        rect.y = positionPercent.y;

        Vector2 sizePercent = getSizeFix(tmp);
        rect.width = sizePercent.x;
        rect.height = sizePercent.y;
        return rect;
    }

    public NzActorPositionner addActor() {
        stage.addActor(actor);
        return this;
    }


    public NzActorPositionner setPositionFix(float x, float y) {
        if (centerActor) {
            StagePlacementUtils.placeCenter(actor, x, y);
        } else {
            actor.setX(x);
            actor.setY(y);
        }
        return this;
    }

    public NzActorPositionner setPositionFix(Vector2 pos) {
        setPositionFix(pos.x, pos.y);
        return this;
    }

    public NzActorPositionner setPositionPercent(Vector2 positionByPercent) {
        setPositionPercent(positionByPercent.x, positionByPercent.y);
        return this;
    }

    public NzActorPositionner setPositionPercent(float percentWitdh, float percentHeight) {
        setXPercent(percentWitdh);
        setYPercent(percentHeight);
        return this;
    }

    public NzActorPositionner setSizeFix(float witdh, float height) {
        actor.setSize(witdh, height);
        return this;
    }

    public NzActorPositionner setSizeFix(float size) {
        actor.setSize(size, size);
        return this;
    }

    public NzActorPositionner setSizeWidthPercent(float percentW, float percentH) {
        actor.setWidth(Percentage.value(percentW, stage.getWidth()));
        actor.setHeight(Percentage.value(percentH, stage.getWidth()));
        return this;
    }

    public NzActorPositionner setSizeWidthPercent(float percentWitdh) {
        float value = Percentage.value(percentWitdh, stage.getWidth());
        actor.setWidth(value);
        actor.setHeight(value);
        return this;
    }

    public NzActorPositionner setSizeHeightPercent(float percentHeight) {
        float value = Percentage.value(percentHeight, stage.getHeight());
        actor.setWidth(value);
        actor.setHeight(value);
        return this;
    }

    public NzActorPositionner setSizeHeightPercent(float percentW, float percentH) {
        actor.setWidth(Percentage.value(percentW, getParentHeight()));
        actor.setHeight(Percentage.value(percentH, getParentHeight()));
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
        actor.setWidth(Percentage.value(percentWitdh, getParentWidth()));
        return this;
    }

    public NzActorPositionner squareHeightPercent(float percentHeight) {
        setHeightPercent(percentHeight);
        actor.setWidth(actor.getHeight());
        return this;
    }

    public NzActorPositionner setXFix(float x) {
        actor.setX(x);
        return this;
    }

    public NzActorPositionner setYFix(float y) {
        actor.setY(y);
        return this;
    }

    public NzActorPositionner squareWidthPercent(float percentWitdh) {
        setWidthPercent(percentWitdh);
        actor.setHeight(actor.getWidth());
        return this;
    }

    public NzActorPositionner setHeightPercent(float percentHeight) {
        actor.setHeight(Percentage.value(percentHeight, getParentHeight()));
        return this;
    }

    public NzActorPositionner setWidthFix(float widthFix) {
        actor.setWidth(widthFix);
        return this;
    }

    public NzActorPositionner setHeightFix(float heightFix) {
        actor.setHeight(heightFix);
        return this;
    }

    public NzActorPositionner setXPercent(float percentWitdh) {
        if (centerActor) {
            float percentXValue = Percentage.value(percentWitdh, getParentWidth());
            StagePlacementUtils.placeCenterX(actor, percentXValue);
        } else {
            actor.setX(Percentage.value(percentWitdh, getParentWidth()));
        }
        return this;
    }

    public NzActorPositionner setYPercent(float percentHeight) {
        if (centerActor) {
            float percentYValue = Percentage.value(percentHeight, getParentHeight());
            StagePlacementUtils.placeCenterY(actor, percentYValue);
        } else {
            actor.setY(Percentage.value(percentHeight, getParentHeight()));
        }
        return this;
    }


    public NzActorPositionner glueRight() {
        if (centerActor)
            setXPercent(100 - Percentage.percentage(actor.getWidth(), getParentWidth()) / 2);
        else
            setXPercent(100 - Percentage.percentage(actor.getWidth(), getParentWidth()));
        return this;
    }

    public NzActorPositionner glueLeft() {
        if (centerActor)
            setXPercent(Percentage.percentage(actor.getWidth(), getParentWidth()) / 2);
        else
            setXPercent(0);
        return this;
    }

    public NzActorPositionner glueBottom() {
        if (centerActor)
            setYPercent(Percentage.percentage(actor.getHeight(), getParentHeight()) / 2);
        else
            setYPercent(0);
        return this;
    }

    public NzActorPositionner glueTop() {
        if (centerActor)
            setYPercent(100 - Percentage.percentage(actor.getHeight(), getParentHeight()) / 2);
        else
            setYPercent(100 - Percentage.percentage(actor.getHeight(), getParentHeight()));
        return this;
    }

    public NzActorPositionner addXPercent(float percent) {
        float posPercent = Percentage.percentage(actor.getX(), getParentWidth());
        setXPercent(posPercent + percent);
        return this;
    }

    public NzActorPositionner addYPercent(float percent) {
        float posPercent = Percentage.percentage(actor.getY(), getParentHeight());
        setYPercent(posPercent + percent);
        return this;
    }

    public NzActorPositionner addPercent(float percentX, float percentY) {
        return addXPercent(percentX).addYPercent(percentY);
    }

    public NzActorPositionner visible(boolean b) {
        actor.setVisible(b);
        return this;
    }

    public NzActorPositionner set(NzPosValue value) {
        this.centerActor = value.isCenter();
        Rectangle bounds = value.getBounds();
        if (value.getType() == NzPosType.Fix) {
            setPositionFix(bounds.x, bounds.y);
            setSizeFix(bounds.width, bounds.height);
        } else {
            setSizePercent(bounds.width, bounds.height);
            setPositionPercent(bounds.x, bounds.y);
        }
        return this;
    }

    public NzActorPositionner save() {
        posSaver.save(actor);
        return this;
    }

    public void update() {
        posSaver.saveOnly(actor);
        posSaver.update(actor);
    }

    public NzActorPositionner fitChildrenSize() {
        if (actor instanceof Group) {
            Group g = (Group) actor;
            StageUtils.fitSizeOnChildren(g);
        }
        return this;
    }

}
