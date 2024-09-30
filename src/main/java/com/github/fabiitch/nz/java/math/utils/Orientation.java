package com.github.fabiitch.nz.java.math.utils;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.github.fabiitch.nz.java.math.shapes.Segment;
import lombok.Getter;

@Getter
public enum Orientation {

    Vertical(Direction.Top, Direction.Bot) {

        @Override
        public float getLength(Rectangle rectangle) {
            return rectangle.getHeight();
        }

        @Override
        public Vector2 getAddTo(float amount, Vector2 result) {
            return result.set(0, amount);
        }
    },
    Horizontal(Direction.Right, Direction.Left) {
        @Override
        public float getLength(Rectangle rectangle) {
            return rectangle.getWidth();
        }

        @Override
        public Vector2 getAddTo(float amount, Vector2 result) {
            return result.set(amount, 0);
        }
    },
    ;

    Orientation(Direction directionA, Direction directionB) {
        this.directionA = directionA;
        this.directionB = directionB;
    }

    public Segment getSegmentFrom(Vector2 position, float segmentSize) {

        Segment segment = new Segment();
        segment.a.set(position).add(directionA.getAddTo(segmentSize / 2));
        segment.b.set(position).add(directionB.getAddTo(segmentSize / 2));
        return segment;
    }

    public Direction[] getDirections() {
        return new Direction[]{directionA, directionB};
    }

    private final static Vector2 tmp = new Vector2();
    private final Direction directionA;
    private final Direction directionB;

    public Orientation getOtherOrientation() {
        if (this == Horizontal)
            return Vertical;
        return Horizontal;
    }

    public Vector2 addTo(Vector2 position, float amount) {
        return position.add(getAddTo(amount, tmp));
    }


    public abstract float getLength(Rectangle rectangle);

    public abstract Vector2 getAddTo(float amount, Vector2 result);
}
