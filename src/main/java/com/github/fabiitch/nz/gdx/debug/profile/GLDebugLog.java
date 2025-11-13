package com.github.fabiitch.nz.gdx.debug.profile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.profiling.GLProfiler;

public class GLDebugLog {
    private final GLProfiler glProfiler = new GLProfiler(Gdx.graphics);
    private static boolean started = false;

    public void start() {
        if (!started) {
            glProfiler.enable();
            started = true;
        }
        glProfiler.reset();
    }

    public void log() {
        System.out.println("===== GL DEBUG =====");
        System.out.println("DrawCalls:        " + glProfiler.getDrawCalls());
        System.out.println("ShaderSwitches:   " + glProfiler.getShaderSwitches());
        System.out.println("TextureBindings:  " + glProfiler.getTextureBindings());
        System.out.println("---------------------");
        System.out.println("VertexCount.max:  " + glProfiler.getVertexCount().max);
        System.out.println("VertexCount.min:  " + glProfiler.getVertexCount().min);
        System.out.println("VertexCount.total:" + glProfiler.getVertexCount().total);
        System.out.println("====================\n");
    }
}
