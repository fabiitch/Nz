package com.github.fabiitch.nz.gdx.render.shape;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.github.fabiitch.nz.gdx.camera.CameraUtils;
import com.github.fabiitch.nz.java.math.shapes.Segment;
import com.github.fabiitch.nz.java.math.shapes.intersectors.IntersectorCircleRect;
import com.github.fabiitch.nz.java.math.shapes.intersectors.IntersectorRectangle;
import com.github.fabiitch.nz.java.math.shapes.utils.RectangleUtils;
import lombok.Getter;

@Getter
public class Frustum2D {

    protected final Rectangle rectangle;

    public Frustum2D() {
        rectangle = new Rectangle();
    }

    public void update(Rectangle rectangle) {
        this.rectangle.set(rectangle);
    }

    public void update(Vector2 center) {
        RectangleUtils.setCenter(rectangle, center);
    }

    public void update(float centerX, float centerY) {
        RectangleUtils.setCenter(rectangle, centerX, centerY);
    }

    public void update(float centerX, float centerY, float width, float height) {
        RectangleUtils.setCenter(rectangle, centerX, centerY, width, height);
    }

    public void update(OrthographicCamera camera) {
        CameraUtils.toRectangle(camera, this.rectangle);
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
