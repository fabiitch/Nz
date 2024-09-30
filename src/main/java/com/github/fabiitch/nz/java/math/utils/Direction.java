package com.github.fabiitch.nz.java.math.utils;

import com.badlogic.gdx.math.Vector2;
import com.github.fabiitch.nz.java.math.vectors.V2;

public enum Direction {

    Top {
        @Override
        public Orientation getOrientation() {
            return Orientation.Vertical;
        }

        @Override
        public Vector2 addTo(Vector2 position, float dst) {
            position.y += dst;
            return position;
        }

        @Override
        public Vector2 getAddTo(float dst) {
            return new Vector2(0, dst);
        }

        @Override
        public Direction getReverse() {
            return Bot;
        }
    },

    Bot {
        @Override
        public Orientation getOrientation() {
            return Orientation.Vertical;
        }

        @Override
        public Vector2 addTo(Vector2 position, float dst) {
            position.y -= dst;
            return position;
        }

        @Override
        public Vector2 getAddTo(float dst) {
            return new Vector2(0, -dst);
        }

        @Override
        public Direction getReverse() {
            return Top;
        }
    },

    Left {
        @Override
        public Orientation getOrientation() {
            return Orientation.Horizontal;
        }

        @Override
        public Vector2 addTo(Vector2 position, float dst) {
            position.x -= dst;
            return position;
        }

        @Override
        public Vector2 getAddTo(float dst) {
            return new Vector2(-dst, 0f);
        }

        @Override
        public Direction getReverse() {
            return Right;
        }
    },

    Right {
        @Override
        public Orientation getOrientation() {
            return Orientation.Horizontal;
        }

        @Override
        public Vector2 addTo(Vector2 position, float dst) {
            position.x += dst;
            return position;
        }

        @Override
        public Vector2 getAddTo(float dst) {
            return new Vector2(dst, 0f);
        }

        @Override
        public Direction getReverse() {
            return Left;
        }
    };


    public abstract Orientation getOrientation();

    public abstract Vector2 addTo(Vector2 position, float dst);

    public Vector2 getSubTo(float dst) {
        return V2.inv(getAddTo(dst));
    }

    public abstract Vector2 getAddTo(float dst);

    public abstract Direction getReverse();
}
