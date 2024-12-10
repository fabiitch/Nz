package com.github.fabiitch.nz.demo.screens.quadtree;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.github.fabiitch.nz.demo.NzDemoScreenLauncher;
import com.github.fabiitch.nz.demo.internal.selectors.DemoScreen;
import com.github.fabiitch.nz.gdx.debug.huddebug.HudDebug;
import com.github.fabiitch.nz.gdx.input.InputUtils;
import com.github.fabiitch.nz.java.math.shapes.utils.RectangleUtils;

@DemoScreen(group = "data.quadtree")
public class DemoQuadTreeMove extends BaseDemoQuadTree<Vector2> {

    public static void main(String[] args) {
        NzDemoScreenLauncher.startScreen(DemoQuadTreeMove.class);
    }

    public DemoQuadTreeMove() {
        super();
        inputMultiplexer.addProcessor(getInput());
        quadRender.getConfig().drawQuadData(false);
        quadRender.getConfig().drawValuesData(false);
        quadTree.build(quadTree.boundingRect, 500, 5);

        HudDebug.addTopMiddle("MaxDepth", Color.RED);
        HudDebug.addTopMiddle("TimeUpdate", 0f, Color.RED);
    }

    @Override
    public void render(float dt) {
        super.render(dt);
        HudDebug.update("MaxDepth", quadTree.getCurrentMaxDepth(0));
        if (rectBodyDestruction != null) {
            shapeRenderer.setProjectionMatrix(camera.combined);
            shapeRenderer.setColor(Color.RED);
            shapeRenderer.begin();
            shapeRenderer.rect(rectBodyDestruction);
            shapeRenderer.end();
        }

        long start = System.nanoTime();
        for (QuadData quadData : values) {
            Vector2 dir = (Vector2) quadData.data;
            Rectangle quadRect = quadTree.boundingRect;
            Rectangle rectangle = quadData.rectangle;

            RectangleUtils.translate(rectangle, dir.x, dir.y);

            if (RectangleUtils.getXMax(rectangle) > RectangleUtils.getXMax(quadRect) - 1) {
                dir.x = -dir.x;
            }
            if (rectangle.x < quadRect.x + 1) {
                dir.x = -dir.x;
            }
            if (RectangleUtils.getYMax(rectangle) > RectangleUtils.getYMax(quadRect) - 1) {
                dir.y = -dir.y;
            }
            if (rectangle.y < quadRect.y + 1) {
                dir.y = -dir.y;
            }
            quadTree.update(quadData);

        }
        long end = System.nanoTime();
        //  quadTree.update();

        HudDebug.update("TimeUpdate", TimeUtils.nanosToMillis(end - start));
    }

    Rectangle rectBodyDestruction = null;
    Vector2 startPositionRect = new Vector2();

    private InputProcessor getInput() {
        return new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                Vector2 clickPos = InputUtils.getClickPos(camera, screenX, screenY);
                if (button == InputUtils.LEFT_CLICK) {
                    for (int i = 0; i < 100; i++) {
                        Rectangle rect = new Rectangle(clickPos.x, clickPos.y, 10, 10);
                        quadAdd(new Vector2().setToRandomDirection(), rect);
                    }
                }
                if (button == InputUtils.RIGHT_CLICK) {
                    startPositionRect.set(clickPos);
                    rectBodyDestruction = new Rectangle();
                }
                return false;
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                if (rectBodyDestruction != null) {
                    Vector2 clickPos = InputUtils.getClickPos(camera, screenX, screenY);
                    rectBodyDestruction.set(startPositionRect.x, startPositionRect.y, 0, 0);
                    rectBodyDestruction.merge(clickPos);
                }
                return false;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                if (rectBodyDestruction != null) {
                    Vector2 clickPos = InputUtils.getClickPos(camera, screenX, screenY);
                    rectBodyDestruction.set(startPositionRect.x, startPositionRect.y, 0, 0);
                    rectBodyDestruction.merge(clickPos);

                    Array<QuadData<Vector2>> result = quadTree.query(rectBodyDestruction, new Array<>());
                    quadRemove(result);
                    rectBodyDestruction = null;
                }
                return false;
            }
        };
    }
}
