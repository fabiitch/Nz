package com.github.fabiitch.nz.gdx.render.shape;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.*;
import com.github.fabiitch.nz.java.math.shapes.Segment;
import com.github.fabiitch.nz.java.math.shapes.Triangle;
import com.github.fabiitch.nz.java.utils.Randoms;

public class NzShapeRenderer extends ShapeRenderer {


    public NzShapeRenderer() {
        super();
    }

    public NzShapeRenderer(int maxVertices) {
        super(maxVertices);
    }

    public NzShapeRenderer(int maxVertices, ShaderProgram defaultShader) {
        super(maxVertices, defaultShader);
    }

    public void segment(Segment segment) {
        line(segment.a, segment.b);
    }

    public void polyline(Polyline polyline) {
        polyline(polyline.getTransformedVertices());
    }

    public void triangle(Triangle triangle) {
        this.polygon(triangle.getTransformedVertices());
    }

    public void circle(Circle circle) {
        this.circle(circle.x, circle.y, circle.radius);
    }

    public void circle(Vector2 pos, float radius) {
        this.circle(pos.x, pos.y, radius);
    }

    public void polygon(Polygon polygon) {
        this.polygon(polygon.getTransformedVertices());
    }

    public void rect(Rectangle rect) {
        this.rect(rect.x, rect.y, rect.width, rect.height);
    }

    public void randomColor() {
        this.setColor(Randoms.color(this.getColor()));
    }

    public void crossPlus(Vector2 position, float lenghtCross) {
        ShapeRenderUtils.crossPlus(this, position, lenghtCross);
    }

    public void crossX(Vector2 position, float lenghtCross) {
        ShapeRenderUtils.crossX(this, position, lenghtCross);
    }

    public NzShapeRenderer triangleFilled(Triangle triangle) {
        ShapeRenderUtils.triangleFilled(this, triangle);
        return this;
    }

    public NzShapeRenderer set(ShapeType shapeType, Color color) {
        this.set(shapeType);
        this.setColor(color);
        return this;
    }

    public NzShapeRenderer line() {
        this.set(ShapeType.Line);
        return this;
    }

    public NzShapeRenderer filled() {
        this.set(ShapeType.Filled);
        return this;
    }

    public NzShapeRenderer point() {
        this.set(ShapeType.Point);
        return this;
    }
}
