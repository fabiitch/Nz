package com.fabiitch.nz.screens.base;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.fabiitch.nz.render.NzShapeRenderer;

public class TestScreenRender<TS extends TestScreen> implements Screen {
    public TS screen;
    protected SpriteBatch spriteBatch = new SpriteBatch();
    protected NzShapeRenderer shapeRenderer = new NzShapeRenderer();
    protected ModelBatch modelBatch = new ModelBatch();

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
