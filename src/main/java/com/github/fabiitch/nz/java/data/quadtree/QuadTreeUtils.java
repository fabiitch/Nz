package com.github.fabiitch.nz.java.data.quadtree;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.github.fabiitch.nz.java.math.shapes.utils.RectangleUtils;

public class QuadTreeUtils {

    public static Rectangle getNW(Rectangle parent, Rectangle child) {
        float halfWidth = parent.width / 2;
        float halfHeight = parent.height / 2;
        return child.set(parent.x, parent.y + halfHeight, halfWidth, halfHeight);
    }

    public static Rectangle getSW(Rectangle parent, Rectangle child) {
        float halfWidth = parent.width / 2;
        float halfHeight = parent.height / 2;
        return child.set(parent.x, parent.y, halfWidth, halfHeight);
    }

    public static Rectangle getNE(Rectangle parent, Rectangle child) {
        float halfWidth = parent.width / 2;
        float halfHeight = parent.height / 2;
        return child.set(parent.x + halfWidth, parent.y + halfHeight, halfWidth, halfHeight);
    }

    public static Rectangle getSE(Rectangle parent, Rectangle child) {
        float halfWidth = parent.width / 2;
        float halfHeight = parent.height / 2;
        return child.set(parent.x + halfWidth, parent.y, halfWidth, halfHeight);
    }

    public static boolean childCanContainsRect(Rectangle container, Rectangle rectInside) {
        Rectangle rectTmp = new Rectangle();
        if (rectInside.width > container.width / 2 || rectInside.height > container.height / 2)
            return false;
        return RectangleUtils.containsStick(getNW(container, rectTmp), rectInside)
                || RectangleUtils.containsStick(getSW(container, rectTmp), rectInside)
                || RectangleUtils.containsStick(getNE(container, rectTmp), rectInside)
                || RectangleUtils.containsStick(getSE(container, rectTmp), rectInside);
    }

    public static boolean childCanContainsRectFast(Rectangle container, Rectangle rectInside) {
        return rectInside.width > container.width / 2 || rectInside.height > container.height / 2;
    }

    public static int countSplittable(QuadTree quadTree) {
        return quadTree.values.size - countUnsplittable(quadTree);
    }

    public static int countUnsplittable(QuadTree quadTree) {
        int count = 0;
        Array<Rectangle> rectValues = quadTree.rectangles;
        for (int i = 0, n = quadTree.values.size; i < n; i++) {
            Rectangle rectangle = rectValues.get(i);
            if (!QuadTreeUtils.childCanContainsRect(quadTree.boundingRect, rectangle))
                count++;

        }
        return count;
    }

//TODO
//    public static QuadTree getBorders(QuadTree quadTree) {
//        if (quadTree.isSplitted()) {
//
//        }else{
//            return quadTree;
//        }
//
//    }
}
