package com.github.fabiitch.nz.java.math.shapes;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NzRectangle implements Shape2D {

    private float x, y;  //center
    private float width, height;

    private Rectangle rectangle;

    @Override
    public boolean contains(Vector2 point) {
        rectangle = toRectangle();
        return rectangle.contains(point);
    }

    @Override
    public boolean contains(float x, float y) {
        rectangle = toRectangle();
        return rectangle.contains(x, y);
    }

    public Rectangle toRectangle() {
        return new Rectangle(x - width / 2, y - height / 2, width, height);
    }
}
