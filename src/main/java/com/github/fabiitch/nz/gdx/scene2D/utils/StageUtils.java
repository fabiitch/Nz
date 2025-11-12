package com.github.fabiitch.nz.gdx.scene2D.utils;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.utils.SnapshotArray;
import com.github.fabiitch.nz.java.math.shapes.utils.RectangleUtils;


public class StageUtils {

    private static final Vector2 tmpv2 = new Vector2();
    private static final Rectangle tmpRect = new Rectangle();

    public static void debugPos(Actor actor) {
        debugPos(actor, actor.getName());
    }

    public static void debugPos(Actor actor, String name) {
        System.out.println("Actor[" + name + "] pos: " + actor.getX() + ", " + actor.getY());
        System.out.println("Actor[" + name + "] size: " + actor.getWidth() + ", " + actor.getHeight());
    }

    public static void buttonFullDisabled(Button button, boolean b) {
        button.setTouchable(b ? Touchable.disabled : Touchable.enabled);
        button.setDisabled(b);
    }

    public static boolean isOn(float x, float y, Actor actor) {
        return actor.getX() < x
                && actor.getX() + actor.getWidth() > x
                && actor.getY() < y
                && actor.getY() + actor.getHeight() > y;
    }

    public static boolean contains(Actor actor, Actor sub) {
        tmpRect.set(actor.getX(), actor.getY(), actor.getWidth(), actor.getHeight());
        Rectangle boundsSub = getBounds(sub);
        return RectangleUtils.containsStick(tmpRect, boundsSub);
    }

    public static Rectangle getBounds(Actor actor) {
        return getBounds(actor, new Rectangle());
    }

    public static Rectangle getBounds(Actor actor, Rectangle result) {
        return result.set(actor.getX(), actor.getY(), actor.getWidth(), actor.getHeight());
    }

    public static Vector2 getCenter(Actor actor) {
        return tmpv2.set(actor.getX() + actor.getWidth() / 2, actor.getY() + actor.getHeight() / 2);
    }

    public static void fitBoundsOnChildrenRecursive(Group widgetGroup) {
        SnapshotArray<Actor> children = widgetGroup.getChildren();
        if (children.size > 0) {
            for (Actor child : children) {
                if (child instanceof Group) {
                    fitBoundsOnChildrenRecursive((Group) child);
                }
            }
            fitBoundsOnChildren(widgetGroup);
        }
    }

    public static void fitBoundsOnChildren(Group widgetGroup) {
        if (widgetGroup.getChildren().size == 0) return;

        float minX = Float.MAX_VALUE, minY = Float.MAX_VALUE;
        float maxX = Float.MIN_VALUE, maxY = Float.MIN_VALUE;

        // 1️⃣ Trouver les min/max de tous les enfants
        for (Actor child : widgetGroup.getChildren()) {
            float childX = child.getX();
            float childY = child.getY();
            float childWidth = child.getWidth();
            float childHeight = child.getHeight();

            minX = Math.min(minX, childX);
            minY = Math.min(minY, childY);
            maxX = Math.max(maxX, childX + childWidth);
            maxY = Math.max(maxY, childY + childHeight);
        }

        float newWidth = maxX - minX;
        float newHeight = maxY - minY;

        // 2️⃣ Déplacer le groupe pour englober ses enfants
        widgetGroup.setPosition(widgetGroup.getX() + minX, widgetGroup.getY() + minY);
        widgetGroup.setSize(newWidth, newHeight);

        // 3️⃣ Repositionner les enfants en conséquence
        for (Actor child : widgetGroup.getChildren()) {
            child.moveBy(-minX, -minY);
        }
    }

    public static void fitBoundsOnChildren22(Group widgetGroup) {
        SnapshotArray<Actor> children = widgetGroup.getChildren();
        Vector2 curr = null;
        Vector2 widgetPos = new Vector2(widgetGroup.getX(), widgetGroup.getY());
        if (children.size > 0) {
            for (Actor child : children) {
                Vector2 childPos = tmpv2.set(child.getX(), child.getY());
                if (!contains(widgetGroup, child)) {
                    childPos.sub(widgetPos);
                    if (curr == null) {
                        curr = childPos.cpy();
                    } else {
                        curr.x = Math.min(curr.x, childPos.x);
                        curr.y = Math.min(curr.y, childPos.y);
                    }
                }
            }
            fitSizeOnChildren(widgetGroup);
            if (curr != null) {
                widgetGroup.setPosition(curr.x, curr.y);
                for (Actor child : children) {
                    if (!contains(widgetGroup, child)) {
                        Vector2 childPos = tmpv2.set(child.getX(), child.getY());
                        childPos.sub(curr);
                        child.setPosition(childPos.x, childPos.y);
                    }
                }
            }
        }
    }

    public static void fitSizeOnChildren(Group widgetGroup) {
        SnapshotArray<Actor> children = widgetGroup.getChildren();
        Rectangle rectangle = null;

        if (children.size > 0) {
            for (Actor child : children) {
                if (rectangle == null)
                    rectangle = getBounds(child);
                else
                    rectangle.merge(getBounds(child));
            }
            widgetGroup.setSize(rectangle.width, rectangle.height);
        }
    }

    public static void printBounds(Actor actor) {
        Rectangle bounds = getBounds(actor);
        String name = actor.getName() != null ? actor.getName() : actor.getClass().getSimpleName();
        System.out.println(name + " " + bounds);
    }


    public static float getSizeRatio(Actor actor) {
        float height = actor.getHeight();
        if (height == 0f) return 1f;

        float ratio = actor.getWidth() / height;
        return Math.round(ratio * 1000f) / 1000f; // ex: 1.777777 → 1.778
    }

}
