package com.github.fabiitch.nz.demo.shapes.path;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.github.fabiitch.nz.demo.internal.BaseTryScreen;
import com.github.fabiitch.nz.demo.internal.selectors.DemoScreen;
import com.github.fabiitch.nz.gdx.debug.huddebug.HudDebug;
import com.github.fabiitch.nz.gdx.log.StrFormat;
import com.github.fabiitch.nz.java.data.Pair;
import com.github.fabiitch.nz.java.math.path.rectangle.corridor.CorridorPath;
import com.github.fabiitch.nz.java.math.path.rectangle.RectanglePathStep;
import com.github.fabiitch.nz.java.math.path.rectangle.corridor.CorridorPathStep;
import com.github.fabiitch.nz.java.utils.Randoms;

@DemoScreen(group = "math.shape2D.rectangle.path")
public class CorridorPathDemo extends BaseTryScreen {

    Array<Pair<Color, Rectangle>> colorRectArray = new Array<>();
    Vector2 start = new Vector2(100, 100);

    public CorridorPathDemo() {
        super();
        Array<CorridorPathStep> rectanglePathSteps = DemoPathUtils.corridorPathStep();

        CorridorPath corridorPath = new CorridorPath();
        corridorPath.getArray().addAll(rectanglePathSteps);

        Array<Rectangle> compute = corridorPath.compute(start);

        for (int i = 0; i < compute.size; i += 2) {
            Color color = Randoms.color();
            CorridorPathStep step = rectanglePathSteps.get(i / 2);
            String format = StrFormat.format("Dir={}, length={}, size={}, wallSize={}", step.getDirection(), step.getLength(), step.getWalkSize(), step.getWallSize());
            HudDebug.addTopRight(i + " ", format, color);
            colorRectArray.add(Pair.of(color, compute.get(i)));
            colorRectArray.add(Pair.of(color, compute.get(i+1)));
        }
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        shapeRenderer.begin();
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.set(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.circle(start, 10);

        for (Pair<Color, Rectangle> colorRect : colorRectArray) {
            shapeRenderer.setColor(colorRect.getKey());
            shapeRenderer.rect(colorRect.getValue());
        }

        shapeRenderer.end();
    }
}
