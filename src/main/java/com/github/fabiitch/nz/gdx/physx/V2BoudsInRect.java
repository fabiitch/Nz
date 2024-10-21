package com.github.fabiitch.nz.gdx.physx;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.github.fabiitch.nz.java.math.shapes.utils.RectangleUtils;

public class V2BoudsInRect {

    public Rectangle bounds;

    public V2BoudsInRect(Rectangle bounds) {
        this.bounds = bounds;
    }

    public boolean inside(Rectangle rectangle) {
        return RectangleUtils.overlapsStick(bounds, rectangle);
    }

    public void update(float dt, float posX, float posY, Vector2 velocity) {
        posX += velocity.x * dt;
        posY += velocity.y * dt;
        float maxX = bounds.x + bounds.width;
        float maxY = bounds.y + bounds.height;
        if (posX > maxX && velocity.x > 0) {
            velocity.x = -velocity.x;
        } else if (posX < bounds.x && velocity.x < 0) {
            velocity.x = -velocity.x;
        }
        if (posY > maxY && velocity.y > 0) {
            velocity.y = -velocity.y;
        } else if (posY < bounds.y && velocity.y < 0) {
            velocity.y = -velocity.y;
        }
    }

    public void update(float dt, Vector2 position, Vector2 velocity) {
        update(dt, position.x, position.y, velocity);
    }

}
