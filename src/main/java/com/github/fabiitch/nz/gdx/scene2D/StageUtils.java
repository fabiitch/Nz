package com.github.fabiitch.nz.gdx.scene2D;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.utils.SnapshotArray;
import com.github.fabiitch.nz.gdx.scene2D.nz.StagePositionnerUtils;
import com.github.fabiitch.nz.java.math.shapes.utils.RectangleUtils;


public class StageUtils {

    private static Vector2 tmp = new Vector2();

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


    public static Vector2 getCenter(Actor actor) {
        return tmp.set(actor.getX() + actor.getWidth() / 2, actor.getY() + actor.getHeight() / 2);
    }

    public static void centerOnParent(Actor actor) {
        Group parent = actor.getParent();
        if (parent != null) {
//            StagePositionnerUtils.center(actor);
//            Vector2 center = getCenter(parent);
//            actor.setPosition(center.x, center.y);
//            actor.setPosition(center.x + actor.getWidth() / 2, center.y + actor.getHeight() / 2);
        }
    }

    public static void fitSizeOnChildren(WidgetGroup widgetGroup) {
        SnapshotArray<Actor> children = widgetGroup.getChildren();
        Rectangle rectangle = null;

        if (children.size > 0) {
            for (Actor child : children) {
                if (rectangle == null)
                    rectangle = getBounds(child);
                else
                    rectangle.merge(getBounds(child));
            }
//            widgetGroup.setPosition(rectangle.x, rectangle.y);
            widgetGroup.setSize(rectangle.width, rectangle.height);
        }
    }
}
