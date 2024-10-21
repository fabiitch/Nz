package com.github.fabiitch.nz.java.math.utils.direction;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Direction {

    Top(new Vector2(0, 1)) {
        @Override
        public Orientation getOrientation() {
            return Orientation.Vertical;
        }

        @Override
        public Direction getReverse() {
            return Bot;
        }
    },

    Bot(new Vector2(0, -1)) {
        @Override
        public Orientation getOrientation() {
            return Orientation.Vertical;
        }


        @Override
        public Direction getReverse() {
            return Top;
        }
    },

    Left(new Vector2(-1, 0)) {
        @Override
        public Orientation getOrientation() {
            return Orientation.Horizontal;
        }

        @Override
        public Direction getReverse() {
            return Right;
        }
    },

    Right(new Vector2(1, 0)) {
        @Override
        public Orientation getOrientation() {
            return Orientation.Horizontal;
        }

        @Override
        public Direction getReverse() {
            return Left;
        }
    };

    private final Vector2 vector;

    public abstract Orientation getOrientation();

    public abstract Direction getReverse();

    public Vector2 addTo(Vector2 position, float dst) {
        return position.add(getAddTo(dst));
    }

    public Vector2 subTo(Vector2 position, float dst) {
        return position.sub(getAddTo(dst));
    }

    public Vector2 getAddTo(float dst) {
        return new Vector2(vector).scl(dst);
    }

    public static Direction[] values = Direction.values();


    public static Direction getPureDirection(Vector2 vector) {
        float angle = vector.angleRad();
        for (Direction direction : values)
            if (MathUtils.isEqual(angle, direction.vector.angleRad()))
                return direction;
        return null;
    }

    public static Direction getClosestDirection(Vector2 vector) {
        float vectorAngle = vector.angleDeg();
        float minAngle = Integer.MAX_VALUE;
        Direction result = null;

        for (Direction direction : values)
            if (Math.abs(vectorAngle - Math.abs(direction.vector.angleDeg())) < minAngle)
                result = direction;

        return result;
    }
}
