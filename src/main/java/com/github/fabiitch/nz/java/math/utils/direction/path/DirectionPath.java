package com.github.fabiitch.nz.java.math.utils.direction.path;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.github.fabiitch.nz.java.data.collections.utils.ArrayUtils;
import com.github.fabiitch.nz.java.math.utils.direction.Direction;
import lombok.Getter;

@Getter
public class DirectionPath implements Pool.Poolable {

    private final Array<DirectionLength> array = new Array<>();

    //TODO a voir pour un pools

    public static DirectionPath get(Direction direction, float length) {
        DirectionPath directionPath = new DirectionPath();
        directionPath.add(direction, length);
        return directionPath;
    }

    public static DirectionPath get(float length, Direction... directions) {
        DirectionPath directionPath = new DirectionPath();
        directionPath.adds(length, directions);
        return directionPath;
    }

    public DirectionPath cpy() {
        DirectionPath copy = new DirectionPath();
        for (DirectionLength directionLength : array) {
            copy.add(new DirectionLength(directionLength));
        }
        return copy;
    }

    public DirectionPath setLength(float length) {
        for (DirectionLength directionLength : array) {
            directionLength.setLength(length);
        }
        return this;
    }

    public Vector2 addTo(Vector2 position) {
        for (DirectionLength directionLength : array) {
            directionLength.addTo(position);
        }
        return position;
    }

    public Vector2 subTo(Vector2 position) {
        for (DirectionLength directionLength : array) {
            directionLength.subTo(position);
        }
        return position;
    }

    private DirectionPath adds(float length, Direction... directions) {
        for (Direction direction : directions) {
            add(direction, length);
        }
        return this;
    }

    private DirectionPath add(Direction direction, float length) {
        array.add(new DirectionLength(direction, length));
        return this;
    }

    private DirectionPath add(DirectionLength directionLength) {
        array.add(directionLength);
        return this;
    }

    private DirectionPath remove(int index) {
        array.removeIndex(index);
        return this;
    }

    private DirectionPath removeLast() {
        ArrayUtils.removeLast(array);
        return this;
    }

    private DirectionPath removeFirst() {
        ArrayUtils.removeFirst(array);
        return this;
    }

    @Override
    public void reset() {
        array.clear();
    }
}
