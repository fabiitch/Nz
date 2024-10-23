package com.github.fabiitch.nz.demo.quadtree;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.github.fabiitch.nz.java.data.quadtree.QuadTree;
import com.github.fabiitch.nz.java.data.quadtree.QuadTreeRenderer;
import com.github.fabiitch.nz.gdx.debug.huddebug.HudDebug;
import com.github.fabiitch.nz.demo.internal.BaseTryScreen;
import com.github.fabiitch.nz.java.math.shapes.builders.RectangleBuilder;
import com.github.fabiitch.nz.java.math.vectors.V3;
import com.github.fabiitch.nz.gdx.render.shape.NzShapeRenderer;

import java.util.ArrayList;


public abstract class BaseDemoQuadTree<T> extends BaseTryScreen {

    public NzShapeRenderer shapeRenderer;
    public QuadTree<QuadData<T>> quadT;
    public OrthographicCamera camera;
    protected QuadTreeRenderer quadRender;
    public InputMultiplexer inputMultiplexer = new InputMultiplexer();

    private Vector2 cameraVelocity = new Vector2();
    private final Vector2 tmpVel = new Vector2();
    public float baseVelocity = 10;

    public volatile int indexQuad = 1;

    public ArrayList<QuadData<T>> values = new ArrayList<>();

    public BaseDemoQuadTree() {
        super();
        hudGlProfiler();
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        quadRender = new QuadTreeRenderer();

        quadT = new QuadTree<>(RectangleBuilder.screen(camera, true), 5, 5);

        shapeRenderer = new NzShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
        Gdx.input.setInputProcessor(inputMultiplexer);
        inputMultiplexer.addProcessor(cameraController());

        hudMsg("arrow for move, mouse for zoom");
        hudMsg("Left for create rect");
        hudMsg("Right Click destroy");
    }

    @Override
    public void show() {
        super.show();
        HudDebug.addTopMiddle("QuadEntryCount", "", Color.RED);
    }

    @Override
    public void render(float dt) {
        super.render(dt);
        HudDebug.update("QuadEntryCount", values.size());
        camera.update();
        V3.add(camera.position, tmpVel.set(cameraVelocity).scl(camera.zoom));
        quadRender.render(quadT, camera);
    }

    protected void quadRemove(Array<QuadData<T>> quadDatas) {
        for (QuadData<T> quadData : quadDatas)
            quadRemove(quadData);
    }

    protected void quadRemove(QuadData<T> quadData) {
        this.values.remove(quadData);
        quadT.remove(quadData);
    }

    protected void quadAdd(T data, Rectangle rect) {
        indexQuad += 1;
        QuadData quadData = new QuadData(indexQuad, rect, data);
        this.values.add(quadData);
        this.quadT.add(quadData, rect);
    }

    public InputProcessor cameraController() {
        return new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                if (keycode == Input.Keys.UP) {
                    cameraVelocity.add(0, baseVelocity);
                } else if (keycode == Input.Keys.DOWN) {
                    cameraVelocity.add(0, -baseVelocity);
                } else if (keycode == Input.Keys.RIGHT) {
                    cameraVelocity.add(baseVelocity, 0);
                } else if (keycode == Input.Keys.LEFT) {
                    cameraVelocity.add(-baseVelocity, 0);
                }
                return false;
            }

            @Override
            public boolean keyUp(int keycode) {
                if (keycode == Input.Keys.UP) {
                    cameraVelocity.add(0, -baseVelocity);
                } else if (keycode == Input.Keys.DOWN) {
                    cameraVelocity.add(0, baseVelocity);
                } else if (keycode == Input.Keys.RIGHT) {
                    cameraVelocity.add(-baseVelocity, 0);
                } else if (keycode == Input.Keys.LEFT) {
                    cameraVelocity.add(baseVelocity, 0);
                }
                return false;
            }

            @Override
            public boolean scrolled(float amountX, float amountY) {
                if (amountY > 0) {
                    if (camera.zoom < 1)
                        camera.zoom = 1;
                    else
                        camera.zoom += 1;
                } else {
                    if (camera.zoom > 1)
                        camera.zoom -= 1;
                    else
                        camera.zoom /= 2;
                }
                return false;
            }
        };
    }

    class QuadData<T> {
        public int index;
        public Rectangle rectangle;
        public T data;

        public QuadData(int index, Rectangle rectangle, T data) {
            this.index = index;
            this.rectangle = rectangle;
            this.data = data;
        }

        @Override
        public String toString() {
            return "" + index;
        }
    }
}
