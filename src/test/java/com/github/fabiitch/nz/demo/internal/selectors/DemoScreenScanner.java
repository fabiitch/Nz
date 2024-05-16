package com.github.fabiitch.nz.demo.internal.selectors;

import com.badlogic.gdx.Screen;
import org.reflections.Reflections;

import java.lang.reflect.Modifier;
import java.util.Set;

public class DemoScreenScanner {

    public static DemoScreenTree scanClasses() {
        Reflections reflections = new Reflections("com.github.fabiitch.nz.demo");

        final DemoScreenTree ROOT = new DemoScreenTree("Root", null);

        Set<Class<?>> allDemoScreen = reflections.getTypesAnnotatedWith(DemoScreen.class);

        for (Class<?> demoClass : allDemoScreen) {
            if (Modifier.isAbstract(demoClass.getModifiers()))
                continue;
            DemoScreen demoScreen = demoClass.getAnnotation(DemoScreen.class);

            String[] groups = demoScreen.group().split(DemoScreen.SEPARATOR);
            if (groups.length == 0 || demoScreen.group().equals("")) {
                ROOT.addLeaf(new DemoScreenTree.Leaf(demoClass.getClass().getSimpleName(),
                        (Class<? extends Screen>) demoClass));
            } else {
                DemoScreenTree localRoot = ROOT;
                for (String group : groups) {
                    boolean groupFind = false;
                    for (DemoScreenTree child : localRoot.getChildrens()) {
                        if (child.getGroupName().equals(group)) {
                            localRoot = child;
                            groupFind = true;
                            break;
                        }
                    }
                    if (!groupFind) {
                        DemoScreenTree childGroup = new DemoScreenTree(group, localRoot);
                        localRoot.addChild(childGroup);
                        localRoot = childGroup;
                    }
                }
                localRoot.addLeaf(new DemoScreenTree.Leaf(demoClass.getSimpleName(),
                        (Class<? extends Screen>) demoClass));
            }
        }
        ROOT.traceAllPath();
        return ROOT;
    }
}
