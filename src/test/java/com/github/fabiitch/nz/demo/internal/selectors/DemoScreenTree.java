package com.github.fabiitch.nz.demo.internal.selectors;

import com.badlogic.gdx.Screen;
import com.github.fabiitch.nz.java.data.TreeLeaf;

public class DemoScreenTree extends TreeLeaf<DemoScreenTree.Leaf, DemoScreenTree> {
    private String groupName;

    public DemoScreenTree(String groupName, DemoScreenTree parent) {
        super(parent);
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    @Override
    public String toString() {
        return groupName;
    }

    public static class Leaf {
        public Leaf(String name, Class<? extends Screen> screenClass) {
            this.name = name;
            this.screenClass = screenClass;
        }

        private String name;
        private Class<? extends Screen> screenClass;

        @Override
        public String toString() {
            return name;
        }

        public String getName() {
            return name;
        }

        public Class<? extends Screen> getScreenClass() {
            return screenClass;
        }
    }
}
