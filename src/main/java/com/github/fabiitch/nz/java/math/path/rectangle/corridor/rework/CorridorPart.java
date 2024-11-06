package com.github.fabiitch.nz.java.math.path.rectangle.corridor.rework;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.github.fabiitch.nz.java.math.shapes.utils.RectangleUtils;
import com.github.fabiitch.nz.java.math.utils.direction.Direction;
import com.github.fabiitch.nz.java.math.utils.direction.Orientation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CorridorPart {

    private Direction direction;
    private Vector2 middleStart, middleEnd;
    private Rectangle rectWallA, rectWallB;
    private Rectangle rectWalk;

    private Rectangle blockerRectA, blockerRectB;

    public Orientation getOrientation() {
        return direction.getOrientation();
    }

    public float getWallSize(Direction posWall) {
        return RectangleUtils.getSize(getWall(posWall), direction.getOtherOrientation());
    }

    public float getWallLenght(Direction posWall) {
        return RectangleUtils.getSize(getWall(posWall), direction.getOrientation());
    }

    public Rectangle getWall(Direction posWall) {
        if (posWall.getOrientation() == direction.getOrientation())
            throw new IllegalArgumentException("wall and corridor part cant have same orientation");

        if (posWall == direction.getOtherOrientation().getDirectionA())
            return rectWallA;
        else
            return rectWallB;
    }

    public float getLength() {
        return RectangleUtils.getSize(rectWalk, direction.getOrientation());
    }

    public float getWalkSize() {
        return RectangleUtils.getSize(rectWalk, direction.getOtherOrientation());
    }

    public float getWallSizeA() {
        return RectangleUtils.getSize(rectWallA, direction.getOtherOrientation());
    }

    public float getWallSizeB() {
        return RectangleUtils.getSize(rectWallB, direction.getOtherOrientation());
    }

    public float getLenghtWallA() {
        return RectangleUtils.getSize(rectWallA, direction.getOrientation());
    }

    public float getLenghtWallB() {
        return RectangleUtils.getSize(rectWallB, direction.getOrientation());
    }
}
