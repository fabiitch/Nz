package com.github.fabiitch.nz.demo.screens.shapes.path;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.github.fabiitch.nz.demo.NzDemoScreenLauncher;
import com.github.fabiitch.nz.demo.internal.BaseDemoScreen;
import com.github.fabiitch.nz.demo.internal.selectors.DemoScreen;
import com.github.fabiitch.nz.gdx.debug.huddebug.HudDebug;
import com.github.fabiitch.nz.gdx.log.StrFormat;
import com.github.fabiitch.nz.gdx.render.ColorUtils;
import com.github.fabiitch.nz.java.math.path.rectangle.corridor.CorridorComputer;
import com.github.fabiitch.nz.java.math.path.rectangle.corridor.CorridorPart;
import com.github.fabiitch.nz.java.math.path.rectangle.corridor.CorridorPathStep;
import com.github.fabiitch.nz.java.math.shapes.utils.RectangleUtils;
import com.github.fabiitch.nz.java.utils.Randoms;

@DemoScreen(group = "math.path")
public class CorridorPathDemo extends BaseDemoScreen {

    public static void main(String[] args) {
        NzDemoScreenLauncher.startScreen(CorridorPathDemo.class);
    }

    Array<Color> colors = new Array<>();
    Vector2 start = new Vector2(200, 10);
    Array<CorridorPart> parts;

    public CorridorPathDemo() {
        super();
        init();
    }

    public void init() {
        Array<CorridorPathStep> rectanglePathSteps = DemoPathUtils.stepNew();

        parts = CorridorComputer.compute(start, rectanglePathSteps);

        for (int i = 0; i < parts.size; i++) {
            Color color = Randoms.color();
            CorridorPart step = parts.get(i);
            String format = StrFormat.format("Dir={}, length={}, size={}, wallA={}, wallB={}, hasBlock={}",
                    step.getDirection(), step.getLength(), step.getWalkSize(), RectangleUtils.printSize(step.getRectWallA()), RectangleUtils.printSize(step.getRectWallB()), step.hasBlockers());
            HudDebug.addTopRight(i + " ", format, color);
            colors.add(color);
        }
    }

    Color white = ColorUtils.get(Color.WHITE, 0.2f);

    @Override
    public void render(float delta) {
        super.render(delta);

        shapeRenderer.begin();
        shapeRenderer.setProjectionMatrix(camera.combined);
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

            if (part.getBlockerRectA() != null)
                shapeRenderer.rect(part.getBlockerRectA());
            if (part.getBlockerRectB() != null)
                shapeRenderer.rect(part.getBlockerRectB());

            shapeRenderer.set(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setColor(white);
            shapeRenderer.rect(part.getRectWalk());
        }
        shapeRenderer.end();
    }
}
