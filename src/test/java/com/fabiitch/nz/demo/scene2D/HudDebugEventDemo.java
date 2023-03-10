package com.fabiitch.nz.demo.scene2D;

import com.badlogic.gdx.graphics.Color;
import com.fabiitch.nz.demo.internal.selectors.DemoScreen;
import com.fabiitch.nz.gdx.debug.huddebug.event.HudDebugEvent;
import com.fabiitch.nz.gdx.debug.huddebug.event.HudDebugEventManager;
import com.fabiitch.nz.demo.internal.BaseTryScreen;
import com.fabiitch.nz.java.time.timers.TimeLocker;

/**
 * Test Hud event display and remove well
 */
@DemoScreen(group = "hud.hud_debug")
public class HudDebugEventDemo extends BaseTryScreen {


    HudDebugEventManager hudDebugEventManager = new HudDebugEventManager();
    TimeLocker timeLocker = new TimeLocker(2);
    int count = 1;

    @Override
    public void show() {
        hudDebugEventManager.addEvent(HudDebugEvent.get("toot " + count, "gogogo", 5, Color.BLUE));
    }

    @Override
    public void render(float dt) {
        super.render(dt);

        if (timeLocker.isOpen(dt)) {
            count++;
            hudDebugEventManager.addEvent(HudDebugEvent.get("toot " + count, "gogogo", 5, Color.BLUE));
        }
        hudDebugEventManager.update();
    }
}
