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
        id = 0;
        key =null;
        value = null;
        duration =0f;
        color = Color.WHITE;
        position = null;
        elapsedTime = 0;
    }
}
