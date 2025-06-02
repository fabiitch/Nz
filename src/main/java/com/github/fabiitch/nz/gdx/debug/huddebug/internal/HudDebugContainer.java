package com.github.fabiitch.nz.gdx.debug.huddebug.internal;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.github.fabiitch.nz.gdx.debug.DebugDisplayUtils;
import com.github.fabiitch.nz.gdx.debug.huddebug.HudDebug;

import java.util.HashMap;
import java.util.Map;

//TODO padding on android
public class HudDebugContainer {

    private final Skin skin;
    private final Stage stage;

    private int positionCounts[];
    private final Map<String, HudDebugLabel> mapLabels;

    public HudDebugContainer(Stage stage, Skin skin) {
        this.skin = skin;
        this.stage = stage;
        this.mapLabels = new HashMap<>();
        this.positionCounts = new int[HudDebugPosition.values().length];
    }

    public HudDebugLabel get(String key) {
        return mapLabels.get(key);
    }

    public void removeGroup(String startKey) {
        for (Map.Entry<String, HudDebugLabel> entry : mapLabels.entrySet()) {
            if (entry.getKey().startsWith(startKey)) {
                remove(entry.getKey());
            }
        }
    }

    public void remove(String key) {
        HudDebugLabel labelStage = mapLabels.get(key);
        if (labelStage != null) {
            HudDebugPosition positionOnStage = labelStage.positionOnStage;
            int positionLabel = labelStage.order;
            mapLabels.remove(key);
            labelStage.remove();

            positionCounts[positionOnStage.ordinal()]--;
            HudContainerUtils.replaceLabels(positionOnStage, positionLabel, stage, mapLabels);
        }
    }

    public boolean exist(String key) {
        return mapLabels.get(key) != null;
    }

    public Color getColor(String key) {
        HudDebugLabel hudDebugLabel = mapLabels.get(key);
        if (hudDebugLabel != null)
            return hudDebugLabel.getColor();
        else
            return Color.WHITE;
    }

    public void clear() {
        positionCounts = new int[HudDebugPosition.values().length];
        for (HudDebugLabel hudDebugLabel : mapLabels.values()) {
            hudDebugLabel.remove();
        }
        mapLabels.clear();
    }

    public void update(String key, String name, Object value) {
        HudDebugLabel label = mapLabels.get(key);
        if (label != null) {
            label.updateNameAndValue(name, value);
            HudContainerUtils.setDebugLabelPosition(stage, label);
        }else{
            createLabel(HudDebugPosition.TOP_RIGHT, key, name, value, Color.WHITE);
        }
    }

    public void update(String key, String value) {
        update(key, key, value);
    }

    public void update(String key, Object value, Color color) {
        update(key, value);
        HudDebug.changeColor(key, color);
    }

    public void update(String key, Object value) {
        HudDebugLabel hudDebugLabel = get(key);
        if (hudDebugLabel != null)
            update(key, hudDebugLabel.name, DebugDisplayUtils.printValue(value));
    }

    public void changeColor(String key, Color color) {
        HudContainerUtils.changeColor(key, color, mapLabels);
    }
    public void changePosition(String key, HudDebugPosition position) {
        HudDebugLabel hudDebugLabel = get(key);
        if(hudDebugLabel != null){
            remove(key);
            createLabel(position, key, hudDebugLabel.name, hudDebugLabel.value, hudDebugLabel.getColor());
        }
    }

    public HudDebugLabel createLabel(HudDebugPosition positionOnstage, String key, String name, Object value,
                                     Color color) {
        HudDebugLabel label = new HudDebugLabel(name, positionOnstage, positionCounts[positionOnstage.ordinal()]++, value, skin);
        if (key == null)
            key = name;

        mapLabels.put(key, label);
        stage.addActor(label);
        label.setColor(color);
        HudContainerUtils.setDebugLabelPosition(stage, label);
        return label;
    }

}
