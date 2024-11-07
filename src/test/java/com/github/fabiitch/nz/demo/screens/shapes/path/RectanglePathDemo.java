package com.github.fabiitch.nz.demo.screens.shapes.path;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.github.fabiitch.nz.demo.internal.BaseTryScreen;
import com.github.fabiitch.nz.demo.internal.selectors.DemoScreen;
import com.github.fabiitch.nz.gdx.debug.DebugDisplayUtils;
import com.github.fabiitch.nz.gdx.debug.huddebug.HudDebug;
import com.github.fabiitch.nz.gdx.log.StrFormat;
import com.github.fabiitch.nz.java.data.Pair;
import com.github.fabiitch.nz.java.math.path.rectangle.RectanglePath;
import com.github.fabiitch.nz.java.math.path.rectangle.RectanglePathStep;
import com.github.fabiitch.nz.java.utils.Randoms;

@DemoScreen(group = "math.path")
public class RectanglePathDemo extends BaseTryScreen {

    Array<Pair<Color, Rectangle>> colorRectArray = new Array<>();
    Vector2 start = new Vector2(100, 100);

    public RectanglePathDemo() {
        super();
        Array<RectanglePathStep> rectanglePathSteps = DemoPathUtils.pathStep();

        RectanglePath path = new RectanglePath();
        path.getArray().addAll(rectanglePathSteps);
        int i = 0;
        Array<Rectangle> compute = path.compute(start);
        for (RectanglePathStep step : path.getArray()) {
            Color color = Randoms.color();
            String format = StrFormat.format("Dir={}, length={}, size={}", step.getDirection(), step.getLength(), step.getSize());
            HudDebug.addTopRight(i + " ", format, color);

            colorRectArray.add(Pair.of(color, compute.get(i)));

            HudDebug.addBotRight(i + " ", DebugDisplayUtils.printRectangleSize(compute.get(i)), color);
            i++;
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
