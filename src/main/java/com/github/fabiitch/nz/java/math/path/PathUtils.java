package com.github.fabiitch.nz.java.math.path;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.github.fabiitch.nz.java.math.shapes.Segment;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PathUtils {


    public static Array<Segment> pathToSegments(Array<Vector2> path) {
        if (path.size < 2)
            throw new IllegalArgumentException("Path should contains at least two points");

        Array<Segment> segments = new Array<>();
        for (int i = 0; i < path.size - 1; i++) {
            Vector2 p0 = path.get(i);
            Vector2 p1 = path.get(i + 1);
            segments.add(new Segment(p0, p1));
        }
        return segments;
    }

}
