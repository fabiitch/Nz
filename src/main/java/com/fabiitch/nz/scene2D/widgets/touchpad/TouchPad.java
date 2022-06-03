package com.fabiitch.nz.scene2D.widgets.touchpad;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.fabiitch.nz.math.vectors.V;
import com.fabiitch.nz.scene2D.nz.NzActorPositionner;
import com.fabiitch.nz.scene2D.nz.NzStage;

public abstract class TouchPad {

    protected final Texture textureBase;
    protected final Texture textureKnob;

    protected final NzStage nzStage;
    protected boolean fixedOnDrag; //fixedOnDrag after activation
    protected boolean fixedOnTouchDown; //fixedOnDrag after activation

    public Image imageBase;//TODO
    public Image imageKnob;

    protected final Vector2 posBase = new Vector2();
    protected final Vector2 posKnob = new Vector2();
    protected final Vector2 posInactive;

    protected float sizeBase;

    public Vector2 direction = new Vector2();
    public float force = 0f;

    public TouchPad(NzStage nzStage,
                    Texture textureBase, Texture textureKnob,
                    Vector2 posInactive,
                    float sizeBase, float sizeKnob,
                    boolean fixedOnDrag) {
        this.nzStage = nzStage;
        this.textureBase = textureBase;
        this.textureKnob = textureKnob;
        this.sizeBase = sizeBase;

        this.posInactive = posInactive;
        this.fixedOnDrag = fixedOnDrag;
        this.init(textureBase, textureKnob, sizeBase, sizeKnob);
    }

    private void init(Texture textureBase, Texture textureKnob, float sizeBase, float sizeKnob) {
        imageBase = new Image(textureBase);
        NzActorPositionner positionner = nzStage.getPositionner(imageBase, true);
        positionner.setSize(sizeBase).setPosition(posInactive.x, posInactive.y);
        positionner.getPosition(posBase);
        nzStage.addActor(imageBase);

        imageKnob = new Image(textureKnob);
        positionner = nzStage.getPositionner(imageKnob, true);
        positionner.setSize(sizeKnob).setPosition(posInactive.x, posInactive.y);
        nzStage.addActor(imageKnob);
        desactive();
    }

    public void updateDirForce() {
        this.direction.set(posKnob).sub(posBase).nor();
        float dst = posBase.dst(posKnob);
        this.force = Math.min(1, dst / sizeBase / 2);
    }

    public void desactive() {
        imageKnob.setColor(1, 1, 1, 0.2f);
        imageBase.setColor(1, 1, 1, 0.2f);
        nzStage.getPositionner(imageBase, true).setPosition(posInactive.x, posInactive.y);
        nzStage.getPositionner(imageKnob, true).setPosition(posInactive.x, posInactive.y);
    }

    public void active() {
        imageKnob.setColor(1, 1, 1, 1);
        imageBase.setColor(1, 1, 1, 1);
    }

    public void moveTouchPad(float x, float y) {
        NzActorPositionner positionner = nzStage.getPositionner(imageBase, true);
        positionner.setPosition(x, y);
        nzStage.getPositionner(imageBase, true).getPosition(posBase);
        moveKnob(x, y);
    }

    public void moveKnob(float x, float y) {
        NzActorPositionner positionner = nzStage.getPositionner(imageKnob, true);
        positionner.setPosition(x, y);
        positionner.getPosition(posKnob);
        float dstToBase = posKnob.dst(posBase);
        if (dstToBase > sizeBase / 2) {
            Vector2 directionTo = V.directionTo(posKnob, posBase, new Vector2()); //TODO newV2
            if (fixedOnDrag) {
                posKnob.set(posBase).add(directionTo.scl(sizeBase / 2));
                nzStage.getPositionner(imageKnob, true).setPosition(posKnob);
            } else {
                posBase.add(directionTo.scl(dstToBase - sizeBase / 2));
                nzStage.getPositionner(imageBase, true).setPosition(posBase);
            }
        }
        this.updateDirForce();
    }

    public void activeOnStage() {
        nzStage.addActor(imageBase);
        nzStage.addActor(imageKnob);
    }

    public void desactiveOnStage() {
        imageBase.remove();
        imageKnob.remove();
    }

    public void dispose() {
        textureKnob.dispose();
        textureBase.dispose();
    }
}
