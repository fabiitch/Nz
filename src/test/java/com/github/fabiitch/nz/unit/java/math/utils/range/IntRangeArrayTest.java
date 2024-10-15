package com.github.fabiitch.nz.unit.java.math.utils.range;

import com.badlogic.gdx.utils.Array;
import com.github.fabiitch.nz.java.math.utils.range.IntRange;
import com.github.fabiitch.nz.java.math.utils.range.IntRangeArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.github.fabiitch.gdxunit.ArrayTestUtils.assertHasSize;

public class IntRangeArrayTest {

    private IntRangeArray rangeArray;
    private Array<IntRange> subRanges;
    private Array<IntRange> cutRanges;

    @Test
    public void testRangeArray() {
        rangeArray = new IntRangeArray(0, 100);
        removeValues(50, 60);

        assertHasSize(2, subRanges);
        checkRange(0, 0, 49);
        checkRange(1, 61, 100);

        assertHasSize(1, cutRanges);
        checkCut(0, 50, 60);

        //change nothing
        removeValues(55, 58);
        assertHasSize(2, subRanges);
        checkRange(0, 0, 49);
        checkRange(1, 61, 100);

        assertHasSize(1, cutRanges);
        checkCut(0, 50, 60);


        //--- grow end present cut
        removeValues(55, 70);
        assertHasSize(2, subRanges);
        checkRange(0, 0, 49);
        checkRange(1, 71, 100);

        assertHasSize(1, cutRanges);
        checkCut(0, 50, 70);

        //--- grow start present cut
        removeValues(45, 55);
        assertHasSize(2, subRanges);
        checkRange(0, 0, 44);
        checkRange(1, 71, 100);

        assertHasSize(1, cutRanges);
        checkCut(0, 45, 70);

        //--- add cut after start
        removeValues(0, 10);
        assertHasSize(2, subRanges);
        checkRange(0, 11, 44);
        checkRange(1, 71, 100);

        assertHasSize(2, cutRanges);
        checkCut(0, 0, 10);
        checkCut(1, 45, 70);

        //--- add cut on end
        removeValues(90, 100);
        assertHasSize(2, subRanges);
        checkRange(0, 11, 44);
        checkRange(1, 71, 89);

        assertHasSize(3, cutRanges);
        checkCut(0, 0, 10);
        checkCut(1, 45, 70);
        checkCut(2, 90, 100);

        //---
        addValues(55,65);
        assertHasSize(3, subRanges);
        checkRange(0, 11, 44);
        checkRange(1, 55, 65);
        checkRange(2, 71, 89);

        assertHasSize(4, cutRanges);
        checkCut(0, 0, 10);
        checkCut(1, 45, 54);
        checkCut(2, 66, 70);
        checkCut(3, 90, 100);

        //---
        addValues(60,70);
        assertHasSize(2, subRanges);
        checkRange(0, 11, 44);
        checkRange(1, 55, 89);

        assertHasSize(3, cutRanges);
        checkCut(0, 0, 10);
        checkCut(1, 45, 54);
        checkCut(2, 90, 100);

        //---
        addValues(88,98);
        assertHasSize(2, subRanges);
        checkRange(0, 11, 44);
        checkRange(1, 55, 98);

        assertHasSize(3, cutRanges);
        checkCut(0, 0, 10);
        checkCut(1, 45, 54);
        checkCut(2, 99, 100);

        //---
        addValues(50,100);
        assertHasSize(2, subRanges);
        checkRange(0, 11, 44);
        checkRange(1, 50, 100);

        assertHasSize(2, cutRanges);
        checkCut(0, 0, 10);
        checkCut(1, 45, 49);
    }


    private void checkSize(int subRange, int cuts) {
        assertHasSize(subRange, subRanges);
        assertHasSize(cuts, cutRanges);
    }

    private void addValues(int start, int end) {
        rangeArray.addValues(start, end);
        computeArrays();
    }

    private void removeValues(int start, int end) {
        rangeArray.removeValues(start, end);
        computeArrays();
    }

    private void computeArrays() {
        subRanges = rangeArray.getSubRange();
        cutRanges = rangeArray.getCuts();
    }

    private void checkRange(int rangeIndex, int start, int end) {
        Assertions.assertEquals(new IntRange(start, end), subRanges.get(rangeIndex));
    }

    private void checkCut(int rangeIndex, int start, int end) {
        Assertions.assertEquals(new IntRange(start, end), cutRanges.get(rangeIndex));
    }
}
