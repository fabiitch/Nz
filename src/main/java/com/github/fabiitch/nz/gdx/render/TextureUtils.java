package com.github.fabiitch.nz.gdx.render;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TextureUtils {

    public static TextureRegionDrawable getDrawable(Texture texture) {
        return new TextureRegionDrawable(texture);
    }

    public static Texture createVerticalGradient(int width, int height, Color top, Color bottom) {
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);

        for (int y = 0; y < height; y++) {
            float t = (float) y / (height - 1);
            float r = MathUtils.lerp(bottom.r, top.r, t);
            float g = MathUtils.lerp(bottom.g, top.g, t);
            float b = MathUtils.lerp(bottom.b, top.b, t);
            float a = MathUtils.lerp(bottom.a, top.a, t);
            pixmap.setColor(r, g, b, a);
            pixmap.drawLine(0, y, width, y);
        }

        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        return texture;
    }


    public static Texture getColorTexture(Color color) {
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fill();
        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        return texture;
    }
    public static Texture getColorTexture(Color color, float alpha) {
        return getColorTexture(ColorUtils.get(color, alpha));
    }

    public static Texture screenshotActor(Actor actor, Stage stage) {
        return screenshotActor(actor, stage, null);
    }

    public static Texture screenshotActor(Actor actor, Stage stage, Texture reuse) {
        Viewport viewport = stage.getViewport();
        Camera camera = stage.getCamera();

        // 1) Convertir les coordonnées ACTOR -> STAGE
        Vector2 blStage = new Vector2(0, 0);
        actor.localToStageCoordinates(blStage);

        Vector2 trStage = new Vector2(actor.getWidth(), actor.getHeight());
        actor.localToStageCoordinates(trStage);

        // 2) Projeter en coordonnées ECRAN
        Vector3 blScreen = new Vector3(blStage.x, blStage.y, 0);
        Vector3 trScreen = new Vector3(trStage.x, trStage.y, 0);

        camera.project(
                blScreen,
                viewport.getScreenX(),
                viewport.getScreenY(),
                viewport.getScreenWidth(),
                viewport.getScreenHeight()
        );
        camera.project(
                trScreen,
                viewport.getScreenX(),
                viewport.getScreenY(),
                viewport.getScreenWidth(),
                viewport.getScreenHeight()
        );

        int x = Math.round(Math.min(blScreen.x, trScreen.x));
        int y = Math.round(Math.min(blScreen.y, trScreen.y));
        int w = Math.max(1, Math.round(Math.abs(trScreen.x - blScreen.x)));
        int h = Math.max(1, Math.round(Math.abs(trScreen.y - blScreen.y)));

        // 3) FLUSH du batch avant le readPixels
        stage.getBatch().flush();

        // 4) Lire le framebuffer en Pixmap
        Pixmap pixmap = Pixmap.createFromFrameBuffer(x, y, w, h);

        // 5) Flip vertical (obligatoire)
        Pixmap flipped = flipPixmapY(pixmap);
        pixmap.dispose();

        // 6) ===============================
        //     REUSE DE LA TEXTURE SI POSSIBLE
        //    ===============================
        if (reuse != null) {
            if (reuse.getWidth() == w && reuse.getHeight() == h) {
                // Blit dans la texture existante
                reuse.draw(flipped, 0, 0);
                flipped.dispose();
                return reuse;
            }

            // Sinon → taille incompatible → recréer
            reuse.dispose();
        }

        // 7) Créer une nouvelle texture
        Texture tex = new Texture(flipped);
        flipped.dispose();

        return tex;
    }

    public static Pixmap flipPixmapY(Pixmap src) {
        int w = src.getWidth();
        int h = src.getHeight();

        Pixmap flipped = new Pixmap(w, h, src.getFormat());

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                flipped.drawPixel(x, h - y - 1, src.getPixel(x, y));
            }
        }

        return flipped;
    }


}
