package com.github.fabiitch.nz.java.math.path.rectangle.corridor.rework;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.github.fabiitch.nz.java.data.collections.utils.ArrayUtils;
import com.github.fabiitch.nz.java.math.utils.direction.Direction;
import com.github.fabiitch.nz.java.utils.ArrayContainer;

public class CorridorPathRework extends ArrayContainer<CorridorStepNew> {

    public void add(Direction direction, int length, int walkSize, float wallSize) {
        array.add(new CorridorStepNew(direction, length, walkSize, wallSize));
    }

}
