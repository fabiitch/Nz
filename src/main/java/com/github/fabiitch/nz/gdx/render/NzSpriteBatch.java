package com.github.fabiitch.nz.gdx.render;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Affine2;
import com.badlogic.gdx.math.Vector2;

public class NzSpriteBatch extends SpriteBatch {


    /// =============     Draw center x,y      ===================
    public void drawCenter(Texture texture, float x, float y, float originX, float originY, float width, float height, float scaleX, float scaleY, float rotation, int srcX, int srcY, int srcWidth, int srcHeight, boolean flipX, boolean flipY) {
        super.draw(texture, x - width / 2, y - height / 2, originX, originY, width, height, scaleX, scaleY, rotation, srcX, srcY, srcWidth, srcHeight, flipX, flipY);
    }

    public void drawCenter(Texture texture, float x, float y, float width, float height, int srcX, int srcY, int srcWidth, int srcHeight, boolean flipX, boolean flipY) {
        super.draw(texture, x - width / 2, y - height / 2, width, height, srcX, srcY, srcWidth, srcHeight, flipX, flipY);
    }

    public void drawCenter(Texture texture, float x, float y, int srcX, int srcY, int srcWidth, int srcHeight) {
        super.draw(texture, x - srcWidth / 2, y - srcHeight / 2, srcX, srcY, srcWidth, srcHeight);
    }

    public void drawCenter(Texture texture, float x, float y, float width, float height, float u, float v, float u2, float v2) {
        super.draw(texture, x - width / 2, y - height / 2, width, height, u, v, u2, v2);
    }

    public void drawCenter(Texture texture, float x, float y) {
        super.draw(texture, x - (float) texture.getWidth() / 2 / 2, y - (float) texture.getHeight() / 2);
    }

    public void drawCenter(Texture texture, float x, float y, float width, float height) {
        super.draw(texture, x - width / 2, y - height / 2, width, height);
    }

    public void drawCenter(TextureRegion region, float x, float y) {
        super.draw(region, x - (float) region.getRegionWidth() / 2, y - (float) region.getRegionHeight() / 2);
    }

    public void drawCenter(Texture texture, float[] spriteVertices, int offset, int count) {
        super.draw(texture, spriteVertices, offset, count);
    }

    public void drawCenter(TextureRegion region, float x, float y, float width, float height) {
        super.draw(region, x - width / 2, y - height / 2, width, height);
    }

    public void drawCenter(TextureRegion region, float x, float y, float originX, float originY, float width, float height, float scaleX, float scaleY, float rotation) {
        super.draw(region, x - width / 2, y - height / 2, originX, originY, width, height, scaleX, scaleY, rotation);
    }

    public void drawCenter(TextureRegion region, float x, float y, float originX, float originY, float width, float height, float scaleX, float scaleY, float rotation, boolean clockwise) {
        super.draw(region, x - width / 2, y - height / 2, originX, originY, width, height, scaleX, scaleY, rotation, clockwise);
    }

    /// =============     Draw Vector pos     ===================
    public void draw(Texture texture, Vector2 pos, float originX, float originY, float width, float height, float scaleX, float scaleY, float rotation, int srcX, int srcY, int srcWidth, int srcHeight, boolean flipX, boolean flipY) {
        super.draw(texture, pos.x, pos.y, originX, originY, width, height, scaleX, scaleY, rotation, srcX, srcY, srcWidth, srcHeight, flipX, flipY);
    }

    public void draw(Texture texture, Vector2 pos, float width, float height, int srcX, int srcY, int srcWidth, int srcHeight, boolean flipX, boolean flipY) {
        super.draw(texture, pos.x, pos.y, width, height, srcX, srcY, srcWidth, srcHeight, flipX, flipY);
    }

    public void draw(Texture texture, Vector2 pos, int srcX, int srcY, int srcWidth, int srcHeight) {
        super.draw(texture, pos.x, pos.y, srcX, srcY, srcWidth, srcHeight);
    }

    public void draw(Texture texture, Vector2 pos, float width, float height, float u, float v, float u2, float v2) {
        super.draw(texture, pos.x, pos.y, width, height, u, v, u2, v2);
    }

    public void draw(Texture texture, Vector2 pos) {
        super.draw(texture, pos.x, pos.y);
    }

    public void draw(Texture texture, Vector2 pos, float width, float height) {
        super.draw(texture, pos.x, pos.y, width, height);
    }

    public void draw(Texture texture, float[] spriteVertices, int offset, int count) {
        super.draw(texture, spriteVertices, offset, count);
    }

    public void draw(TextureRegion region, Vector2 pos) {
        super.draw(region, pos.x, pos.y);
    }

    public void draw(TextureRegion region, Vector2 pos, float width, float height) {
        super.draw(region, pos.x, pos.y, width, height);
    }

    public void draw(TextureRegion region, Vector2 pos, float originX, float originY, float width, float height, float scaleX, float scaleY, float rotation) {
        super.draw(region, pos.x, pos.y, originX, originY, width, height, scaleX, scaleY, rotation);
    }

    public void draw(TextureRegion region, Vector2 pos, float originX, float originY, float width, float height, float scaleX, float scaleY, float rotation, boolean clockwise) {
        super.draw(region, pos.x, pos.y, originX, originY, width, height, scaleX, scaleY, rotation, clockwise);
    }

    /// =============     DrawCenter  Vector pos     ===================

    public void drawCenter(Texture texture, Vector2 pos, float originX, float originY, float width, float height, float scaleX, float scaleY, float rotation, int srcX, int srcY, int srcWidth, int srcHeight, boolean flipX, boolean flipY) {
        super.draw(texture, pos.x - width / 2, pos.y - height / 2, originX, originY, width, height, scaleX, scaleY, rotation, srcX, srcY, srcWidth, srcHeight, flipX, flipY);
    }

    public void drawCenter(Texture texture, Vector2 pos, float width, float height, int srcX, int srcY, int srcWidth, int srcHeight, boolean flipX, boolean flipY) {
        super.draw(texture, pos.x - width / 2, pos.y - height / 2, width, height, srcX, srcY, srcWidth, srcHeight, flipX, flipY);
    }

    public void drawCenter(Texture texture, Vector2 pos, int srcX, int srcY, int srcWidth, int srcHeight) {
        super.draw(texture, pos.x - (float) srcWidth / 2, pos.y - (float) srcHeight / 2, srcX, srcY, srcWidth, srcHeight);
    }

    public void drawCenter(Texture texture, Vector2 pos, float width, float height, float u, float v, float u2, float v2) {
        super.draw(texture, pos.x - width / 2, pos.y - height / 2, width, height, u, v, u2, v2);
    }

    public void drawCenter(Texture texture, Vector2 pos) {
        super.draw(texture, pos.x - (float) texture.getWidth() / 2, pos.y - (float) texture.getHeight() / 2);
    }

    public void drawCenter(Texture texture, Vector2 pos, float width, float height) {
        super.draw(texture, pos.x - width / 2, pos.y - height / 2, width, height);
    }

    public void drawCenter(TextureRegion region, Vector2 pos) {
        super.draw(region, pos.x - (float) region.getRegionWidth() / 2, pos.y - (float) region.getRegionHeight() / 2);
    }

    public void drawCenter(TextureRegion region, Vector2 pos, float width, float height) {
        super.draw(region, pos.x - width / 2, pos.y - height / 2, width, height);
    }

    public void drawCenter(TextureRegion region, Vector2 pos, float originX, float originY, float width, float height, float scaleX, float scaleY, float rotation) {
        super.draw(region, pos.x - width / 2, pos.y - height / 2, originX, originY, width, height, scaleX, scaleY, rotation);
    }

    public void drawCenter(TextureRegion region, Vector2 pos, float originX, float originY, float width, float height, float scaleX, float scaleY, float rotation, boolean clockwise) {
        super.draw(region, pos.x - width / 2, pos.y - height / 2, originX, originY, width, height, scaleX, scaleY, rotation, clockwise);
    }
}
