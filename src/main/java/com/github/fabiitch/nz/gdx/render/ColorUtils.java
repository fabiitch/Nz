package com.github.fabiitch.nz.gdx.render;

import com.badlogic.gdx.graphics.Color;

public class ColorUtils {

    public static Color get(Color color, float alpha) {
        return new Color(color.r, color.g, color.b, alpha);
    }

    public static Color set(Color source, Color target, float alpha) {
        return source.set(target.r, target.g, target.b, alpha);
    }

}
