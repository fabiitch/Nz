package com.github.fabiitch.nz.demo.shapes.path;

import com.badlogic.gdx.utils.Array;
import com.github.fabiitch.nz.java.math.path.rectangle.corridor.CorridorPath;
import com.github.fabiitch.nz.java.math.path.rectangle.RectanglePath;
import com.github.fabiitch.nz.java.math.path.rectangle.RectanglePathStep;
import com.github.fabiitch.nz.java.math.path.rectangle.corridor.CorridorPathStep;
import com.github.fabiitch.nz.java.math.utils.direction.Direction;

public class DemoPathUtils {


    public static Array<RectanglePathStep> pathStep(){
        RectanglePath rectanglePath = new RectanglePath();
        rectanglePath.add(Direction.Top, 150, 50);
        rectanglePath.add(Direction.Top, 100, 20);

        rectanglePath.add(Direction.Right, 100, 40);
        rectanglePath.add(Direction.Bot, 100, 20);
        rectanglePath.add(Direction.Bot, 50, 10);

        rectanglePath.add(Direction.Right, 200, 20);
        rectanglePath.add(Direction.Top, 100, 10);
        return rectanglePath.getArray();
    }

    public static Array<CorridorPathStep> corridorPathStep(){
        CorridorPath rectanglePath = new CorridorPath();
        rectanglePath.add(Direction.Top, 150, 50,30);
        rectanglePath.add(Direction.Top, 100, 20,20);

        rectanglePath.add(Direction.Right, 100, 40,50);
        rectanglePath.add(Direction.Bot, 100, 20,30);
        rectanglePath.add(Direction.Bot, 50, 10,10);

        rectanglePath.add(Direction.Right, 200, 20,5);
        rectanglePath.add(Direction.Top, 100, 10,40);
        rectanglePath.getArray().forEach(w-> w.setWalkSize(10));

        return rectanglePath.getArray();
    }
}
