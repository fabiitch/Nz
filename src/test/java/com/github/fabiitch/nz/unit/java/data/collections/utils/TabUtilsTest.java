package com.github.fabiitch.nz.unit.java.data.collections.utils;

import com.github.fabiitch.nz.java.data.collections.utils.TabUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class TabUtilsTest {

    @Test
    public void clearValuesBeginEndTest() {
        Integer[] array = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        TabUtils.clearValues(array, 3, 8);
        Assertions.assertEquals(10, array.length);
        for (int i = 0; i < 10; i++) {
            if (i >= 3 && i <= 8)
                Assertions.assertNull(array[i]);
            else
                Assertions.assertNotNull(array[i]);
        }
    }

    @Test
    public void clearValuesBeginTest() {
        Integer[] array = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        TabUtils.clearValues(array, 3);
        Assertions.assertEquals(10, array.length);
        for (int i = 0; i < array.length; i++) {
            if (i >= 3)
                Assertions.assertNull(array[i]);
            else
                Assertions.assertNotNull(array[i]);
        }
    }

    @Test
    public void clearValuesTest() {
        Integer[] array = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        TabUtils.clearValues(array);
        Assertions.assertEquals(10, array.length);
        Arrays.stream(array).forEach(i -> Assertions.assertNull(i));
    }

    @Test
    public void removeAndDecalTest() {
        Integer[] array = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        TabUtils.removeAndDecal(array, 5);

        Assertions.assertEquals(10, array.length);

        Assertions.assertEquals(0, array[0]);
        Assertions.assertEquals(1, array[1]);
        Assertions.assertEquals(2, array[2]);
        Assertions.assertEquals(3, array[3]);
        Assertions.assertEquals(4, array[4]);
        Assertions.assertEquals(6, array[5]);
        Assertions.assertEquals(7, array[6]);
        Assertions.assertEquals(8, array[7]);
        Assertions.assertEquals(9, array[8]);
        Assertions.assertEquals(null, array[9]);
    }
}
