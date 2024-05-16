package com.github.fabiitch.nz.demo.internal;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.github.fabiitch.nz.demo.internal.selectors.DemoScreenScanner;
import com.github.fabiitch.nz.demo.internal.selectors.DemoScreenTree;
import com.github.fabiitch.nz.demo.internal.selectors.TreeDemoSelectorScreen;
import com.github.fabiitch.nz.java.logger.LogStr;

import java.lang.reflect.Constructor;

public class NzDemoApplication extends Game {

    private static final boolean SMOOT_DT_DEBUG = true;
    DemoScreenTree lastTree;
    Screen lastDemoScreen;

    public NzDemoApplication() {

    }

    @Override
    public void create() {
        setSelectorScreen(DemoScreenScanner.scanClasses());
        setSelectorScreen(DemoScreenScanner.scanClasses());
    }

    public void setScreen(Screen newScreen) {
        if (this.screen != null) {
            this.screen.dispose();
        }
        super.setScreen(newScreen);
    }

    public void setDemoScreen(Class<? extends Screen> screenClass) {
        try {
            Constructor cons = screenClass.getConstructor();
            Object newInstance = cons.newInstance();

            System.out.println(LogStr.SEPARATOR);
            System.out.println("SetDemo : " + screenClass.getSimpleName());
            System.out.println(LogStr.SEPARATOR);

            setScreen((Screen) newInstance);
        } catch (Exception e) {
            System.err.println("Cant instance class " + screenClass.getSimpleName());
            e.printStackTrace();
        }
    }

    public void setSelectorScreen(DemoScreenTree tree) {
        TreeDemoSelectorScreen nzDemoSelectorScreen = new TreeDemoSelectorScreen(this, tree);
        lastTree = tree;

        System.out.println(LogStr.SEPARATOR);
        System.out.println("SetSelector : " + tree.getGroupName());
        System.out.println(LogStr.SEPARATOR);

        setScreen(nzDemoSelectorScreen);
    }


    @Override
    public void render() {
        float dt = Gdx.graphics.getDeltaTime();
        if (dt > 1 && SMOOT_DT_DEBUG)  //for debug breakpoint //SMOOT_DT_DEBUG
            dt = 1 / 60f;

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            if (screen instanceof TreeDemoSelectorScreen) {
                Gdx.app.log("Test Exit", "Escape pressed");
                if (lastTree.isRoot()) {
                    System.exit(0);
                } else {
                    setSelectorScreen(lastTree.getParent());
                }
            } else {
                setSelectorScreen(lastTree);
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            Gdx.app.log("Test Reset", "Enter pressed");
            if (!(screen instanceof TreeDemoSelectorScreen)) {
                setDemoScreen(this.screen.getClass());
            }
        }
        screen.render(dt);
    }

}
