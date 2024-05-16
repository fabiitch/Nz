package com.github.fabiitch.nz.gdx.scene2D.builders;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.SnapshotArray;


public class NzGroup extends Group {
    protected Rectangle border = new Rectangle();

    private int increaseBorder = 1;

    public NzGroup() {
    }

    public void setIncreaseBorder(int increaseBorder) {
        this.increaseBorder = increaseBorder;
    }

    @Override
    public void childrenChanged() {
        SnapshotArray<Actor> children = super.getChildren();

        if (children.isEmpty())
            return;

        float minX = Float.MAX_VALUE, maxX = 0;
        float minY = Float.MAX_VALUE, maxY = 0;

        for (Actor actor : children) {
            actor.setX(actor.getX() + increaseBorder);
            actor.setY(actor.getY() + increaseBorder);

            minX = Math.min(minX, actor.getX());
            minY = Math.min(minY, actor.getY());
            maxX = Math.max(maxX, actor.getRight());
            maxY = Math.max(maxY, actor.getTop());
        }

        this.setWidth(maxX - minX + increaseBorder * 2);
        this.setHeight(maxY - minY + increaseBorder * 2);

        border.set(minX, minY, maxX, maxY);
    }

    @Override
    public void setX(float x) {
        super.setX(x + increaseBorder);
    }

    public void recompute() {
        childrenChanged();
    }

    @Override
    public SnapshotArray<Actor> getChildren() {
        return super.getChildren();
    }

    public void addActor(Actor... actors) {
        for (Actor actor : actors)
            addActor(actor);
    }
}
