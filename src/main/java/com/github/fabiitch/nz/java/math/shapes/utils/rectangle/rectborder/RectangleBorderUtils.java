package com.github.fabiitch.nz.java.math.shapes.utils.rectangle.rectborder;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.github.fabiitch.nz.java.math.utils.direction.Direction;
import com.github.fabiitch.nz.java.math.utils.direction.Orientation;

/**
 * see rectBorder.png
 */
public class RectangleBorderUtils {

    private final static Vector2 tmpV2 = new Vector2();
    private final static RectangleBorder tmpBorder = new RectangleBorder(new Vector2(), Orientation.Vertical, 0, 0, 0);

    public static RectangleBorder get(Rectangle rectangle, Orientation orientation, float borderRight, float borderLeft, float borderTop, float borderBottom) {
        return new RectangleBorder(rectangle.getCenter(tmpV2), orientation, rectangle.getWidth(), rectangle.getHeight(), borderRight, borderLeft, borderTop, borderBottom);
    }

    public static RectangleBorder get(Rectangle rectangle, Orientation orientation, float borderHorizontal, float borderVertical) {
        return new RectangleBorder(rectangle.getCenter(tmpV2), orientation, rectangle.getWidth(), rectangle.getHeight(), borderHorizontal, borderHorizontal, borderVertical, borderVertical);
    }

    public static RectangleBorder get(Rectangle rectangle, Orientation orientation, float allBorderSize) {
        return new RectangleBorder(rectangle.getCenter(tmpV2), orientation, rectangle.getWidth(), rectangle.getHeight(), allBorderSize);
    }


    public static Rectangle getRectBorder(Rectangle rect, Orientation orientation, Direction borderDirection, float borderSize) {
        tmpBorder.setCenter(rect.getCenter(tmpV2));
        tmpBorder.setBorderCloseOrientation(orientation);
        tmpBorder.setInsideWidth(rect.getWidth());
        tmpBorder.setInsideHeight(rect.getHeight());
        tmpBorder.setAllBorder(borderSize);
        return tmpBorder.getBorder(borderDirection);
    }

}
