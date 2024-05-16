package com.github.fabiitch.nz.gdx.debug.huddebug.internal;

/**
 * Mapping for order on HudDebug
 */
public class HudDebugPosition {

    public static final int TOP_LEFT = 0;
    public static final int TOP_MIDDLE = 1;
    public static final int TOP_RIGHT = 2;

    public static final int BOT_LEFT = 3;
    public static final int BOT_MIDDLE = 4;
    public static final int BOT_RIGHT = 5;

    public static final int MIDDLE_LEFT = 6;
    public static final int MIDDLE_RIFHT = 7;


    public static boolean isLeft(int positionOnStage) {
        return positionOnStage == TOP_LEFT || positionOnStage == BOT_LEFT || positionOnStage == MIDDLE_LEFT;
    }

    public static boolean isRight(int positionOnStage) {
        return positionOnStage == TOP_RIGHT || positionOnStage == BOT_RIGHT || positionOnStage == MIDDLE_RIFHT;
    }

    public static boolean isTop(int positionOnStage) {
        return positionOnStage == TOP_LEFT || positionOnStage == TOP_MIDDLE || positionOnStage == TOP_RIGHT;
    }

    public static boolean isBot(int positionOnStage) {
        return positionOnStage == BOT_LEFT || positionOnStage == BOT_MIDDLE || positionOnStage == BOT_RIGHT;
    }

    public static boolean isMiddleHorizontal(int positionOnStage) {
        return positionOnStage == TOP_MIDDLE || positionOnStage == BOT_MIDDLE;
    }

    public static boolean isMiddleVertical(int positionOnStage) {
        return positionOnStage == MIDDLE_LEFT || positionOnStage == MIDDLE_RIFHT;
    }
}
