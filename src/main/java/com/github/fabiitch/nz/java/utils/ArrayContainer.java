package com.github.fabiitch.nz.java.utils;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import lombok.Getter;

@Getter
public abstract class ArrayContainer<T> implements Pool.Poolable {

    protected final Array<T> array = new Array<>();

    public ArrayContainer<T> add(T value) {
        array.add(value);
        return this;
    }

    public ArrayContainer<T> addAll(T... values) {
        array.addAll(values);
        return this;
    }

    public ArrayContainer<T> addAll(Array<T> values) {
        array.addAll(values);
        return this;
    }

    public ArrayContainer<T> addAll(ArrayContainer<T> values) {
        array.addAll(values.array);
        return this;
    }

    public ArrayContainer<T> remove(int index) {
        array.removeIndex(index);
        return this;
    }


    @Override
    public void reset() {
        array.clear();
    }
}
