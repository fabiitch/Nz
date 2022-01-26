package com.fabiitch.nz.math.vectors;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class V2 {

    public static float[] toFloatArray(Array<Vector2> array) {
        return toFloatArray(array.toArray(Vector2.class));
    }

    public static float[] toFloatArray(Vector2[] array) {
        float[] verts = new float[array.length * 2];
        for (int i = 0, j = 0; i < array.length * 2; i += 2, j++) {
            Vector2 vector2 = array[j];
            verts[i] = vector2.x;
            verts[i + 1] = vector2.y;
        }
        return verts;
    }

}
