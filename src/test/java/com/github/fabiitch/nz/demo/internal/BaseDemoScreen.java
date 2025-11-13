package com.github.fabiitch.nz.demo.internal;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.github.fabiitch.nz.gdx.debug.huddebug.impl.HudDebugGLProfiler;
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
    protected HudDebugGLProfiler hudDebugGlProfiler;

    protected InputMultiplexer inputMultiplexer = new InputMultiplexer();
    protected InputKeyBinder inputKeyBinder = new InputKeyBinder();

    private final KeyboardCameraController keyboardCameraController;
    protected Camera camera;
    protected Viewport viewport;

    public FPSLogger fpsLogger;

    protected SpriteBatch spriteBatch = new SpriteBatch();
    protected NzShapeRenderer shapeRenderer = new NzShapeRenderer();

    public BaseDemoScreen() {
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewport = new ScreenViewport(camera);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);

        this.nzStage = new NzStage();
        this.skin = new Skin(Gdx.files.internal("skins/ui/uiskin.json"));
        this.hudDebug = new HudDebug(nzStage, skin);
        this.hudDebugTracker = new HudDebugTracker();
        this.dt_tracker = new DT_Tracker(HudDebugPosition.TOP_LEFT, Color.WHITE);
        shapeRenderer.setAutoShapeType(true);
        hudDebugGlProfiler = new HudDebugGLProfiler();

        fpsLogger = new FPSLogger(50);

        keyboardCameraController = new KeyboardCameraController(camera);
        inputMultiplexer.addProcessor(keyboardCameraController);
        inputMultiplexer.addProcessor(inputKeyBinder);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    private final Vector2 tmp = new Vector2();

    public Vector2 project(float x, float y) {
        return viewport.project(tmp.set(x, y));
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


    public abstract void doRender(float dt);

    @Override
    public void render(float dt) {
        dt_tracker.start();
        fpsLogger.log();
        Gdx.graphics.setTitle(this.getClass().getSimpleName() + " FPS:" + Gdx.graphics.getFramesPerSecond());
        ScreenUtils.clear(Color.BLACK, true);
        dt_tracker.updateHud();
        hudDebugTracker.update();

        keyboardCameraController.update();
        camera.update();

        nzStage.act();
        nzStage.draw();

        spriteBatch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);
        doRender(dt);
        dt_tracker.end();
        hudDebugGlProfiler.update();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    public abstract void doDispose();

    @Override
    public void dispose() {
        nzStage.dispose();
        skin.dispose();
        spriteBatch.dispose();
        shapeRenderer.dispose();
    }

}
