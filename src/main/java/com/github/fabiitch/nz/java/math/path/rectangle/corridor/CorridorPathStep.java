package com.github.fabiitch.nz.java.math.path.rectangle.corridor;

import com.badlogic.gdx.utils.Pool;
import com.github.fabiitch.nz.java.math.utils.direction.Direction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CorridorPathStep implements Pool.Poolable {

    private Direction direction;
    private float length;

    private float walkSize;
    private float wallSize;


    @Override
    public void reset() {

    }
}
