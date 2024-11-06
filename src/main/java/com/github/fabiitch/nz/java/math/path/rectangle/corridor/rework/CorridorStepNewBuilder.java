package com.github.fabiitch.nz.java.math.path.rectangle.corridor.rework;

import com.github.fabiitch.nz.java.math.utils.direction.Direction;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CorridorStepNewBuilder {

    private static CorridorStepNew getBasic(Direction direction, float length, float walkSize) {
        CorridorStepNew step = new CorridorStepNew();
        step.setDirection(direction);
        step.setLength(length);
        step.setWalkSize(walkSize);
        return step;
    }

    public static CorridorStepNew top(float length, float walkSize, float rightWallSize, float leftWallSize) {
        CorridorStepNew step = getBasic(Direction.Top, length, walkSize);
        step.setWallSize(Direction.Right, rightWallSize);
        step.setWallSize(Direction.Left, leftWallSize);
        return step;
    }

    public static CorridorStepNew bot(float length, float walkSize, float rightWallSize, float leftWallSize) {
        CorridorStepNew step = getBasic(Direction.Bot, length, walkSize);
        step.setWallSize(Direction.Right, rightWallSize);
        step.setWallSize(Direction.Left, leftWallSize);
        return step;
    }

    public static CorridorStepNew right(float length, float walkSize, float topWallSize, float botWallSize) {
        CorridorStepNew step = getBasic(Direction.Right, length, walkSize);
        step.setWallSize(Direction.Top, topWallSize);
        step.setWallSize(Direction.Bot, botWallSize);
        return step;
    }

    public static CorridorStepNew left(float length, float walkSize, float topWallSize, float botWallSize) {
        CorridorStepNew step = getBasic(Direction.Left, length, walkSize);
        step.setWallSize(Direction.Top, topWallSize);
        step.setWallSize(Direction.Bot, botWallSize);
        return step;
    }
}
