package com.github.fabiitch.nz.gdx.scene2D.nz.value;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Pool;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NzPosValue implements Pool.Poolable {
    private Rectangle bounds;
    private boolean center;
    private NzPosType type = NzPosType.Percent;


    @Override
    public void reset() {
        type = NzPosType.Percent;
    }
}
