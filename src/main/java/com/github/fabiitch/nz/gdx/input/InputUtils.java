package com.github.fabiitch.nz.gdx.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Plane;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.github.fabiitch.nz.java.math.vectors.V3;
import com.github.fabiitch.nz.java.math.vectors.v2.V2;

public class InputUtils {
    public final static int LEFT_CLICK = Input.Buttons.LEFT;
    public final static int RIGHT_CLICK = Input.Buttons.RIGHT;
    public final static int WHEEL_CLICK = Input.Buttons.MIDDLE;

    private InputUtils() {

    }

    public static boolean isRightClick(int button) {
        return button == RIGHT_CLICK;
    }
    public static boolean isLeftClick(int button) {
        return button == LEFT_CLICK;
    }

    public static Vector2 getClickPos(Camera camera, Vector2 screenXy) {
        return V2.set(screenXy, camera.unproject(V3.tmp(screenXy.x, screenXy.y)));
    }

    public static Vector2 getClickPos(Camera camera, int screenX, int screenY) {
        return V2.set(new Vector2(), camera.unproject(V3.tmp(screenX, screenY)));
    }

    public static int yTo2DCoords(int y) {
        return Gdx.graphics.getHeight() - 1 - y;
    }

    public static float yTo2DCoords(float y) {
        return Gdx.graphics.getHeight() - 1 - y;
    }

    public static Vector3 getWorldCoord(int screenX, int screenY, Camera camera) {
        Ray ray = camera.getPickRay(screenX, screenY);
        final Plane xzPlane = new Plane(new Vector3(0, 0, 1), 0);
        final Vector3 intersection = new Vector3();
        Intersector.intersectRayPlane(ray, xzPlane, intersection);
        return intersection;
    }
}
