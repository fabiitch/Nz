package com.github.fabiitch.nz.java.math.shapes.builders;

import com.badlogic.gdx.math.Vector2;
import com.github.fabiitch.nz.java.math.shapes.Triangle;

public class TriangleBuilder {

    private TriangleBuilder() {
    }

    public static Triangle fromOneVertex(int vertexNum, Vector2 posVertex, float angleDegVertex, float lenghtSeg1, float lenghtSeg2) {
        vertexNum %= 3;
        float[] vertices = createVertices(vertexNum, posVertex);

        vertexNum = getNextVertex(vertexNum);
        setVertex(vertexNum, getNextVertex(posVertex, angleDegVertex, lenghtSeg1), vertices);

        vertexNum = getNextVertex(vertexNum);
        setVertex(vertexNum, getNextVertex(posVertex, -angleDegVertex, lenghtSeg2), vertices);
        return new Triangle(vertices);
    }


    public static Triangle rectangle(int vertexNum, Vector2 posVertex, float lenghtSeg1, float lenghtSeg2) {
        vertexNum %= 3;
        float[] vertices = createVertices(vertexNum, posVertex);

        vertexNum = getNextVertex(vertexNum);
        setVertex(vertexNum, getNextVertex(posVertex, 90, lenghtSeg1), vertices);

        vertexNum = getNextVertex(vertexNum);
        setVertex(vertexNum, getNextVertex(posVertex, -90, lenghtSeg1), vertices);
        return new Triangle(vertices);
    }

    public static Triangle isoscelesRectangle(int vertexNum, Vector2 posVertex, float lenghtSegments) {
        return isosceles(vertexNum, posVertex, 90, lenghtSegments);
    }

    public static Triangle isosceles(int vertexNum, Vector2 posVertex, float angleVertex, float lenghtSegments) {
        vertexNum %= 3;
        float[] vertices = createVertices(vertexNum, posVertex);

        vertexNum = getNextVertex(vertexNum);
        setVertex(vertexNum, getNextVertex(posVertex, angleVertex, lenghtSegments), vertices);

        vertexNum = getNextVertex(vertexNum);
        setVertex(vertexNum, getNextVertex(posVertex, -angleVertex, lenghtSegments), vertices);
        return new Triangle(vertices);
    }

    public static Triangle equilateral(int vertexNum, Vector2 posVertex, float lenghtSegments) {
        vertexNum %= 3;
        float[] vertices = createVertices(vertexNum, posVertex);

        vertexNum = getNextVertex(vertexNum);
        setVertex(vertexNum, getNextVertex(posVertex, 60, lenghtSegments), vertices);

        vertexNum = getNextVertex(vertexNum);
        setVertex(vertexNum, getNextVertex(posVertex, -60, lenghtSegments), vertices);
        return new Triangle(vertices);
    }

    private static int getNextVertex(int vertexNum) {
        return (vertexNum + 1) % 3;
    }

    private static Vector2 getNextVertex(Vector2 posFrom, float angleDeg, float lenghtSeg) {
        return Triangle.tmpV1.set(posFrom).add(Triangle.tmpV2.set(Vector2.X).setAngleDeg(angleDeg / 2).scl(lenghtSeg));
    }

    private static void setVertex(int vertexNum, Vector2 position, float[] vertices) {
        vertices[2 * vertexNum] = position.x;
        vertices[2 * vertexNum + 1] = position.y;
    }

    private static float[] createVertices(int vertexNum, Vector2 position) {
        vertexNum = vertexNum % 3;
        float[] vertices = new float[6];

        vertices[2 * vertexNum] = position.x;
        vertices[2 * vertexNum + 1] = position.y;
        return vertices;
    }

}
