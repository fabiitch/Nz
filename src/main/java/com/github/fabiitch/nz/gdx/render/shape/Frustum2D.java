package com.github.fabiitch.nz.gdx.render.shape;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.github.fabiitch.nz.java.math.shapes.Segment;
import com.github.fabiitch.nz.java.math.shapes.intersectors.IntersectorCircleRect;
import com.github.fabiitch.nz.java.math.shapes.intersectors.IntersectorRectangle;
import com.github.fabiitch.nz.java.math.shapes.utils.RectangleUtils;
import lombok.Getter;

@Getter
public class Frustum2D {

    private final Rectangle rectangle;

    public Frustum2D() {
        rectangle = new Rectangle();
    }

    public void update(OrthographicCamera camera) {
        RectangleUtils.setCenter(rectangle, camera.position.x, camera.position.y,
                Math.abs(camera.viewportWidth * camera.zoom),
                Math.abs(camera.viewportHeight * camera.zoom));
    }

    public boolean isInside(Segment segment) {
        return IntersectorRectangle.segment(rectangle, segment);
    }

    public boolean isInside(Vector2 vector2) {
        return rectangle.contains(vector2);
    }

    public boolean isInside(Rectangle rectangle) {
        return this.rectangle.overlaps(rectangle);
    }

    public boolean isInside(Polygon polygon) {
        return IntersectorRectangle.polygon(rectangle, polygon);
    }

    public boolean isInside(Circle circle) {
        return IntersectorCircleRect.overlapStick(rectangle, circle);
    }
}
