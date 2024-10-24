package com.github.fabiitch.nz.java.math.path.rectangle.corridor;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.github.fabiitch.nz.java.math.utils.direction.Direction;
import com.github.fabiitch.nz.java.math.utils.direction.Orientation;

public class CorridorPathResult  {

    private Vector2 start;
    private Vector2 end;

    public Array<CorridorRectangle> arrayOne;
    public Array<CorridorRectangle> arrayTwo;
    public Array<Rectangle> middle;



    public class Step {
        private float lenght;
        private Rectangle middle;
        private Vector2 start, end;
        private Direction direction;
    }

    public class CorridorRectangle {
        Orientation orientation;
        Direction posFromPath;
        Rectangle rectangle;
    }
}
