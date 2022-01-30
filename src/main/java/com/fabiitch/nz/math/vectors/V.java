package com.fabiitch.nz.math.vectors;

import com.badlogic.gdx.math.Vector;

public class V {
    private V() {

    }

    public static <V extends Vector<V>> V directionTo(V from, V to, V result) {
        return result.set(from).sub(to).nor();
    }
}
