package com.github.fabiitch.nz.java.math.interpolation;

import com.badlogic.gdx.math.Interpolation;
import lombok.AllArgsConstructor;

/**
 * <a href="https://libgdx.com/wiki/math-utils/interpolation">...</a>
 */
@AllArgsConstructor
public enum Interpolations {
    linear(Interpolation.linear),
    smooth(Interpolation.smooth),
    smooth2(Interpolation.smooth2),
    smoother(Interpolation.smoother),
    fade(Interpolation.fade),
    pow2(Interpolation.pow2),
    slowFast(Interpolation.slowFast),
    pow2InInverse(Interpolation.pow2InInverse),
    pow2OutInverse(Interpolation.pow2OutInverse),
    pow3(Interpolation.pow3),
    pow3In(Interpolation.pow3In),
    pow3Out(Interpolation.pow3Out),
    pow3InInverse(Interpolation.pow3InInverse),
    pow3OutInverse(Interpolation.pow3OutInverse),
    pow4(Interpolation.pow4),
    pow4In(Interpolation.pow4In),
    pow4Out(Interpolation.pow4Out),
    pow5(Interpolation.pow5),
    pow5In(Interpolation.pow5In),
    pow5Out(Interpolation.pow5Out),
    sine(Interpolation.sine),
    sineIn(Interpolation.sineIn),
    sineOut(Interpolation.sineOut),
    exp10(Interpolation.exp10),
    exp10In(Interpolation.exp10In),
    exp10Out(Interpolation.exp10Out),
    exp5(Interpolation.exp5),
    exp5In(Interpolation.exp5In),
    exp5Out(Interpolation.exp5Out),
    circle(Interpolation.circle),
    circleIn(Interpolation.circleIn),
    circleOut(Interpolation.circleOut),
    elastic(Interpolation.elastic),
    elasticIn(Interpolation.elasticIn),
    elasticOut(Interpolation.elasticOut),
    swing(Interpolation.swing),
    swingIn(Interpolation.swingIn),
    swingOut(Interpolation.swingOut),
    bounce(Interpolation.bounce),
    bounceIn(Interpolation.bounceIn),
    bounceOut(Interpolation.bounceOut),
    ;

    private final Interpolation interpolation;


    public static final Interpolations[] VALUES = Interpolations.values();


    public float apply(float a) {
        return interpolation.apply(a);
    }

    public float apply(float start, float end, float a) {
        return interpolation.apply(start, end, a);
    }
}
