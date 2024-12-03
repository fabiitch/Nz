package com.github.fabiitch.nz.gdx.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.Vector2;
import com.github.fabiitch.nz.java.math.vectors.V3;
import lombok.Getter;
import lombok.Setter;

public class KeyboardCameraController extends InputAdapter {
    private final static int BASE_VELOCITY = 5;

    @Setter
    private float camVelocity;

    private OrthographicCamera camera2D;
    private PerspectiveCamera camera3D;

    @Getter
    private final Vector2 cameraVelocity = new Vector2();
    private final Vector2 tmpVel = new Vector2();

    public KeyboardCameraController(Camera camera, float camVelocity) {
        if (camera instanceof OrthographicCamera) {
            this.camera2D = (OrthographicCamera) camera;
        }
        if (camera instanceof PerspectiveCamera) {
            this.camera3D = (PerspectiveCamera) camera;
        }
        this.camVelocity = camVelocity;
    }

    public KeyboardCameraController(Camera camera) {
        this(camera, BASE_VELOCITY);
    }

    public void update() {
        if(cameraVelocity.isZero())
            return;

        if (camera2D != null)
            V3.add(camera2D.position, tmpVel.set(cameraVelocity).scl(camVelocity));
        else
            V3.add(camera3D.position, tmpVel.set(cameraVelocity).scl(camVelocity));
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.UP) {
            cameraVelocity.add(0, camVelocity);
        } else if (keycode == Input.Keys.DOWN) {
            cameraVelocity.add(0, -camVelocity);
        } else if (keycode == Input.Keys.RIGHT) {
            cameraVelocity.add(camVelocity, 0);
        } else if (keycode == Input.Keys.LEFT) {
            cameraVelocity.add(-camVelocity, 0);
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.UP) {
            cameraVelocity.add(0, -camVelocity);
        } else if (keycode == Input.Keys.DOWN) {
            cameraVelocity.add(0, camVelocity);
        } else if (keycode == Input.Keys.RIGHT) {
            cameraVelocity.add(-camVelocity, 0);
        } else if (keycode == Input.Keys.LEFT) {
            cameraVelocity.add(camVelocity, 0);
        }
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        if (camera2D != null) {
            if (amountY > 0) {
                if (camera2D.zoom < 1)
                    camera2D.zoom = 1;
                else
                    camera2D.zoom += 1;
            } else {
                if (camera2D.zoom > 1)
                    camera2D.zoom -= 1;
                else
                    camera2D.zoom /= 2;
            }
        }
        return false;
    }
}
