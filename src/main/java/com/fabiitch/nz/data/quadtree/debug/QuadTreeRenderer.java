package com.fabiitch.nz.data.quadtree.debug;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.fabiitch.nz.data.quadtree.QuadTree;

public class QuadTreeRenderer {
    private QuadTree quadTree;
    private ShapeRenderer shapeRenderer;

    private Color colorQuad;
    private Color colorData;

    public QuadTreeRenderer(QuadTree quadTree,
                            ShapeRenderer shapeRenderer,
                            Color colorQuad, Color colorData) {
        this.quadTree = quadTree;
        this.shapeRenderer = shapeRenderer;
        this.colorQuad = colorQuad;
        this.colorData = colorData;
    }

    public QuadTreeRenderer(QuadTree quadTree) {
        this(quadTree, new ShapeRenderer(), Color.RED, Color.BLUE);
    }

    public void render() {
        this.render(true);
    }

    public void render(boolean callBeginEnd) {
        if (callBeginEnd)
            shapeRenderer.begin();
        shapeRenderer.setColor(colorQuad);
        drawQuadTree(this.quadTree);
        if (callBeginEnd)
            shapeRenderer.end();
    }

    private void drawQuadTree(QuadTree quadTree) {
        Rectangle rectangle = quadTree.boundingRect;
        shapeRenderer.rect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        if (quadTree.isSplitted()) {
            drawQuadTree(quadTree.ne);
            drawQuadTree(quadTree.nw);
            drawQuadTree(quadTree.se);
            drawQuadTree(quadTree.sw);
        }
    }


}
