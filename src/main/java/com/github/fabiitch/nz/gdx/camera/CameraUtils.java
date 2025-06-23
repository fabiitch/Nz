package com.github.fabiitch.nz.gdx.camera;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.github.fabiitch.nz.java.math.shapes.builders.RectangleBuilder;
import com.github.fabiitch.nz.java.math.shapes.utils.RectangleUtils;
import com.github.fabiitch.nz.java.math.vectors.V3;
import com.github.fabiitch.nz.java.math.vectors.v2.V2;

public class CameraUtils {

    public static  Rectangle unProject(Camera camera, Rectangle rectangle) {
        Vector3 tmp = new Vector3();
        Vector3 tmp = new Vector3();

     RectangleBuilder.
    }

    public static Vector2 unproject(Camera camera, Vector2 point) {
        Vector3 tmp = V3.v(point);
        Vector3 unproject = camera.unproject(tmp);
        return V2.v(unproject);
    }

    public static Rectangle toRectangle(OrthographicCamera camera, Rectangle res) {
        return RectangleUtils.setCenter(res, camera.position.x, camera.position.y,
                Math.abs(camera.viewportWidth * camera.zoom),
                Math.abs(camera.viewportHeight * camera.zoom));
    }

    public static Rectangle toRectangle(OrthographicCamera camera) {
        return RectangleBuilder.fromCenter(camera.position.x, camera.position.y,
                Math.abs(camera.viewportWidth * camera.zoom),
                Math.abs(camera.viewportHeight * camera.zoom));
    }
}
