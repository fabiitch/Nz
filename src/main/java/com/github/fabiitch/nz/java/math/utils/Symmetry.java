package com.github.fabiitch.nz.java.math.utils;

import com.badlogic.gdx.math.Vector2;
import com.github.fabiitch.nz.java.math.shapes.Segment;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Symmetry {

    private final static Segment tmpSeg = new Segment();
    private final static Vector2 tmpV2 = new Vector2();

    public static Vector2 withPoint(Vector2 origin, Vector2 pos) {
        tmpV2.set(pos).sub(origin);

        return new Vector2(pos).sub(tmpV2);

    }
}
