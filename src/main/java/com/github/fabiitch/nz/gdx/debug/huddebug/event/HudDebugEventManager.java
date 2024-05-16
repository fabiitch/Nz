package com.github.fabiitch.nz.gdx.debug.huddebug.event;

import com.badlogic.gdx.utils.Array;
import com.github.fabiitch.nz.gdx.debug.huddebug.HudDebug;
import com.github.fabiitch.nz.gdx.debug.huddebug.internal.HudDebugPosition;

import java.util.HashMap;

public class HudDebugEventManager {
    private final static String PREFIX_KEY = "HudDebugEvent##";

    private Array<HudDebugEvent> events = new Array<>();
    private Array<HudDebugEvent> eventFilteredTmp = new Array<>();

    private HashMap<String, HudDebugEvent> eventMap = new HashMap<>();

    private long idCount;

    public void update() {
        long currentTimeMillis = System.currentTimeMillis();

        for (int i = 0; i < events.size; i++) {
            HudDebugEvent event = events.get(i);
            if (event.millisStart + ((long) event.duration * 1000) <= currentTimeMillis) {
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

    public void addEvent(HudDebugEvent event) {
        HudDebugEvent existEvent = eventMap.get(event.key);

        if (existEvent != null) {
            existEvent.value = event.value;
            HudDebug.update(PREFIX_KEY + existEvent.id, event.value);
            existEvent.millisStart = System.currentTimeMillis(); //TODO
        } else {
            if (idCount == Long.MAX_VALUE)
                idCount = 0;
            event.id = idCount++;
            HudDebug.add(PREFIX_KEY + event.id, event.key, event.value, HudDebugPosition.TOP_RIGHT, event.color);
            events.add(event);
            eventMap.put(event.key, event);
        }
    }
}
