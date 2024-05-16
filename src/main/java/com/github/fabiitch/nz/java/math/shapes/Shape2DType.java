package com.github.fabiitch.nz.java.math.shapes;

import com.badlogic.gdx.math.Shape2D;

public enum Shape2DType {
    Rectangle, Square,
    Polygon, Triangle,
    Circle, Ellipse,
    Segment,
    Polyline;


    public static <S extends Shape2D> S getShape(Shape2D shape2D, Shape2DType type) {
        S cast = (S) shape2D;
        return cast;
    }
}
