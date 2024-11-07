package com.github.fabiitch.nz.java.math.path.rectangle.corridor;

import com.github.fabiitch.nz.java.math.utils.direction.Direction;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CorridorPathStepBuilder {

    private static CorridorPathStep getBasic(Direction direction, float length, float walkSize) {
        CorridorPathStep step = new CorridorPathStep();
        step.setDirection(direction);
        step.setLength(length);
        step.setWalkSize(walkSize);
        return step;
    }

    public static CorridorPathStep top(float length, float walkSize, float rightWallSize, float leftWallSize) {
        CorridorPathStep step = getBasic(Direction.Top, length, walkSize);
        step.setWallSize(Direction.Right, rightWallSize);
        step.setWallSize(Direction.Left, leftWallSize);
        return step;
    }

    public static CorridorPathStep bot(float length, float walkSize, float rightWallSize, float leftWallSize) {
        CorridorPathStep step = getBasic(Direction.Bot, length, walkSize);
        step.setWallSize(Direction.Right, rightWallSize);
        step.setWallSize(Direction.Left, leftWallSize);
        return step;
    }

    public static CorridorPathStep right(float length, float walkSize, float topWallSize, float botWallSize) {
        CorridorPathStep step = getBasic(Direction.Right, length, walkSize);
        step.setWallSize(Direction.Top, topWallSize);
        step.setWallSize(Direction.Bot, botWallSize);
        return step;
    }

    public static CorridorPathStep left(float length, float walkSize, float topWallSize, float botWallSize) {
        CorridorPathStep step = getBasic(Direction.Left, length, walkSize);
        step.setWallSize(Direction.Top, topWallSize);
        step.setWallSize(Direction.Bot, botWallSize);
        return step;
    }
}
