package com.github.fabiitch.nz.java.math.utils.direction;

import com.github.fabiitch.gdxunit.tab.TabTestUtils;
import org.junit.jupiter.api.Test;

import static com.github.fabiitch.gdxunit.shape.ShapeTestBuilder.v2;
import static com.github.fabiitch.nz.java.math.utils.direction.Direction.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class DirectionTest {

    @Test
    public void getClockwiseTest() {
        TabTestUtils.assertEquals(new Direction[]{Top, Right, Bot, Left}, getClockwiseFrom(Top), true);
        TabTestUtils.assertEquals(new Direction[]{Bot, Left, Top, Right}, getClockwiseFrom(Bot), true);
        TabTestUtils.assertEquals(new Direction[]{Right, Bot, Left, Top}, getClockwiseFrom(Right), true);
        TabTestUtils.assertEquals(new Direction[]{Left, Top, Right, Bot}, getClockwiseFrom(Left), true);
    }

    @Test
    public void getCounterClockwiseTest() {
        TabTestUtils.assertEquals(new Direction[]{Top, Left, Bot, Right}, getCounterClockwiseFrom(Top), true);
        TabTestUtils.assertEquals(new Direction[]{Bot, Right, Top, Left}, getCounterClockwiseFrom(Bot), true);
        TabTestUtils.assertEquals(new Direction[]{Right, Top, Left, Bot}, getCounterClockwiseFrom(Right), true);
        TabTestUtils.assertEquals(new Direction[]{Left, Bot, Right, Top}, getCounterClockwiseFrom(Left), true);
    }

    @Test
    public void findDirectionTest() {
        assertEquals(Right, getPureDirection(v2(1, 0)));
        assertEquals(Left, getPureDirection(v2(-1, 0)));
        assertEquals(Top, getPureDirection(v2(0, 1)));
        assertEquals(Bot, getPureDirection(v2(0, -1)));
        assertNull(getPureDirection(v2(1, 1)));
    }


    @Test
    public void findClosestDirTest() {
        assertEquals(Right, getClosestDirection(v2(1, 0)));
        assertEquals(Right, getClosestDirection(v2(1, 0.2f)));

        assertEquals(Top, getClosestDirection(v2(0, 1.1f)));
    }
}
