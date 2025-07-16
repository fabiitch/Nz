package com.github.fabiitch.nz.gdx.debug.profile;

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

    private final IntCounter callsCounter = new IntCounter();
    private final IntCounter drawCallsCounter = new IntCounter();
    private final IntCounter shaderSwitchesCounter = new IntCounter();
    private final IntCounter textureBindingsCounter = new IntCounter();

    @Override
    public void reset() {
        callsCounter.put(getCalls());
        drawCallsCounter.put(getDrawCalls());
        shaderSwitchesCounter.put(getShaderSwitches());
        textureBindingsCounter.put(getTextureBindings());
    }


    public float getCallsAverage() {
        return callsCounter.average;
    }

    public float getDrawCallAverage() {
        return drawCallsCounter.average;
    }

    public float getShaderSwitchesAverage() {
        return shaderSwitchesCounter.average;
    }

    public float getTextureBindingsAverage() {
        return textureBindingsCounter.average;
    }

    public float getVertexCountAverage() {
        return getVertexCount().average;
    }

    public void clear() {
        callsCounter.reset();
        drawCallsCounter.reset();
        shaderSwitchesCounter.reset();
        textureBindingsCounter.reset();
    }

}
