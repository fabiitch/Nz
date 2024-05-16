package com.github.fabiitch.nz.java.math.shapes;

import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;

//TODO a continue
//voir si on met sa dans quadtree
public class Square implements Shape2D {
    public float lenght;
    public float x, y;

    public Square() {

    }

    public Square(float lenght) {
        this.lenght = lenght;
    }

    public Square(float lenght, float x, float y) {
        this.lenght = lenght;
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean contains(Vector2 point) {
        return contains(point.x, point.y);
    }

    @Override
    public boolean contains(float x, float y) {
        return this.x <= x && this.x + this.lenght >= x && this.y <= y && this.y + this.lenght >= y;
    }
}
