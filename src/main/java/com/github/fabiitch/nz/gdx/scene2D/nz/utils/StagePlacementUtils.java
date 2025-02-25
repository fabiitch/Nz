package com.github.fabiitch.nz.gdx.scene2D.nz.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.github.fabiitch.nz.java.math.percent.Percentage;

//TODO full reprise et merge avec celle de NzStage (objet new a create c'est mieux)
// StagePlacementUtils.get(actors...).top().mid().bottom()
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

    public static void top(Actor... actors) {
        for (Actor actor : actors)
            top(actor);
    }

    public static void top(Actor actor, float percent) {
        float parentHeight = getParentHeight(actor);
        actor.setY(parentHeight - actor.getHeight() - Percentage.value(percent, parentHeight));
    }

    public static void bottom(Actor actor) {
        actor.setY(0);
    }

    public static void bottom(Actor... actors) {
        for (Actor actor : actors)
            bottom(actor);
    }

    public static void bottom(Actor actor, float percent) {
        float parentHeight = getParentHeight(actor);
        actor.setY(Percentage.value(percent, parentHeight));
    }

    public static void right(Actor... actors) {
        for (Actor actor : actors)
            right(actor);
    }

    public static void right(Actor actor) {
        float parentWidth = getParentWitdh(actor);
        actor.setX(parentWidth - actor.getWidth());
    }

    public static void right(Actor actor, float percent) {
        float parentWidth = getParentWitdh(actor);
        actor.setX(parentWidth - actor.getWidth() - Percentage.value(percent, parentWidth));
    }

    public static void left(Actor... actors) {
        for (Actor actor : actors)
            left(actor);
    }

    public static void left(Actor actor) {
        actor.setX(0);
    }

    public static void centerWitdh(Actor actor) {
        float parentWidth = getParentWitdh(actor);
        actor.setX(parentWidth / 2 - actor.getWidth() / 2);
    }

    public static void centerHeight(Actor actor) {
        float parentHeight = getParentHeight(actor);
        actor.setX(parentHeight / 2 - actor.getHeight() / 2);
    }

    public static void left(Actor actor, float percent) {
        float parentWidth = getParentWitdh(actor);
        actor.setX(Percentage.value(percent, parentWidth));
    }

    public static void center(Actor actor) {
        float parentWidth = getParentWitdh(actor);
        float parentHeight = getParentHeight(actor);
        actor.setX(parentWidth - actor.getWidth() / 2);
        actor.setY(parentHeight - actor.getHeight() / 2);
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

    private static float getParentWitdh(Actor actor) {
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
}
