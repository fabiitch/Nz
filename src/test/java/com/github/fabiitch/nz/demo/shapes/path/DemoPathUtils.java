package com.github.fabiitch.nz.demo.shapes.path;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.utils.Array;
import com.github.fabiitch.nz.java.math.shapes.utils.rectangle.path.CorridorPath;
import com.github.fabiitch.nz.java.math.shapes.utils.rectangle.path.RectanglePath;
import com.github.fabiitch.nz.java.math.shapes.utils.rectangle.path.RectanglePathStep;
import com.github.fabiitch.nz.java.math.utils.direction.Direction;

public class DemoPathUtils {


    public static Array<RectanglePathStep> pathStep(){
        RectanglePath rectanglePath = new CorridorPath();
        rectanglePath.add(Direction.Right, 200, 50);
        rectanglePath.add(Direction.Top, 100, 20);

        rectanglePath.add(Direction.Top, 100, 40);
        rectanglePath.add(Direction.Left, 100, 20);
        rectanglePath.add(Direction.Bot, 100, 20);
        rectanglePath.add(Direction.Left, 100, 10);
        rectanglePath.add(Direction.Top, 200, 50);
        rectanglePath.add(Direction.Right, 300, 10);
        rectanglePath.add(Direction.Bot, 50, 5);
        rectanglePath.add(Direction.Bot, 50, 25);
        rectanglePath.add(Direction.Right, 70, 5);
        rectanglePath.add(Direction.Bot, 150, 25);
        return rectanglePath.getArray();
    }
}
