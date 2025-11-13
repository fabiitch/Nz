package com.github.fabiitch.nz.gdx.debug.huddebug.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.profiling.GLProfiler;
import com.github.fabiitch.nz.gdx.debug.huddebug.HudDebug;
import com.github.fabiitch.nz.gdx.debug.huddebug.internal.HudDebugPosition;

public class HudGlProfiler {
    private GLProfiler glProfiler;
    HudDebugPosition STAGE_POSITION = HudDebugPosition.TOP_RIGHT;
    Color color = Color.RED;

    public HudGlProfiler(HudDebugPosition stagePosition, Color color) {
        this.STAGE_POSITION = stagePosition;
        this.color = color;
        start();
    }

    public HudGlProfiler() {
        start();
    }

    public void start() {
        if (glProfiler == null) {
            this.glProfiler = new GLProfiler(Gdx.graphics);
            glProfiler.enable();
            HudDebug.add("GlListener enabled", glProfiler + "", STAGE_POSITION, color);
            HudDebug.add("getCalls", 100000, STAGE_POSITION, color);
            HudDebug.add("getDrawCalls", 100000, STAGE_POSITION, color);
            HudDebug.add("getShaderSwitches", 100000, STAGE_POSITION, color);
            HudDebug.add("getTextureBindings", 100000, STAGE_POSITION, color);
            HudDebug.add("getVertexCountAverage", 100000, STAGE_POSITION, color);
        }
    }

    public void end() {
        glProfiler.disable();
        this.glProfiler = null;

        HudDebug.remove("GlListener enabled");
        HudDebug.remove("getCalls");
        HudDebug.remove("getDrawCalls");
        HudDebug.remove("getShaderSwitches");
        HudDebug.remove("getTextureBindings");
        HudDebug.remove("getVertexCountAverage");
    }

    public void update() {
        if (glProfiler != null) {
            HudDebug.update("GlListener enabled", glProfiler.isEnabled() + "");
            HudDebug.update("getCalls", glProfiler.getCalls());
            HudDebug.update("getDrawCalls", glProfiler.getDrawCalls());
            HudDebug.update("getShaderSwitches", glProfiler.getShaderSwitches());
            HudDebug.update("getTextureBindings", glProfiler.getTextureBindings());
            HudDebug.update("getVertexCountAverage", glProfiler.getVertexCount().average);
            glProfiler.reset();
        }
    }
}
