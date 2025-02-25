package com.github.fabiitch.nz.demo.screens.quadtree;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.github.fabiitch.nz.demo.NzDemoScreenLauncher;
import com.github.fabiitch.nz.demo.internal.selectors.DemoScreen;
import com.github.fabiitch.nz.gdx.input.InputUtils;

@DemoScreen(group = "data.quadtree")
public class DemoQuadTreeAddRemove extends BaseDemoQuadTree {

    public static void main(String[] args) {
        NzDemoScreenLauncher.startScreen(DemoQuadTreeAddRemove.class);
    }

    public DemoQuadTreeAddRemove() {
        super();
        inputMultiplexer.addProcessor(addRemoveController());
        quadRender.getConfig().drawValuesData(true);
    }

    @Override
    public void render(float dt) {
        super.render(dt);
        if (rectBodyCreation != null || rectBodyDestruction != null) {
            shapeRenderer.setProjectionMatrix(camera.combined);
            shapeRenderer.begin();
            if (rectBodyCreation != null) {
                shapeRenderer.setColor(Color.GREEN);
                shapeRenderer.rect(rectBodyCreation);
            }
            if (rectBodyDestruction != null) {
                shapeRenderer.setColor(Color.RED);
                shapeRenderer.rect(rectBodyDestruction);
            }
            shapeRenderer.end();
        }
    }

    Rectangle rectBodyCreation = null;
    Rectangle rectBodyDestruction = null;
    Vector2 startPositionRect = new Vector2();

    public InputProcessor addRemoveController() {
        return new InputAdapter() {

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                Vector2 clickPos = InputUtils.getClickPos(camera, screenX, screenY);
                startPositionRect.set(clickPos);
                if (button == InputUtils.LEFT_CLICK) {
                    rectBodyCreation = new Rectangle();
                } else if (button == InputUtils.RIGHT_CLICK) {
                    rectBodyDestruction = new Rectangle();
                }
                return false;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                Vector2 clickPos = InputUtils.getClickPos(camera, screenX, screenY);
                if (rectBodyCreation != null) {
                    rectBodyCreation.set(startPositionRect.x, startPositionRect.y, 0, 0);
                    rectBodyCreation.merge(clickPos);

                    quadAdd(new Vector2(1, 0).setToRandomDirection(), rectBodyCreation);
                    rectBodyCreation = null;
                } else if (rectBodyDestruction != null) {
                    rectBodyDestruction.set(startPositionRect.x, startPositionRect.y, 0, 0);
                    rectBodyDestruction.merge(clickPos);
                    Array<Integer> result = quadTree.query(rectBodyDestruction, new Array<>());
                    quadTree.remove(result);

                    rectBodyDestruction = null;
                }
                return false;
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                Vector2 clickPos = InputUtils.getClickPos(camera, screenX, screenY);
                Rectangle rect = rectBodyCreation != null ? rectBodyCreation : rectBodyDestruction;
                if(rect != null){
                    rect.set(startPositionRect.x, startPositionRect.y, 0, 0);
                    rect.merge(clickPos);
                }
                return false;
            }
        };
    }

}
