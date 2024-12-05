package com.github.fabiitch.nz.java.math.shapes;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;
import com.github.fabiitch.nz.java.math.shapes.utils.CircleUtils;
import com.github.fabiitch.nz.java.math.shapes.utils.SegmentUtils;
import com.github.fabiitch.nz.java.math.vectors.v2.V2;
import lombok.Getter;

import java.util.Objects;

@Getter
public class Segment implements Shape2D {

    private final static Vector2 tmpv1 = new Vector2(); //TODO groups pools
    private final static Vector2 tmpv2 = new Vector2(); //TODO groups pools

    public final Vector2 a;
    public final Vector2 b;

    public Segment() {
        this.a = new Vector2();
        this.b = new Vector2();
    }

    public Segment(Vector2 a, Vector2 b) {
        this.a = a;
        this.b = b;
    }

    public Segment(float aX, float aY, float bX, float bY) {
        this.a = new Vector2(aX, aY);
        this.b = new Vector2(bX, bY);
    }

    public void move(float x, float y) {
        this.a.add(x, y);
        this.b.add(x, y);
    }

    public void move(Vector2 add) {
        this.a.add(add.x, add.y);
        this.b.add(add.x, add.y);
    }

    public void setRotation(float degrees) {
        getMiddle(tmpv1);
        a.set(CircleUtils.posWithAngleDeg(tmpv1, dst() / 2, degrees, tmpv2));
        b.set(CircleUtils.posWithAngleDeg(tmpv1, dst() / 2, degrees + 180, tmpv2));
    }

    public void rotate(float degrees) {
        float angleDeg = getDir(tmpv1).angleDeg();
        setRotation(angleDeg + degrees);
    }

    public Segment reverse() {
        tmpv1.set(a);
        a.set(b);
        b.set(tmpv1);
        return this;
    }

    /**
     * @param centerX new center position
     * @param centerY center position
     */
    public void setCenter(float centerX, float centerY) {
        float currentCenterX = (a.x + b.x) / 2;
        float currentCenterY = (a.y + b.y) / 2;

        // Calculer le décalage (vecteur de déplacement)
        float deltaX = centerX - currentCenterX;
        float deltaY = centerY - currentCenterY;

        // Appliquer le décalage aux points a et b
        a.x += deltaX;
        a.y += deltaY;
        b.x += deltaX;
        b.y += deltaY;
    }

    public float dst(Vector2 point) {
        return SegmentUtils.closestPoint(this, point, tmpv1).dst(point);
    }

    public Vector2 closestPoint(Vector2 point, Vector2 result) {
        return SegmentUtils.closestPoint(this, point, result);
    }

    /**
     * from a to b
     *
     * @param result
     * @return
     */
    public Vector2 getDir(Vector2 result) {
        float dx = b.x - a.x;
        float dy = b.y - a.y;
        return result.set(dx, dy).nor();
    }

    /**
     * from a to b
     *
     * @param result
     * @return
     */
    public Vector2 getDirInv(Vector2 result) {
        return V2.inv(getDir(result));
    }

    public Vector2 getMiddle(Vector2 result) {
        return result.set((a.x + b.x) / 2, (a.y + b.y) / 2);
    }

    public float dst() {
        return a.dst(b);
    }

    /**
     * Normal orienté du coté du point
     */
    public Vector2 getNormal(Vector2 point, Vector2 normal) {
        Vector2 middle = getMiddle(tmpv1);
        getNormal(normal);
        V2.directionTo(middle, point, tmpv2);

        if (tmpv2.hasOppositeDirection(normal))
            normal.rotateDeg(180);
        return normal;
    }

    public Vector2 getNormal(Vector2 normal) {
        Vector2 dir = getDir(normal);
        float newX = -dir.y;
        float newY = dir.x;
        return normal.set(newX, newY);
    }

    public Segment set(float aX, float aY, float bX, float bY) {
        setA(aX, aY);
        setB(bX, bY);
        return this;
    }

    public Segment set(Vector2 a, Vector2 b) {
        this.a.set(a);
        this.b.set(b);
        return this;
    }

    public Segment set(Segment segment2D) {
        return set(segment2D.a, segment2D.b);
    }

    public Segment setA(Vector2 a) {
        this.a.set(a);
        return this;
    }

    public Segment setA(float aX, float aY) {
        this.a.set(aX, aY);
        return this;
    }

    public Segment setB(Vector2 b) {
        this.b.set(b);
        return this;
    }

    public Segment setB(float bX, float bY) {
        this.b.set(bX, bY);
        return this;
    }

    @Override
    public String toString() {
        return "Segment2D{" +
                "a=" + a +
                ", b=" + b +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Segment segment2D = (Segment) o;
        return Objects.equals(a, segment2D.a) &&
                Objects.equals(b, segment2D.b);
    }

    public boolean equalsPoints(Segment segment) {
        if (this == segment)
            return true;
        return (this.a.epsilonEquals(segment.a) && this.b.epsilonEquals(segment.b))
                || (this.b.epsilonEquals(segment.a) && this.a.epsilonEquals(segment.b));
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }

    @Override
    public boolean contains(Vector2 point) {
        return dst(point) < MathUtils.FLOAT_ROUNDING_ERROR;
    }

    @Override
    public boolean contains(float x, float y) {
        return contains(tmpv1.set(x, y));
    }


    public boolean isCollinear(Segment s2) {
        Vector2 dirThis = tmpv1.set(b).sub(a);
        Vector2 dirOther = tmpv2.set(s2.b).sub(s2.a);
        return dirThis.isCollinear(dirOther);
    }

    public boolean isCollinear(Segment s2, float epsilon) {
        Vector2 dirThis = tmpv1.set(b).sub(a);
        Vector2 dirOther = tmpv2.set(s2.b).sub(s2.a);
        return dirThis.isCollinear(dirOther, epsilon);
    }

    public boolean hasCommonPart(Segment other) {
        // Projeter les segments sur l'axe X ou Y et vérifier s'ils se chevauchent

        // Utilisation de l'axe X pour les projections
        float min1 = Math.min(a.x, b.x);
        float max1 = Math.max(a.x, b.x);
        float min2 = Math.min(other.a.x, other.b.x);
        float max2 = Math.max(other.a.x, other.b.x);

        return Math.min(max1, max2) >= Math.max(min1, min2);
    }
}
