package com.github.fabiitch.nz.java.math.utils.direction;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.github.fabiitch.gdxunit.ShapeTestBuilder.v2;
import static com.github.fabiitch.nz.java.math.utils.direction.Direction.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class DirectionTest {

    @Test
    public void getClockwiseTest() {
        Direction[] clockwiseFrom = getClockwiseFrom(Top);
        Assertions.assertEquals(CLOCKWISE, clockwiseFrom);

        clockwiseFrom = getClockwiseFrom(Bot);
        Assertions.assertEquals(new Direction[]{Bot, Left, Top, Right}, clockwiseFrom);
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
