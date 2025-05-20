package com.github.fabiitch.nz.gdx.debug.huddebug.event;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.ReflectionPool;
import com.github.fabiitch.nz.gdx.debug.huddebug.HudDebug;
import com.github.fabiitch.nz.gdx.debug.huddebug.internal.HudDebugPosition;

import java.util.HashMap;

public class HudDebugEventManager extends Group {
    private final static String PREFIX_KEY = "HudDebugEvent##";
    private final static float DEFAULT_DURATION_S = 5f;
    private final Array<HudDebugEvent> events = new Array<>();
    private final Array<HudDebugEvent> eventFilteredTmp = new Array<>();
    private final HashMap<String, HudDebugEvent> eventMap = new HashMap<>();

    private long idCount;

    private final Pool<HudDebugEvent> eventPool = new ReflectionPool<>(HudDebugEvent.class);
    @Override
    public void act(float delta) {
        super.act(delta);
        for (int i = 0; i < events.size; i++) {
            HudDebugEvent event = events.get(i);
            event.elapsedTime += delta;
            if (event.elapsedTime >= event.duration) {
                HudDebug.remove(PREFIX_KEY + event.id);
                eventMap.remove(event.key);
            } else {
                HudDebug.update(PREFIX_KEY + event.id, event.value);
                eventFilteredTmp.add(event);
            }
        }
        events.clear();
        events.addAll(eventFilteredTmp);
        eventFilteredTmp.clear();
    }
    public HudDebugEvent addEvent(String key, Object value, float duration, Color color, HudDebugPosition position) {
        HudDebugEvent event = eventMap.get(key);
        if (event != null) {
            event.value = value;
            event.duration = duration;
            event.color = color;
            event.elapsedTime = 0;

            if (event.position != position) {
                event.position = position;
                HudDebug.remove(PREFIX_KEY + event.id);
                HudDebug.add(PREFIX_KEY + event.id, event.key, event.value, position, event.color);
            } else {
                HudDebug.update(PREFIX_KEY + event.id, event.value);
            }
        } else {
            event = eventPool.obtain();
            event.key = key;
            event.id = idCount++;

            event.value = value;
            event.duration = duration;
            event.color = color;
            event.position = position;
            HudDebug.add(PREFIX_KEY + event.id, event.key, event.value, position, event.color);
            events.add(event);
            eventMap.put(event.key, event);
        }

        return event;
    }

    public HudDebugEvent addEvent(String key, Object value, float duration, Color color) {
        return this.addEvent(key, value, duration, color, HudDebugPosition.TOP_RIGHT);
    }

    public HudDebugEvent addEvent(String key, Object value, float duration) {
        return this.addEvent(key, value, duration, Color.WHITE, HudDebugPosition.TOP_RIGHT);
    }

    public HudDebugEvent addEvent(String key, Object value) {
        return this.addEvent(key, value, DEFAULT_DURATION_S, Color.WHITE, HudDebugPosition.TOP_RIGHT);
    }



    public void remove(HudDebugEvent event) {
        HudDebug.remove(PREFIX_KEY + event.id);
        eventMap.remove(event.key);
        events.removeValue(event, true);
    }

    public void remove(String key) {
        HudDebugEvent existEvent = eventMap.get(key);
        if (existEvent != null) {
            remove(existEvent);
        }
    }

    public void clear() {
        for (HudDebugEvent value : eventMap.values()) {
            remove(value);
        }
    }

}


