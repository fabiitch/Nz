package com.github.fabiitch.nz.gdx.scene2D;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;


public class StageUtils {
    public static void buttonFullDisabled(Button button, boolean b) {
        button.setTouchable(b ? Touchable.disabled : Touchable.enabled);
        button.setDisabled(b);
    }
}
