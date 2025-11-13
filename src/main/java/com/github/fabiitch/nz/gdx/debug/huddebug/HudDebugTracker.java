package com.github.fabiitch.nz.gdx.debug.huddebug;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ObjectMap;
import com.github.fabiitch.nz.gdx.debug.huddebug.internal.HudDebugPosition;

import java.util.function.Supplier;

public class HudDebugTracker {

    private final ObjectMap<String, Supplier<Object>> trackers = new ObjectMap<>();

    public void update() {
        for (ObjectMap.Entry<String, Supplier<Object>> entry : trackers.entries()) {
            HudDebug.update(entry.key, entry.value.get());
        }
    }
    public void track(String key, Supplier<Object> trackFunction) {
        HudDebug.add(key, trackFunction.get(), HudDebugPosition.TOP_LEFT, Color.WHITE);
        trackers.put(key, trackFunction);
    }
    public void track(String key, Supplier<Object> trackFunction, HudDebugPosition position) {
        HudDebug.add(key, trackFunction.get(), position, Color.WHITE);
        trackers.put(key, trackFunction);
    }

    public void track(String key, Supplier<Object> trackFunction, HudDebugPosition position, Color color) {
        HudDebug.add(key, trackFunction.get(), position, color);
        trackers.put(key, trackFunction);
    }

    public void unTrack(String key) {
        HudDebug.remove(key);
        trackers.remove(key);
    }

    public void clear(){
        trackers.keys().forEach(HudDebug::remove);
        trackers.clear();
    }
}
