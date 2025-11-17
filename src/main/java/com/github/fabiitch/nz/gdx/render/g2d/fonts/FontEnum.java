package com.github.fabiitch.nz.gdx.render.g2d.fonts;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FontEnum {
    CD("OpenSans-Bold"),
    Info("OpenSans-Bold"),

    Title ("Exo2-Bold"),
    SubTitle ("Exo2-SemiBold"),
    Text ("Exo2-Regular"),

    ;

    private final String fileName;

    public String getExtension() {
        return ".ttf";
    }

    public String getFontName() {
        return fileName;
    }

    public String getFileName() {
        return "shared/fonts/" + fileName + getExtension();
    }

}
