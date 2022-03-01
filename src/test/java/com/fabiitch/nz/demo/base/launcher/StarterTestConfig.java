package com.fabiitch.nz.demo.base.launcher;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class StarterTestConfig {

    //https://github.com/BinaryTweed/libgdx-test-runner

    public static Lwjgl3Application startLwjgl3(ApplicationListener main, int witdh, int height) {
        Lwjgl3ApplicationConfiguration configuration = new Lwjgl3ApplicationConfiguration();
        configuration.setTitle("Tester Lwjgl3");
        configuration.setWindowedMode(witdh, height);
        configuration.setWindowIcon("icons/libgdx128.png", "icons/libgdx64.png", "icons/libgdx32.png", "icons/libgdx16.png");
        return new Lwjgl3Application(main, configuration);
    }

    public static HeadlessApplication startHeadless(ApplicationListener main) {
        HeadlessApplicationConfiguration configuration = new HeadlessApplicationConfiguration();
        configuration.updatesPerSecond = 120;
        return new HeadlessApplication(main, configuration);
    }
}
