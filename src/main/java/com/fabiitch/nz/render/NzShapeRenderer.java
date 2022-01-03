package com.fabiitch.nz.render;

import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.*;

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

//    public void segment(Segment segment) {
//        line(segment.a, segment.b);
//    }

    public void polyline(Polyline polyline) {
        polyline(polyline.getTransformedVertices());
    }

//    public void triangle(Triangle triangle) {
//        this.polygon(triangle.getTransformedVertices());
//    }

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

//    public void randomColor() {
//        this.setColor(Randoms.toRandom(this.getColor()));
//    }
}
