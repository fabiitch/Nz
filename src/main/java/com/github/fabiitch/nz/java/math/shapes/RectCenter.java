package com.github.fabiitch.nz.java.math.shapes;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RectCenter implements Shape2D , Pool.Poolable {

    @Setter
    private float x, y;  //center
    @Setter
    private float width, height;

    private Rectangle rectangle;

    public RectCenter(Rectangle r) {
        set(r);
    }

    public RectCenter(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public RectCenter(Vector2 position, float width, float height) {
        this.x = position.x;
        this.y = position.y;
        this.width = width;
        this.height = height;
    }

    public RectCenter setPosition(float x, float y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public RectCenter setSize(float width, float height) {
        this.width = width;
        this.height = height;
        return this;
    }

    public RectCenter set(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        return this;
    }

    public RectCenter set(Rectangle r) {
        set(r.x + r.width / 2, r.y + r.height / 2, r.width, r.height);
        return this;
    }

    @Override
    public boolean contains(Vector2 point) {
        rectangle = toRectangle(this.rectangle);
        return rectangle.contains(point);
    }

    @Override
    public boolean contains(float x, float y) {
        rectangle = toRectangle(this.rectangle);
        return rectangle.contains(x, y);
    }

    public Rectangle toRectangle(Rectangle rectangle) {
        return rectangle.set(x - width / 2, y - height / 2, width, height);
    }

    @Override
    public void reset() {
        x=y=width=height=0;
    }
}
