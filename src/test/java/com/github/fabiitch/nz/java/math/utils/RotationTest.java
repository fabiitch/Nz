package com.github.fabiitch.nz.java.math.utils;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.github.fabiitch.gdxunit.shape.ShapeTestUtils;
import com.github.fabiitch.gdxunit.vectors.VTestUtils;
import com.github.fabiitch.nz.java.math.shapes.builders.PolygonBuilder;
import org.junit.jupiter.api.Test;

import static com.github.fabiitch.gdxunit.shape.ShapeTestBuilder.rCenter;
import static com.github.fabiitch.gdxunit.shape.ShapeTestBuilder.v2;

public class RotationTest {

    @Test
    public void v2RotateTest() {
        Vector2 start = v2(-10, 0);
        Vector2 rotationCenter = v2(0, 0);
        VTestUtils.assertEquals(v2(10, 0), Rotation.rotateAround(start, rotationCenter, 180));
    }

    @Test
    public void rectangleRotateTest() {
        Rectangle rect = rCenter(-10, 0, 5, 5);
        Vector2 rotationCenter = v2(0, 0);
        ShapeTestUtils.assertEquals(rect, Rotation.rotateAround(rect, rotationCenter, 180));
    }

    @Test
    public void polygonRotateTest() {
        Polygon polygon = PolygonBuilder.rectangle(0, 0, 10, 5, true);
        Vector2 rotationCenter = v2(10, 10);


        Rotation.rotateAround(polygon, rotationCenter, 180);
       // ShapeTestUtils.assertEquals(rCenter(20, 20, 10, 5), polygon);
    }


}
