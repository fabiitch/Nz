package com.github.fabiitch.nz.demo.internal;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ScreenUtils;
import com.github.fabiitch.nz.demo.internal.huds.HudGlProfiler;
import com.github.fabiitch.nz.demo.internal.input.InputKeyBinder;
import com.github.fabiitch.nz.gdx.debug.DT_Tracker;
import com.github.fabiitch.nz.gdx.debug.huddebug.HudDebug;
import com.github.fabiitch.nz.gdx.debug.huddebug.HudDebugTracker;
import com.github.fabiitch.nz.gdx.debug.huddebug.internal.HudDebugPosition;
import com.github.fabiitch.nz.gdx.input.KeyboardCameraController;
import com.github.fabiitch.nz.gdx.render.shape.NzShapeRenderer;
import com.github.fabiitch.nz.gdx.scene2D.nz.NzStage;

public abstract class BaseDemoScreen extends ScreenAdapter {

    protected NzStage nzStage;
    protected Skin skin;
    protected HudDebug hudDebug;
    protected HudDebugTracker hudDebugTracker;
    protected DT_Tracker dt_tracker;
    protected HudGlProfiler hudGlProfiler;

    protected InputMultiplexer inputMultiplexer = new InputMultiplexer();

    protected InputKeyBinder inputKeyBinder = new InputKeyBinder();

    private KeyboardCameraController keyboardCameraController;
    protected Camera camera;

    public FPSLogger fpsLogger;

    protected SpriteBatch spriteBatch = new SpriteBatch();
    protected NzShapeRenderer shapeRenderer = new NzShapeRenderer();

    public BaseDemoScreen() {
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        this.nzStage = new NzStage();
        this.skin = new Skin(Gdx.files.internal("skins/ui/uiskin.json"));
        this.hudDebug = new HudDebug(nzStage, skin);
        this.hudDebugTracker = new HudDebugTracker();
        this.dt_tracker = new DT_Tracker(HudDebugPosition.TOP_LEFT, Color.WHITE);
        shapeRenderer.setAutoShapeType(true);
        hudGlProfiler = new HudGlProfiler();

        fpsLogger = new FPSLogger(50);

        keyboardCameraController = new KeyboardCameraController(camera);
        inputMultiplexer.addProcessor(keyboardCameraController);
        inputMultiplexer.addProcessor(inputKeyBinder);
        Gdx.input.setInputProcessor(inputMultiplexer);
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
        dt_tracker.start();
        fpsLogger.log();
        Gdx.graphics.setTitle(this.getClass().getSimpleName() + " FPS:" + Gdx.graphics.getFramesPerSecond());
        ScreenUtils.clear(Color.BLACK, true);

        hudGlProfiler.update();
        dt_tracker.end();
        dt_tracker.updateHud();
        hudDebugTracker.update();
        nzStage.act();
        nzStage.draw();


        keyboardCameraController.update();
        camera.update();
        spriteBatch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);
    }

    @Override
    public void dispose() {
        nzStage.dispose();
        skin.dispose();
        spriteBatch.dispose();
        shapeRenderer.dispose();
    }

}
