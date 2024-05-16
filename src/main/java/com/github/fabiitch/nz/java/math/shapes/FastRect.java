package com.github.fabiitch.nz.java.math.shapes;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;

//TODO a voir
public class FastRect implements Shape2D {
    public float x, y;
    public float width, height;

    public FastRect() {

    }

    public FastRect(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public FastRect(FastRect rect) {
        x = rect.x;
        y = rect.y;
        width = rect.width;
        height = rect.height;
    }

    public FastRect(Rectangle rect) {
        x = rect.x;
        y = rect.y;
        width = rect.width;
        height = rect.height;
    }

    public FastRect set(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        return this;
    }

    @Override
    public boolean contains(Vector2 point) {
        return contains(point.x, point.y);
    }

    @Override
    public boolean contains(float x, float y) {
        return this.x <= x && this.x + this.width >= x && this.y <= y && this.y + this.height >= y;
    }
}
