package com.github.fabiitch.nz.gdx.camera;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.github.fabiitch.nz.java.math.shapes.builders.RectangleBuilder;
import com.github.fabiitch.nz.java.math.shapes.utils.RectangleUtils;
import com.github.fabiitch.nz.java.math.vectors.V3;
import com.github.fabiitch.nz.java.math.vectors.v2.V2;

public class CameraUtils {

    public static Rectangle project(Rectangle rectangle, Camera camera) {
        Vector3 bottomLeft = new Vector3(rectangle.x, rectangle.y, 0);
        Vector3 topRight   = new Vector3(rectangle.x + rectangle.width, rectangle.y + rectangle.height, 0);

        camera.project(bottomLeft);
        camera.project(topRight);

        float x = bottomLeft.x;
        float y = topRight.y; // car Y est inversé entre monde et screen
        float width = topRight.x - bottomLeft.x;
        float height = bottomLeft.y - topRight.y;

        return new Rectangle(x, y, width, height);
    }
    public static Circle project(Circle circle, Camera camera) {
        Vector3 center = new Vector3(circle.x, circle.y, 0);
        Vector3 edge   = new Vector3(circle.x + circle.radius, circle.y, 0); // point à droite du centre

        camera.project(center);
        camera.project(edge);

        float screenRadius = edge.x - center.x; // rayon en pixels (en X)

        return new Circle(center.x, center.y, screenRadius);
    }
    public static Vector2 project(Vector2 point, Camera camera) {
        Vector3 tmp = V3.v(point);
        Vector3 project = camera.project(tmp);
        return V2.v(project);
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
