package com.github.fabiitch.nz.demo;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.github.fabiitch.nz.demo.internal.NzUniqueScreenDemo;
import com.github.fabiitch.nz.demo.screens.shapes.path.CorridorPathDemo;

public class NzDemoScreenLauncher {
    private static final int WITDH = 800;
    private static final int HEIGHT = 500;


    private final static Class<? extends Screen> screenDemoClass = CorridorPathDemo.class;

    public static void main(String[] args) {
        startScreen(screenDemoClass);
    }

    public static void startScreen(Class<? extends Screen> screenDemoClass) {
        Lwjgl3ApplicationConfiguration configuration = new Lwjgl3ApplicationConfiguration();
        configuration.setTitle("Nz Demo");
        configuration.setWindowedMode(WITDH, HEIGHT);
        configuration.setWindowIcon("icons/libgdx128.png", "icons/libgdx64.png", "icons/libgdx32.png", "icons/libgdx16.png");

        NzUniqueScreenDemo demoApplication = new NzUniqueScreenDemo(screenDemoClass);
        new Lwjgl3Application(demoApplication, configuration);
    }
}
