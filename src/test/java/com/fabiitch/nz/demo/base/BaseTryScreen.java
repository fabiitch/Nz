package com.fabiitch.nz.demo.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ScreenUtils;
import com.fabiitch.nz.debug.DT_Tracker;
import com.fabiitch.nz.debug.huddebug.HudDebug;
import com.fabiitch.nz.debug.huddebug.HudDebugPosition;
import com.fabiitch.nz.scene2D.nz.NzStage;

public abstract class BaseTryScreen extends ScreenAdapter {
    protected NzStage nzStage;
    protected Skin skin;
    protected HudDebug hudDebug;
    protected DT_Tracker dt_tracker;

    public BaseTryScreen() {
        this.nzStage = new NzStage();
        this.skin = new Skin(Gdx.files.internal("skins/ui/uiskin.json"));
        this.hudDebug = new HudDebug(nzStage, skin);
        this.dt_tracker = new DT_Tracker(HudDebugPosition.TOP_LEFT, Color.WHITE, 60, 120);
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

    @Override
    public void render(float delta) {
        Gdx.graphics.setTitle(this.getClass().getSimpleName()+" FPS:"+Gdx.graphics.getFramesPerSecond());
        ScreenUtils.clear(Color.BLACK, true);
        dt_tracker.start();
        nzStage.act();
        nzStage.draw();
        dt_tracker.end();
        dt_tracker.updateHud();
    }

    @Override
    public final void dispose() {
        nzStage.dispose();
        skin.dispose();
    }

}
