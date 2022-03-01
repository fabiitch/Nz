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

        int LOOP = 1;
        int NB_ADD = 1000;


        Rectangle quadRect = r(500, 500);
        for (int loopStart = 0; loopStart < LOOP; loopStart++) {
            Array<Integer> values = new Array<>();
            QuadTree<Integer> quadTree = new QuadTree<>(quadRect, 5, 10);
            for (int i = 0; i < NB_ADD; i++) {
                Rectangle rect = r(RectangleUtils.getRandomPos(quadRect, v2()), 1, 1);
                quadTree.add(i, rect);
            }

            quadTree.getAllValues(values);
            Assertions.assertEquals(NB_ADD, values.size);


            values = new Array<>();
            for (int i = 0; i < NB_ADD; i++) {
                quadTree.remove(i);
            }
            quadTree.getAllValues(values);
            Assertions.assertEquals(0, values.size);
        }
    }
}
