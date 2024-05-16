package com.github.fabiitch.nz.demo.quadtree;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.github.fabiitch.nz.demo.internal.selectors.DemoScreen;
import com.github.fabiitch.nz.gdx.debug.huddebug.HudDebug;
import com.github.fabiitch.nz.gdx.input.InputUtils;
import com.github.fabiitch.nz.java.math.shapes.utils.RectangleUtils;
@DemoScreen(group = "data.quadtree")
public class DemoQuadTreeMove extends BaseDemoQuadTree<Vector2> {

    public DemoQuadTreeMove() {
        super();
        inputMultiplexer.addProcessor(getInput());
        quadRender.drawQuadData = false;
        quadRender.drawUserData = false;
        quadT.build(quadT.boundingRect, 500, 5);

        HudDebug.addTopMiddle("MaxDepth", Color.RED);
    }

    @Override
    public void render(float dt) {
        super.render(dt);
        HudDebug.update("MaxDepth", quadT.getCurrentMaxDepth(0));
        if (rectBodyDestruction != null) {
            shapeRenderer.setProjectionMatrix(camera.combined);
            shapeRenderer.setColor(Color.RED);
            shapeRenderer.begin();
            shapeRenderer.rect(rectBodyDestruction);
            shapeRenderer.end();
        }

        for (QuadData quadData : values) {
            Vector2 dir = (Vector2) quadData.data;
            Rectangle rectangle = quadT.getRectangle(quadData);
            Rectangle quadRect = quadT.boundingRect;
            if(rectangle ==null)
                rectangle = quadData.rectangle;
            RectangleUtils.translate(rectangle, dir.x, dir.y);
            if (RectangleUtils.getXMax(rectangle) > RectangleUtils.getXMax(quadRect)) {
                dir.x = -dir.x;
            }
            if (rectangle.x < quadRect.x) {
                dir.x = -dir.x;
            }
            if (RectangleUtils.getYMax(rectangle) > RectangleUtils.getYMax(quadRect)) {
                dir.y = -dir.y;
            }
            if (rectangle.y < quadRect.y) {
                dir.y = -dir.y;
            }

        }
        quadT.update();
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

                    Array<QuadData<Vector2>> result = quadT.query(new Array<>(), rectBodyDestruction);
                    quadRemove(result);
                    rectBodyDestruction = null;
                }
                return false;
            }
        };
    }
}
