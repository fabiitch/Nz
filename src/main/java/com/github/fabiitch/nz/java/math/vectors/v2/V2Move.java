package com.github.fabiitch.nz.java.math.vectors.v2;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import lombok.Getter;

@Getter
public class V2Move implements Pool.Poolable {

    private final Vector2 direction = new Vector2();
    private float length;

    public V2Move(Vector2 dir, float length) {
        setDirection(dir);
        this.length = length;
    }

    public V2Move set(float x, float y, float length) {
        setDirection(x, y);
        setLength(length);
        return this;
    }

    public V2Move set(Vector2 dir, float length) {
        setDirection(dir);
        setLength(length);
        return this;
    }

    public Vector2 compute(Vector2 result) {
        return result.set(this.direction).scl(length);
    }

    public Vector2 compute() {
        return this.direction.cpy().scl(length);
    }

    public V2Move setDirection(float x, float y) {
        this.direction.set(x, y);
        this.direction.nor();
        return this;
    }

    public V2Move setDirection(final Vector2 dir) {
        setDirection(dir.x, dir.y);
        return this;
    }

    public V2Move setLength(float length) {
        this.length = length;
        return this;
    }

    @Override
    public void reset() {
        direction.setZero();
        length = 0;
    }
}
