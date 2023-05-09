package com.fabiitch.nz.gdx.scene2D.nz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.fabiitch.nz.java.math.utils.Percentage;

public class StagePlacementUtils {

    private StagePlacementUtils() {

    }

    public static void setY(float y, Actor... actors) {
        for (Actor actor : actors)
            actor.setY(y);
    }

    public static void setX(float x, Actor... actors) {
        for (Actor actor : actors)
            actor.setX(x);
    }

    public static void top(Actor actor) {
        Group parent = actor.getParent();
        if (parent == null)
            return;
        actor.setY(parent.getHeight() - actor.getHeight());
    }

    public static void top(Actor actor, float percent) {
        Group parent = actor.getParent();
        if (parent == null)
            return;
        actor.setY(parent.getHeight() - actor.getHeight() - Percentage.value(percent, parent.getHeight()));
    }

    public static void bottom(Actor actor) {
        Group parent = actor.getParent();
        if (parent == null)
            return;
        actor.setY(0);
    }

    public static void bottom(Actor actor, float percent) {
        Group parent = actor.getParent();
        if (parent == null)
            return;
        actor.setY(Percentage.value(percent, parent.getHeight()));
    }

    public static void right(Actor actor) {
        Group parent = actor.getParent();
        if (parent == null)
            return;
        actor.setX(parent.getWidth() - actor.getWidth());
    }

    public static void right(Actor actor, float percent) {
        Group parent = actor.getParent();
        if (parent == null)
            return;
        actor.setX(parent.getWidth() - actor.getWidth() - Percentage.value(percent, parent.getWidth()));
    }

    public static void left(Actor actor) {
        Group parent = actor.getParent();
        if (parent == null)
            return;
        actor.setX(0);
    }

    public static void centerWitdh(Actor actor) {
        Group parent = actor.getParent();
        if (parent == null)
            return;
        actor.setX(parent.getWidth() / 2 - actor.getWidth() / 2);
    }

    public static void centerHeight(Actor actor) {
        Group parent = actor.getParent();
        if (parent == null)
            return;
        actor.setX(parent.getHeight() / 2 - actor.getHeight() / 2);
    }

    public static void left(Actor actor, float percent) {
        Group parent = actor.getParent();
        if (parent == null)
            return;
        actor.setX(Percentage.value(percent, parent.getWidth()));
    }

    public static void centerScreen(Actor actor) {
        actor.setX(Gdx.graphics.getWidth() / 2 - actor.getWidth() / 2);
        actor.setY(Gdx.graphics.getHeight() / 2 - actor.getHeight() / 2);
    }

    public static Vector2 getCenterPos(Actor actor, Vector2 pos) {
        return pos.set(pos.x + actor.getWidth() / 2, pos.y + actor.getHeight() / 2);
    }

    public static void placeCenter(Actor actor, Vector2 pos) {
        actor.setPosition(pos.x - actor.getWidth() / 2, pos.y - actor.getHeight() / 2);
    }

    public static void placeCenter(Actor actor, float x, float y) {
        actor.setPosition(x - actor.getWidth() / 2, y - actor.getHeight() / 2);
    }

    public static void placeCenterX(Actor actor, float x) {
        actor.setX(x - actor.getWidth() / 2);
    }

    public static void placeCenterY(Actor actor, float y) {
        actor.setY(y - actor.getHeight() / 2);
    }
}
