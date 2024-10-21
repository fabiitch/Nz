package com.github.fabiitch.nz.java.math.utils.direction;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.github.fabiitch.gdxunit.ShapeTestBuilder.v2;

public class DirectionTest {


    @Test
    public void findDirectionTest() {
        Assertions.assertEquals(Direction.Right, Direction.getPureDirection(v2(1, 0)));
        Assertions.assertEquals(Direction.Left, Direction.getPureDirection(v2(-1, 0)));
        Assertions.assertEquals(Direction.Top, Direction.getPureDirection(v2(0, 1)));
        Assertions.assertEquals(Direction.Bot, Direction.getPureDirection(v2(0, -1)));
        Assertions.assertNull(Direction.getPureDirection(v2(1, 1)));
    }


    @Test
    public void findClosestDirTest() {
        Assertions.assertEquals(Direction.Right, Direction.getClosestDirection(v2(1, 0)));
        Assertions.assertEquals(Direction.Right, Direction.getClosestDirection(v2(1, 0.2f)));
    }
}
