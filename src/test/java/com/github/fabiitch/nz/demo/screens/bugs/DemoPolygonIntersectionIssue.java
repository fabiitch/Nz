package com.github.fabiitch.nz.demo.screens.bugs;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.github.fabiitch.nz.demo.NzDemoScreenLauncher;
import com.github.fabiitch.nz.demo.internal.BaseDemoScreen;
import com.github.fabiitch.nz.demo.internal.selectors.DemoScreen;
import com.github.fabiitch.nz.gdx.debug.DebugDisplayUtils;
import com.github.fabiitch.nz.gdx.debug.huddebug.HudDebug;

@DemoScreen(group = "gdx-issues")
public class DemoPolygonIntersectionIssue extends BaseDemoScreen {
    Polygon p1, p2, overlap;

    public static void main(String[] args) {
        NzDemoScreenLauncher.startScreen(DemoPolygonIntersectionIssue.class);
    }

    public DemoPolygonIntersectionIssue() {
        super();
        p1 = new Polygon(new float[]{1, -1, 2, -1, 2, -2, 1, -2});
        p1.scale(100);
        p2 = new Polygon(new float[]{0.5f, -1.5f, 1.5f, -1.5f, 1.5f, -2.5f});
        p2.scale(100);
        overlap = new Polygon();

        boolean isOverlap = Intersector.intersectPolygons(p1, p2, overlap);
        System.out.println("Overlap ="  + isOverlap);
        System.out.println("Number of overlap vertices: " + overlap.getVertexCount());

        HudDebug.addTopLeft("P1", DebugDisplayUtils.polygon(p1));
        HudDebug.addTopLeft("P2", DebugDisplayUtils.polygon(p2));
        HudDebug.addTopLeft("Overlap", DebugDisplayUtils.polygon(overlap));
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        shapeRenderer.begin();

        shapeRenderer.setColor(Color.RED);
        shapeRenderer.polygon(p1);

        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.polygon(p1);

        shapeRenderer.setColor(Color.YELLOW);
        shapeRenderer.polygon(overlap);

        shapeRenderer.end();
    }
}
