package com.github.fabiitch.nz.java.math.utils.range;

import com.github.fabiitch.nz.java.math.utils.range.IntRange;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IntRangeTest {


    @Test
    public void testIntRange() {
        IntRange range = new IntRange(1, 10);

        assertTrue(range.contains(1));
        assertFalse(range.contains(15));

        assertTrue(range.intersect(new IntRange(-20, 20)));
        assertTrue(range.intersect(new IntRange(-20, 5)));

        assertTrue(range.intersect(new IntRange(5, 8)));
        assertTrue(range.intersect(new IntRange(5, 20)));

        assertFalse(range.intersect(new IntRange(-10, 0)));
        assertFalse(range.intersect(new IntRange(11, 12)));
    }
}
