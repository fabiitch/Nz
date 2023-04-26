package com.fabiitch.nz.unit.algo;

import com.badlogic.gdx.utils.Array;
import com.fabiitch.nz.java.algo.DependantSorter;
import com.fabiitch.nz.java.algo.SequenceLockException;
import com.fabiitch.nz.java.data.collections.utils.ArrayUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DependantSorterTest {

    @Test
    public void serviceInitTest() {
        DependantSorterMock s1 = new DependantSorterMock();
        DependantSorterMock s2 = new DependantSorterMock(s1);
        DependantSorterMock s3 = new DependantSorterMock();
        DependantSorterMock s4 = new DependantSorterMock(s1, s3);
        DependantSorterMock s5 = new DependantSorterMock(s2, s3);
        DependantSorterMock s6 = new DependantSorterMock(s5);
        DependantSorterMock s7 = new DependantSorterMock();

        Array<DependantSorter> array = new Array<>();
        array.add(s1, s2, s3, s4);
        array.add(s5, s6, s7);

        List<DependantSorter> sort = DependantSorter.sort(ArrayUtils.toList(array));
        assertDoesNotThrow(() -> DependantSorter.sort(ArrayUtils.toList(array)));

    }

    @Test
    public void serviceInitFailTest() {
        DependantSorterMock s1 = new DependantSorterMock();
        DependantSorterMock s2 = new DependantSorterMock();
        DependantSorterMock s3 = new DependantSorterMock();

        s1.setWaiting(s2, s3);
        s2.setWaiting(s1);

        Array<DependantSorter> array = new Array<>();
        array.add(s1, s2, s3);

        assertThrows(SequenceLockException.class, () -> DependantSorter.sort(ArrayUtils.toList(array)));

    }

    private class DependantSorterMock implements DependantSorter {

        private DependantSorter[] servicesToWait;

        public DependantSorterMock(DependantSorter... servicesToWait) {
            this.servicesToWait = servicesToWait;
        }

        public void setWaiting(DependantSorter... servicesToWait) {
            this.servicesToWait = servicesToWait;
        }

        @Override
        public List<DependantSorter> waitingList() {
            return Arrays.asList(servicesToWait);
        }
    }
}
