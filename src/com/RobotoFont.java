package com;

import java.awt.*;
import java.io.IOException;

public class RobotoFont {
    private Font selectedFont;
    private int size;

    public RobotoFont(int size){
        this.size = size;
    }

    public Font mediumRoboto(){
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/com/assets/font/Roboto-Medium.ttf"));
            Font robotoFont = font.deriveFont(Font.PLAIN, (float) size);
            setSelectedFont(robotoFont);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return selectedFont;
    }

    public Font lightRoboto(){
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/com/assets/font/Roboto-Light.ttf"));
            Font robotoFont = font.deriveFont(Font.PLAIN, (float) size);
            setSelectedFont(robotoFont);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return selectedFont;
    }

    public Font boldRoboto(){
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/com/assets/font/Roboto-Bold.ttf"));
            Font robotoFont = font.deriveFont(Font.PLAIN, (float) size);
            setSelectedFont(robotoFont);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return selectedFont;
    }

    public void setSelectedFont(Font selectedFont) {
        this.selectedFont = selectedFont;
    }
}
