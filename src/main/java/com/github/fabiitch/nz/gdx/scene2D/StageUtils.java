package com.github.fabiitch.nz.gdx.scene2D;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.utils.SnapshotArray;
import com.github.fabiitch.nz.java.math.shapes.utils.RectangleUtils;


public class StageUtils {
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

    public static Rectangle getBounds(Actor actor) {
        return getBounds(actor, new Rectangle());
    }

    public static Rectangle getBounds(Actor actor, Rectangle result) {
        return result.set(actor.getX(), actor.getY(), actor.getWidth(), actor.getHeight());
    }

    public static void fitSizeOnChildren(WidgetGroup widgetGroup) {
        SnapshotArray<Actor> children = widgetGroup.getChildren();
        Rectangle rectangle = null;

        if(children.size > 0) {
            for (Actor child : children) {
                if (rectangle == null)
                    rectangle = getBounds(child);
                else
                    RectangleUtils.mergeFloorCeil(rectangle, getBounds(child));
            }
            widgetGroup.setPosition(rectangle.x, rectangle.y);
            widgetGroup.setSize(rectangle.width, rectangle.height);
        }
    }
}
