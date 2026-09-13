package com.fabiitch.nz.demo.screens.scene2D.huddebug;

import com.badlogic.gdx.utils.Array;
import com.fabiitch.nz.demo.internal.BaseDemoScreen;
import com.fabiitch.nz.demo.internal.selectors.DemoScreen;
import com.fabiitch.nz.gdx.debug.DebugDisplayUtils;
import com.fabiitch.nz.gdx.debug.huddebug.HudDebug;
import com.fabiitch.nz.gdx.debug.huddebug.event.HudDebugEvent;
import com.fabiitch.nz.java.time.timers.TimeLocker;
import com.fabiitch.nz.java.utils.randoms.Randoms;

/**
 * Test Hud event display and remove well
 */
@DemoScreen(group = "scene2D.hud_debug")
public class HudDebugEventDemo extends BaseDemoScreen {


    TimeLocker timeLocker = new TimeLocker(2);
    int count = 1;

    private Array<HudDebugEvent> events = new Array<HudDebugEvent>();

    @Override
    public void show() {
    }

    @Override
    public void doRender(float dt) {
        if (timeLocker.isOpen(dt)) {
            count++;
            HudDebugEvent event = HudDebug.addEvent("Event " + count, "start !", Randoms.get(4,20), Randoms.color());
            events.add(event);
        }
        for (HudDebugEvent event : events) {
            event.value = DebugDisplayUtils.printSeconds(event.duration - event.elapsedTime);
        }

    }

    @Override
    public void doDispose() {

    }
}
