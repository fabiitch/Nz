package com.github.fabiitch.nz.gdx.render.shape;

import com.badlogic.gdx.math.Rectangle;
import com.github.fabiitch.nz.java.math.shapes.Segment;
import com.github.fabiitch.nz.java.math.shapes.intersectors.IntersectorSegmentRectangle;

public class Frustum2D {

    private Rectangle rectangle = new Rectangle();

    public boolean inside(Segment segment){
        return false;
    }
}
