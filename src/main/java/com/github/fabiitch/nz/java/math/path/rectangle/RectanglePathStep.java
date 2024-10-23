package com.github.fabiitch.nz.java.math.path.rectangle;

import com.badlogic.gdx.utils.Pool;
import com.github.fabiitch.nz.java.math.utils.direction.Direction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RectanglePathStep implements Pool.Poolable {

    private Direction direction;
    private float length;
    private float size;

    @Override
    public void reset() {
        direction= null;
        length= 0f;
        size= 0f;
    }
}
