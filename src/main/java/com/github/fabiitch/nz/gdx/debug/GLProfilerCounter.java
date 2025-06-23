package com.github.fabiitch.nz.gdx.debug;

import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.profiling.GLProfiler;
import com.github.fabiitch.nz.java.utils.IntCounter;
import lombok.Getter;

@Getter
public class GLProfilerCounter extends GLProfiler {
    /**
     * Create a new instance of GLProfiler to monitor a {@link Graphics} instance's gl calls
     *
     * @param graphics instance to monitor with this instance, With Lwjgl 2.x you can pass in Gdx.graphics, with Lwjgl3 use
     *                 Lwjgl3Window.getGraphics()
     */
    public GLProfilerCounter(Graphics graphics) {
        super(graphics);
    }


    private final IntCounter callCounter = new IntCounter();
    private final IntCounter drawCallCounter = new IntCounter();
    private final IntCounter shaderSwitchesCounter = new IntCounter();
    private final IntCounter textureBindingsCounter = new IntCounter();

    public void update() {
        callCounter.put(getCalls());
        drawCallCounter.put(getDrawCalls());
        shaderSwitchesCounter.put(getShaderSwitches());
        textureBindingsCounter.put(getTextureBindings());
    }
    public void clear() {
        callCounter.reset();
        drawCallCounter.reset();
        shaderSwitchesCounter.reset();
        textureBindingsCounter.reset();
    }

}
