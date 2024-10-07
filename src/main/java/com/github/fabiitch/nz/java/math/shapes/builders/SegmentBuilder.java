package com.github.fabiitch.nz.java.math.shapes.builders;

import com.badlogic.gdx.math.Vector2;
import com.github.fabiitch.nz.java.math.shapes.Segment;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SegmentBuilder {

    private final static Vector2 tmpV2 = new Vector2();

    public static Segment postAndDir(Vector2 pos, Vector2 dir, float lenght) {
        tmpV2.set(dir).nor().setLength(lenght);
        Vector2 b = pos.cpy().add(tmpV2);

        return new Segment(pos.cpy(), b);
    }

    public static Segment posMiddleDir(Vector2 pos, Vector2 dir, float lenght) {
        tmpV2.set(dir).nor().setLength(lenght / 2);

        Vector2 a = pos.cpy().add(tmpV2);
        Vector2 b = pos.cpy().sub(tmpV2);

        return new Segment(a, b);
    }
}
