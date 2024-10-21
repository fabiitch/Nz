package com.github.fabiitch.nz.java.math.utils;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.github.fabiitch.nz.java.math.shapes.utils.CircleUtils;
import com.github.fabiitch.nz.java.math.shapes.utils.RectangleUtils;
import com.github.fabiitch.nz.java.math.vectors.V2;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Rotation {

    private final static Vector2 tmp1V2 = new Vector2();
    private final static Vector2 tmp2V2 = new Vector2();

    public Vector2 rotateAround(Vector2 pos, Vector2 rotationCenter, float angleDeg) {
        // Convertir l'angle en radians
        float angleRad = (float) Math.toRadians(angleDeg);

        // Décaler le point en fonction du centre de rotation (on le place à l'origine temporairement)
        float translatedX = pos.x - rotationCenter.x;
        float translatedY = pos.y - rotationCenter.y;

        // Appliquer la rotation
        float cosTheta = (float) Math.cos(angleRad);
        float sinTheta = (float) Math.sin(angleRad);
        float rotatedX = translatedX * cosTheta - translatedY * sinTheta;
        float rotatedY = translatedX * sinTheta + translatedY * cosTheta;

        // Replacer le point à sa nouvelle position en rajoutant le centre de rotation
        float finalX = rotatedX + rotationCenter.x;
        float finalY = rotatedY + rotationCenter.y;

        return new Vector2(finalX, finalY);
    }

    public Rectangle rotateAround(Rectangle rect, Vector2 rotationCenter, float angleDeg) {
        // Convertir l'angle en radians
        float angleRad = (float) Math.toRadians(angleDeg);

        Vector2 center = RectangleUtils.getCenter(rect, tmp1V2);

        // Décaler le point en fonction du centre de rotation (on le place à l'origine temporairement)
        float translatedX = center.x - rotationCenter.x;
        float translatedY = center.y - rotationCenter.y;

        // Appliquer la rotation
        float cosTheta = (float) Math.cos(angleRad);
        float sinTheta = (float) Math.sin(angleRad);
        float rotatedX = translatedX * cosTheta - translatedY * sinTheta;
        float rotatedY = translatedX * sinTheta + translatedY * cosTheta;

        // Replacer le point à sa nouvelle position en rajoutant le centre de rotation
        float finalX = rotatedX + rotationCenter.x;
        float finalY = rotatedY + rotationCenter.y;

        RectangleUtils.setPosWithCenter(rect, finalX, finalY);
        return rect;
    }

    public Polygon rotateAround(Polygon polygon, Vector2 rotationCenter, float dst, float angleDeg) {
        Vector2 centroid = polygon.getCentroid(tmp1V2);
        Vector2 posTo = CircleUtils.posWithAngleDeg(rotationCenter, dst, angleDeg, tmp2V2);

        Vector2 translation = V2.getTranslation(centroid, posTo, new Vector2());
        polygon.translate(translation.x, translation.y);

        polygon.rotate(translation.angleDeg());
        return polygon;
    }


    //TODO not tested seen PolygonRotationAroundDemo
    public Polygon rotateAround(Polygon polygon, Vector2 rotationCenter, float angleDeg) {
        Vector2 centroid = polygon.getCentroid(tmp1V2);
        Vector2 centroidAfterRotation = rotateAround(centroid, rotationCenter, angleDeg);

        Vector2 translation = V2.getTranslation(centroid, centroidAfterRotation, new Vector2());
        polygon.translate(translation.x, translation.y);


        polygon.rotate(translation.angleDeg());
        return polygon;
    }
}
