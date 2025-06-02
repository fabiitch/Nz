package com.github.fabiitch.nz.gdx.debug.huddebug;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.github.fabiitch.nz.gdx.debug.DebugDisplayUtils;
import com.github.fabiitch.nz.gdx.debug.huddebug.event.HudDebugEvent;
import com.github.fabiitch.nz.gdx.debug.huddebug.event.HudDebugEventManager;
import com.github.fabiitch.nz.gdx.debug.huddebug.internal.HudDebugContainer;
import com.github.fabiitch.nz.gdx.debug.huddebug.internal.HudDebugLabel;
import com.github.fabiitch.nz.gdx.debug.huddebug.internal.HudDebugPosition;
import com.github.fabiitch.nz.gdx.debug.huddebug.internal.HudDebugPreInitItem;

public class HudDebug {
    public static HudDebug instance;
    private static Array<HudDebugPreInitItem> arrayBeforeInit;
    private final HudDebugContainer container;
    private final HudDebugEventManager eventManager;

    public HudDebug(Stage stage, Skin skin) {
        this.container = new HudDebugContainer(stage, skin);
        HudDebug.instance = this;

        if (arrayBeforeInit != null) {
            for (HudDebugPreInitItem item : arrayBeforeInit) {
                instance.container.createLabel(item.positionOnStage, item.key, item.name, item.value, item.color);
            }
            HudDebug.arrayBeforeInit.clear();
            HudDebug.arrayBeforeInit = null;
        }
        eventManager = new HudDebugEventManager();
        stage.addActor(eventManager);
    }

    public static void clear() {
        if (instance == null) {
            arrayBeforeInit.clear();
        } else {
            instance.container.clear();
        }
    }

    public HudDebugLabel get(String key) {
        if (instance == null) {
            return null;
        }
        return instance.container.get(key);
    }

    public static boolean exist(String key) {
        if (instance == null) {
            for (HudDebugPreInitItem item : arrayBeforeInit)
                if (key.equals(item.key))
                    return true;
            return false;
        } else {
            return instance.container.exist(key);
        }
    }

    public static Color getColor(String key) {
        if (instance == null) {
            for (HudDebugPreInitItem item : arrayBeforeInit)
                if (key.equals(item.name))
                    return item.color;
            return Color.WHITE;
        } else {
            return instance.container.getColor(key);
        }
    }

    public static void removeGroup(String startKey) {
        if (instance == null) {
            if (arrayBeforeInit != null) {
                for (HudDebugPreInitItem item : arrayBeforeInit) {
                    if (item.key.startsWith(startKey)) {
                        arrayBeforeInit.removeValue(item, true);
                        break;
                    }
                }
            }
        } else {
            instance.container.removeGroup(startKey);
        }
    }

    public static void remove(String key) {
        if (instance == null) {
            for (HudDebugPreInitItem item : arrayBeforeInit) {
                if (key.equals(item.key)) {
                    arrayBeforeInit.removeValue(item, true);
                    break;
                }
            }
        } else {
            instance.container.remove(key);
        }
    }

    public static void changeColor(String key, Color color) {
        if (instance != null)
            instance.container.changeColor(key, color);
    }

    public static void changePosition(String key, HudDebugPosition position) {
        if (instance != null)
            instance.container.changePosition(key, position);
    }

    public static void update(String key, String name, Object value) {
        if (instance != null)
            instance.container.update(key, name, value);
    }

    public static void update(String key, Object value, Color color) {
        if (instance != null)
            instance.container.update(key, value, color);
    }

    public static void update(String key, Object value) {
        if (instance != null)
            instance.container.update(key, value);
    }

    public static void update(String key, String value) {
        if (instance != null)
            instance.container.update(key, value);
        else{
            add(key, value);
        }
    }

    private static void addInitList(String key, String name, Object value, HudDebugPosition positionOnStage, Color color) {
        if (arrayBeforeInit == null)
            arrayBeforeInit = new Array<>();

        for (HudDebugPreInitItem item : arrayBeforeInit)
            if (key.equals(item.key))
                return;
        arrayBeforeInit.add(new HudDebugPreInitItem(key, name, DebugDisplayUtils.printValue(value), positionOnStage, color));
    }

    public static void addTopLeft(String key, String name, Object value, Color color) {
        add(key, name, value, HudDebugPosition.TOP_LEFT, color);
    }

    public static void addTopLeft(String name, Object value, Color color) {
        addTopLeft(name, name, value, color);
    }

    public static void addTopMiddle(String name) {
        addTopLeft(name, name, "", Color.WHITE);
    }

    public static void addTopLeft(String name, Object value) {
        addTopLeft(name, name, value, Color.WHITE);
    }

    public static void addTopMiddle(String key, String name, Object value, Color color) {
        add(key, name, value, HudDebugPosition.TOP_MIDDLE, color);
    }

    public static void addTopMiddle(String name, Object value, Color color) {
        addTopMiddle(name, name, value, color);
    }

    public static void addTopMiddle(String name, Object value) {
        addTopMiddle(name, name, value, Color.WHITE);
    }

    public static void addTopRight(String key, String name, Object value, Color color) {
        add(key, name, value, HudDebugPosition.TOP_RIGHT, color);
    }

    public static void addTopRight(String name, Object value, Color color) {
        addTopRight(name, name, value, color);
    }

    public static void addTopRight(String name, Object value) {
        addTopRight(name, value, Color.WHITE);
    }

    public static void addBotLeft(String key, String name, Object value, Color color) {
        add(key, name, value, HudDebugPosition.BOT_LEFT, color);
    }

    public static void addBotLeft(String name, Object value, Color color) {
        addBotLeft(name, name, value, color);
    }

    public static void addBotLeft(String name, Object value) {
        addBotLeft(name, name, value, Color.WHITE);
    }

    public static void addBotMiddle(String key, String name, Object value, Color color) {
        add(key, name, value, HudDebugPosition.BOT_MIDDLE, color);
    }

    public static void addBotMiddle(String name, Object value, Color color) {
        addBotMiddle(name, name, value, color);
    }

    public static void addBotMiddle(String name, Object value) {
        addBotMiddle(name, name, value, Color.WHITE);
    }

    public static void addBotRight(String key, String name, Object value, Color color) {
        add(key, name, value, HudDebugPosition.BOT_RIGHT, color);
    }

    public static void addBotRight(String name, Object value, Color color) {
        addBotRight(name, name, value, color);
    }

    public static void addBotRight(String name, Object value) {
        addBotRight(name, name, value, Color.WHITE);
    }


    public static void addMiddleLeft(String key, String name, Object value, Color color) {
        add(key, name, value, HudDebugPosition.MIDDLE_LEFT, color);
    }

    public static void addMiddleLeft(String name, Object value, Color color) {
        addMiddleLeft(name, name, value, color);
    }

    public static void addMiddleLeft(String name, Object value) {
        addMiddleLeft(name, name, value, Color.WHITE);
    }


    public static void addMiddleRight(String key, String name, Object value, Color color) {
        add(key, name, value, HudDebugPosition.MIDDLE_RIGHT, color);
    }

    public static void addMiddleRight(String name, Object value, Color color) {
        addMiddleRight(name, name, value, color);
    }

    public static void addMiddleRight(String name, Object value) {
        addMiddleRight(name, name, value, Color.WHITE);
    }

    public static void add(String name, Object value) {
        add(name, name, value, HudDebugPosition.TOP_RIGHT, Color.WHITE);
    }
    public static void add(String name, Object value, HudDebugPosition positionOnstage) {
        add(name, name, value, positionOnstage, Color.WHITE);
    }

    public static void add(String name, Object value, HudDebugPosition positionOnstage, Color color) {
        add(name, name, value, positionOnstage, color);
    }

    public static void add(String key, String name, Object value, HudDebugPosition positionOnstage) {
        add(key, name, value, positionOnstage, Color.WHITE);
    }

    public static void add(String key, String name, Object value, HudDebugPosition positionOnstage, Color color) {
        if (instance == null) {
            addInitList(key, name, value, positionOnstage, color);
        } else {
            instance.container.createLabel(positionOnstage, key, name, value, color);
        }
    }

    public static HudDebugEvent addEvent(String key, Object value, float duration, Color color, HudDebugPosition position) {
        if (instance != null) {
            return instance.eventManager.addEvent(key, value, duration, color, position);
        }
        return null;
    }

    public static HudDebugEvent addEvent(String key, Object value, float duration, Color color) {
        if (instance != null) {
            return instance.eventManager.addEvent(key, value, duration, color);
        }
        return null;
    }

    public static HudDebugEvent addEvent(String key, Object value, float duration) {
        if (instance != null) {
            return instance.eventManager.addEvent(key, value, duration);
        }
        return null;
    }

    public static HudDebugEvent addEvent(String key, Object value) {
        if (instance != null) {
            return instance.eventManager.addEvent(key, value);
        }
        return null;
    }

}
