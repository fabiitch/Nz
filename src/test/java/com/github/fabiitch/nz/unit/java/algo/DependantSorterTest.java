package com.github.fabiitch.nz.unit.java.algo;

import com.badlogic.gdx.utils.Array;
import com.github.fabiitch.nz.java.algo.DependantSorter;
import com.github.fabiitch.nz.java.algo.DependencyLockException;
import com.github.fabiitch.nz.java.data.collections.utils.ArrayUtils;
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

        Array<DependantSorterMock> array = new Array<>();
        array.add(s1, s2, s3, s4);
        array.add(s5, s6, s7);
        array.shuffle();

        assertDoesNotThrow(() -> {
            List<DependantSorterMock> sort = DependantSorter.sort(ArrayUtils.toList(array));
            for (DependantSorterMock re : sort) {
                re.checkDependantAreInit();
            }
        });

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

        assertThrows(DependencyLockException.class, () -> DependantSorter.sort(ArrayUtils.toList(array)));

    }

    private static int countInstance = 1;

    private class DependantSorterMock implements DependantSorter {

        private DependantSorterMock[] servicesToWait;
        public int instanceCount;
        public boolean init = false;

        public DependantSorterMock(DependantSorterMock... servicesToWait) {
            this.servicesToWait = servicesToWait;
            this.instanceCount = countInstance++;
        }

        public void checkDependantAreInit() {
            for (DependantSorterMock dependantSorterMock : servicesToWait)
                if (!dependantSorterMock.init)
                    throw new DependencyLockException("locked !");
            this.init = true;
        }

        public void setWaiting(DependantSorterMock... servicesToWait) {
            this.servicesToWait = servicesToWait;
        }

        @Override
        public List<DependantSorterMock> dependencies() {
            return Arrays.asList(servicesToWait);
        }

        @Override
        public String name() {
            return null;
        }
    }
}
