package com.github.fabiitch.nz.java.math.path.rectangle.corridor.rework;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.github.fabiitch.nz.java.math.utils.direction.Direction;
import com.github.fabiitch.nz.java.math.utils.direction.Orientation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CorridorStepNew implements Pool.Poolable {

    private Direction direction;
    private float length;

    private float walkSize;

    private float wallSizeA;
    private float wallSizeB;

    public CorridorStepNew(Direction direction, float length, float walkSize, float wallSize) {
        this.direction = direction;
        this.length = length;
        this.walkSize = walkSize;
        setWallsSize(wallSize);
    }

    public Direction getPositionWallA() {
        return direction.getOtherOrientation().getDirectionA();
    }

    public Direction getPositionWallB() {
        return direction.getOtherOrientation().getDirectionB();
    }

    public Vector2 addTo(Vector2 vector) {
        return direction.addTo(vector, length);
    }

    public void setWallsSize(float size) {
        setWallSizeA(size);
        setWallSizeB(size);
    }

    public float getWallSize(Direction wallDirection) {
        if (this.direction.getOrientation() == wallDirection.getOrientation())
            throw new IllegalArgumentException("wall and step cant have same orientation");

        if (wallDirection == this.direction.getOtherOrientation().getDirectionA())
            return wallSizeA;
        else
            return wallSizeB;
    }

    public void setWallSize(Direction wallDirection, float size) {
        if (this.direction.getOrientation() == wallDirection.getOrientation())
            throw new IllegalArgumentException("wall and step cant have same orientation");

        if (wallDirection == this.direction.getOtherOrientation().getDirectionA())
            setWallSizeA(size);
        else
            setWallSizeB(size);
    }

    public Orientation getOrientation() {
        return direction.getOrientation();
    }

    public Orientation getOtherOrientation() {
        return direction.getOtherOrientation();
    }

    @Override
    public void reset() {
        this.direction = null;
        this.length = 0;
        this.walkSize = 0;
        this.wallSizeA = 0;
        this.wallSizeB = 0;
    }
}
