package com.github.fabiitch.nz.gdx.scene2D.widgets.touchpad;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.github.fabiitch.nz.gdx.scene2D.nz.NzActorPositionner;
import com.github.fabiitch.nz.gdx.scene2D.nz.NzStage;
import com.github.fabiitch.nz.java.math.percent.Percentage;
import com.github.fabiitch.nz.java.math.vectors.V;

public abstract class TouchPad {
    protected final NzStage nzStage;

    public Image imageBase;//TODO
    public Image imageKnob;

    public boolean pressed;
    private boolean disabled;

    protected final Vector2 posBase = new Vector2();
    protected final Vector2 posKnob = new Vector2();

    private final Vector2 internalV2 = new Vector2();

    public Vector2 direction = new Vector2(); //always nor
    public float intensity = 0f; //alpha

    protected TouchPadConfig config;

    public TouchPad(NzStage nzStage,
                    TouchPadConfig config) {
        this.nzStage = nzStage;
        this.config = config;
        this.init(config);
    }

    private void init(TouchPadConfig config) {
        imageBase = new Image(config.getTextureBase());
        NzActorPositionner positionner = nzStage.getPositionner(imageBase, true);
        positionner.setSize(config.getSizeBase()).setPosition(config.getPosInactive());
        positionner.getPosition(posBase);
        nzStage.addActor(imageBase);

        imageKnob = new Image(config.getTextureKnob());
        positionner = nzStage.getPositionner(imageKnob, true);
        positionner.setSize(config.getSizeKnob()).setPosition(config.getPosInactive());
        nzStage.addActor(imageKnob);
        touchUp();
    }

    private void updateDirForce() {
        this.direction.set(posKnob).sub(posBase).nor();
        float dst = posBase.dst(posKnob);
        this.intensity = Percentage.alpha(dst, config.getSizeBase() / 2);
    }


    public void pressed() {
        pressed = true;
        imageKnob.setColor(1, 1, 1, 1);
        imageBase.setColor(1, 1, 1, 1);
    }

    public void unPressed() {
        pressed = false;

        imageKnob.setColor(1, 1, 1, 0.2f);
        imageBase.setColor(1, 1, 1, 0.2f);
    }

    public void resetPosition() {
        direction.setZero();
        intensity = 0;
        nzStage.getPositionner(imageBase, true).setPosition(config.getPosInactive());
        nzStage.getPositionner(imageKnob, true).setPosition(config.getPosInactive());
    }

    public boolean touchDown(float x, float y) {
        if (config.isFixedOnTouchDown()) {
            float dstToBase = posBase.dst(x, y);
            if (dstToBase > (config.getSizeBase() / 2) + Percentage.value(10, config.getSizeBase()))
                return false;
        } else {
            NzActorPositionner positionner = nzStage.getPositionner(imageBase, true);
            positionner.setPosition(x, y);
        }
        pressed();

        nzStage.getPositionner(imageBase, true).getPosition(posBase);
        touchDragged(x, y);
        return true;
    }

    public void touchDragged(float x, float y) {
        if (!pressed)
            return;
        NzActorPositionner positionner = nzStage.getPositionner(imageKnob, true);
        positionner.setPosition(x, y);
        positionner.getPosition(posKnob);
        float dstToBase = posKnob.dst(posBase);
        if (dstToBase > config.getSizeBase() / 2) {
            Vector2 directionTo = V.directionTo(posKnob, posBase, internalV2);
            if (config.isFixedOnDrag()) {
                posKnob.set(posBase).add(directionTo.scl(config.getSizeBase() / 2));
                nzStage.getPositionner(imageKnob, true).setPosition(posKnob);
            } else {
                posBase.add(directionTo.scl(dstToBase - config.getSizeBase() / 2));
                nzStage.getPositionner(imageBase, true).setPosition(posBase);
            }
        }
        this.updateDirForce();
    }

    public Vector3 compute(float x, float y, Vector3 result) {
        Vector2 knobPos = new Vector2(x, y);
        float dstToBase = knobPos.dst(posBase);
        Vector2 direction = this.internalV2.set(knobPos).sub(posBase).nor();
        float force = Percentage.alpha(dstToBase, config.getSizeBase() / 2);
        force = Math.max(1, force);
        result.set(direction, force);
        return result;
    }

    public void touchUp() {
        resetPosition();
        unPressed();
    }


    public void setStickPos(Vector2 direction, float force) {
        Vector2 center = posBase.cpy();
        float dstToAdd = force * config.getSizeBase() / 2;
        Vector2 pos = center.cpy().add(new Vector2(1, 0).setAngleDeg(direction.angleDeg()).setLength(dstToAdd));
        if (this.pressed) {
            touchDragged(pos.x, pos.y);
        } else {
            touchDown(pos.x, pos.y);
        }
    }

    public void addToStage() {
        nzStage.addActor(imageBase);
        nzStage.addActor(imageKnob);
    }

    public void removeToStage() {
        imageBase.remove();
        imageKnob.remove();
    }

    public void dispose() {
        config.getTextureBase().dispose();
        config.getTextureKnob().dispose();
    }
}
