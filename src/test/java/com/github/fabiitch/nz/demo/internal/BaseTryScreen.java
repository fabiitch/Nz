package com.github.fabiitch.nz.demo.internal;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.profiling.GLProfiler;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ScreenUtils;
import com.github.fabiitch.nz.gdx.debug.DT_Tracker;
import com.github.fabiitch.nz.gdx.debug.huddebug.HudDebug;
import com.github.fabiitch.nz.gdx.debug.huddebug.internal.HudDebugPosition;
import com.github.fabiitch.nz.gdx.scene2D.nz.NzStage;

public abstract class BaseTryScreen extends ScreenAdapter {
    protected NzStage nzStage;
    protected Skin skin;
    protected HudDebug hudDebug;
    protected DT_Tracker dt_tracker;
    private GLProfiler glProfiler;
    public FPSLogger fpsLogger;

    public BaseTryScreen() {
        this.nzStage = new NzStage();
        this.skin = new Skin(Gdx.files.internal("skins/ui/uiskin.json"));
        this.hudDebug = new HudDebug(nzStage, skin);
        this.dt_tracker = new DT_Tracker(HudDebugPosition.TOP_LEFT, Color.WHITE);

        fpsLogger = new FPSLogger(50);
    }

    public void hudMsg(String msg) {
        HudDebug.addTopLeft("", msg);
    }

    protected void hudMsg(String s, Color color) {
        HudDebug.addTopLeft(s, "", color);
    }

    protected void hudMsg(String key, String value) {
        HudDebug.addTopLeft(key, value);
    }

    protected void hudMsg(String key, String value, Color color) {
        HudDebug.addTopLeft(key, value, color);
    }

    @Override
    public void show() {
        Gdx.graphics.setTitle(this.getClass().getSimpleName());
    }


    public void hudGlProfiler() {
        if (glProfiler == null) {
            int positionOnStage = HudDebugPosition.TOP_RIGHT;
            Color color = Color.RED;
            this.glProfiler = new GLProfiler(Gdx.graphics);
            glProfiler.enable();
            HudDebug.add("GlListener enabled", glProfiler + "", positionOnStage, color);
            HudDebug.add("getCalls", 100000, positionOnStage, color);
            HudDebug.add("getDrawCalls", 100000, positionOnStage, color);
            HudDebug.add("getShaderSwitches", 100000, positionOnStage, color);
            HudDebug.add("getTextureBindings", 100000, positionOnStage, color);
            HudDebug.add("getVertexCountAverage", 100000, positionOnStage, color);
        } else {
            HudDebug.update("GlListener enabled", glProfiler.isEnabled() + "");
            HudDebug.update("getCalls", glProfiler.getCalls());
            HudDebug.update("getDrawCalls", glProfiler.getDrawCalls());
            HudDebug.update("getShaderSwitches", glProfiler.getShaderSwitches());
            HudDebug.update("getTextureBindings", glProfiler.getTextureBindings());
            HudDebug.update("getVertexCountAverage", glProfiler.getVertexCount().average);
        }
    }

    @Override
    public void render(float delta) {
        dt_tracker.start();
        fpsLogger.log();
        Gdx.graphics.setTitle(this.getClass().getSimpleName() + " FPS:" + Gdx.graphics.getFramesPerSecond());
        ScreenUtils.clear(Color.BLACK, true);
        nzStage.act();
        nzStage.draw();
        if (glProfiler != null) {
            hudGlProfiler();
            glProfiler.reset();
        }
        dt_tracker.end();
        dt_tracker.updateHud();
    }

    @Override
    public void dispose() {
        nzStage.dispose();
        skin.dispose();
    }

}
