package com.fabiitch.nz.unit.data.quad;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.fabiitch.nz.data.quadtree.QuadTree;
import com.fabiitch.nz.math.shapes.utils.RectangleUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.fabiitch.nz.unit.math.MathTestUtils.r;
import static com.fabiitch.nz.unit.math.MathTestUtils.v2;

public class QuadTreeTest {

    @Test
    public void testAdd() {
        Rectangle quadRect = r(500, 500);
        for (int pp = 0; pp < 10; pp++) {
            QuadTree<Integer> quadTree = new QuadTree<>(quadRect, 5, 10);
            for (int i = 0; i < 500; i++) {
                Rectangle rect = r(RectangleUtils.getRandomPos(quadRect, v2()), 1, 1);
                quadTree.add(i, rect);
            }

            Array<Integer> values = new Array<>();
            quadTree.getAllValues(values);
            Assertions.assertEquals(500, values.size);
        }
    }
}
