package com.github.fabiitch.nz.java.math.shapes;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.github.fabiitch.nz.java.math.shapes.builders.RectangleBuilder;
import com.github.fabiitch.nz.java.math.shapes.utils.PolygonUtils;
import com.github.fabiitch.nz.java.math.shapes.utils.RectangleUtils;
import com.github.fabiitch.nz.java.math.vectors.V2;

/**
 * /**
 * * D3-----C2
 * * |------|
 * * |------|
 * * A0-----B1
 * <p>
 * x,y is center
 */
public class PolygonRectangle extends Polygon {
    public PolygonRectangle(float width, float height) {
        this(0, 0, width, height);
    }

    public PolygonRectangle(float x, float y, float width, float height) {
        this(RectangleBuilder.fromCenter(x, y, width, height));
    }

    public PolygonRectangle(Rectangle rect) {
        super(RectangleUtils.toVertices(rect, true));
        Vector2 center = PolygonUtils.getCenter(this, new Vector2());
        setOrigin(center.x, center.y);
    }

    //TODO pools
    public Vector2 getCenterAB(){
        Vector2 A = PolygonUtils.getVertex(this, 0, new Vector2());
        Vector2 B = PolygonUtils.getVertex(this, 1, new Vector2());
        Vector2 centerAB = V2.middle(A, B, new Vector2());
        return centerAB;
    }


    //TODO pas bon
    public void setCenterABAt(Vector2 posAB) {
        Vector2 centerAB = getCenterAB();
        Vector2 translate = new Vector2(posAB).sub(centerAB);

        this.translate(translate.x, translate.y);
    }
}
