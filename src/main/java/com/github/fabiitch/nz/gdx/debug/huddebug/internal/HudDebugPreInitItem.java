package com.github.fabiitch.nz.gdx.debug.huddebug.internal;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;

import java.util.function.Consumer;

/**
 * Used in HudDebug before init();
 */
public class HudDebugPreInitItem<T> {
    public String key; // key in map for HudDebug.update(key,value)
    public String name; // name displayed
    public T value;
    public Array<Consumer<T>> consumers;
    public Color color;
    public int positionOnStage;

    public HudDebugPreInitItem(String key, String name, T value, int positionOnStage, Color color) {
        this.key = key;
        this.name = name;
        if (key == null)
            this.key = name;
        this.value = value;
        this.color = color;
        this.positionOnStage = positionOnStage;
    }
}
