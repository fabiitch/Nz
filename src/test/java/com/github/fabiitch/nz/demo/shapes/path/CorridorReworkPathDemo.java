package com.github.fabiitch.nz.demo.shapes.path;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.github.fabiitch.nz.demo.NzDemoScreenLauncher;
import com.github.fabiitch.nz.demo.internal.BaseTryScreen;
import com.github.fabiitch.nz.demo.internal.selectors.DemoScreen;
import com.github.fabiitch.nz.gdx.debug.huddebug.HudDebug;
import com.github.fabiitch.nz.gdx.log.StrFormat;
import com.github.fabiitch.nz.gdx.render.ColorUtils;
import com.github.fabiitch.nz.java.math.path.rectangle.corridor.rework.CorridorComputer;
import com.github.fabiitch.nz.java.math.path.rectangle.corridor.rework.CorridorPart;
import com.github.fabiitch.nz.java.math.path.rectangle.corridor.rework.CorridorStepNew;
import com.github.fabiitch.nz.java.math.shapes.utils.RectangleUtils;
import com.github.fabiitch.nz.java.utils.Randoms;

@DemoScreen(group = "math.path")
public class CorridorReworkPathDemo extends BaseTryScreen {

    public static void main(String[] args) {
        NzDemoScreenLauncher.startScreen(CorridorReworkPathDemo.class);
    }

    Array<Color> colors = new Array<>();
    Vector2 start = new Vector2(50, 20);
    Array<CorridorPart> parts;

    public CorridorReworkPathDemo() {
        super();
        init();
    }

    public void init() {
        Array<CorridorStepNew> rectanglePathSteps = DemoPathUtils.stepNew();

        parts = CorridorComputer.compute(start, rectanglePathSteps);

        for (int i = 0; i < parts.size; i++) {
            Color color = Randoms.color();
            CorridorPart step = parts.get(i);
            String format = StrFormat.format("Dir={}, length={}, size={}, wallA={}, wallB={}",
                    step.getDirection(), step.getLength(), step.getWalkSize(), RectangleUtils.printSize(step.getRectWallA()), RectangleUtils.printSize(step.getRectWallB()));
            HudDebug.addTopRight(i + " ", format, color);
            colors.add(color);
        }
    }

    Color white = ColorUtils.get(Color.WHITE, 0.2f);

    @Override
    public void render(float delta) {
        super.render(delta);
        shapeRenderer.begin();
        shapeRenderer.setColor(Color.YELLOW);
        shapeRenderer.set(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.circle(start, 10);

        for (int i = 0; i < parts.size; i++) {
            CorridorPart part = parts.get(i);
            shapeRenderer.set(ShapeRenderer.ShapeType.Filled);

            shapeRenderer.setColor(Color.RED);
            shapeRenderer.circle(part.getMiddleStart(), 3);

            shapeRenderer.setColor(Color.BLUE);
            shapeRenderer.circle(part.getMiddleEnd(), 6);

            shapeRenderer.setColor(colors.get(i));
            shapeRenderer.rect(part.getRectWallA());
            shapeRenderer.rect(part.getRectWallB());

            shapeRenderer.set(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setColor(white);
            shapeRenderer.rect(part.getRectWalk());
        }
        shapeRenderer.end();
    }
}
