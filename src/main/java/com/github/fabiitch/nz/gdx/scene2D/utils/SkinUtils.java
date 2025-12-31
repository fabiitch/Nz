package com.github.fabiitch.nz.gdx.scene2D.utils;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SkinUtils {

    public static TextButton.TextButtonStyle getTextButtonStyle(Skin skin) {
        return skin.get(TextButton.TextButtonStyle.class);
    }

    public static ImageButton.ImageButtonStyle getImageButtonStyle(Skin skin) {
        return skin.get(ImageButton.ImageButtonStyle.class);
    }
}
