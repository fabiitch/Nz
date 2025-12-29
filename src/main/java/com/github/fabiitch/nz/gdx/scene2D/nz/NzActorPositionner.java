package com.github.fabiitch.nz.gdx.scene2D.nz;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.github.fabiitch.nz.gdx.scene2D.nz.saver.NzStagePosSaver;
import com.github.fabiitch.nz.gdx.scene2D.nz.saver.value.NzPosType;
import com.github.fabiitch.nz.gdx.scene2D.nz.saver.value.NzPosValue;
import com.github.fabiitch.nz.gdx.scene2D.nz.utils.StagePlacementUtils;
import com.github.fabiitch.nz.gdx.scene2D.utils.StageUtils;
import com.github.fabiitch.nz.java.math.percent.Percentage;

public class NzActorPositionner {
    private Actor actor;
    private Actor fakeActor = new Actor();
    private boolean centerActor;

    private final Vector2 tmp = new Vector2();

    public NzActorPositionner() {
    }

    public NzActorPositionner giveActor(Actor actor, boolean centerActor) {
        this.actor = actor != null ? actor : fakeActor;
        this.centerActor = centerActor;
        return this;
    }

    public NzActorPositionner centerActor(boolean centerActor) {
        this.centerActor = centerActor;
        return this;
    }

    public NzActorPositionner zIndex(int zIndex) {
        this.actor.setZIndex(zIndex);
        return this;
    }


    public Vector2 getPos() {
        return getPos(new Vector2(), this.centerActor);
    }

    public Vector2 getPos(Vector2 pos) {
        return getPos(pos, this.centerActor);
    }

    public Vector2 getPos(Vector2 result, boolean centerActor) {
        if (centerActor) {
            return result.set(actor.getX() + actor.getWidth() / 2, actor.getY() + actor.getHeight() / 2);
        } else {
            return result.set(actor.getX(), actor.getY());
        }
    }

    public Vector2 getPosPercent() {
        return getPosPercent(new Vector2(), this.centerActor);
    }

    public Vector2 getPosPercent(Vector2 result, boolean centerActor) {
        Vector2 positionFix = getPos(tmp, centerActor);
        return result.set(Percentage.percentage(positionFix.x, getParentWidth()), Percentage.percentage(positionFix.y, getParentHeight()));
    }

    private float getParentWidth() {
        return actor.getParent() != null ? actor.getParent().getWidth() : 0;
    }

    private float getParentHeight() {
        return actor.getParent() != null ? actor.getParent().getHeight() : 0;
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

        Vector2 positionPercent = getPosPercent(tmp, centerActor);
        rect.x = positionPercent.x;
        rect.y = positionPercent.y;

        Vector2 sizePercent = getSizePercent(tmp);
        rect.width = sizePercent.x;
        rect.height = sizePercent.y;
        return rect;
    }

    public Rectangle getBounds() {
        return getBounds(this.centerActor);
    }

    public Rectangle getBounds(boolean centerActor) {
        Rectangle rect = new Rectangle();

        Vector2 positionPercent = getPos(tmp, centerActor);
        rect.x = positionPercent.x;
        rect.y = positionPercent.y;

        Vector2 sizePercent = getSizeFix(tmp);
        rect.width = sizePercent.x;
        rect.height = sizePercent.y;
        return rect;
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

    public NzActorPositionner setPositionPercent(float positionByPercent) {
        setPositionPercent(positionByPercent, positionByPercent);
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

    public NzActorPositionner setSize(float witdh, float height) {
        actor.setSize(witdh, height);
        return this;
    }

    public NzActorPositionner setSize(float size) {
        actor.setSize(size, size);
        return this;
    }

    public NzActorPositionner setBoundsPercent(Rectangle bounds) {
        setSizePercent(bounds.width, bounds.height);
        setPositionPercent(bounds.x, bounds.y);
        return this;
    }

    public NzActorPositionner setBoundsPercent(float x, float y, float width, float height) {
        setSizePercent(width, height);
        setPositionPercent(x, y);
        return this;
    }


    public NzActorPositionner setBounds(Rectangle bounds) {
        actor.setSize(bounds.width, bounds.height);
        setPosition(bounds.x, bounds.y);
        return this;
    }

    public NzActorPositionner setSizePercent(float percent) {
        setSizePercent(percent, percent);
        return this;
    }

    public NzActorPositionner setSizePercent(float percentWidth, float percentHeight) {
        setWidthPercent(percentWidth);
        setHeightPercent(percentHeight);
        return this;
    }

    public NzActorPositionner setSizePercent(Vector2 sizePercent) {
        return setSizePercent(sizePercent.x, sizePercent.y);
    }

    public NzActorPositionner setWidthPercent(float percentWidth) {
        actor.setWidth(Percentage.value(percentWidth, getParentWidth()));
        return this;
    }

    public NzActorPositionner squareHeightPercent(float percentHeight) {
        setHeightPercent(percentHeight);
        actor.setWidth(actor.getHeight());
        return this;
    }

    public NzActorPositionner setX(float x) {
        actor.setX(x);
        return this;
    }

    public NzActorPositionner setY(float y) {
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

    public NzActorPositionner setWidth(float widthFix) {
        actor.setWidth(widthFix);
        return this;
    }

    public NzActorPositionner setHeight(float heightFix) {
        actor.setHeight(heightFix);
        return this;
    }

    public NzActorPositionner squareMinPercent(float percentWidthHeight) {
        return this.squareMinPercent(percentWidthHeight, percentWidthHeight);
    }

    public NzActorPositionner squareMinPercent(float percentWidth, float percentHeight) {
        float valueWidth = Percentage.value(percentWidth, getParentWidth());
        float valueHeight = Percentage.value(percentHeight, getParentHeight());
        return square(Math.min(valueWidth, valueHeight));
    }

    public NzActorPositionner square(float sizeFix) {
        setWidth(sizeFix);
        setHeight(sizeFix);
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

    public NzActorPositionner addX(float x) {
        actor.setX(actor.getX() + x);
        return this;
    }

    public NzActorPositionner addY(float y) {
        actor.setY(actor.getY() + y);
        return this;
    }

    public NzActorPositionner add(float x, float y) {
        return addX(x).addY(y);
    }

    public NzActorPositionner addXPercent(float percent) {
        float posPercent = Percentage.value(percent, getParentWidth());
        actor.setX(actor.getX() + posPercent);
        return this;
    }

    public NzActorPositionner addYPercent(float percent) {
        float posPercent = Percentage.percentage(percent, getParentHeight());
        actor.setY(actor.getY() + posPercent);
        return this;
    }

    public NzActorPositionner addPercent(float percentX, float percentY) {
        return addXPercent(percentX).addYPercent(percentY);
    }

    public NzActorPositionner visible(boolean b) {
        actor.setVisible(b);
        return this;
    }

    public NzActorPositionner setFull() {
        boolean oldCenter = this.centerActor;
        return centerActor(false).setSizePercent(100)
                .setPositionPercent(0, 0).centerActor(oldCenter);
    }

    public NzActorPositionner center() {
        return centerX().centerY();
    }

    public NzActorPositionner center(float widthPercent, float heightPercent) {
        return centerX(widthPercent).centerY(heightPercent);
    }

    public NzActorPositionner centerX(float percent) {
        boolean oldCenterActor = centerActor;
        this.centerActor = true;
        setXPercent(percent);
        this.centerActor = oldCenterActor;
        return this;
    }

    public NzActorPositionner centerX() {
        return centerX(50);
    }

    public NzActorPositionner centerY(float percent) {
        boolean oldCenterActor = centerActor;
        this.centerActor = true;
        setYPercent(percent);
        this.centerActor = oldCenterActor;
        return this;
    }

    public NzActorPositionner centerY() {
        return centerY(50);
    }

    public NzActorPositionner copyBounds(Actor actor) {
        setSize(actor.getWidth(), actor.getHeight());
        setPosition(actor.getX(), actor.getY());
        return this;
    }

    public NzActorPositionner glueRight(Actor target) {
        actor.setX(target.getX() + target.getWidth());
        return this;
    }

    public NzActorPositionner glueLeft(Actor target) {
        actor.setX(target.getX() - actor.getWidth());
        return this;
    }

    public NzActorPositionner glueTop(Actor target) {
        actor.setX(target.getY() + target.getWidth());
        return this;
    }

    public NzActorPositionner glueBottom(Actor target) {
        actor.setX(target.getY() - actor.getWidth());
        return this;
    }

    //=============================

    public NzActorPositionner set(NzPosValue value) {
        this.centerActor = value.isCenter();
        Rectangle bounds = value.getBounds();
        if (value.getType() == NzPosType.Fix) {
            setPosition(bounds.x, bounds.y);
            setSize(bounds.width, bounds.height);
        } else {
            setSizePercent(bounds.width, bounds.height);
            setPositionPercent(bounds.x, bounds.y);
        }
        return this;
    }

    public NzActorPositionner fitChildrenSize() {
        if (actor instanceof Group) {
            Group g = (Group) actor;
            StageUtils.fitSizeOnChildren(g);
        }
        return this;
    }

}
