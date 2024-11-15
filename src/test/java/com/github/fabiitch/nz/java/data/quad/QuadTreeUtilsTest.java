package com.github.fabiitch.nz.java.data.quad;

import com.badlogic.gdx.math.Rectangle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.github.fabiitch.gdxunit.shape.ShapeTestBuilder.*;
import static com.github.fabiitch.nz.java.data.quadtree.QuadTreeUtils.*;

public class QuadTreeUtilsTest {

        @Test
        public void getSubRegionRect() {
            Rectangle rect = new Rectangle(0, 0, 200, 100);
            Assertions.assertEquals(getNW(rect, r()), r(0, 50, 100, 50));
            Assertions.assertEquals(getSW(rect, r()), r(0, 0, 100, 50));
            Assertions.assertEquals(getNE(rect, r()), r(100, 50, 100, 50));
            Assertions.assertEquals(getSE(rect, r()), r(100, 0, 100, 50));
        }
}
