package com.github.fabiitch.nz.java.math.utils.direction;

import com.badlogic.gdx.math.Vector2;

public enum CompassDirection {

    Top(Direction.Top.getVector()) {
        @Override
        public CompassDirection getOpposite() {
            return Bottom;
        }
    },
    TopRight(new Vector2(1, 1).nor()) {
        @Override
        public CompassDirection getOpposite() {
            return BottomLeft;
        }
    },
    Right(Direction.Right.getVector()) {
        @Override
        public CompassDirection getOpposite() {
            return Left;
        }
    },
    BottomRight(new Vector2(1, -1).nor()) {
        @Override
        public CompassDirection getOpposite() {
            return TopLeft;
        }
    },
    Bottom(Direction.Bot.getVector()) {
        @Override
        public CompassDirection getOpposite() {
            return Top;
        }
    },
    BottomLeft(new Vector2(-1, -1).nor()) {
        @Override
        public CompassDirection getOpposite() {
            return TopRight;
        }
    },
    Left(Direction.Left.getVector()) {
        @Override
        public CompassDirection getOpposite() {
            return Right;
        }
    },
    TopLeft(new Vector2(-1, 1).nor()) {
        @Override
        public CompassDirection getOpposite() {
            return BottomRight;
        }
    };

    private final Vector2 vector;

    CompassDirection(Vector2 vector) {
        this.vector = vector;
    }

    public Vector2 getVector() {
        return vector;
    }

    public boolean isDiagonal(){
        return this.vector.x != 0 && this.vector.y != 0;
    }

    public CompassDirection getOpposite() {
        return this;
    }
}

