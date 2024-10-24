package com.github.fabiitch.nz.java.math.path;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.github.fabiitch.nz.java.math.vectors.V2Move;
import com.github.fabiitch.nz.java.utils.ArrayContainer;

public class LinePath extends ArrayContainer<V2Move> {

    public Vector2 getArrival(Vector2 start) {
        Vector2 tmp = new Vector2();
        Vector2 cpy = start.cpy();

        for (V2Move v2Move : array) {
            cpy.add(v2Move.compute(tmp));
        }
        return cpy;
    }

    public Array<Vector2> compute(Vector2 start) {
        Vector2 tmp = new Vector2();
        Vector2 cpy = start.cpy();
        Array<Vector2> result = new Array<>();

        for (V2Move v2Move : array) {
            cpy.add(v2Move.compute(tmp));
            result.add(cpy.cpy());
        }
        return result;
    }
}
