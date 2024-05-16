package com.github.fabiitch.nz.gdx.debug.huddebug.internal;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.github.fabiitch.nz.gdx.debug.DebugDisplayUtils;
import com.github.fabiitch.nz.gdx.utils.GdxUtils;

import java.util.function.Consumer;

/**
 * Used in HudDebug
 */
public class HudDebugLabel<T> extends Label {
    private static final String SEPARATOR = " : ";
    public String name;
    public T value;

    public Array<Consumer<T>> consumers;

    public int positionOnStage;
    public int order;
    float maxWitdh; //only for right/mid

    public HudDebugLabel(String name, int positionOnStage, int order, T value, Skin skin) {
        super(name + SEPARATOR + DebugDisplayUtils.printValue(value), skin);
        if (GdxUtils.isMobile()) {
            this.setWidth(this.getWidth() * 2);
            this.setHeight(this.getHeight() * 2);
            this.setFontScale(2);
        }
        this.name = name;
        this.value = value;
        this.positionOnStage = positionOnStage;
        this.order = order;
        this.setTouchable(Touchable.disabled);
    }

    public void updateNameAndValue(String name, T value) {
        this.name = name;
        this.value = value;
        updateValue(value);
    }

    public void updateValue(){
        this.updateValue(value);
    }

    public void updateValue(T value) {
        this.setText(this.name + SEPARATOR + DebugDisplayUtils.printValue(value));
        //cancel shake
        if (HudDebugPosition.isRight(this.positionOnStage) || HudDebugPosition.isMiddleHorizontal(this.positionOnStage)) {
            maxWitdh = Math.max(maxWitdh, getPrefWidth());
            setSize(maxWitdh, getPrefHeight());
        }

        if (consumers != null)
            for (Consumer<T> consumer : consumers) {
                consumer.accept(value);
            }
    }
}
