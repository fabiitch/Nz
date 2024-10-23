package com.github.fabiitch.nz.demo.shapes.path;

import com.badlogic.gdx.utils.Array;
import com.github.fabiitch.nz.java.math.path.rectangle.CorridorPath;
import com.github.fabiitch.nz.java.math.path.rectangle.RectanglePath;
import com.github.fabiitch.nz.java.math.path.rectangle.RectanglePathStep;
import com.github.fabiitch.nz.java.math.utils.direction.Direction;

public class DemoPathUtils {


    public static Array<RectanglePathStep> pathStep(){
        RectanglePath rectanglePath = new CorridorPath();
        rectanglePath.add(Direction.Top, 150, 50);
        rectanglePath.add(Direction.Top, 100, 20);

        rectanglePath.add(Direction.Right, 100, 40);
        rectanglePath.add(Direction.Bot, 100, 20);
        rectanglePath.add(Direction.Bot, 50, 10);

        rectanglePath.add(Direction.Right, 200, 20);
        rectanglePath.add(Direction.Top, 100, 10);
        return rectanglePath.getArray();
    }
}
