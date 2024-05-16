package com.github.fabiitch.nz.java.math.shapes;

import com.badlogic.gdx.math.GeometryUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

/**
 * TODO
 * contains opti?
 */
public class Triangle extends Polygon {

    /**
     * Static temporary Vector2. Use with care! Use only when sure other code will
     * not also use this.
     */
    public static final Vector2 tmpV1 = new Vector2();
    public static final Vector2 tmpV2 = new Vector2();

    public Triangle(float[] vertices) {
        super(vertices);
        if (vertices.length != 6) throw new IllegalArgumentException("Triangle must have 3 points.");
    }

    public Triangle(float aX, float aY, float bX, float bY, float cX, float cY) {
        super(new float[]{aX, aY, bX, bY, cX, cY});
    }

    public Triangle(Vector2 a, Vector2 b, Vector2 c) {
        this(a.x, a.y, b.x, b.y, c.x, c.y);
    }

    public void setVertices(float[] vertices) {
        if (vertices.length != 6) throw new IllegalArgumentException("Triangle must have 3 points.");
        super.setVertices(vertices);
    }

    public void setPosition(Vector2 pos) {
        setPosition(pos.x, pos.y);
    }

    public Vector2 getA(Vector2 pos) {
        return getVertex(0, pos);
    }

    public Vector2 getB(Vector2 pos) {
        return getVertex(1, pos);
    }

    public Vector2 getC(Vector2 pos) {
        return getVertex(2, pos);
    }

    public Vector2 getVertex(int vertexNum, Vector2 pos) {
        if (vertexNum < 0 || vertexNum > 2)
            throw new IllegalArgumentException("getVertex can return vertex range (0,2)");

        return pos.set(getVertexValue(vertexNum, true), getVertexValue(vertexNum, false));
    }

    /**
     * return real vertex in triangle
     */
    private int getVertexNum(int vertexNum) {
        return vertexNum % 3;
    }

    private float getVertexValue(int vertexNum, boolean xValue) {
        vertexNum %= 3;
        float[] transformedVertices = this.getTransformedVertices();
        if (xValue)
            return transformedVertices[2 * vertexNum];
        return transformedVertices[2 * vertexNum + 1];
    }

    public Vector2 getDir(int vertex1, int vertex2, Vector2 dir) {
        getVertex(getVertexNum(vertex2), dir);
        getVertex(getVertexNum(vertex1), tmpV1);
        dir.sub(tmpV1);
        return dir.nor();
    }

    public float getAngleDeg(int vertex) {
        getVertex(getVertexNum(vertex), tmpV1);
        tmpV1.sub(getVertexValue(vertex + 1, true), getVertexValue(vertex + 1, false));

        getVertex(getVertexNum(vertex), tmpV2);
        tmpV2.sub(getVertexValue(vertex + 2, true), getVertexValue(vertex + 2, false));

        float angle = tmpV1.angleDeg(tmpV2);
        if (angle > 180)
            angle = 360 - angle;
        return angle;
    }

    public void setOrigin(Vector2 pos) {
        this.setOrigin(pos.x, pos.y);
    }

    public void setOriginOnVertex(int vertexNum) {
        getVertex(vertexNum, tmpV2);
        this.setOrigin(tmpV2.x, tmpV2.y);
    }

    public Vector2 getCentroid(Vector2 result) {
        float[] vertices = getTransformedVertices();
        return GeometryUtils.triangleCentroid(vertices[0], vertices[1],
                vertices[2], vertices[3],
                vertices[4], vertices[5], result);
    }

    public Vector2 getCircumcenter(Vector2 result) {
        float[] vertices = getTransformedVertices();
        return GeometryUtils.triangleCircumcenter(vertices[0], vertices[1],
                vertices[2], vertices[3],
                vertices[4], vertices[5], result);
    }


    @Override
    public String toString() {
        String a = getA(tmpV1).toString();
        String b = getB(tmpV1).toString();
        String c = getC(tmpV1).toString();
        return "Triangle[a=" + a + " b=" + b + " c=" + c + "]";
    }

}
