package com.github.fabiitch.nz.demo.shapes;

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
import com.github.fabiitch.nz.java.math.shapes.utils.rectangle.path.RectanglePath;
import com.github.fabiitch.nz.java.math.shapes.utils.rectangle.path.RectanglePathSmooth;
import com.github.fabiitch.nz.java.math.shapes.utils.rectangle.path.RectanglePathStep;
import com.github.fabiitch.nz.java.math.utils.direction.Direction;
import com.github.fabiitch.nz.java.utils.Randoms;

@DemoScreen(group = "math.shape2D.rectangle.path")
public class RectanglePathDemo extends BaseTryScreen {

    Array<Pair<Color, Rectangle>> colorRectArray = new Array<>();
    Vector2 start = new Vector2(100, 100);

    public RectanglePathDemo() {
        super();
        RectanglePath rectanglePath = new RectanglePathSmooth();
        rectanglePath.add(Direction.Right, 200, 50);
        rectanglePath.add(Direction.Top, 100, 20);

        rectanglePath.add(Direction.Top, 100, 40);
        rectanglePath.add(Direction.Left, 100, 20);
        rectanglePath.add(Direction.Bot, 100, 20);
        rectanglePath.add(Direction.Left, 100, 10);
        rectanglePath.add(Direction.Top, 200, 50);
        rectanglePath.add(Direction.Right, 300, 10);
        rectanglePath.add(Direction.Bot, 50, 5);
        rectanglePath.add(Direction.Bot, 50, 25);
        rectanglePath.add(Direction.Right, 70, 5);
        rectanglePath.add(Direction.Bot, 150, 25);

        int i = 0;
        Array<Rectangle> compute = rectanglePath.compute(start);
        for (RectanglePathStep step : rectanglePath.getArray()) {
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
