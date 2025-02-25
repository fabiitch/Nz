package com.github.fabiitch.nz.gdx.scene2D.nz.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.github.fabiitch.nz.java.data.collections.utils.ToArray;

public class StagePositionner {

    private boolean centerActor;
    private Actor[] actors;

    private StagePositionner(Actor... actors) {
        this.actors = actors;
    }

    public StagePositionner get(Actor... actors) {
        return new StagePositionner(actors);
    }

    //========================================================
    public void addX(float x) {
        for (Actor actor : actors)
            StagePositionnerUtils.addX(actor, x);
    }

    public void addY(float y) {
        for (Actor actor : actors)
            StagePositionnerUtils.addY(actor, y);
    }

    public void subX(float x) {
        for (Actor actor : actors)
            StagePositionnerUtils.subX(actor, x);
    }

    public void subY(float y) {
        for (Actor actor : actors)
            StagePositionnerUtils.subY(actor, y);
    }

    public void addXPercent(float xPercent) {
        for (Actor actor : actors)
            StagePositionnerUtils.addXPercent(actor, xPercent);
    }

    public void addYPercent(float yPercent) {
        for (Actor actor : actors)
            StagePositionnerUtils.addYPercent(actor, yPercent);
    }

    public void subXPercent(float xPercent) {
        for (Actor actor : actors)
            StagePositionnerUtils.subXPercent(actor, xPercent);
    }

    public void subYPercent(float yPercent) {
        for (Actor actor : actors)
            StagePositionnerUtils.subYPercent(actor, yPercent);
    }


    //----------------------------------------------------
    public void top() {
        for (Actor actor : actors)
            StagePositionnerUtils.top(actor);
    }

    public void top(float value) {
        for (Actor actor : actors)
            StagePositionnerUtils.top(actor, value);
    }

    public void topPercent(float yPercent) {
        for (Actor actor : actors)
            StagePositionnerUtils.topPercent(actor, yPercent);
    }

    public void bottom() {
        for (Actor actor : actors)
            StagePositionnerUtils.bottom(actor);
    }

    public void bottom(float value) {
        for (Actor actor : actors)
            StagePositionnerUtils.bottom(actor, value);
    }

    public void bottomPercent(float yPercent) {
        for (Actor actor : actors)
            StagePositionnerUtils.bottomPercent(actor, yPercent);
    }

    public void right() {
        for (Actor actor : actors)
            StagePositionnerUtils.right(actor);
    }

    public void right(float value) {
        for (Actor actor : actors)
            StagePositionnerUtils.right(actor, value);
    }

    public void rightPercent(float xPercent) {
        for (Actor actor : actors)
            StagePositionnerUtils.rightPercent(actor, xPercent);
    }

    public void left() {
        for (Actor actor : actors)
            StagePositionnerUtils.left(actor);
    }

    public void left(float value) {
        for (Actor actor : actors)
            StagePositionnerUtils.left(actor, value);
    }

    public void leftPercent(float xPercent) {
        for (Actor actor : actors)
            StagePositionnerUtils.leftPercent(actor, xPercent);
    }

    public void centerX() {
        for (Actor actor : actors)
            StagePositionnerUtils.centerX(actor);
    }

    public void centerY() {
        for (Actor actor : actors)
            StagePositionnerUtils.centerY(actor);
    }

    public void center() {
        for (Actor actor : actors)
            StagePositionnerUtils.center(actor);
    }

    public void centerXPercent(float xPercent) {
        for (Actor actor : actors)
            StagePositionnerUtils.centerXPercent(actor, xPercent);
    }

    public void centerYPercent(float yPercent) {
        for (Actor actor : actors)
            StagePositionnerUtils.centerYPercent(actor, yPercent);
    }

    public void centerAt(float x, float y) {
        for (Actor actor : actors)
            StagePositionnerUtils.centerAt(actor, x, y);
    }

    public void centerAt(Vector2 pos) {
        for (Actor actor : actors)
            StagePositionnerUtils.centerAt(actor, pos);
    }

    public void centerAtPercent(float xPercent, float yPercent) {
        for (Actor actor : actors)
            StagePositionnerUtils.centerAtPercent(actor, xPercent, yPercent);
    }

    public void centerAtPercent(Vector2 posPercent) {
        for (Actor actor : actors)
            StagePositionnerUtils.centerAtPercent(actor, posPercent);
    }

    //========================================================
    public boolean isCenterActor() {
        return centerActor;
    }

    public StagePositionner setCenterActor(boolean centerActor) {
        this.centerActor = centerActor;
        return this;
    }

    public Array<Actor> getActors() {
        return ToArray.get(actors);
    }

    public StagePositionner setActors(Actor... actors) {
        this.actors = actors;
        return this;
    }

}
