package com.github.fabiitch.nz.gdx.camera;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.github.fabiitch.nz.java.math.shapes.builders.RectangleBuilder;
import com.github.fabiitch.nz.java.math.shapes.utils.RectangleUtils;

public class CameraUtils {

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
