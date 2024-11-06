package com.github.fabiitch.nz.demo.shapes.path;

import com.badlogic.gdx.utils.Array;
import com.github.fabiitch.nz.java.math.path.rectangle.corridor.CorridorPath;
import com.github.fabiitch.nz.java.math.path.rectangle.RectanglePath;
import com.github.fabiitch.nz.java.math.path.rectangle.RectanglePathStep;
import com.github.fabiitch.nz.java.math.path.rectangle.corridor.CorridorPathStep;
import com.github.fabiitch.nz.java.math.path.rectangle.corridor.rework.CorridorStepNew;
import com.github.fabiitch.nz.java.math.path.rectangle.corridor.rework.CorridorStepNewBuilder;
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

    public static Array<CorridorStepNew> stepNew(){
        Array<CorridorStepNew> array = new Array<>();
        array.add(CorridorStepNewBuilder.top(100,20,50,5));
        array.add(CorridorStepNewBuilder.top(50,40,30,25));
        array.add(CorridorStepNewBuilder.top(100,20,30,25));

        array.add(CorridorStepNewBuilder.right(150,40,200,25));
        array.add(CorridorStepNewBuilder.bot(150,50,100,20));


        array.add(CorridorStepNewBuilder.right(100,20,30,50));
        array.add(CorridorStepNewBuilder.right(50,80,50,50));

        array.add(CorridorStepNewBuilder.right(200,50,20,5));
        array.add(CorridorStepNewBuilder.top(100,25,20,15));
        array.add(CorridorStepNewBuilder.right(100,25,20,15));
        array.add(CorridorStepNewBuilder.top(100,25,20,15));
        return array;
    }
}
