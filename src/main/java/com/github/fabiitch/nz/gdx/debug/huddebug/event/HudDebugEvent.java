package com.github.fabiitch.nz.gdx.debug.huddebug.event;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Pool;
import com.github.fabiitch.nz.gdx.debug.huddebug.internal.HudDebugPosition;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class HudDebugEvent implements Pool.Poolable {
    public long id;
    public String key;
    public Object value;
    public float duration;//seconds
    public Color color;

    public HudDebugPosition position;
    public float elapsedTime;


    @Override
    public void reset() {
        elapsedTime = 0;
        value = null;
    }
}
