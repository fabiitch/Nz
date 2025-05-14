package com.github.fabiitch.nz.demo.screens.scene2D.huddebug;

import com.badlogic.gdx.graphics.Color;
import com.github.fabiitch.nz.demo.internal.BaseDemoScreen;
import com.github.fabiitch.nz.demo.internal.selectors.DemoScreen;
import com.github.fabiitch.nz.gdx.debug.huddebug.HudDebug;
import com.github.fabiitch.nz.gdx.debug.huddebug.event.HudDebugEvent;
import com.github.fabiitch.nz.gdx.debug.huddebug.event.HudDebugEventManager;
import com.github.fabiitch.nz.java.time.timers.TimeLocker;
import com.github.fabiitch.nz.java.utils.randoms.Randoms;

/**
 * Test Hud event display and remove well
 */
@DemoScreen(group = "scene2D.hud_debug")
public class HudDebugEventDemo extends BaseDemoScreen {


    TimeLocker timeLocker = new TimeLocker(2);
    int count = 1;

    @Override
    public void show() {
        HudDebug.addEvent("toot " + count, "gogogo", 5, Randoms.color());
    }

    @Override
    public void doRender(float dt) {
        if (timeLocker.isOpen(dt)) {
            count++;
            HudDebug.addEvent("toot " + count, "gogogo", 5, Randoms.color());
        }
    }

    @Override
    public void doDispose() {

    }
}
