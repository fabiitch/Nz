package com.github.fabiitch.nz.java.math.utils.direction.path;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.github.fabiitch.nz.java.math.utils.direction.Direction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NonNull
public class DirectionLength implements Pool.Poolable {
    private Direction direction;
    private float length;

    public DirectionLength(DirectionLength directionLength) {
        this.direction = directionLength.getDirection();
        this.length = directionLength.getLength();
    }

    public Vector2 addTo(Vector2 vector) {
        return direction.addTo(vector, length);
    }

    public Vector2 subTo(Vector2 vector) {
        return direction.subTo(vector, length);
    }

    @Override
    public void reset() {
        direction = null;
        length = 0;
    }
}
