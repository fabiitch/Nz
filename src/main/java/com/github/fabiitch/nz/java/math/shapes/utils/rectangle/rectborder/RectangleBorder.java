package com.github.fabiitch.nz.java.math.shapes.utils.rectangle.rectborder;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.github.fabiitch.nz.java.data.Pair;
import com.github.fabiitch.nz.java.math.shapes.builders.RectangleBuilder;
import com.github.fabiitch.nz.java.math.utils.direction.Direction;
import com.github.fabiitch.nz.java.math.utils.direction.Orientation;
import lombok.Getter;
import lombok.Setter;

/**
 * see rectBorder.png
 */
@Getter
@Setter
public class RectangleBorder {

    private Vector2 center;
    private Orientation orientation;

    private float insideWidth, insideHeight;
    private float borderRight, borderLeft, borderTop, borderBottom;

    public RectangleBorder(Vector2 center, Orientation orientation,
                           float insideWidth, float insideHeight,
                           float borderRight, float borderLeft,
                           float borderTop, float borderBottom) {
        this.center = center;
        this.orientation = orientation;
        this.insideWidth = insideWidth;
        this.insideHeight = insideHeight;
        this.borderRight = borderRight;
        this.borderLeft = borderLeft;
        this.borderTop = borderTop;
        this.borderBottom = borderBottom;
    }

    public RectangleBorder(Vector2 center, Orientation orientation,
                           float insideWidth, float insideHeight,
                           float border) {
        this(center, orientation, insideWidth, insideHeight, border, border, border, border);
    }

    public Rectangle getInside() {
        return RectangleBuilder.fromCenter(center, insideWidth, insideHeight);
    }

    public Rectangle getTotal() {
        return RectangleBuilder.fromCenter(center, getTotalWidth(), getTotalHeight());
    }

    public float getTotalWidth() {
        return insideWidth + borderRight + borderLeft;
    }

    public float getTotalHeight() {
        return insideHeight + borderTop + borderBottom;
    }

    public Rectangle getRight() {
        if (orientation == Orientation.Vertical) {
            Vector2 pos = center.cpy().add(insideWidth / 2, -insideHeight / 2 - borderBottom);
            return RectangleBuilder.get(pos, borderRight, getTotalHeight());
        } else {
            Vector2 pos = center.cpy().add(insideWidth / 2, -insideHeight / 2);
            return RectangleBuilder.get(pos, borderRight, insideHeight);
        }
    }

    public Rectangle getLeft() {
        if (orientation == Orientation.Vertical) {
            Vector2 pos = center.cpy().add(-insideWidth / 2 - borderLeft, -insideHeight / 2 - borderBottom);
            return RectangleBuilder.get(pos, borderLeft, getTotalHeight());
        } else {
            Vector2 pos = center.cpy().add(-insideWidth / 2 - borderLeft, -insideHeight / 2);
            return RectangleBuilder.get(pos, borderLeft, insideHeight);
        }
    }

    public Rectangle getTop() {
        if (orientation == Orientation.Vertical) {
            Vector2 pos = center.cpy().add(-insideWidth / 2, insideHeight / 2);
            return RectangleBuilder.get(pos, insideWidth, borderTop);
        } else {
            Vector2 pos = center.cpy().add(-insideWidth / 2 - borderLeft, insideHeight / 2);
            return RectangleBuilder.get(pos, getTotalWidth(), borderTop);
        }
    }

    public Rectangle getBottom() {
        if (orientation == Orientation.Vertical) {
            Vector2 pos = center.cpy().add(-insideWidth / 2, -insideHeight / 2 - borderBottom);
            return RectangleBuilder.get(pos, insideWidth, borderBottom);
        } else {
            Vector2 pos = center.cpy().add(-insideWidth / 2 - borderLeft, -insideHeight / 2 - borderBottom);
            return RectangleBuilder.get(pos, getTotalWidth(), borderBottom);
        }
    }

    public Rectangle getBorder(Direction direction) {
        switch (direction) {
            case Top:
                return getTop();
            case Bot:
                return getBottom();
            case Left:
                return getLeft();
            case Right:
                return getRight();
        }
        return null;
    }

    public Array<Pair<Direction, Rectangle>> getBordersDir() {
        Array<Pair<Direction, Rectangle>> res = new Array<>();
        for (Direction dir : Direction.values) {
            res.add(Pair.of(dir, getBorder(dir)));
        }
        return res;
    }

    public Array<Rectangle> getBorders() {
        Array<Rectangle> res = new Array<>();
        res.add(getRight());
        res.add(getLeft());
        res.add(getTop());
        res.add(getBottom());
        return res;
    }

}
