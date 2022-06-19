package com.fabiitch.nz.data.array;

import com.badlogic.gdx.utils.Array;

public class ToArray {

    public static <T> Array<T> get(T[][] tab) {
        Array<T> array = new Array<>();
        for (int i = 0; i < tab.length; i++) {
            array.addAll(tab[i]);
        }
        return array;
    }
}
