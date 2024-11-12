package com.github.fabiitch.nz.java.math.shapes.utils.rectangle.rectborder;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.github.fabiitch.nz.java.data.Pair;
import com.github.fabiitch.nz.java.math.shapes.builders.RectangleBuilder;
import com.github.fabiitch.nz.java.math.shapes.utils.RectangleUtils;
import com.github.fabiitch.nz.java.math.utils.direction.Direction;
import com.github.fabiitch.nz.java.math.utils.direction.Orientation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * see rectBorder.png
 */
@Getter
@Setter
@AllArgsConstructor
public class RectangleBorder {

    private final Vector2 tmpV2 = new Vector2();

    private Vector2 center;
    private Orientation borderCloseOrientation;

    private float insideWidth, insideHeight;
    private float borderRight, borderLeft, borderTop, borderBottom;

    public RectangleBorder(Vector2 center, Orientation borderCloseOrientation,
                           float insideWidth, float insideHeight,
                           float horizontalBorderSize, float verticalBorderSize) {
        this(center, borderCloseOrientation, insideWidth, insideHeight, horizontalBorderSize, horizontalBorderSize, verticalBorderSize, verticalBorderSize);
    }

    public RectangleBorder(Vector2 center, Orientation borderCloseOrientation,
                           float insideWidth, float insideHeight,
                           float allBorderSize) {
        this(center, borderCloseOrientation, insideWidth, insideHeight, allBorderSize, allBorderSize, allBorderSize, allBorderSize);
    }

    public Rectangle getInsideRect() {
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
        tmpV2.set(center);
        if (borderCloseOrientation == Orientation.Vertical) {
            Vector2 pos = tmpV2.add(insideWidth / 2, -insideHeight / 2 - borderBottom);
            return RectangleBuilder.get(pos, borderRight, getTotalHeight());
        } else {
            Vector2 pos = tmpV2.add(insideWidth / 2, -insideHeight / 2);
            return RectangleBuilder.get(pos, borderRight, insideHeight);
        }
    }

    public Rectangle getLeft() {
        tmpV2.set(center);
        if (borderCloseOrientation == Orientation.Vertical) {
            Vector2 pos = tmpV2.add(-insideWidth / 2 - borderLeft, -insideHeight / 2 - borderBottom);
            return RectangleBuilder.get(pos, borderLeft, getTotalHeight());
        } else {
            Vector2 pos = tmpV2.add(-insideWidth / 2 - borderLeft, -insideHeight / 2);
            return RectangleBuilder.get(pos, borderLeft, insideHeight);
        }
    }

    public Rectangle getTop() {
        tmpV2.set(center);
        if (borderCloseOrientation == Orientation.Vertical) {
            Vector2 pos = tmpV2.add(-insideWidth / 2, insideHeight / 2);
            return RectangleBuilder.get(pos, insideWidth, borderTop);
        } else {
            Vector2 pos = tmpV2.add(-insideWidth / 2 - borderLeft, insideHeight / 2);
            return RectangleBuilder.get(pos, getTotalWidth(), borderTop);
        }
    }

    public Rectangle getBottom() {
        tmpV2.set(center);
        if (borderCloseOrientation == Orientation.Vertical) {
            Vector2 pos = tmpV2.add(-insideWidth / 2, -insideHeight / 2 - borderBottom);
            return RectangleBuilder.get(pos, insideWidth, borderBottom);
        } else {
            Vector2 pos = tmpV2.add(-insideWidth / 2 - borderLeft, -insideHeight / 2 - borderBottom);
            return RectangleBuilder.get(pos, getTotalWidth(), borderBottom);
        }
    }

    public float getBorderSize(Direction direction) {
        Rectangle border = getBorder(direction);
        return RectangleUtils.getSize(border, direction);
    }

    public float getBorderLength(Direction direction) {
        Rectangle border = getBorder(direction);
        return RectangleUtils.getSize(border, direction.getOtherOrientation());
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
        for (Direction dir : Direction.VALUES) {
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

    public void setAllBorder(float size) {
        this.borderLeft = size;
        this.borderRight = size;
        this.borderBottom = size;
        this.borderTop = size;
    }
}
