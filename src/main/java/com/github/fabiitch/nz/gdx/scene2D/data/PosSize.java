package com.github.fabiitch.nz.gdx.scene2D.data;

import com.badlogic.gdx.math.Vector2;

// ??? fufu
public class PosSize {

    public final Vector2 pos;
    public final Vector2 size;

    public PosSize() {
        pos = new Vector2();
        size = new Vector2();
    }

    public PosSize(Vector2 pos, Vector2 size) {
        this.pos = new Vector2(pos);
        this.size = new Vector2(size);
    }

    public PosSize(Vector2 pos, float size) {
        this.pos = new Vector2(pos);
        this.size = new Vector2(size, size);
    }

    public PosSize(float x, float y, float witdh, float height) {
        this.pos = new Vector2(x, y);
        this.size = new Vector2(witdh, height);
    }

    public PosSize(float x, float y, float size) {
        this.pos = new Vector2(x, y);
        this.size = new Vector2(size, size);
    }

    public static PosSize get(float x, float y, float witdh, float height) {
        return new PosSize(x, y, witdh, height);
    }

    public static PosSize get(float x, float y, float size) {
        return new PosSize(x, y, size);
    }

    public static PosSize get(Vector2 pos, Vector2 size) {
        return new PosSize(pos, size);
    }

    public static PosSize get(Vector2 pos, float size) {
        return new PosSize(pos, size);
    }

    public Vector2 getPos() {
        return pos;
    }

    public float getX() {
        return pos.x;
    }

    public float getY() {
        return pos.y;
    }

    public Vector2 getSize() {
        return size;
    }

    public float getWitdh() {
        return size.x;
    }

    public float getHeight() {
        return size.y;
    }
}
