//package com.github.fabiitch.nz.gdx.scene2D.listener;
//
//import com.badlogic.gdx.graphics.g2d.BitmapFont;
//import com.badlogic.gdx.graphics.g2d.GlyphLayout;
//import com.badlogic.gdx.scenes.scene2d.ui.Label;
//import lombok.experimental.UtilityClass;
//
///**
// * Bof
// */
//@UtilityClass
//public class LabelUtils {
//
//    private final static GlyphLayout glyphLayout = new GlyphLayout();
//    public static void scaleToFit(Label label) {
//// Ajustement automatique du fontScale
//        BitmapFont font = label.getStyle().font;
//        glyphLayout.setText(font, label.getText());
//
//        float availableWidth = label.getWidth();
//
//// ⚡ On scale vers le bas uniquement
//        float scale = availableWidth / glyphLayout.width;
//        if (scale < 1f) {
//            label.setFontScale(scale);
//        } else {
//            label.setFontScale(1f); // on garde la taille normale sinon
//        }
//        System.out.println(scale);
//        label.setFontScale(scale);
//    }
//
//    public static float getScaleToFit(Label label) {
//        float availableWidth = label.getWidth();
//        float scale = availableWidth / glyphLayout.width;
//        return scale;
//    }
//}
