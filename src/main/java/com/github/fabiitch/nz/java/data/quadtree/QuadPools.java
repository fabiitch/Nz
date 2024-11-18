package com.github.fabiitch.nz.java.data.quadtree;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;


public class QuadPools<T> {
    private final Pool<QuadTree<T>> quadPool = new Pool<QuadTree<T>>() {
        @Override
        protected QuadTree<T> newObject() {
            return new QuadTree<>();
        }
    };
    private final Pool<Rectangle> rectPool = new Pool<Rectangle>() {
        @Override
        protected Rectangle newObject() {
            return new Rectangle();
        }
    };
    private final Pool<Array<T>> arrayPool = new Pool<Array<T>>() {
        @Override
        protected Array<T> newObject() {
            return new Array<>();
        }
    };
    private final Pool<Array<Rectangle>> arrayRectPool = new Pool<Array<Rectangle>>() {
        @Override
        protected Array<Rectangle> newObject() {
            return new Array<>();
        }
    };

    public QuadTree<T> getQuad() {
        return quadPool.obtain();
    }

    public void free(QuadTree<T> quadTree) {
        if (quadTree.isSplit()) {
            quadPool.free(quadTree.nw);
            quadPool.free(quadTree.ne);
            quadPool.free(quadTree.se);
            quadPool.free(quadTree.sw);
        }

        quadTree.values.clear();
        quadTree.rectangles.clear();
        quadPool.free(quadTree);
    }

    public Rectangle getRect() {
        return rectPool.obtain();
    }

    public void free(Rectangle rectangle) {
        rectPool.free(rectangle);
    }

    public Array<T> getArray() {
        return arrayPool.obtain();
    }

    public void free(Array<T> array) {
        array.clear();
        arrayPool.free(array);
    }

    public Array<Rectangle> getArrayRectangle() {
        return arrayRectPool.obtain();
    }

    public void freeArrayRect(Array<Rectangle> array) {
        array.clear();
        arrayRectPool.free(array);
    }
}
