package com.fabiitch.nz.debug.huddebug.event;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Pool;

public class HudDebugEvent implements Pool.Poolable {

    public Color color;
    public String name;
    public Object value;
    public float duration;
    public long millisStart;
    public long id;

    private HudDebugEvent() {

    }

    public static HudDebugEvent get(String name, Object value, float duration, Color color) {
        HudDebugEvent event = new HudDebugEvent();
        event.name = name;
        event.value = value;
        event.duration = duration;
        event.color = color;
        event.millisStart = System.currentTimeMillis();
        return event;
    }

    @Override
    public void reset() {

    }
}
