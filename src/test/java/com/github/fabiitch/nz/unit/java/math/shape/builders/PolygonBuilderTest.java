package com.github.fabiitch.nz.unit.java.math.shape.builders;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.github.fabiitch.nz.java.math.shapes.builders.PolygonBuilder;
import com.github.fabiitch.nz.java.math.shapes.utils.PolygonUtils;
import com.github.fabiitch.nz.java.math.vectors.V2;
import com.fabiitch.gdxunit.VTestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PolygonBuilderTest {

    @Test
    public void rectangleTest() {
        Polygon rectangle = PolygonBuilder.rectangle(100, 50, false);
        Vector2[] verticesAsVectors = PolygonUtils.getVerticesAsVectors(rectangle);

        VTestUtils.assertEquals(V2.v(0, 0), verticesAsVectors[0]);
        VTestUtils.assertEquals(V2.v(100, 0), verticesAsVectors[1]);
        VTestUtils.assertEquals(V2.v(100, 50), verticesAsVectors[2]);
        VTestUtils.assertEquals(V2.v(0, 50), verticesAsVectors[3]);

        rectangle = PolygonBuilder.rectangle(100, 50, true);
        verticesAsVectors = PolygonUtils.getVerticesAsVectors(rectangle);

        VTestUtils.assertEquals(V2.v(-50, -25), verticesAsVectors[0]);
        VTestUtils.assertEquals(V2.v(50, -25), verticesAsVectors[1]);
        VTestUtils.assertEquals(V2.v(50, 25), verticesAsVectors[2]);
        VTestUtils.assertEquals(V2.v(-50, 25), verticesAsVectors[3]);
    }

    @Test
    public void polygonFromVectorsTest() {

        Polygon polygon = PolygonBuilder.get(V2.v(0, 0), V2.v(50, 50), V2.v(60, 60), V2.v(300, 0));
        float[] vertices = polygon.getTransformedVertices();
        float[] expected = new float[]{0, 0, 50, 50, 60, 60, 300, 0};
        Assertions.assertEquals(vertices.length, expected.length);
        for (int i = 0; i < vertices.length; i++) {
            Assertions.assertEquals(expected[i], vertices[i]);
        }
    }
}
