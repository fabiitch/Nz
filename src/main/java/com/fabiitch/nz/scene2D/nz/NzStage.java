package com.fabiitch.nz.scene2D.nz;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.fabiitch.nz.math.utils.Percentage;

/**
 * {@link Stage} extended with percent placement
 * for scale with size of screen
 * <p>
 * //TODO continue , do actions too
 */
public class NzStage extends Stage {

    private NzActorPositionner nzPositionner;

    public NzStage() {
        super(new ScreenViewport());
        this.nzPositionner = new NzActorPositionner(this.getWidth(), this.getHeight());
    }

    public NzStage(Batch batch) {
        super(new ScreenViewport(), batch);
        this.nzPositionner = new NzActorPositionner(this.getWidth(), this.getHeight());
    }

    public NzActorPositionner getPositionner(Actor actor) {
        nzPositionner.giveActor(actor, true);
        return nzPositionner;
    }

    public NzActorPositionner getPositionner(Actor actor, boolean center) {
        nzPositionner.giveActor(actor, center);
        return nzPositionner;
    }

    public float getPosX(float percent) {
        return Percentage.getValue(percent, this.getWidth());
    }

    public float getPosY(float percent) {
        return Percentage.getValue(percent, this.getHeight());
    }

    public Vector2 getPos(float percentX, float percentY) {
        return new Vector2(Percentage.getValue(percentX, this.getWidth()), Percentage.getValue(percentY, this.getHeight()));
    }

    public void addActors(Actor... actors) {
        for (Actor actor : actors)
            addActor(actor);
    }

    @Override
    public void dispose() {
        super.dispose();
        this.nzPositionner = null;
    }

    public void resize(int width, int height) {
        if (width == 0 || height == 0)
            return;
        resizeAllActors(width, height);
        this.getViewport().update(width, height, true);
    }

    private void resizeAllActors(int width, int height) {
        Array<Actor> actors = getActors();
        float oldWidth = this.getWidth();
        float oldheight = this.getHeight();

        float percentWitdh = Percentage.getPercent(oldWidth, width);
        float percentHeight = Percentage.getPercent(oldheight, height);

        for (Actor actor : actors) {
            actor.setWidth(actor.getWidth() / percentWitdh * 100);
            actor.setHeight(actor.getHeight() / percentHeight * 100);

            actor.setX(actor.getX() / percentWitdh * 100);
            actor.setY(actor.getY() / percentHeight * 100);
        }
    }
}
