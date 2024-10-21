package com.github.fabiitch.nz.java.math.vectors;

import com.badlogic.gdx.math.Vector2;
import com.github.fabiitch.gdxunit.VTestUtils;
import com.github.fabiitch.nz.java.math.vectors.V2CpyArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class V2CpyArrayTest {
    V2CpyArray array;

    @Test
    public void testCreate() {
        array = new V2CpyArray(10);
        Assertions.assertEquals(0, array.getSize());
        Assertions.assertEquals(10, array.getMaxSize());
        for (int i = 0; i < 10; i++) {
            Vector2 v = array.get(i);
            assertTrue(v.isZero());
        }
        assertThrows(IndexOutOfBoundsException.class, () -> array.get(11));
        assertThrows(IndexOutOfBoundsException.class, () -> array.get(50));
    }

    @Test
    public void testAdd() {
        array = new V2CpyArray(10);
        array.add(10, 10);
        array.add(20, 20);
        VTestUtils.assertEquals(10, 10, array.get(0));
        VTestUtils.assertEquals(20, 20, array.get(1));

        Assertions.assertEquals(2, array.getSize());
        Assertions.assertEquals(10, array.getMaxSize());
    }

    @Test
    public void testGrow() {
        testCreate();
        Assertions.assertEquals(0, array.getSize());
        Assertions.assertEquals(10, array.getMaxSize());
        array.grow(10);
        assertTrue(array.get(11).isZero());
        assertTrue(array.get(19).isZero());
        Assertions.assertEquals(0, array.getSize());
        Assertions.assertEquals(20, array.getMaxSize());
    }

    @Test
    public void testDecrease() {
        array = new V2CpyArray(10);
        array.decrease(5);

        Assertions.assertEquals(0, array.getSize());
        Assertions.assertEquals(5, array.getMaxSize());

        array.add(10, 10);
        Assertions.assertEquals(1, array.getSize());
        Assertions.assertEquals(5, array.getMaxSize());
    }

    @Test
    public void loopTest() {
        array = new V2CpyArray(10);


        array.add(new Vector2());
        array.add(new Vector2());
        array.add(new Vector2());
        testLoopMethods(3);

        array.remove(1);
        testLoopMethods(2);

        array.grow(21);
        testLoopMethods(2);

        for (int i = 0; i < 28; i++) {
            array.add(new Vector2());
        }
        testLoopMethods(30);


    }

    private void testLoopMethods(int expected) {
        Assertions.assertEquals(expected, countForEach(), "Count for each fail");
        Assertions.assertEquals(expected, countForI(), "Count for i fail");
        Assertions.assertEquals(expected, countLambda(), "Count Lambda fail");

    }

    private int countForEach() {
        int count = 0;
        for (Vector2 v : array) {
            count++;
        }
        return count;
    }

    private int countForI() {
        int count = 0;
        for (int i = 0, n = array.getSize(); i < n; i++) {
            count++;
        }
        return count;
    }

    private int countLambda() {
        AtomicInteger count = new AtomicInteger();
        array.forEach(vector2 -> count.getAndIncrement());
        return count.get();
    }
}
