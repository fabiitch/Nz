package com.github.fabiitch.nz.demo.internal;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.github.fabiitch.nz.java.logger.LogStr;

import java.lang.reflect.Constructor;

public class NzUniqueScreenDemo extends Game {

    private Class<? extends Screen> screenClass;

    public NzUniqueScreenDemo(Class<? extends Screen> screenClass) {
        this.screenClass = screenClass;
    }

    @Override
    public void create() {
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
}
