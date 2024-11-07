package com.github.fabiitch.nz.demo.screens.scene2D;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.github.fabiitch.nz.demo.internal.BaseTryScreen;
import com.github.fabiitch.nz.demo.internal.selectors.DemoScreen;
import com.github.fabiitch.nz.gdx.debug.huddebug.HudDebug;
@DemoScreen(group = "hud.hud_debug")
public class HudDebugDisplayDemo extends BaseTryScreen {

    public HudDebugDisplayDemo(){
            for (int i = 0; i < 5; i++) {
                HudDebug.addTopLeft("addTopLeft " + i, Gdx.app.getJavaHeap(), Color.RED);
            }
            for (int i = 0; i < 5; i++) {
                HudDebug.addTopMiddle("addTopMiddle " + i, Gdx.app.getJavaHeap(), Color.WHITE);
            }
            for (int i = 0; i < 5; i++) {
                HudDebug.addTopRight("addTopRight " + i, Gdx.app.getJavaHeap(), Color.YELLOW);
            }
            for (int i = 0; i < 5; i++) {
                HudDebug.addBotLeft("addBotLeft " + i, Gdx.app.getJavaHeap(), Color.GOLD);
            }
            for (int i = 0; i < 5; i++) {
                HudDebug.addBotMiddle("addBotMiddle " + i, Gdx.app.getJavaHeap(), Color.GREEN);
            }
            for (int i = 0; i < 5; i++) {
                HudDebug.addBotRight("addBotRight" + i, Gdx.app.getJavaHeap(), Color.BROWN);
            }
            for (int i = 0; i < 5; i++) {
                HudDebug.addMiddleRight("addRightMiddle" + i, Gdx.app.getJavaHeap(), Color.GRAY);
            }
            for (int i = 0; i < 5; i++) {
                HudDebug.addMiddleLeft("addLeftMiddle" + i, Gdx.app.getJavaHeap(), Color.CYAN);
            }
    }
}
