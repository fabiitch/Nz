package com.github.fabiitch.nz.java.math.shapes.utils.rectangle.rectborder;

import com.badlogic.gdx.math.Vector2;

public class DoubleRectBuilder {

    private float centerX, centerY;


    private float smallWidth, smallHeight;

    public DoubleRectBuilder(Vector2 center) {

    }

    public DoubleRectBuilder(float centerX, float centerY) {
    }

    public DoubleRectBuilder smallRect(float width, float height) {
        this.smallWidth =width;
        this.smallHeight = height;
        return this;
    }


    public boolean isReady() {

    }
}
