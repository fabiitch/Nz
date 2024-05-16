package com.github.fabiitch.nz.unit.java.data.collections.utils;

import com.badlogic.gdx.utils.Array;
import com.github.fabiitch.nz.java.data.collections.utils.ToArray;
import com.github.fabiitch.nz.java.data.collections.utils.ArrayUtils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayUtilsTest {

    private static <T> void assertArrayEquals(Array<T> array1, Array<T> array2) {
        assertEquals(array1.size, array2.size);
        for (int i = 0; i < array1.size; i++) {
            assertEquals(array1.get(i), array2.get(i));
        }
    }

    @Test
    @Disabled //TODO DOODODOD
    public void getRangeFromEndTest() {
        Array<Integer> array = new Array<>();
        array.add(0, 1, 2, 3);
        array.add(4, 5, 6, 7);

        //normal //TODO probleme vient de ArrayGdx.items
        assertArrayEquals(ToArray.get(7, 6, 5), ArrayUtils.getRangeFromEnd(array, 0, 2, new Array<>()));
        assertArrayEquals(ToArray.get(5, 4, 3), ArrayUtils.getRangeFromEnd(array, 3, 5, new Array<>()));
    }

    @Test
    public void getFromEndTest() {
        Array<Integer> array = new Array<>();
        array.add(1, 2, 3);
        array.add(4, 5, 6);

        assertEquals(6, ArrayUtils.getFromEnd(array, 0));
        assertEquals(1, ArrayUtils.getFromEnd(array, 5));
        assertNull(ArrayUtils.getFromEnd(array, 7));
    }

    @Test
    public void getLastTest() {
        {
            Array<Integer> array = new Array<>();
            array.add(1, 2, 3);
            assertEquals(3, ArrayUtils.getLast(array));
        }
        {
            Array<Integer> array = new Array<>();
            array.add(1);
            assertEquals(1, ArrayUtils.getLast(array));
        }
        {
            assertNull(ArrayUtils.getLast(new Array<>()));
        }
    }

    @Test
    public void addIfNotPresentValueTest() {
        Array<Integer> array = new Array<>();
        array.add(1, 2, 3);
        assertEquals(3, array.size);
        ArrayUtils.addIfNotPresent(array, Integer.valueOf(4));
        assertEquals(4, array.size);
        ArrayUtils.addIfNotPresent(array, Integer.valueOf(4));
        assertEquals(4, array.size);
    }

    @Test
    public void addIfNotPresentArrayGdxTest() {
        Array<Integer> arrayBase = new Array<>();
        Integer one = 1, three = 3, nine = 9;
        arrayBase.add(one, 2, three, nine);

        assertEquals(4, arrayBase.size);

        Array<Integer> arrayAdd = new Array<>();
        arrayAdd.add(one, 10, three);

        ArrayUtils.addIfNotPresent(arrayBase, arrayAdd);
        assertEquals(5, arrayBase.size);
    }

    @Test
    public void addIfNotPresentArrayTest() {
        Array<Integer> arrayBase = new Array<>();
        Integer one = 1, three = 3, nine = 9;
        arrayBase.add(one, 2, three, nine);
        assertEquals(4, arrayBase.size);

        Integer[] arrayAdd = new Integer[]{one, 55, nine};
        ArrayUtils.addIfNotPresent(arrayBase, arrayAdd);
        assertEquals(5, arrayBase.size);
    }

    @Test
    public void containsOnlyTest() {
        Array<Object> array = new Array<>();
        Object a = new Object(), b = new Object(), c = new Object(), d = new Object(), e = new Object();
        array.add(a, b, c, d);

        assertTrue(ArrayUtils.containsOnly(array, a, b, c, d));
        assertFalse(ArrayUtils.containsOnly(array, a, b, c));
        assertFalse(ArrayUtils.containsOnly(array, a, b, c, d, e));

        Object cpy = a;
        assertTrue(ArrayUtils.containsOnly(array, cpy, b, c, d));
        assertFalse(ArrayUtils.containsOnly(array, a, cpy, b, c));
        assertFalse(ArrayUtils.containsOnly(array, a, cpy, b, c, d));

        array.add(cpy, cpy);
        assertTrue(ArrayUtils.containsOnly(array, a, b, c, d, a, a));
        assertFalse(ArrayUtils.containsOnly(array, a, b, c, d, cpy));
    }

    @Test
    public void copyTest() {
        Array<Integer> source = new Array<>();
        source.add(5, 5, 6, 8);
        {
            //empty
            Array<Integer> target = ArrayUtils.copy(source, new Array<>());
            ArrayUtilsTest.assertArrayEquals(source, target);
        }
        {
            //source  bigger
            Array<Integer> target = new Array<>();
            target.add(1, 2);
            ArrayUtils.copy(source, target);
            ArrayUtilsTest.assertArrayEquals(source, target);
        }
        {
            //target  bigger
            Array<Integer> target = new Array<>();
            target.add(1, 2, 5, 9);
            target.add(1, 2, 5, 9);
            ArrayUtils.copy(source, target);
            ArrayUtilsTest.assertArrayEquals(source, target);
        }
        {
            //same size
            Array<Integer> target = new Array<>();
            source.add(5, 5, 6, 8);
            source.add(3, 2, 6, 8);
            ArrayUtils.copy(source, target);
            ArrayUtilsTest.assertArrayEquals(source, target);
        }


    }
}
