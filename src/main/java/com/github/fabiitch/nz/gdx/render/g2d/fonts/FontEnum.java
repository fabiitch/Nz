package com.github.fabiitch.nz.gdx.render.g2d.fonts;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum FontEnum {
    CD("OpenSans-Bold"),
    Info("OpenSans-Bold");

    private final String fileName;

    public String getFileName() {
        return "fonts/" + fileName + ".ttf";
    }

}
