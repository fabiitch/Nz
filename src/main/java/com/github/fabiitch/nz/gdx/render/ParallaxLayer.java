package com.github.fabiitch.nz.gdx.render;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ParallaxLayer {
    Texture texture;
    public float factor;
    boolean wrapHorizontally;
    boolean wrapVertically;
    Camera camera;
    public float yDecal;

    public ParallaxLayer(Texture texture, float factor, boolean wrapHorizontally, boolean wrapVertically, Camera camera) {
        this.texture = texture;
        this.factor = factor;
        this.wrapHorizontally = wrapHorizontally;
        this.wrapVertically = wrapVertically;
        this.texture.setWrap(
                this.wrapHorizontally ? Texture.TextureWrap.Repeat : Texture.TextureWrap.ClampToEdge,
                this.wrapVertically ? Texture.TextureWrap.Repeat : Texture.TextureWrap.ClampToEdge
        );
        setCamera(camera);
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }


    public void render(SpriteBatch batch) {
        int xOffset = (int) (camera.position.x * factor);
        int yOffset = (int) (camera.position.y * factor);
        TextureRegion region = new TextureRegion(texture);
        region.setRegionX(xOffset % texture.getWidth());
        region.setRegionY(yOffset % texture.getHeight());
        region.setRegionWidth(wrapHorizontally ? (int) camera.viewportWidth : texture.getWidth());
        region.setRegionHeight(wrapVertically ? (int) camera.viewportHeight : texture.getHeight());
        batch.draw(region, camera.position.x - camera.viewportWidth / 2, yDecal + camera.position.y - camera.viewportHeight / 2);
    }
}

