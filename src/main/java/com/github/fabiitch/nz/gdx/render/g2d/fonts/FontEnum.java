package com.github.fabiitch.nz.gdx.render.g2d.fonts;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FontEnum {
    CD("OpenSans-Bold"),
    Info("OpenSans-Bold");

    private final String fileName;

    public String getExtension() {
        return ".ttf";
    }

    public String getFontName() {
        return fileName;
    }

    public String getFileName() {
        return "fonts/" + fileName + getExtension();
    }

}
