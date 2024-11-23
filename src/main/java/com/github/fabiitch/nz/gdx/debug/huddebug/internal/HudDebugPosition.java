package com.github.fabiitch.nz.gdx.debug.huddebug.internal;

/**
 * Mapping for order on HudDebug
 */
public enum HudDebugPosition {

    TOP_LEFT, TOP_MIDDLE,TOP_RIGHT,
    BOT_LEFT, BOT_MIDDLE, BOT_RIGHT,
    MIDDLE_LEFT, MIDDLE_RIGHT;


    public static boolean isLeft(HudDebugPosition position) {
        return position == TOP_LEFT || position == BOT_LEFT || position == MIDDLE_LEFT;
    }

    public static boolean isRight(HudDebugPosition position) {
        return position == TOP_RIGHT || position == BOT_RIGHT || position == MIDDLE_RIGHT;
    }

    public static boolean isTop(HudDebugPosition position) {
        return position == TOP_LEFT || position == TOP_MIDDLE || position == TOP_RIGHT;
    }

    public static boolean isBot(HudDebugPosition position) {
        return position == BOT_LEFT || position == BOT_MIDDLE || position == BOT_RIGHT;
    }

    public static boolean isMiddleHorizontal(HudDebugPosition position) {
        return position == TOP_MIDDLE || position == BOT_MIDDLE;
    }

    public static boolean isMiddleVertical(HudDebugPosition position) {
        return position == MIDDLE_LEFT || position == MIDDLE_RIGHT;
    }
}
