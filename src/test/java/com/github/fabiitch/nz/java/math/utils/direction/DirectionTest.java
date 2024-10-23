package com.github.fabiitch.nz.java.math.utils.direction;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.github.fabiitch.gdxunit.ShapeTestBuilder.v2;
import static org.junit.jupiter.api.Assertions.*;

public class DirectionTest {


    @Test
    public void findDirectionTest() {
        assertEquals(Direction.Right, Direction.getPureDirection(v2(1, 0)));
        assertEquals(Direction.Left, Direction.getPureDirection(v2(-1, 0)));
        assertEquals(Direction.Top, Direction.getPureDirection(v2(0, 1)));
        assertEquals(Direction.Bot, Direction.getPureDirection(v2(0, -1)));
        assertNull(Direction.getPureDirection(v2(1, 1)));
    }


    @Test
    public void findClosestDirTest() {
        assertEquals(Direction.Right, Direction.getClosestDirection(v2(1, 0)));
        assertEquals(Direction.Right, Direction.getClosestDirection(v2(1, 0.2f)));

        assertEquals(Direction.Top, Direction.getClosestDirection(v2(0, 1.1f)));
    }
}
