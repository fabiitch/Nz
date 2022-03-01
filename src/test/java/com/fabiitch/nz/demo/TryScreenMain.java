package com.fabiitch.nz.demo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.fabiitch.nz.demo.base.launcher.StarterTestConfig;
import com.fabiitch.nz.demo.quadtree.DemoQuadTreeMove;

import java.lang.reflect.Constructor;

public class TryScreenMain extends Game {
    private static final Class screenTestClass = DemoQuadTreeMove.class;

    private static final boolean SmootDtDebug = true;
    private static final int WITDH = 800;
    private static final int HEIGHT = 500;

    public static void main(String[] args) {
        StarterTestConfig.startLwjgl3(new TryScreenMain(),
                WITDH, HEIGHT);
    }

    @Override
    public void render() {
        float dt = Gdx.graphics.getDeltaTime();
        if (dt > 1)  //for debug breakpoint
            dt = 1 / 60f;

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.log("Test Exit", "Escape pressed");
            System.exit(0);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            Gdx.app.log("Test Reset", "Enter pressed");
            newScreen();
        }

        screen.render(dt);
    }

    private Screen newScreen() {
        if (screen != null)
            screen.dispose();
        try {
            Constructor cons = screenTestClass.getConstructor();
            Object newInstance = cons.newInstance();
            setScreen((Screen) newInstance);
        } catch (Exception e) {
            System.err.println("Cant instance class " + screenTestClass.getSimpleName());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void create() {
        newScreen();
    }
}
