package com.github.fabiitch.nz.gdx.scene2D.nz.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.github.fabiitch.nz.java.math.percent.Percentage;

public class StagePositionnerUtils {
    private StagePositionnerUtils() {

    }

    public static void addX(Actor actor, float x) {
        actor.setX(actor.getX() + x);
    }

    public static void addY(Actor actor, float y) {
        actor.setX(actor.getY() + y);
    }

    public static void subX(Actor actor, float x) {
        actor.setX(actor.getX() - x);
    }

    public static void subY(Actor actor, float y) {
        actor.setY(actor.getY() - y);
    }

    public static void addXPercent(Actor actor, float xPercent) {
        actor.setX(actor.getX() + getWidthPercent(actor, xPercent));
    }

    public static void addYPercent(Actor actor, float yPercent) {
        actor.setY(actor.getY() + getHeightPercent(actor, yPercent));
    }

    public static void subXPercent(Actor actor, float xPercent) {
        actor.setX(actor.getX() - getWidthPercent(actor, xPercent));
    }

    public static void subYPercent(Actor actor, float yPercent) {
        actor.setY(actor.getY() - getWidthPercent(actor, yPercent));
    }


    //----------------------------------------------------
    public static void top(Actor actor) {
        actor.setY(getParentHeight(actor) - actor.getHeight());
    }

    public static void top(Actor actor, float value) {
        actor.setY(getParentHeight(actor) - actor.getHeight() - value);
    }

    public static void topPercent(Actor actor, float yPercent) {
        actor.setY(getParentHeight(actor) - actor.getHeight() - getHeightPercent(actor, yPercent));
    }

    public static void bottom(Actor actor) {
        actor.setY(0);
    }

    public static void bottom(Actor actor, float value) {
        actor.setY(value);
    }

    public static void bottomPercent(Actor actor, float yPercent) {
        actor.setY(getHeightPercent(actor, yPercent));
    }

    public static void right(Actor actor) {
        actor.setX(getParentWidth(actor) - actor.getHeight());
    }

    public static void right(Actor actor, float value) {
        actor.setX(getParentWidth(actor) - actor.getHeight() - value);
    }

    public static void rightPercent(Actor actor, float xPercent) {
        actor.setX(getParentWidth(actor) - actor.getHeight() - getWidthPercent(actor, xPercent));
    }

    public static void left(Actor actor) {
        actor.setX(0);
    }

    public static void left(Actor actor, float value) {
        actor.setX(value);
    }

    public static void leftPercent(Actor actor, float xPercent) {
        actor.setX(getWidthPercent(actor, xPercent));
    }

    public static void centerX(Actor actor) {
        actor.setX(getParentWidth(actor) / 2 - actor.getWidth() / 2);
    }

    public static void centerY(Actor actor) {
        actor.setY(getParentHeight(actor) / 2 - actor.getHeight() / 2);
    }

    public static void center(Actor actor) {
        centerX(actor);
        centerY(actor);
    }

    public static void centerXPercent(Actor actor, float xPercent) {
        actor.setX(getWidthPercent(actor, xPercent) - actor.getWidth() / 2);
    }

    public static void centerYPercent(Actor actor, float yPercent) {
        actor.setY(getHeightPercent(actor, yPercent) - actor.getHeight() / 2);
    }

    public static void centerAt(Actor actor, float x, float y) {
        actor.setPosition(x - actor.getWidth() / 2, y - actor.getHeight() / 2);
    }

    public static void centerAt(Actor actor, Vector2 pos) {
        centerAt(actor, pos.x, pos.y);
    }

    public static void centerAtPercent(Actor actor, float xPercent, float yPercent) {
        centerXPercent(actor, xPercent);
        centerYPercent(actor, yPercent);
    }

    public static void centerAtPercent(Actor actor, Vector2 posPercent) {
        centerAtPercent(actor, posPercent.x, posPercent.y);
    }

    //========================================================
    private static float getParentWidth(Actor actor) {
        Group parent = actor.getParent();
        if (parent == null || parent.getWidth() == 0)
            return Gdx.graphics.getWidth();
        return parent.getWidth();
    }

    private static float getParentHeight(Actor actor) {
        Group parent = actor.getParent();
        if (parent == null || parent.getHeight() == 0)
            return Gdx.graphics.getHeight();
        return parent.getHeight();
    }

    private static float getWidthPercent(Actor actor, float percent) {
        Group parent = actor.getParent();
        if (parent == null || parent.getWidth() == 0)
            return Percentage.value(percent, Gdx.graphics.getWidth());
        return Percentage.value(percent, parent.getWidth());
    }

    private static float getHeightPercent(Actor actor, float percent) {
        Group parent = actor.getParent();
        if (parent == null || parent.getHeight() == 0)
            return Percentage.value(percent, Gdx.graphics.getHeight());
        return Percentage.value(percent, parent.getHeight());
    }
}
