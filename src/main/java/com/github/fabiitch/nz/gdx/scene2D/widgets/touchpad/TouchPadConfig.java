package com.github.fabiitch.nz.gdx.scene2D.widgets.touchpad;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TouchPadConfig {

    private Texture textureBase;
    private Texture textureKnob;

    private float sizeBase, sizeKnob;

    private Vector2 posInactive;
    private boolean fixedOnTouchDown, fixedOnDrag;


}
