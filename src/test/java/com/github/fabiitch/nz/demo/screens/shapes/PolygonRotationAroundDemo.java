package com.github.fabiitch.nz.demo.screens.shapes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.github.fabiitch.nz.demo.internal.BaseDemoScreen;
import com.github.fabiitch.nz.demo.internal.selectors.DemoScreen;
import com.github.fabiitch.nz.gdx.debug.huddebug.HudDebug;
import com.github.fabiitch.nz.gdx.input.InputUtils;
import com.github.fabiitch.nz.gdx.utils.ScreenUtils;
import com.github.fabiitch.nz.java.math.shapes.builders.PolygonBuilder;
import com.github.fabiitch.nz.java.math.utils.Rotation;
import com.github.fabiitch.nz.java.math.vectors.V2;

@DemoScreen(group = "math.shape2D.rotation.polygon")
public class PolygonRotationAroundDemo extends BaseDemoScreen {


    private Array<Polygon> polygons = new Array<>();

    private Polygon polygon;

    private Vector2 rotationPosition = ScreenUtils.getScreenCenter(new Vector2());

    public PolygonRotationAroundDemo() {
        HudDebug.addTopLeft("NOT GOOD", "NOT GOOD", Color.RED);
        HudDebug.addTopLeft("NOT GOOD", "NOT GOOD", Color.RED);
        HudDebug.addTopLeft("NOT GOOD", "NOT GOOD", Color.RED);
        HudDebug.addTopRight("NOT GOOD", "NOT GOOD", Color.RED);
        HudDebug.addTopRight("NOT GOOD", "NOT GOOD", Color.RED);
        HudDebug.addTopRight("NOT GOOD", "NOT GOOD", Color.RED);
        shapeRenderer.setAutoShapeType(true);

        Polygon rectPoly = PolygonBuilder.rectangle(100, 100, 200, 100, true);
        polygons.add(rectPoly);

        Polygon losange = PolygonBuilder.get(V2.v(0, 0), V2.v(50, 50), V2.v(60, 60), V2.v(300, 0));
        polygons.add(losange);

        Polygon losange2 = PolygonBuilder.get(V2.v(-100, 0), V2.v(0, -50), V2.v(50, 0), V2.v(0, 50));
        polygons.add(losange2);

        polygon = polygons.get(0);

        HudDebug.addTopLeft("Test Method", "Rotation.rotateAround() ");
        HudDebug.addTopRight("Left Click", "Replace rotation center ");
        HudDebug.addTopRight("Right click ", "Replace polygon");

        HudDebug.addTopRight("Space", "Change polygon");
        HudDebug.addTopRight("C & V ", "Rotation +10 / -10");

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                screenY = InputUtils.yTo2DCoords(screenY);
                if (InputUtils.isRightClick(button)) {
                    replacePolygon(new Vector2(screenX, screenY));
                }
                if (InputUtils.isLeftClick(button)) {
                    rotationPosition.set(screenX, screenY);
                }
                return super.touchDown(screenX, screenY, pointer, button);
            }

            @Override
            public boolean keyDown(int keycode) {
                if (keycode == Input.Keys.SPACE) {
                    changePolygon();
                }
                if (keycode == Input.Keys.C) {
                    rotate(false);
                }
                if (keycode == Input.Keys.V) {
                    rotate(true);
                }
                return super.keyDown(keycode);
            }
        });
    }

    public void changePolygon() {
        Vector2 centroid = polygon.getCentroid(new Vector2());

        int index = polygons.indexOf(polygon, true);
        index++;
        if (index == polygons.size)
            index = 0;

        polygon = polygons.get(index);
        polygon.setPosition(centroid.x, centroid.y);
    }

    private void replacePolygon(Vector2 pos) {
        Vector2 direction = V2.directionTo(rotationPosition, pos, new Vector2());
        Vector2 centroid = polygon.getCentroid(new Vector2());

        Rotation.rotateAround(polygon, rotationPosition, pos.dst(centroid), direction.angleDeg());
    }

    private void replacePolygon(float angle) {
        Rotation.rotateAround(polygon, rotationPosition, angle);
    }

    public void rotate(boolean add) {
        Vector2 centroid = polygon.getCentroid(new Vector2());
        Vector2 direction = V2.directionTo(rotationPosition, centroid, new Vector2());

        if (add)
            replacePolygon(direction.angleDeg() + 10);
        else
            replacePolygon(direction.angleDeg() - 10);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        shapeRenderer.begin();

        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.crossX(rotationPosition, 20);

        shapeRenderer.setColor(Color.RED);
        shapeRenderer.polygon(polygon);
        shapeRenderer.end();
    }

}
