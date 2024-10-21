package com.github.fabiitch.nz.java.math.shapes.utils.rectangle;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Pool;
import com.github.fabiitch.nz.java.math.shapes.utils.RectangleUtils;
import com.github.fabiitch.nz.java.math.utils.direction.Orientation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CuttingRectangle implements Pool.Poolable {

    /**
     * Example vertical cut
     *          | ---------------------|
     *          |       |     |        |
     *          | newR1 | Cut |  newR2 |
     *          |       |     |        |
     *          |----------------------|
     */

    private Orientation orientation;
    private Rectangle oldRect;
    private Rectangle cuttingRect;
    private Rectangle newRectangle1;
    private Rectangle newRectangle2;

    public boolean isCuttingMiddle(){
        return getNewRectangleCount() == 3;
    }

    public int getNewRectangleCount() {
        int count = 0;
        if (cuttingRect != null) {
            count++;
        }
        if (newRectangle2 != null) {
            count++;
        }
        if (newRectangle1 != null) {
            count++;
        }
        return count;
    }

    private static float checkAndAdjustCutSize(Orientation orientation, Rectangle rectangle, float cutPosition, float cutSize) {
        if (cutPosition > orientation.getLength(rectangle)) {
            throw new IllegalArgumentException("Cant cut rectangle  more than his length");
        }
        if (cutPosition + cutSize > orientation.getLength(rectangle)) {
            cutSize = rectangle.width - cutPosition;
        }
        return cutSize;
    }


    public static CuttingRectangle cutRectangle(Rectangle rectangle, Orientation orientation, float cutPosition, float cutSize) {
        if (orientation == Orientation.Horizontal) {
            // Si la découpe est horizontale (on découpe sur l'axe Y).
            return cutRectangleHorizontally(rectangle, cutPosition, cutSize);
        } else {
            // Si la découpe est verticale (on découpe sur l'axe X).
            return cutRectangleVertically(rectangle, cutPosition, cutSize);
        }
    }

    public static CuttingRectangle cutRectangleVertically(Rectangle rectangle, float cutPosition, float cutSize) {
        cutSize = checkAndAdjustCutSize(Orientation.Vertical, rectangle, cutPosition, cutSize);
        float x = rectangle.x;
        float y = rectangle.y;
        float width = rectangle.width;
        float height = rectangle.height;

        Rectangle newRectangle1 = new Rectangle(x, y, cutPosition, height);
        Rectangle cut = new Rectangle(x + cutPosition, y, cutSize, height);
        Rectangle newRectangle2 = new Rectangle(x + cutPosition + cutSize, y, width - cutPosition - cutSize, height);

        newRectangle1 = RectangleUtils.isALine(newRectangle1) ? null : newRectangle1;
        newRectangle2 = RectangleUtils.isALine(newRectangle2) ? null : newRectangle2;
        return new CuttingRectangle(Orientation.Vertical, rectangle, cut, newRectangle1, newRectangle2);
    }

    public static CuttingRectangle cutRectangleHorizontally(Rectangle rectangle, float cutPosition, float cutSize) {
        cutSize = checkAndAdjustCutSize(Orientation.Horizontal, rectangle, cutPosition, cutSize);
        float x = rectangle.x;
        float y = rectangle.y;
        float width = rectangle.width;
        float height = rectangle.height;

        Rectangle newRectangle1 = new Rectangle(x, y, width, cutPosition);
        Rectangle cut = new Rectangle(x, y + cutPosition, width, cutSize);
        Rectangle newRectangle2 = new Rectangle(x, y + cutPosition + cutSize, width,
            height - cutPosition - cutSize);

        newRectangle1 = RectangleUtils.isALine(newRectangle1) ? null : newRectangle1;
        newRectangle2 = RectangleUtils.isALine(newRectangle2) ? null : newRectangle2;
        return new CuttingRectangle(Orientation.Horizontal, rectangle, cut, newRectangle1, newRectangle2);

    }

    @Override
    public void reset() {
        orientation = null;
        cuttingRect = null;
        newRectangle1= null;
        newRectangle2 = null;
        oldRect = null;
    }
}
