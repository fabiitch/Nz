package com.github.fabiitch.nz.java.math.path.rectangle;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.github.fabiitch.nz.java.math.path.rectangle.corridor.CorridorPathStep;
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
        direction = null;
        length = 0f;
        size = 0f;
    }


    public static CorridorPathStep toCorridor(RectanglePathStep step, float wallSize) {
        CorridorPathStep corridor = new CorridorPathStep();
        corridor.setDirection(step.getDirection());
        corridor.setLength(step.getLength());
        corridor.setWalkSize(step.getSize());
        corridor.setWallsSize(wallSize);
        return corridor;
    }

    public static Array<CorridorPathStep> toCorridor(Array<RectanglePathStep> steps, float wallSize) {
        Array<CorridorPathStep> res = new Array<>(steps.size);
        for (RectanglePathStep step : steps) {
            res.add(toCorridor(step, wallSize));
        }
        return res;
    }
}
