package com.fabiitch.nz.debug;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.TimeUtils;
import com.fabiitch.nz.debug.huddebug.HudDebug;
import com.fabiitch.nz.debug.huddebug.HudDebugPosition;
import com.fabiitch.nz.math.utils.Percentage;
import com.fabiitch.nz.utils.TimeLocker;

import java.util.concurrent.TimeUnit;

public class DT_Tracker {

    private static final String TOTAL_TIME = "render()";
    private static final String TOTAL_TIME_PERCENT = "% to";
    private static final String MAX_FPS_POSSIBLE = "Max Fps possible";
    private static final String DT_SEPARATOR = "-----";

    private static final int FPS_DEFAULT = 100;

    public int[] fpsTargets;

    private long nanoFrameStart;
    private long nanoFrame;
    private final TimeLocker timeLocker = new TimeLocker(1);


    public DT_Tracker(int positionOnStage, Color color, int... fpsTargets) {
        this.fpsTargets = fpsTargets;
        HudDebug.add(TOTAL_TIME, 100000f, positionOnStage, color);
//
        removeFromHudDebug();//clean older
        for (int fpsTarget : fpsTargets) {
            HudDebug.add(TOTAL_TIME_PERCENT + fpsTarget + "FPS", "100%", positionOnStage, color);
        }
        HudDebug.add(MAX_FPS_POSSIBLE, 500, positionOnStage, color);
        HudDebug.add(DT_SEPARATOR, DT_SEPARATOR, positionOnStage, color);

    }

    public DT_Tracker() {
        this(HudDebugPosition.TOP_LEFT, Color.WHITE, FPS_DEFAULT);
    }

    public DT_Tracker(int... fpsTargets) {
        this(HudDebugPosition.TOP_LEFT, Color.WHITE, fpsTargets);
    }


    public void start() {
        this.nanoFrameStart = TimeUtils.nanoTime();
    }

    public void end() {
        nanoFrame = TimeUtils.nanoTime() - nanoFrameStart;
    }

    /**
     * Call with your total render time method
     */
    public void updateHud() {
        if(timeLocker.isLock(Gdx.graphics.getDeltaTime())){
            return;
        }
        HudDebug.update(TOTAL_TIME, DebugDisplayUtils.printNanoToMs(nanoFrame));
        for (int fpsTarget : fpsTargets) {
            HudDebug.update(TOTAL_TIME_PERCENT + fpsTarget,
                    Percentage.getPercent(nanoFrame, TimeUnit.SECONDS.toNanos(1) / fpsTarget) + " %");
        }
        long maxFps = TimeUnit.SECONDS.toNanos(1) / nanoFrame;
        HudDebug.update(MAX_FPS_POSSIBLE, maxFps);

    }

    public void removeFromHudDebug() {
        for (int fpsTarget : fpsTargets) {
            HudDebug.remove(TOTAL_TIME_PERCENT + fpsTarget + "FPS");
        }
        HudDebug.remove(TOTAL_TIME_PERCENT);
        HudDebug.remove(MAX_FPS_POSSIBLE);
        HudDebug.remove(DT_SEPARATOR);
    }

}
