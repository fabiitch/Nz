package com.github.fabiitch.nz.java.math.vectors.v2;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class IntVector2 implements Vector<IntVector2> {
    public int x;
    public int y;

    @Override
    public IntVector2 cpy() {
        return new IntVector2(x, y);
    }

    @Override
    public float len() {
        return (float) Math.sqrt(x * x + y * y);
    }

    @Override
    public float len2() {
        return x * x + y * y;
    }

    @Override
    public IntVector2 limit(float limit) {
        return limit2(limit * limit);
    }

    @Override
    public IntVector2 limit2(float limit2) {
        float len2 = len2();
        if (len2 > limit2) {
            return scl((float) Math.sqrt(limit2 / len2));
        }
        return this;
    }

    @Override
    public IntVector2 setLength(float len) {
        return setLength2(len * len);
    }

    @Override
    public IntVector2 setLength2(float len2) {
        float oldLen2 = len2();
        return (oldLen2 == 0 || oldLen2 == len2) ? this : scl((float) Math.sqrt(len2 / oldLen2));
    }

    @Override
    public IntVector2 clamp(float min, float max) {
        final float len2 = len2();
        if (len2 == 0f) return this;
        float max2 = max * max;
        if (len2 > max2) return scl((float) Math.sqrt(max2 / len2));
        float min2 = min * min;
        if (len2 < min2) return scl((float) Math.sqrt(min2 / len2));
        return this;
    }

    public IntVector2 set(int x, int y) {
        this.x = x;
        this.y = y;
        return this;
    }

    @Override
    public IntVector2 set(IntVector2 v) {
        return set(v.x, v.y);
    }

    @Override
    public IntVector2 sub(IntVector2 v) {
        this.x -= x;
        this.y -= y;
        return this;
    }

    public IntVector2 sub(int x, int y) {
        this.x -= x;
        this.y -= y;
        return this;
    }

    @Override
    public IntVector2 nor() {
        float len = len();
        if (len != 0) {
            x /= (int) len;
            y /= (int) len;
        }
        return this;
    }

    @Override
    public IntVector2 add(IntVector2 v) {
        x += v.x;
        y += v.y;
        return this;
    }

    public IntVector2 add(int x, int y) {
        this.x += x;
        this.y += y;
        return this;
    }

    @Override
    public float dot(IntVector2 v) {
        return x * v.x + y * v.y;
    }

    public float dot(int ox, int oy) {
        return x * ox + y * oy;
    }


    @Override
    public IntVector2 scl(float scalar) {
        x *= (int) scalar;
        y *= (int) scalar;
        return this;
    }

    @Override
    public IntVector2 scl(IntVector2 v) {
        this.x *= v.x;
        this.y *= v.y;
        return this;
    }
    public IntVector2 scl (float x, float y) {
        this.x *= (int) x;
        this.y *= (int) y;
        return this;
    }
    @Override
    public float dst(IntVector2 v) {
        return 0;
    }

    @Override
    public float dst2(IntVector2 v) {
        return 0;
    }

    @Override
    public IntVector2 lerp(IntVector2 target, float alpha) {
        return null;
    }

    @Override
    public IntVector2 interpolate(IntVector2 target, float alpha, Interpolation interpolation) {
        return lerp(target, interpolation.apply(alpha));
    }

    @Override
    public IntVector2 setToRandomDirection() {
        float theta = MathUtils.random(0f, MathUtils.PI2);
        return this.set((int) MathUtils.cos(theta), (int) MathUtils.sin(theta));
    }

    @Override
    public boolean isUnit() {
        return isUnit(0.000000001f);
    }

    @Override
    public boolean isUnit(float margin) {
        return false;
    }

    @Override
    public boolean isZero() {
        return false;
    }

    @Override
    public boolean isZero(float margin) {
        return Math.abs(len2() - 1f) < margin;
    }

    @Override
    public boolean isOnLine (IntVector2 other) {
        return MathUtils.isZero(x * other.y - y * other.x);
    }

    @Override
    public boolean isOnLine (IntVector2 other, float epsilon) {
        return MathUtils.isZero(x * other.y - y * other.x, epsilon);
    }

    @Override
    public boolean isCollinear (IntVector2 other, float epsilon) {
        return isOnLine(other, epsilon) && dot(other) > 0f;
    }

    @Override
    public boolean isCollinear (IntVector2 other) {
        return isOnLine(other) && dot(other) > 0f;
    }

    @Override
    public boolean isCollinearOpposite (IntVector2 other, float epsilon) {
        return isOnLine(other, epsilon) && dot(other) < 0f;
    }

    @Override
    public boolean isCollinearOpposite (IntVector2 other) {
        return isOnLine(other) && dot(other) < 0f;
    }

    @Override
    public boolean isPerpendicular (IntVector2 vector) {
        return MathUtils.isZero(dot(vector));
    }

    @Override
    public boolean isPerpendicular (IntVector2 vector, float epsilon) {
        return MathUtils.isZero(dot(vector), epsilon);
    }

    @Override
    public boolean hasSameDirection (IntVector2 vector) {
        return dot(vector) > 0;
    }

    @Override
    public boolean hasOppositeDirection (IntVector2 vector) {
        return dot(vector) < 0;
    }

    @Override
    public boolean epsilonEquals(IntVector2 other, float epsilon) {
        if (other == null) return false;
        if (Math.abs(other.x - x) > epsilon) return false;
        if (Math.abs(other.y - y) > epsilon) return false;
        return true;
    }

    @Override
    public IntVector2 mulAdd(IntVector2 vec, float scalar) {
        this.x += (int) (vec.x * scalar);
        this.y += (int) (vec.y * scalar);
        return this;
    }

    @Override
    public IntVector2 mulAdd(IntVector2 vec, IntVector2 mulVec) {
        this.x += vec.x * mulVec.x;
        this.y += vec.y * mulVec.y;
        return this;
    }

    @Override
    public IntVector2 setZero() {
        this.x = 0;
        this.y = 0;
        return this;
    }
}
