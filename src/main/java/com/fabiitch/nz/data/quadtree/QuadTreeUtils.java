package com.fabiitch.nz.data.quadtree;

import com.badlogic.gdx.math.Rectangle;
import com.fabiitch.nz.math.utils.RectangleUtils;

public class QuadTreeUtils {
    private static Rectangle rectTmp = new Rectangle(); //TODo pas static

    public static Rectangle getNW(Rectangle parent, Rectangle child) {
        float halfWitdh = parent.width / 2;
        float halfHeight = parent.height / 2;
        return child.set(parent.x, parent.y + halfHeight, halfWitdh, halfHeight);
    }

    public static Rectangle getSW(Rectangle parent, Rectangle child) {
        float halfWitdh = parent.width / 2;
        float halfHeight = parent.height / 2;
        return child.set(parent.x, parent.y, halfWitdh, halfHeight);
    }

    public static Rectangle getNE(Rectangle parent, Rectangle child) {
        float halfWitdh = parent.width / 2;
        float halfHeight = parent.height / 2;
        return child.set(parent.x + halfWitdh, parent.y + halfHeight, halfWitdh, halfHeight);
    }

    public static Rectangle getSE(Rectangle parent, Rectangle child) {
        float halfWitdh = parent.width / 2;
        float halfHeight = parent.height / 2;
        return child.set(parent.x + halfWitdh, parent.y, halfWitdh, halfHeight);
    }

    public static boolean splitContainsRect(Rectangle container, Rectangle rectInside) {
        if (rectInside.width > container.width / 2 || rectInside.height > container.height / 2)
            return false;
        return RectangleUtils.containsStick(getNW(container, rectTmp), rectInside)
                || RectangleUtils.containsStick(getSW(container, rectTmp), rectInside)
                || RectangleUtils.containsStick(getNE(container, rectTmp), rectInside)
                || RectangleUtils.containsStick(getSE(container, rectTmp), rectInside);
    }

    public static int countValuesCantBeSplitted(QuadTree quadTree) {
        int count = 0;
        for (int i = 0, n = quadTree.valuesCount; i < n; i++) {
            QuadData quadData = quadTree.values[i];
            if (!QuadTreeUtils.splitContainsRect(quadTree.boundingRect, quadData.getRectangle()))
                count++;
        }
        return count;
    }

}
