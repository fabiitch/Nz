package com.github.fabiitch.nz.gdx.scene2D.builders;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

public class GroupBuilder {

    private Group group = new Group();

    private GroupBuilder() {

    }

    public GroupBuilder add(Actor actor) {
        group.addActor(actor);
        return this;
    }

    public GroupBuilder setX(float x) {
        group.setX(x);
        return this;
    }

    public GroupBuilder setY(float y) {
        group.setY(y);
        return this;
    }

    public GroupBuilder setPosition(float x, float y) {
        group.setX(x);
        group.setY(y);
        return this;
    }
}
