package com.github.fabiitch.nz.gdx.pools;

import com.badlogic.gdx.utils.Pool;

public abstract class BasePool<T extends Pool.Poolable> extends Pool<T> {

    @Override
    protected void reset(T object) {
        object.reset();
    }
}
