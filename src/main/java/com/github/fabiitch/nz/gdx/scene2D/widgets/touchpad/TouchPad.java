package com.github.fabiitch.nz.gdx.scene2D.widgets.touchpad;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.github.fabiitch.nz.java.math.utils.Percentage;
import com.github.fabiitch.nz.java.math.vectors.V;
import com.github.fabiitch.nz.gdx.scene2D.nz.NzActorPositionner;
import com.github.fabiitch.nz.gdx.scene2D.nz.NzStage;

public abstract class TouchPad {

    protected final Texture textureBase;
    protected final Texture textureKnob;

    protected final NzStage nzStage;
    protected boolean fixedOnDrag; //fixedOnDrag after activation
    protected boolean fixedOnTouchDown; //fixedOnDrag after activation

    public Image imageBase;//TODO
    public Image imageKnob;

    public boolean isActive;

    protected final Vector2 posBase = new Vector2();
    protected final Vector2 posKnob = new Vector2();
    protected final Vector2 posInactive;

    protected float sizeBase;
    protected float sizeKnob;

    private final Vector2 internalV2 = new Vector2();

    public Vector2 direction = new Vector2(); //always nor
    public float force = 0f;

    public TouchPad(NzStage nzStage,
                    Texture textureBase, Texture textureKnob,
                    Vector2 posInactive,
                    float sizeBase, float sizeKnob,
                    boolean fixedOnTouchDown, boolean fixedOnDrag) {
        this.nzStage = nzStage;
        this.textureBase = textureBase;
        this.textureKnob = textureKnob;
        this.sizeBase = sizeBase;
        this.sizeKnob = sizeKnob;

        this.posInactive = posInactive;
        this.fixedOnDrag = fixedOnDrag;
        this.fixedOnTouchDown = fixedOnTouchDown;
        this.init(textureBase, textureKnob);
    }

    private void init(Texture textureBase, Texture textureKnob) {
        imageBase = new Image(textureBase);
        NzActorPositionner positionner = nzStage.getPositionner(imageBase, true);
        positionner.setSize(sizeBase).setPosition(posInactive.x, posInactive.y);
        positionner.getPosition(posBase);
        nzStage.addActor(imageBase);

        imageKnob = new Image(textureKnob);
        positionner = nzStage.getPositionner(imageKnob, true);
        positionner.setSize(sizeKnob).setPosition(posInactive.x, posInactive.y);
        nzStage.addActor(imageKnob);
        touchUp();
    }

    public void updateDirForce() {
        this.direction.set(posKnob).sub(posBase).nor();
        float dst = posBase.dst(posKnob);
        this.force = Percentage.alpha(dst, sizeBase / 2);
    }


    public void active() {
        isActive = true;
        imageKnob.setColor(1, 1, 1, 1);
        imageBase.setColor(1, 1, 1, 1);
    }

    public boolean touchDown(float x, float y) {
        if (fixedOnTouchDown) {
            float dstToBase = posBase.dst(x, y);
            if (dstToBase > (sizeBase / 2) + Percentage.value(10, sizeBase))
                return false;
        } else {
            NzActorPositionner positionner = nzStage.getPositionner(imageBase, true);
            positionner.setPosition(x, y);
        }
        active();

        nzStage.getPositionner(imageBase, true).getPosition(posBase);
        touchDragged(x, y);
        return true;
    }

    public void touchDragged(float x, float y) {
        if(!isActive)
            return;
        NzActorPositionner positionner = nzStage.getPositionner(imageKnob, true);
        positionner.setPosition(x, y);
        positionner.getPosition(posKnob);
        float dstToBase = posKnob.dst(posBase);
        if (dstToBase > sizeBase / 2) {
            Vector2 directionTo = V.directionTo(posKnob, posBase, internalV2);
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

    public void touchUp() {
        direction.setZero();
        force = 0;
        isActive = false;

        imageKnob.setColor(1, 1, 1, 0.2f);
        imageBase.setColor(1, 1, 1, 0.2f);

        nzStage.getPositionner(imageBase, true).setPosition(posInactive.x, posInactive.y);
        nzStage.getPositionner(imageKnob, true).setPosition(posInactive.x, posInactive.y);
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
