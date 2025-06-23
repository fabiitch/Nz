package com.github.fabiitch.nz.java.math.vectors;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class V3 {

    public static Vector3 tmp = new Vector3(); // TODO use ? server pb?

    private V3() {

    }

    public static Vector3 v() {
        return new Vector3();
    }

    public static Vector3 v(Vector2 v2) {
        return new Vector3(v2.x, v2.y, 0);
    }

    public static Vector3 v(float x, float y, float z) {
        return new Vector3(x, y, z);
    }

    public static Vector3 tmp(float x, float y) {
        return tmp.set(x, y, 0);
    }

    public static Vector3 tmp(Vector2 vector2) {
        return tmp.set(vector2, 0);
    }

    public static Vector3 tmp(Vector3 vector3) {
        return tmp.set(vector3);
    }

    public static Vector3 set(Vector3 v, Vector2 values) {
        v.x = values.x;
        v.y = values.y;
        return v;
    }

    public static Vector3 add(Vector3 v, Vector2 values) {
        v.x += values.x;
        v.y += values.y;
        return v;
    }

    public static Vector3 sub(Vector3 v, Vector2 values) {
        v.x -= values.x;
        v.y -= values.y;
        return v;
    }
}
