package com.github.fabiitch.nz.gdx.scene2D.nz;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.github.fabiitch.nz.gdx.scene2D.nz.sver.NzStagePosSaver;
import com.github.fabiitch.nz.java.math.percent.Percentage;
import lombok.Getter;

/**
 * {@link Stage} extended with percent placement
 * for scale with size of screen
 * <p>
 * //TODO continue , do actions too
 */
@Getter
public class NzStage extends Stage {

    private NzActorPositionner nzPositionner;
    private final NzStagePosSaver posSaver;

    private Actor fakeActorIfNull = new Actor();

    public NzStage(Viewport viewport, Batch batch) {
        super(viewport, batch);
        this.getRoot().setSize(viewport.getWorldWidth(), viewport.getWorldHeight());
        this.posSaver = new NzStagePosSaver(this);
        this.nzPositionner = new NzActorPositionner(this);
    }

    public NzStage() {
        this(new ScreenViewport(), new SpriteBatch());
    }


    public NzStage(Viewport viewport) {
        this(viewport, new SpriteBatch());
    }

    public NzStage(Batch batch) {
        this(new ScreenViewport(), batch);
    }

    public NzActorPositionner getNewPositionner(Actor actor, boolean center) {
        NzActorPositionner positionner = new NzActorPositionner(this);
        positionner.giveActor(actor, center);
        return positionner;
    }

    public NzActorPositionner getNewPositionner(Actor actor) {
        return getNewPositionner(actor, true);
    }

    public NzActorPositionner getPositionner(Actor actor) {
        nzPositionner.giveActor(actor, true);
        return nzPositionner;
    }

    public NzActorPositionner getPositionner(Actor actor, boolean center) {
        if (actor == null)
            actor = fakeActorIfNull;
        nzPositionner.giveActor(actor, center);
        return nzPositionner;
    }

    public NzActorPositionner add(Actor actor, boolean center) {
        getPositionner(actor, center).addActor();
        return nzPositionner;
    }

    public float getPosX(float percent) {
        return Percentage.value(percent, this.getWidth());
    }

    public float getPosY(float percent) {
        return Percentage.value(percent, this.getHeight());
    }

    public Vector2 getPos(float percentX, float percentY) {
        return new Vector2(Percentage.value(percentX, this.getWidth()), Percentage.value(percentY, this.getHeight()));
    }

    public Vector2 getPos(Vector2 percent) {
        return new Vector2(Percentage.value(percent.x, this.getWidth()), Percentage.value(percent.y, this.getHeight()));
    }

    public NzStage addActors(Actor... actors) {
        for (Actor actor : actors) {
            if (actor != null)
                addActor(actor);
        }
        return this;
    }

    public NzStage removeActors(Actor... actors) {
        for (Actor actor : actors)
            if (actor != null)
                removeActor(actor);
        return this;
    }

    public NzStage removeActor(Actor actor) {
        getRoot().removeActor(actor);
        return this;
    }

    @Override
    public void dispose() {
        super.dispose();
        this.nzPositionner = null;
    }

    public void resize(int width, int height) {
        if (width == 0 || height == 0)
            return;
//        resizeAllActors(width, height);
        this.getViewport().update(width, height, true);
//        getRoot().setSize(width, height);
//        posSaver.repack();
    }

    public void savePos() {
        posSaver.save(this.getRoot());
    }


    private void resizeAllActors(int width, int height) {
        Array<Actor> actors = getActors();
        float oldWidth = this.getWidth();
        float oldheight = this.getHeight();

        float percentWitdh = Percentage.percentage(oldWidth, width);
        float percentHeight = Percentage.percentage(oldheight, height);

        for (Actor actor : actors) {
            actor.setWidth(actor.getWidth() / percentWitdh * 100);
            actor.setHeight(actor.getHeight() / percentHeight * 100);

            actor.setX(actor.getX() / percentWitdh * 100);
            actor.setY(actor.getY() / percentHeight * 100);
        }
    }


}
