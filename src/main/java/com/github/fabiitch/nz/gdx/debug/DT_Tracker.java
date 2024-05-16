package com.github.fabiitch.nz.gdx.debug;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.TimeUtils;
import com.github.fabiitch.nz.gdx.debug.huddebug.HudDebug;
import com.github.fabiitch.nz.gdx.debug.huddebug.internal.HudDebugPosition;
import com.github.fabiitch.nz.java.time.timers.TimeLocker;

public class DT_Tracker {

    private static final String RENDER_DURATION = "render()";

    public long nanoFrameStart;
    public long nanoFrame;
    private final TimeLocker timeLocker = new TimeLocker(1);


    public DT_Tracker(int positionOnStage, Color color) {
        removeFromHudDebug();//clean older
        HudDebug.add(RENDER_DURATION, 100000f, positionOnStage, color);
    }

    public DT_Tracker() {
        this(HudDebugPosition.TOP_LEFT, Color.RED);
    }


    public void start() {
        this.nanoFrameStart = TimeUtils.millis();
    }

    public void end() {
        nanoFrame = TimeUtils.millis() - nanoFrameStart;
    }

    /**
     * Call with your total render time method
     */
    public void updateHud() {
        if (timeLocker.isLock(Gdx.graphics.getDeltaTime())) {
            return;
        }
        HudDebug.update(RENDER_DURATION, DebugDisplayUtils.printMs(nanoFrame));
    }

    public void removeFromHudDebug() {
        HudDebug.remove(RENDER_DURATION);
    }

}
