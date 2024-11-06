package com.github.fabiitch.nz.java.math.path.rectangle.corridor.rework;

import com.github.fabiitch.nz.java.math.utils.direction.Direction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CorridorStepNew {

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

    public void setWallsSize(float size) {
        setWallSizeA(size);
        setWallSizeB(size);
    }

    public void setWallSize(Direction direction, float size) {
        if (this.direction == null)
            throw new IllegalArgumentException("direction of step is null");
        if (this.direction.getOrientation() == direction.getOrientation())
            throw new IllegalArgumentException("wall and step cant have same orientation");

        if (direction == this.direction.getOtherOrientation().getDirectionA())
            setWallSizeA(size);
        else
            setWallSizeB(size);
    }
}
