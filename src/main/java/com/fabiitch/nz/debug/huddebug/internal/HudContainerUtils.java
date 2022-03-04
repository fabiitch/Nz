package com.fabiitch.nz.debug.huddebug.internal;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

import java.util.Collection;
import java.util.Map;

public class HudContainerUtils {
    private final static float standartRight = 100;
    private final static float right_decal_safe = 15;

    private HudContainerUtils() {

    }

    public static void replaceLabels(int positionOnStage, int atPosition, Stage stage,
                                     Map<String, HudDebugLabel> mapLabels) {
        Collection<HudDebugLabel> allLabels = mapLabels.values();
        for (HudDebugLabel hudLabel : allLabels) {
            if (hudLabel.positionOnStage == positionOnStage && hudLabel.order > atPosition) {
                hudLabel.order--;
                setDebugLabelPosition(stage, hudLabel);
            }
        }
    }

    public static void setDebugLabelPosition(Stage stage, HudDebugLabel label) {
        switch (label.positionOnStage) {
            case HudDebugPosition.TOP_LEFT:
                label.setPosition(1, stage.getViewport().getWorldHeight() - label.getHeight() * (label.order + 1));
                break;
            case HudDebugPosition.TOP_MIDDLE:
                label.setPosition(stage.getViewport().getWorldWidth() / 2 - stage.getViewport().getWorldWidth() / 20,
                        stage.getViewport().getWorldHeight() - label.getHeight() * (label.order + 1));
                break;
            case HudDebugPosition.TOP_RIGHT:
                float witdhMax = Math.max(label.getWidth(), standartRight);
                label.setPosition(stage.getViewport().getWorldWidth() - witdhMax - right_decal_safe,
                        stage.getViewport().getWorldHeight() - label.getHeight() * (label.order + 1));
                break;
            case HudDebugPosition.BOT_LEFT:
                label.setPosition(4, label.getHeight() * (label.order - 1) + 1);
                break;
            case HudDebugPosition.BOT_MIDDLE:
                float x = Math.max(label.getWidth() + 2, standartRight);
                label.setPosition(stage.getViewport().getWorldWidth() / 2 - stage.getViewport().getWorldWidth() / 20,
                        label.getHeight() * (label.order - 1) + 1);
                break;
            case HudDebugPosition.BOT_RIGHT:
                x = Math.max(label.getWidth(), standartRight);
                label.setPosition(stage.getViewport().getWorldWidth() - x - right_decal_safe, label.getHeight() * (label.order - 1) + 1);
                break;
            case HudDebugPosition.LEFT_MIDDLE:
                label.setPosition(1, stage.getViewport().getWorldHeight() / 2 + stage.getViewport().getWorldHeight() / 6
                        - label.getHeight() * label.order - 1 - Gdx.graphics.getSafeInsetTop());
                break;
            case HudDebugPosition.RIGHT_MIDDLE:
                x = Math.max(label.getWidth() + 2, standartRight);
                label.setPosition(stage.getViewport().getWorldWidth() - x - right_decal_safe,
                        stage.getViewport().getWorldHeight() / 2 + stage.getViewport().getWorldHeight() / 6
                                - label.getHeight() * label.order - 1 - Gdx.graphics.getSafeInsetTop());
                break;
            default:
                break;
        }
    }

    public static void changeColorBeforeInit(String label, Color color, Array<HudDebugPreInitItem> arrayBeforeInit) {
        for (HudDebugPreInitItem item : arrayBeforeInit.items) {
            if (item.name.equals(label))
                item.color = color;
        }
    }

    public static void changeColor(String key, Color color, Map<String, HudDebugLabel> mapLabels) {
        for (Map.Entry<String, HudDebugLabel> entry : mapLabels.entrySet()) {
            if (entry.getKey().equals(key)) {
                entry.getValue().setColor(color);
            }
        }
    }

}
