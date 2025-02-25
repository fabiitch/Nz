package com.github.fabiitch.nz.demo.screens.shapes.path;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.github.fabiitch.nz.demo.NzDemoScreenLauncher;
import com.github.fabiitch.nz.demo.internal.BaseDemoScreen;
import com.github.fabiitch.nz.demo.internal.selectors.DemoScreen;
import com.github.fabiitch.nz.gdx.debug.DebugDisplayUtils;
import com.github.fabiitch.nz.gdx.debug.huddebug.HudDebug;
import com.github.fabiitch.nz.gdx.log.StrFormat;
import com.github.fabiitch.nz.java.data.Pair;
import com.github.fabiitch.nz.java.math.path.rectangle.RectanglePathSmooth;
import com.github.fabiitch.nz.java.math.path.rectangle.RectanglePathStep;
import com.github.fabiitch.nz.java.utils.randoms.Randoms;

@DemoScreen(group = "math.path")
public class RectangleSmoothPathDemo extends BaseDemoScreen {

    public static void main(String[] args) {
        NzDemoScreenLauncher.startScreen(RectangleSmoothPathDemo.class);
    }

    Array<Pair<Color, Rectangle>> colorRectArray = new Array<>();
    Vector2 start = new Vector2(100, 100);

    public RectangleSmoothPathDemo() {
        super();
        Array<RectanglePathStep> rectanglePathSteps = DemoPathUtils.pathStep();
        RectanglePathSmooth smoothPath = new RectanglePathSmooth();
        smoothPath.getArray().addAll(rectanglePathSteps);

        int i = 0;
        Array<Rectangle> compute = smoothPath.compute(start);
        for (RectanglePathStep step : smoothPath.getArray()) {
            Color color = Randoms.color();
            String format = StrFormat.format("Dir={}, length={}, size={}", step.getDirection(), step.getLength(), step.getSize());
            HudDebug.addTopRight(i + " ", format, color);

            colorRectArray.add(Pair.of(color, compute.get(i)));

            HudDebug.addBotRight(i + " ", DebugDisplayUtils.printRectangleSize(compute.get(i)), color);
            i++;
        }
    }

    @Override
    public void doRender(float dt) {
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

    @Override
    public void doDispose() {

    }
}
