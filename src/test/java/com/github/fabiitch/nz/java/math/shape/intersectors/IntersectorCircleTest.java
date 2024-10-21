package com.github.fabiitch.nz.java.math.shape.intersectors;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.github.fabiitch.gdxunit.VTestUtils;
import com.github.fabiitch.nz.java.math.shapes.Segment;
import com.github.fabiitch.nz.java.math.shapes.intersectors.IntersectorCircle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class IntersectorCircleTest {

    @Test
    public void overlapStickRectangleTest() {
        Circle circle = new Circle(0, 0, 100);
        Rectangle rect = new Rectangle(0, 0, 100, 50);

        Assertions.assertTrue(IntersectorCircle.overlapStickRectangle(circle, rect));

        rect.setPosition(100, 0);
        Assertions.assertTrue(IntersectorCircle.overlapStickRectangle(circle, rect)); //stick

        rect.setPosition(100.001f, 0);
        Assertions.assertFalse(IntersectorCircle.overlapStickRectangle(circle, rect));

        rect.setPosition(0, 110);
        Assertions.assertFalse(IntersectorCircle.overlapStickRectangle(circle, rect));
    }

    @Test
    public void segmentIntersectionTest() {
        Circle circle = new Circle(0, 0, 50);
        Segment segment = new Segment(-200, 0, 200, 0);

        Vector2 intersect1 = new Vector2();
        Vector2 intersect2 = new Vector2();
        int nbIntersection = IntersectorCircle.segmentIntersection(circle, segment, intersect1, intersect2);

        Assertions.assertTrue(nbIntersection == 2);
        VTestUtils.assertEquals(new Vector2(-50, 0), intersect1);
        VTestUtils.assertEquals(new Vector2(50, 0), intersect2);
    }

    @Test
    public void polygonTest() {
        Circle circle = new Circle(0, 0, 200);

        Polygon polygon = new Polygon(new float[]{0, 300, 300, 0, -250, 0});
        boolean intersect = IntersectorCircle.polygon(circle, polygon);
        assertTrue(intersect);
    }
}
