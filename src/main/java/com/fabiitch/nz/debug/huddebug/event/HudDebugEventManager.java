package com.fabiitch.nz.debug.huddebug.event;

import com.badlogic.gdx.utils.Array;
import com.fabiitch.nz.debug.huddebug.HudDebug;
import com.fabiitch.nz.debug.huddebug.internal.HudDebugPosition;

public class HudDebugEventManager {
    private final static String KEY_HUD_DEBUG_EVENT = "HudDebugEvent##";

    private Array<HudDebugEvent> debugEvents = new Array<>();
    private Array<HudDebugEvent> debugEventFiltered = new Array<>();

    private long idCount;

    public void update(long currentTimeMillis) {
        for (int i = 0; i < debugEvents.size; i++) {
            HudDebugEvent event = debugEvents.get(i);

            if (event.millisStart + ((long) event.duration * 1000) <= currentTimeMillis) {
                HudDebug.remove(KEY_HUD_DEBUG_EVENT + event.id);
            } else {
                HudDebug.update(KEY_HUD_DEBUG_EVENT + event.id, event.value);
                debugEventFiltered.add(event);
            }
        }
        debugEvents.clear();
        debugEvents.addAll(debugEventFiltered);
        debugEventFiltered.clear();
    }

    public void addEvent(HudDebugEvent event) {
        if (idCount == Long.MAX_VALUE)
            idCount = 0;
        idCount++;
        event.id = idCount;
        HudDebug.add(KEY_HUD_DEBUG_EVENT + event.id, event.name, event.value, HudDebugPosition.TOP_RIGHT, event.color);
        debugEvents.add(event);
    }
}
