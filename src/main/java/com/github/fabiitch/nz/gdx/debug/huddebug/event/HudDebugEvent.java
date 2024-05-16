package com.github.fabiitch.nz.gdx.debug.huddebug.event;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Pool;

public class HudDebugEvent implements Pool.Poolable {

    public Color color;
    public String key;
    public Object value;
    public float duration;//seconds
    public long millisStart;
    public long id;

    private HudDebugEvent() {

    }

    public static HudDebugEvent get(String key, Object value, float duration, Color color) {
        HudDebugEvent event = new HudDebugEvent(); //TODO pools
        event.key = key;
        event.value = value;
        event.duration = duration;
        event.color = color;
        event.millisStart = System.currentTimeMillis(); //TODO
        return event;
    }

    @Override
    public void reset() {

    }
}
