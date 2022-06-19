package com.fabiitch.nz.unit.data.array;

import com.badlogic.gdx.utils.Array;
import com.fabiitch.nz.data.array.ArrayUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ArrayUtilsTest {

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
}
