package com.github.fabiitch.nz.gdx.render;

import com.badlogic.gdx.graphics.Color;

public class ColorUtils {

    public static Color get(Color color, float alpha) {
        return new Color(color.r, color.g, color.b, alpha);
    }

    public static Color set(Color source, Color target, float alpha) {
        return source.set(target.r, target.g, target.b, alpha);
    }

    public static Color setAlpha(Color color, float alpha) {
        color.a = alpha;
        return color;
    }

    public static Color setAlphaCpy(Color color, float alpha) {
        return setAlpha(color.cpy(), alpha);
    }

    public static Color getGradientColor(
            float value,
            float min,
            float max,
            Color startColor,
            Color endColor) {

        if (max == min) return startColor;

        // Clamp value
        float ratio = (value - min) / (max - min);
        ratio = Math.max(0.0f, Math.min(1.0f, ratio));

        return lerp(startColor, endColor, ratio);
    }
    public static Color lerp(Color from, Color to, float t) {
        t = Math.max(0f, Math.min(1f, t)); // clamp
        float r = from.r + (to.r - from.r) * t;
        float g = from.g + (to.g - from.g) * t;
        float b = from.b + (to.b - from.b) * t;
        float a = from.a + (to.a - from.a) * t;
        return new Color(r, g, b, a);
    }

}
