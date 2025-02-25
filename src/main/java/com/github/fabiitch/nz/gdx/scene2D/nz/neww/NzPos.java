package com.github.fabiitch.nz.gdx.scene2D.nz.neww;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.github.fabiitch.nz.java.math.percent.Percentage;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NzPos {
    private boolean dirty = true;

    private float x, y, width = 100, height = 100;
    private boolean squareWidth = false, squareHeight = false;

    private boolean centerX;
    private boolean centerY;
    private boolean glueTop, glueBot, glueLeft, glueRight;

    private Actor reference;

    public void setDirty() {
        this.dirty = true;
    }

    public Rectangle compute(Rectangle parent) {
        float totalWidth = parent.getWidth();
        float totalHeight = parent.getHeight();

        float sizeWidth, siteHeight;
        if (squareWidth) {
            siteHeight = sizeWidth = Percentage.value(width, totalWidth);
        } else if (squareHeight) {
            sizeWidth = siteHeight = Percentage.value(height, totalHeight);
        } else {
            sizeWidth = Percentage.value(width, totalWidth);
            siteHeight = Percentage.value(height, totalHeight);
        }

        float posX, posY;
        if (glueLeft) {
            posX = 0;
        } else if (glueRight) {
            posX = totalWidth - sizeWidth;
        } else if (centerX) {
            posX = Percentage.value(x, totalWidth);
            posX = posX - (sizeWidth / 2);
        } else {
            posX = Percentage.value(x, totalWidth);
        }

        if (glueBot) {
            posY = 0;
        } else if (glueTop) {
            posY = totalHeight - siteHeight;
        } else if (centerY) {
            posY = Percentage.value(y, totalHeight);
            posY = posY - (siteHeight / 2);
        } else {
            posY = Percentage.value(y, totalHeight);
        }

        return null;
    }

    public NzPos(float x, float y, float width, float height) {
        set(x, y, width, height);
    }

    public NzPos(float x, float y, float size) {
        this(x, y, size, size);
    }

    public NzPos set(float x, float y, float width, float height) {
        setDirty();
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        return this;
    }

    public NzPos set(float x, float y, float size) {
        return set(x, y, size, size);
    }


    public NzPos x(float x) {
        setDirty();
        this.x = x;
        unglueHorizontal();
        return this;
    }

    public NzPos y(float y) {
        setDirty();
        this.y = y;
        unglueVertical();
        return this;
    }

    public NzPos pos(float x, float y) {
        x(x);
        y(y);
        return this;
    }

    public NzPos size(float width, float height) {
        width(width);
        height(height);
        squareWidth = squareHeight = false;
        return this;
    }

    public NzPos width(float width) {
        setDirty();
        this.width = width;
        return this;
    }

    public NzPos height(float height) {
        setDirty();
        this.height = height;
        return this;
    }

    public NzPos center() {
        setDirty();
        return center(50, 50);
    }

    public NzPos center(float x, float y) {
        setDirty();
        centerX(x);
        centerY(y);
        return this;
    }

    public NzPos centerX() {
        setDirty();
        return centerX(50);
    }

    public NzPos centerX(float x) {
        setDirty();
        this.centerX = true;
        unglueHorizontal();
        this.x = x;
        return this;
    }

    public NzPos centerY(float y) {
        setDirty();
        this.centerY = true;
        unglueVertical();
        this.y = y;
        return this;
    }

    public NzPos centerY() {
        setDirty();
        return centerY(50);
    }

    public NzPos glueLeft() {
        setDirty();
        this.glueLeft = true;
        this.centerX = false;
        return this;
    }

    public NzPos glueRight() {
        setDirty();
        this.glueRight = true;
        this.centerX = false;
        return this;
    }

    public NzPos glueTopLeft() {
        glueTop();
        glueLeft();
        return this;
    }

    public NzPos glueTopRight() {
        glueTop();
        glueRight();
        return this;
    }

    public NzPos glueBotLeft() {
        glueBot();
        glueLeft();
        return this;
    }

    public NzPos glueBotRight() {
        glueBot();
        glueRight();
        return this;
    }

    public NzPos glueTop() {
        setDirty();
        this.glueTop = true;
        this.centerY = false;
        return this;
    }

    public NzPos glueBot() {
        setDirty();
        this.glueBot = true;
        this.centerY = false;
        return this;
    }

    public NzPos squareWidth(float percent) {
        squareWidth = true;
        squareHeight = false;
        return width(percent);
    }

    public NzPos squareHeight(float percent) {
        squareWidth = false;
        squareHeight = true;
        return height(percent);
    }

    private void unglue() {
        unglueHorizontal();
        unglueVertical();
    }

    private void unglueHorizontal() {
        glueLeft = glueRight = false;
    }

    private void unglueVertical() {
        glueTop = glueBot = false;
    }
}
