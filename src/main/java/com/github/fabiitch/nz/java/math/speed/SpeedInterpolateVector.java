package com.github.fabiitch.nz.java.math.speed;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import lombok.Getter;

@Getter
public class SpeedInterpolateVector implements Pool.Poolable {

    private final Vector2 from = new Vector2(), to = new Vector2();
    private final Vector2 velocity = new Vector2();
    private Interpolation interpolation;

    private float totalTime, elapsedTime;

    public SpeedInterpolateVector(Vector2 from, Vector2 to, float totalTime, Interpolation interpolation) {
        this.from.set(from);
        this.to.set(to);
        this.totalTime = totalTime;
        this.interpolation = interpolation;
    }

    public void compute(float dt) {
        elapsedTime += dt;

        float progress = elapsedTime / totalTime;
        progress = Math.min(1, progress);  // Limiter la progression à 1

        float interpolatedProgress = interpolation.apply(progress);
        velocity.set(from).lerp(to, interpolatedProgress);

        // Calculer la nouvelle vitesse nécessaire pour atteindre la position interpolée
        velocity.sub(from).scl(1 / dt);
    }

    @Override
    public void reset() {
        from.setZero();
        to.setZero();
        elapsedTime = 0;
        interpolation = null;
        totalTime = 0;
    }
}
