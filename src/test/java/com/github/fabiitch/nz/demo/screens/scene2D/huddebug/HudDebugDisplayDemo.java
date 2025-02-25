package com.github.fabiitch.nz.demo.screens.scene2D.huddebug;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.github.fabiitch.nz.demo.internal.BaseDemoScreen;
import com.github.fabiitch.nz.demo.internal.selectors.DemoScreen;
import com.github.fabiitch.nz.gdx.debug.huddebug.HudDebug;
@DemoScreen(group = "scene2D.hud_debug")
public class HudDebugDisplayDemo extends BaseDemoScreen {

    public HudDebugDisplayDemo(){
        int messageCount = 10;
            for (int i = 0; i < messageCount; i++) {
                HudDebug.addTopLeft("addTopLeft " + i, Gdx.app.getJavaHeap(), Color.RED);
            }
            for (int i = 0; i < messageCount; i++) {
                HudDebug.addTopMiddle("addTopMiddle " + i, Gdx.app.getJavaHeap(), Color.WHITE);
            }
            for (int i = 0; i < messageCount; i++) {
                HudDebug.addTopRight("addTopRight " + i, Gdx.app.getJavaHeap(), Color.YELLOW);
            }
            for (int i = 0; i < messageCount; i++) {
                HudDebug.addBotLeft("addBotLeft " + i, Gdx.app.getJavaHeap(), Color.GOLD);
            }
            for (int i = 0; i < messageCount; i++) {
                HudDebug.addBotMiddle("addBotMiddle " + i, Gdx.app.getJavaHeap(), Color.GREEN);
            }
            for (int i = 0; i < messageCount; i++) {
                HudDebug.addBotRight("addBotRight" + i, Gdx.app.getJavaHeap(), Color.BROWN);
            }
            for (int i = 0; i < messageCount; i++) {
                HudDebug.addMiddleRight("addRightMiddle" + i, Gdx.app.getJavaHeap(), Color.GRAY);
            }
            for (int i = 0; i < messageCount; i++) {
                HudDebug.addMiddleLeft("addLeftMiddle" + i, Gdx.app.getJavaHeap(), Color.CYAN);
            }
    }

    @Override
    public void doRender(float dt) {

    }

    @Override
    public void doDispose() {

    }
}
