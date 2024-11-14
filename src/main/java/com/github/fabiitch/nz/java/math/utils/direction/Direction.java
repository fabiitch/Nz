package com.github.fabiitch.nz.java.math.utils.direction;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.github.fabiitch.nz.java.data.collections.utils.TabUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Direction {

    Top(new Vector2(0, 1), Orientation.Vertical) {
        @Override
        public Direction getReverse() {
            return Bot;
        }
    },

    Bot(new Vector2(0, -1), Orientation.Vertical) {
        @Override
        public Direction getReverse() {
            return Top;
        }
    },

    Left(new Vector2(-1, 0), Orientation.Horizontal) {
        @Override
        public Direction getReverse() {
            return Right;
        }
    },

    Right(new Vector2(1, 0), Orientation.Horizontal) {
        @Override
        public Direction getReverse() {
            return Left;
        }
    };

    public static final Direction[] VALUES = Direction.values();
    public static final Direction[] CLOCKWISE = {Top, Right, Bot, Left};

    private final Vector2 vector;
    @Getter
    private final Orientation orientation;

    public Orientation getOtherOrientation() {
        return orientation.getOtherOrientation();
    }

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

    public Vector2 getVector() {
        return vector.cpy();
    }

    public boolean isHorizontal() {
        return getOrientation() == Orientation.Horizontal;
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

    public static Direction[] getClockwiseFrom(Direction start) {
        int indexStart = TabUtils.indexOf(CLOCKWISE, start);
        Direction[] res = new Direction[4];
        for(int i = indexStart ; i < 4 ; i++)
            res[i] = CLOCKWISE[i];
        for(int i = 0 ; i < indexStart ; i++)
            res[i] = CLOCKWISE[i];
        return res;
    }

}
