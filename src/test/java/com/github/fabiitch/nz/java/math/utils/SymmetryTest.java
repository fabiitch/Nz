package com.github.fabiitch.nz.java.math.utils;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.github.fabiitch.gdxunit.tab.FloatTabTestUtils;
import com.github.fabiitch.nz.java.math.shapes.Segment;
import com.github.fabiitch.nz.java.math.shapes.builders.PolygonBuilder;
import org.junit.jupiter.api.Test;

import static com.github.fabiitch.gdxunit.shape.ShapeTestBuilder.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SymmetryTest {

    @Test
    public void v2OnV2() {
        Vector2 symmetryPoint = new Vector2(10, 10);

        assertEquals(v2(20, 20), Symmetry.withPoint(v2(), symmetryPoint));
        assertEquals(v2(40, 10), Symmetry.withPoint(v2(-20, 10), symmetryPoint));
    }

    @Test
    public void v2OnSeg() {
        Segment symmetrySegment = new Segment(0, 10, 10, 10);

        assertEquals(v2(0, 20), Symmetry.withSegment(v2(), symmetrySegment));
        assertEquals(v2(20, 10), Symmetry.withSegment(v2(-20, 10), symmetrySegment));
    }

    @Test
    public void rectOnV2() {
        Vector2 symmetryPoint = new Vector2(10, 10);

        Rectangle r = rCenter(0, 0, 5, 5); //20,20
        assertEquals(rCenter(20, 20, 5, 5), Symmetry.withPoint(r, symmetryPoint));
    }

    @Test
    public void rectOnSeg() {
        Segment symmetrySegment = new Segment(-50, 0, 50, 0);

        Rectangle r = rCenter(0, -10, 5, 5); //20,20
        assertEquals(rCenter(0, 10, 5, 5), Symmetry.withSegment(r, symmetrySegment));
    }


    @Test
    public void circleOnV2() {
        Vector2 symmetryPoint = new Vector2(10, 10);

        Circle c = c(0, 0, 5);
        assertEquals(c(20, 20, 5), Symmetry.withPoint(c, symmetryPoint));
    }

    @Test
    public void circleOnSegment() {
        Segment symmetrySegment = new Segment(-50, 0, 50, 0);

        Circle c1 = c(0, -20, 5);
        assertEquals(c(0, 20, 5), Symmetry.withSegment(c1, symmetrySegment));

        c1 = c(-10, -20, 5);
        assertEquals(c(-10, 20, 5), Symmetry.withSegment(c1, symmetrySegment));
    }

    @Test
    public void polygonOnV2() {
        Vector2 symmetryPoint = new Vector2(10, 10);

        Polygon polygon = PolygonBuilder.rectangle(0, 0, 5, 5, true);
        FloatTabTestUtils.assertEquals(PolygonBuilder.rectangle(20, 20, 5, 5, true).getTransformedVertices(),
                Symmetry.withPoint(polygon, symmetryPoint).getTransformedVertices());
    }

    @Test
    public void polygonOnSegment() {
        Segment symmetrySegment = new Segment(-50, 0, 50, 0);
        Polygon polygon = PolygonBuilder.rectangle(0, -20, 5, 5, true);

        FloatTabTestUtils.assertEquals(PolygonBuilder.rectangle(0, 20, 5, 5, true).getTransformedVertices(),
                Symmetry.withSegment(polygon, symmetrySegment).getTransformedVertices());
    }
}
