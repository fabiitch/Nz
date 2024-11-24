package com.github.fabiitch.nz.java.data.quadtree;

public enum QuadTreeRegion {
    TopLeft,
    TopRight,
    BotLeft,
    BotRight,
    Out,
    This;

    public static QuadTreeRegion[] VALUES = values();
    public static QuadTreeRegion CHILD[] = new QuadTreeRegion[]{TopLeft, TopRight, BotLeft, BotRight};

    public boolean isChild() {
        return this.ordinal() <= 3;
    }
}
