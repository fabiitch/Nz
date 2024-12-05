package com.github.fabiitch.nz.java.math.angle;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.github.fabiitch.nz.java.math.shapes.Segment;
import com.github.fabiitch.nz.java.math.vectors.v2.V2;

public class AngleUtils {
    private AngleUtils() {

    }

    private static final Vector2 tmp = new Vector2();

    /**
     * Angle incidence, angle collision
     * Angle reflexion, angle rebond
     * https://fr.wikipedia.org/wiki/Lois_de_Snell-Descartes
     * https://upload.wikimedia.org/wikipedia/commons/9/91/Reflexion_fr.png?uselang=fr
     * Voir Screen Test : STCircleReflexionRay
     */
    public static float incidenceToReflexion(float angleReflexionDeg) {
        return 180 + angleReflexionDeg;
    }

    public static float angleIncidenceRad(float angleReflexionRad) {
        return MathUtils.PI + angleReflexionRad;
    }

    public static float angleIncidenceDeg(Segment segment, Vector2 dir) {
        return incidenceToReflexion(angleIncidence(segment, dir));
    }

    public static float angleIncidence(Segment segmentIntersect, Vector2 dir) {
        Vector2 normal = segmentIntersect.getNormal(tmp);
        return V2.angleDeg(normal) - (V2.angleDeg(dir) - V2.angleDeg(normal));
    }

    public static float angleReflexionRad(Segment segment, Vector2 dir) {
        Vector2 normal = segment.getNormal(tmp);
        return V2.angleRad(normal) - (V2.angleRad(dir) - V2.angleRad(normal));
    }

    public static float incidenceToReflexion(Segment segment, Vector2 dir) {
        return 180 + angleIncidence(segment, dir);
    }

    public static float angleIncidenceRad(Segment segment, Vector2 dir) {
        return MathUtils.PI + angleIncidenceRad(segment, dir);
    }

    /**
     * angleIncidence = rebondit dans le sens du vecteur
     */
    public static float angleIncidence(Vector2 dirEdge, Vector2 dirBullet) {
        Vector2 normal = V2.getNormal(dirEdge, tmp);
        return V2.angleDeg(normal) - (V2.angleDeg(dirBullet) - V2.angleDeg(normal));
    }

    public static float angleReflexionRad(Vector2 dirEdge, Vector2 dirBullet) {
        Vector2 normal = V2.getNormal(dirEdge, tmp);
        return V2.angleRad(normal) - (V2.angleRad(dirBullet) - V2.angleRad(normal));
    }

    public static int distanceAbs(int alpha, int beta) {
        int phi = Math.abs(beta - alpha) % 360; // This is either the distance or 360 - distance
        return phi > 180 ? 360 - phi : phi;
    }

    public static float distanceAbs(float alpha, float beta) {
        float phi = Math.abs(beta - alpha) % 360; // This is either the distance or 360 - distance
        return phi > 180 ? 360 - phi : phi;
    }

    public static float distanceAbs(Vector2 dir, Vector2 input) {
        return distanceAbs(V2.angleDeg(dir), V2.angleDeg(input));
    }

    public static float distanceSigned(float a, float b) {
        float d = Math.abs(a - b) % 360;
        float r = d > 180 ? 360 - d : d;

        // calculate sign
        int sign = (a - b >= 0 && a - b <= 180) || (a - b <= -180 && a - b >= -360) ? 1 : -1;
        r *= sign;
        return r;
    }

    public static float distanceSigned(Vector2 v1, Vector2 v2) {
        return distanceSigned(V2.angleDeg(v1), V2.angleDeg(v2));
    }


    /**
     * Normalize an angle so that it is between 0 and 360.
     */
    public static float normaliseDeg(float angleDeg) {
        return (angleDeg >= 0 ? angleDeg : (360 - ((-angleDeg) % 360))) % 360;
    }

    /**
     * normalize an angle between 0 and 2pi
     * see org.apache.commons.math3.util.MathUtils#normalizeAngle(double, double)
     */
    public static float normaliseRad02Pi(float angleRad) {
        return angleRad - MathUtils.PI2 * MathUtils.floor(angleRad / MathUtils.PI2);
    }

    /**
     * normalize an angle between -pi and +pi
     * see org.apache.commons.math3.util.MathUtils#normalizeAngle(double, double)
     */
    public static float normaliseRadPiNPi(float angleRad) {
        return angleRad - MathUtils.PI2 * MathUtils.floor((angleRad + MathUtils.PI) / MathUtils.PI2);
    }

    //TODO maybe opimise

    /**
     * angle in deg
     */
    public static boolean isSameLine(float a1, float a2, float tolerance) {
        a1 = normaliseDeg(a1);
        a2 = normaliseDeg(a2);
        if (MathUtils.isEqual(a1, a2, tolerance)) {
            return true;
        }
        a1 = normaliseDeg(a1 + 180);
        return MathUtils.isEqual(a1, a2, tolerance);
    }
    public static boolean isReverse(float a1, float a2, float tolerance){
        a1 = normaliseDeg(a1 +180);
        a2 = normaliseDeg(a2);

        return MathUtils.isEqual(a1, a2, tolerance);
    }

}
