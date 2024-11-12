package com.github.fabiitch.nz.java.math.shapes.utils;

import com.badlogic.gdx.math.*;
import com.github.fabiitch.nz.java.math.vectors.V2;

public class CircleUtils {

    private final static Vector2 tmp = new Vector2(); //TODO group sa
    private final static Vector2 tmp2 = new Vector2(); //TODO g

    private CircleUtils() {
    }

    public static Vector2 getCenter(Circle circle, Vector2 center) {
        return center.set(circle.x, circle.y);
    }

    public static Vector2 getTangent(Circle circle, Vector2 posOnCircle, Vector2 result) {
        getCenter(circle, tmp);
        return V2.directionTo(tmp, posOnCircle, result).rotate90(0);
    }

    public static Vector2 getTangentDeg(Circle circle, float angleDeg, Vector2 result) {
        return getTangentRad(circle, MathUtils.degreesToRadians * angleDeg, result);
    }

    //tested by STCircleTangent
    public static Vector2 getTangentRad(Circle circle, float angleRad, Vector2 result) {
        posWithAngleRad(circle, angleRad, tmp);
        getCenter(circle, tmp2);
        return V2.directionTo(tmp2, tmp, result).rotate90(0);
    }


    public static Vector2 dirFromCenter(Circle circle, Vector2 posOnCircle, Vector2 normalResult) {
        getCenter(circle, tmp);
        V2.directionTo(tmp, posOnCircle, normalResult);
        return normalResult;
    }

    public static Vector2 dirToCenter(Circle circle, Vector2 posOnCircle, Vector2 normalResult) {
        getCenter(circle, tmp);
        V2.directionTo(posOnCircle, tmp, normalResult);
        return normalResult;
    }

    public static Vector2 dirFromCenter(Circle circle, float angleDeg, Vector2 normalResult) {
        getCenter(circle, tmp);
        V2.directionTo(tmp, posWithAngleDeg(circle, angleDeg, tmp2), normalResult);
        return normalResult;
    }

    public static Vector2 dirToCenter(Circle circle, float angleDeg, Vector2 normalResult) {
        getCenter(circle, tmp);
        V2.directionTo(posWithAngleDeg(circle, angleDeg, tmp2), tmp, normalResult);
        return normalResult;
    }

    public static Vector2 posWithAngleDeg(Circle cirle, float angleDeg, Vector2 returnV) {
        return posWithAngleRad(cirle.x, cirle.y, cirle.radius, MathUtils.degreesToRadians * angleDeg, returnV);
    }

    public static Vector2 posWithAngleRad(Circle cirle, float angleRadian, Vector2 returnV) {
        return posWithAngleRad(cirle.x, cirle.y, cirle.radius, angleRadian, returnV);
    }

    public static Vector2 posWithAngleDeg(Vector2 center, float radius, float angleDeg, Vector2 returnV) {
        return posWithAngleRad(center.x, center.y, radius, MathUtils.degreesToRadians * angleDeg, returnV);
    }

    public static Vector2 posWithAngleRad(float xP, float yP, float radius, float angleRadian, Vector2 returnV) {
        returnV.x = xP + radius * MathUtils.cos(angleRadian);
        returnV.y = yP + radius * MathUtils.sin(angleRadian);
        return returnV;
    }


    public static Vector2 posWithAngleRad(Vector2 positionStart, float rayon, float angleRadian, Vector2 returnV) {
        return posWithAngleRad(positionStart.x, positionStart.y, rayon, angleRadian, returnV);
    }

    public static Vector3 posWithAngleRad(Vector3 positionStart, float rayon, float angleRadian, Vector3 returnV) {
        returnV.x = positionStart.x + rayon * MathUtils.cos(angleRadian);
        returnV.y = positionStart.y + rayon * MathUtils.sin(angleRadian);
        return returnV;
    }

    public static Vector2 getReflexion(Circle cirle, Vector2 dir, Vector2 posOnCircle, Vector2 reflexionDir) {
        Vector2 normal = dirFromCenter(cirle, posOnCircle, tmp);

//        float angleDiff = normal.angleDeg()
//        reflexionDir.set(1,0)
        return normal;
    }

    public static Rectangle getRectBounds(Circle circle, Rectangle rectangle) {
        float radius = circle.radius;
        rectangle.set(circle.x - radius, circle.y - radius, radius * 2, radius * 2);
        return rectangle;
    }

    public static Vector2 getRandomPos(Vector2 position, float radius, Vector2 result) {
        return getRandomPos(position.x, position.y, radius, result);
    }


    public static Vector2 getRandomPos(float x, float y, float radius, Vector2 result) {
        float r = (float) (radius * Math.sqrt(MathUtils.random()));
        float theta = MathUtils.random() * 2 * MathUtils.PI;
        return posWithAngleRad(x, y, r, theta, result);
    }

    public static Vector2 getRandomPos(Circle circle, Vector2 result) {
        return getRandomPos(circle.x, circle.y, circle.radius, result);
    }

//    public static float getAngleReflexionRad(Circle circle, float angleRad) {
//        Vector2 tangent = getTangentRad(circle, angleRad, tmp);
//        tmp2.setAngleRad(angleRad);
//        return AngleUtils.angleReflexionRad(tangent, tmp2);
//    }


    //tangent point extérieure :
    //  https://github.com/williamfiset/Algorithms/blob/master/src/main/java/com/williamfiset/algorithms/geometry/PointCircleTangent.java
}
