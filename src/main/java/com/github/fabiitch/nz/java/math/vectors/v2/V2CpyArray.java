package com.github.fabiitch.nz.java.math.vectors.v2;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * Array Vector who only copy values inside him
 */
public class V2CpyArray implements Iterable<Vector2> {
    public Array<Vector2> array;

    boolean sizeFaked = false;
    int fakeSize = 0;
    int realSize = 0;

    public V2CpyArray(int size) {
        this.array = new Array<>(size);
        grow(size);
    }

    public int getSize() {
        return fakeSize;
    }

    public int getMaxSize() {
        return array.size;
    }

    public Vector2 get(int index) {
        return array.get(index);
    }

    public void add(Vector2 v2) {
        this.add(v2.x, v2.y);
    }

    public void add(float x, float y) {
        trueSize();
        array.get(fakeSize++).set(x, y);
    }


    public void grow(int size) {
        trueSize();
        for (int i = 0; i < size; i++)
            array.add(new Vector2());
    }


    public void clear() {
        array.clear();
    }

    public void remove(int index) {
        trueSize();
        array.removeIndex(index);
        fakeSize--;
    }

    public void decrease(int size) {
        trueSize();
        array.setSize(array.size - size);
    }

    private void fakeSize() {
        if (!sizeFaked) {
            realSize = array.size;
            array.size = fakeSize;
            sizeFaked = true;
        }
    }

    private void trueSize() {
        if (sizeFaked) {
            array.size = realSize;
            sizeFaked = false;
        }
    }

    @Override
    public Iterator<Vector2> iterator() {
        fakeSize();
        return array.iterator();
    }

    @Override
    public void forEach(Consumer<? super Vector2> action) {
        fakeSize();
        array.forEach(action);
    }

    @Override
    public Spliterator<Vector2> spliterator() {
        fakeSize();
        return array.spliterator();
    }
}
