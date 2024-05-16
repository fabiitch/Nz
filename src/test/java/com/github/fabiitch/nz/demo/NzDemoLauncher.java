package com.github.fabiitch.nz.demo;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.github.fabiitch.nz.demo.internal.NzDemoApplication;

public class NzDemoLauncher {
    private static final int WITDH = 800;
    private static final int HEIGHT = 500;

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration configuration = new Lwjgl3ApplicationConfiguration();
        configuration.setTitle("Nz Demo");
        configuration.setWindowedMode(WITDH, HEIGHT);
        configuration.setWindowIcon("icons/libgdx128.png", "icons/libgdx64.png", "icons/libgdx32.png", "icons/libgdx16.png");

        NzDemoApplication demoApplication = new NzDemoApplication();
        new Lwjgl3Application(demoApplication, configuration);
    }
}
