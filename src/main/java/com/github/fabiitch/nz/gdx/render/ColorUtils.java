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
            double value,
            double min,
            double max,
            Color startColor,
            Color endColor) {

        if (max == min) return startColor;

        // Clamp value
        double ratio = (value - min) / (max - min);
        ratio = Math.max(0.0, Math.min(1.0, ratio));

        int r = (int) (startColor.r   + ratio * (endColor.r   - startColor.r));
        int g = (int) (startColor.g + ratio * (endColor.g - startColor.g));
        int b = (int) (startColor.b  + ratio * (endColor.b  - startColor.b));

        return new Color(r, g, b, 1);
    }

}
