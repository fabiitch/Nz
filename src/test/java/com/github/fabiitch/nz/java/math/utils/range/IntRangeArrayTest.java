package com.github.fabiitch.nz.java.math.utils.range;

import com.badlogic.gdx.utils.Array;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.github.fabiitch.gdxunit.array.ArrayTestUtils.assertSize;

public class IntRangeArrayTest {

    private IntRangeArray rangeArray;
    private Array<IntRange> subRanges;
    private Array<IntRange> cutRanges;

    @Test
    public void testRangeArray() {
        rangeArray = new IntRangeArray(0, 100);
        removeValues(50, 60);

        assertSize(2, subRanges);
        checkRange(0, 0, 49);
        checkRange(1, 61, 100);

        assertSize(1, cutRanges);
        checkCut(0, 50, 60);

        //change nothing
        removeValues(55, 58);
        assertSize(2, subRanges);
        checkRange(0, 0, 49);
        checkRange(1, 61, 100);

        assertSize(1, cutRanges);
        checkCut(0, 50, 60);


        //--- grow end present cut
        removeValues(55, 70);
        assertSize(2, subRanges);
        checkRange(0, 0, 49);
        checkRange(1, 71, 100);

        assertSize(1, cutRanges);
        checkCut(0, 50, 70);

        //--- grow start present cut
        removeValues(45, 55);
        assertSize(2, subRanges);
        checkRange(0, 0, 44);
        checkRange(1, 71, 100);

        assertSize(1, cutRanges);
        checkCut(0, 45, 70);

        //--- add cut after start
        removeValues(0, 10);
        assertSize(2, subRanges);
        checkRange(0, 11, 44);
        checkRange(1, 71, 100);

        assertSize(2, cutRanges);
        checkCut(0, 0, 10);
        checkCut(1, 45, 70);

        //--- add cut on end
        removeValues(90, 100);
        assertSize(2, subRanges);
        checkRange(0, 11, 44);
        checkRange(1, 71, 89);

        assertSize(3, cutRanges);
        checkCut(0, 0, 10);
        checkCut(1, 45, 70);
        checkCut(2, 90, 100);

        //---
        addValues(55, 65);
        assertSize(3, subRanges);
        checkRange(0, 11, 44);
        checkRange(1, 55, 65);
        checkRange(2, 71, 89);

        assertSize(4, cutRanges);
        checkCut(0, 0, 10);
        checkCut(1, 45, 54);
        checkCut(2, 66, 70);
        checkCut(3, 90, 100);

        //---
        addValues(60, 70);
        assertSize(2, subRanges);
        checkRange(0, 11, 44);
        checkRange(1, 55, 89);

        assertSize(3, cutRanges);
        checkCut(0, 0, 10);
        checkCut(1, 45, 54);
        checkCut(2, 90, 100);

        //---
        addValues(88, 98);
        assertSize(2, subRanges);
        checkRange(0, 11, 44);
        checkRange(1, 55, 98);

        assertSize(3, cutRanges);
        checkCut(0, 0, 10);
        checkCut(1, 45, 54);
        checkCut(2, 99, 100);

        //---
        addValues(50, 100);
        assertSize(2, subRanges);
        checkRange(0, 11, 44);
        checkRange(1, 50, 100);

        assertSize(2, cutRanges);
        checkCut(0, 0, 10);
        checkCut(1, 45, 49);
    }

    @Test
    public void testChangeEndMore() {
        rangeArray = new IntRangeArray(0, 100);
        rangeArray.setEnd(150, true);
        checkSize(1, 0);
        checkRange(0, 0, 150);

        rangeArray = new IntRangeArray(0, 100);
        rangeArray.setEnd(150, false);
        checkSize(1, 1);
        checkRange(0, 0, 100);
        checkCut(0, 101, 150);
    }

    @Test
    public void testChangeEndLess() {
        rangeArray = new IntRangeArray(0, 100);
        rangeArray.setEnd(50, true);

        checkSize(1, 0);
        checkRange(0, 0, 50);

        rangeArray = new IntRangeArray(0, 100);
        rangeArray.setEnd(50, true);

        checkSize(1, 0);
        checkRange(0, 0, 50);
    }

    @Test
    public void testChangeStartLess() {
        rangeArray = new IntRangeArray(50, 100);
        rangeArray.setStart(0, true);

        checkSize(1, 0);
        checkRange(0, 0, 100);

        rangeArray = new IntRangeArray(50, 100);
        rangeArray.setStart(0, false);

        checkSize(1, 1);
        checkRange(0, 50, 100);
        checkCut(0, 0, 49);
    }



    private void checkSize(int subRange, int cuts) {
        computeArrays();
        assertSize(subRange, subRanges);
        assertSize(cuts, cutRanges);
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
        computeArrays();
        Assertions.assertEquals(new IntRange(start, end), subRanges.get(rangeIndex));
    }

    private void checkCut(int rangeIndex, int start, int end) {
        computeArrays();
        Assertions.assertEquals(new IntRange(start, end), cutRanges.get(rangeIndex));
    }
}
