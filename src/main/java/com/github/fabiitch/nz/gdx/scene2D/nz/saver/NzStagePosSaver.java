package com.github.fabiitch.nz.gdx.scene2D.nz.saver;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.github.fabiitch.nz.gdx.scene2D.nz.NzActorPositionner;
import com.github.fabiitch.nz.gdx.scene2D.nz.NzStage;
import com.github.fabiitch.nz.gdx.scene2D.nz.saver.value.NzPosType;
import com.github.fabiitch.nz.gdx.scene2D.nz.saver.value.NzPosValue;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class NzStagePosSaver {

    private final Map<Actor, NzPosValue> repackMap = new HashMap<Actor, NzPosValue>();
    private final HashSet<Actor> alreadyPack = new HashSet<>();

    @Getter
    @Setter
    private NzPosType defaultType = NzPosType.Percent;

    @Getter
    @Setter
    private boolean defaultCenter = true;

    private final NzStage stage;

    public NzStagePosSaver(NzStage stage) {
        this.stage = stage;
    }

    public void setDefaultConfig(NzPosType posType, boolean center) {
        this.defaultType = posType;
        this.defaultCenter = center;
    }

    public void save(Actor actor) {
        save(actor, defaultType, defaultCenter);
    }
    public void saveOnly(Actor actor) {
        saveOnly(actor, defaultType, defaultCenter);
    }

    public void update(Actor actor) {
        NzPosValue posValue = repackMap.get(actor);
        if(posValue != null) {
            stage.getPositionner(actor).set(posValue);
        }
        if (actor instanceof Group) {
            Group group = (Group) actor;
            for (Actor child : group.getChildren()) {
                update(child);
            }
        }
    }

    public void saveOnly(Actor actor, NzPosType posType, boolean centerActor) {
        NzActorPositionner positionner = stage.getPositionner(actor, centerActor);
        Rectangle rectangle;
        if (posType == NzPosType.Fix) {
            rectangle = positionner.getBounds();
        } else {
            rectangle = positionner.getBoundsPercent();
        }
        NzPosValue posValue = new NzPosValue(rectangle, centerActor, posType);
        repackMap.put(actor, posValue);
    }

    public void save(Actor actor, NzPosType posType, boolean centerActor) {
        saveOnly(actor, posType, centerActor);
        if (actor instanceof Group) {
            Group group = (Group) actor;
            for (Actor child : group.getChildren()) {
                save(child, posType, centerActor);
            }
        }
    }

    public void repack() {
        for (Map.Entry<Actor, NzPosValue> entrySet : repackMap.entrySet()) {
            Actor actor = entrySet.getKey();
            repack(actor);
        }
    }

    public void repack(Actor actor) {
        Group parent = actor.getParent();
        if (parent != null && repackMap.containsKey(parent))
            repack(parent);

        if (repackMap.containsKey(actor)) {
            if (!alreadyPack.contains(actor)) {
                NzPosValue posValue = repackMap.get(actor);

                stage.getPositionner(actor).set(posValue);
                alreadyPack.add(actor);
            }
        }
        alreadyPack.clear();
    }
}
