package com.github.fabiitch.nz.unit.java.data.quad;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.github.fabiitch.nz.java.data.quadtree.QuadTree;
import com.github.fabiitch.nz.java.math.shapes.utils.RectangleUtils;
import com.github.fabiitch.nz.java.math.vectors.V2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static com.github.fabiitch.gdxunit.ShapeTestBuilder.r;
import static com.github.fabiitch.gdxunit.ShapeTestBuilder.v2;


//TODO cassé refaire le quadtree
@Disabled
public class QuadTreeTest {

    @Test
    public void addTest() {

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

    @Test
    public void currentMaxDepthTest() {
        QuadTree quadTree = new QuadTree<>(r(50, 50), 5, 500);
        QuadTree quadTreeOrig = quadTree;
        for (int i = 0; i < 9; i++) {
            int random = MathUtils.random(1, 4);
            if (random == 1)
                quadTree = quadTree.split().nw;
            if (random == 2)
                quadTree = quadTree.split().ne;
            if (random == 3)
                quadTree = quadTree.split().se;
            if (random == 4)
                quadTree = quadTree.split().sw;
        }
        Assertions.assertEquals(10, quadTreeOrig.getCurrentMaxDepth(0));
    }

    @Disabled
    @Test
    public void maxDepthTry() {
        QuadTree<Integer> quadTree = new QuadTree<>(r(50, 50), 1, 500);
        Rectangle Rdm = r(1, 1, 49, 49);
        for (int i = 0; i < 1_000_000; i++) {
            Rectangle rect = r(RectangleUtils.getRandomPos(Rdm, V2.tmp), 0.00001f, 0.00001f);
            quadTree.add(i, rect);
        }
        System.err.println("MaxDepth=" + quadTree.getCurrentMaxDepth(0));
    }
}
