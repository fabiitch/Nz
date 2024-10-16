package com.github.fabiitch.nz.java.math.utils.direction;

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

    private final Vector2 direction;

    public abstract Orientation getOrientation();

    public abstract Direction getReverse();

    public Vector2 addTo(Vector2 position, float dst) {
        return position.add(getAddTo(dst));
    }

    public Vector2 getAddTo(float dst) {
        return new Vector2(direction).scl(dst);
    }
}
