package com.fabiitch.nz.screens.quadtree;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.fabiitch.nz.data.quadtree.QuadTree;
import com.fabiitch.nz.utils.NzUtils;

public abstract class BaseQuadTreeTestScreen {
    public QuadTree<String> quad;

    public Camera camera;

    public BaseQuadTreeTestScreen() {
        this.camera = new OrthographicCamera();
        this.quad = new QuadTree<>(NzUtils.screenAsRectangle(camera, true),5,5);

        quad.add("toot",new Rectangle());
    }



}
