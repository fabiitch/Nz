package com.github.fabiitch.nz.gdx.scene2D.nz;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.github.fabiitch.nz.gdx.scene2D.nz.value.NzPosValue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class NzStageResizer {

    private final Map<Actor, NzPosValue> repackMap = new HashMap<Actor, NzPosValue>();
    private final HashSet<Actor> alreadyPack = new HashSet<>();

    private NzActorPositionner positionner;

    public NzStageResizer(NzActorPositionner positionner) {
        this.positionner = positionner;
    }

    public void save(Actor actor, NzPosValue posValue){
        repackMap.put(actor, posValue);
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

                positionner.set(actor, posValue);
                alreadyPack.add(actor);
            }
        }
        alreadyPack.clear();
    }


}
