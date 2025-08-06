package com.github.fabiitch.nz.java.math.utils.direction;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.github.fabiitch.nz.java.data.collections.utils.TabUtils;
import com.github.fabiitch.nz.java.math.vectors.v2.V2;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Direction {

    Top(new Vector2(0, 1)) {
        @Override
        public Direction getReverse() {
            return Bot;
        }

        @Override
        public Orientation getOrientation() {
            return Orientation.Vertical;
        }
    },

    Bot(new Vector2(0, -1)) {
        @Override
        public Direction getReverse() {
            return Top;
        }

        @Override
        public Orientation getOrientation() {
            return Orientation.Vertical;
        }
    },

    Left(new Vector2(-1, 0)) {
        @Override
        public Direction getReverse() {
            return Right;
        }

        @Override
        public Orientation getOrientation() {
            return Orientation.Horizontal;
        }
    },

    Right(new Vector2(1, 0)) {
        @Override
        public Direction getReverse() {
            return Left;
        }

        @Override
        public Orientation getOrientation() {
            return Orientation.Horizontal;
        }
    };

    private final static Vector2 tmpV2 = new Vector2();
    public static final Direction[] VALUES = Direction.values();
    public static final Direction[] CLOCKWISE = {Top, Right, Bot, Left};
    public static final Direction[] COUNTER_CLOCKWISE = {Top, Left, Bot, Right};

    private final Vector2 vector;

    public Orientation getOtherOrientation() {
        return getOrientation().getOtherOrientation();
    }

    public abstract Direction getReverse();

    public abstract Orientation getOrientation();

    public Vector2 addTo(Vector2 position, float dst) {
        return position.add(getAddTo(dst));
    }

    public Vector2 subTo(Vector2 position, float dst) {
        return position.sub(getAddTo(dst));
    }

    public Vector2 getAddTo(float dst) {
        return new Vector2(vector).scl(dst);
    }

    public boolean isHorizontal() {
        return getOrientation() == Orientation.Horizontal;
    }

    public Vector2 getVector() {
        return vector.cpy();
    }

    public boolean isVertical() {
        return getOrientation() == Orientation.Vertical;
    }

    public static Direction getPureDirection(Vector2 vector) {
        float angle = vector.angleRad();
        for (Direction direction : VALUES)
            if (MathUtils.isEqual(angle, direction.vector.angleRad()))
                return direction;
        return null;
    }

    public static Direction getClosestDirection(Vector2 vector) {
        return getClosestDirectionBetween(vector, VALUES);
    }

    public static Direction getClosestDirectionBetween(Vector2 vector, Direction... directions) {
        Direction closestDirection = null;
        float maxDotProduct = -Float.MAX_VALUE;

        for (Direction direction : directions) {
            float dotProduct = vector.dot(direction.vector);
            // Si le produit scalaire est plus grand, on met à jour la direction la plus proche
            if (dotProduct > maxDotProduct) {
                maxDotProduct = dotProduct;
                closestDirection = direction;
            }
        }
        return closestDirection;
    }

    public static Direction getDirection(Vector2 from, Vector2 to) {
        Vector2 dir = V2.directionTo(from, to, tmpV2);
        return getClosestDirection(dir);
    }

    public static Direction[] getClockwiseFrom(Direction start) {
        return getClockwiseFrom(start, CLOCKWISE);
    }

    public static Direction[] getCounterClockwiseFrom(Direction start) {
        return getClockwiseFrom(start, COUNTER_CLOCKWISE);
    }

    private static Direction[] getClockwiseFrom(Direction start, Direction[] directions) {
        Direction[] res = new Direction[4];
        int indexStart = TabUtils.indexOf(directions, start);

        if (4 - indexStart >= 0) System.arraycopy(directions, indexStart, res, 0, 4 - indexStart);

        if (indexStart > 0) System.arraycopy(directions, 0, res, 4 - indexStart, indexStart);
        return res;
    }


}
