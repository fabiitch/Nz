package com.github.fabiitch.nz.demo.screens.shapes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.github.fabiitch.nz.demo.internal.BaseDemoScreen;
import com.github.fabiitch.nz.demo.internal.selectors.DemoScreen;
import com.github.fabiitch.nz.gdx.debug.huddebug.HudDebug;
import com.github.fabiitch.nz.gdx.input.InputUtils;
import com.github.fabiitch.nz.java.math.shapes.PolygonRectangle;
import com.github.fabiitch.nz.java.math.shapes.builders.RectangleBuilder;

@DemoScreen(group = "math.shape2D.polygon.rect")
public class PolygonRectangleSetRelativePosDemo extends BaseDemoScreen {

    PolygonRectangle polygonRectangle = new PolygonRectangle(RectangleBuilder.get(300, 300, 200, 100));
    Vector2 lastPos = new Vector2(300, 300);

    public PolygonRectangleSetRelativePosDemo() {
        HudDebug.addTopLeft(" ", "Method setCenterABAt() ");
        HudDebug.addTopRight("1", "Click for Replace ");
        HudDebug.addTopRight("2", "A/Z for rotate");
        shapeRenderer.setAutoShapeType(true);

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                lastPos.set(screenX, InputUtils.yTo2DCoords(screenY));
                polygonRectangle.setCenterABAt(lastPos);
                return super.touchDown(screenX, screenY, pointer, button);
            }

            @Override
            public boolean keyDown(int keycode) {
                if (keycode == Input.Keys.Q) {
                    polygonRectangle.rotate(45);
                }
                if (keycode == Input.Keys.W) {
                    polygonRectangle.rotate(-45);
                }
                return super.keyDown(keycode);
            }
        });
    }

    @Override
    public void doRender(float dt) {
        shapeRenderer.begin();
        shapeRenderer.polygon(polygonRectangle);
        shapeRenderer.end();
    }

    @Override
    public void doDispose() {

    }

}
