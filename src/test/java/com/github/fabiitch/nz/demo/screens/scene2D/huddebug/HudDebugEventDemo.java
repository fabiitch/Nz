package com.github.fabiitch.nz.demo.screens.scene2D.huddebug;

import com.badlogic.gdx.graphics.Color;
import com.github.fabiitch.nz.demo.internal.BaseDemoScreen;
import com.github.fabiitch.nz.demo.internal.selectors.DemoScreen;
import com.github.fabiitch.nz.gdx.debug.huddebug.event.HudDebugEvent;
import com.github.fabiitch.nz.gdx.debug.huddebug.event.HudDebugEventManager;
import com.github.fabiitch.nz.java.time.timers.TimeLocker;

/**
 * Test Hud event display and remove well
 */
@DemoScreen(group = "scene2D.hud_debug")
public class HudDebugEventDemo extends BaseDemoScreen {


    HudDebugEventManager hudDebugEventManager = new HudDebugEventManager();
    TimeLocker timeLocker = new TimeLocker(2);
    int count = 1;

    @Override
    public void show() {
        hudDebugEventManager.addEvent(HudDebugEvent.get("toot " + count, "gogogo", 5, Color.BLUE));
    }

    @Override
    public void doRender(float dt) {
        if (timeLocker.isOpen(dt)) {
            count++;
            hudDebugEventManager.addEvent(HudDebugEvent.get("toot " + count, "gogogo", 5, Color.BLUE));
        }
        hudDebugEventManager.update();
    }

    @Override
    public void doDispose() {

    }
}
