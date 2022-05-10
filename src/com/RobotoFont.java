package com;

import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class RobotoFont {
    private Font selectedFont;
    private final int size;

    //setter of size variable
    public RobotoFont(int size){
        this.size = size;
    }

    //setter of selected font so that the method can return
    public void setSelectedFont(Font selectedFont) {
        this.selectedFont = selectedFont;
    }

    //medium Roboto Font
    public Font mediumRoboto(){
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(getClass().getResourceAsStream("/com/assets/font/Roboto-Medium.ttf")));
            Font robotoFont = font.deriveFont(Font.PLAIN, (float) size);
            setSelectedFont(robotoFont);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
        return selectedFont;
    }

    //light Roboto Font
    public Font lightRoboto(){
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(getClass().getResourceAsStream("/com/assets/font/Roboto-Light.ttf")));
            Font robotoFont = font.deriveFont(Font.PLAIN, (float) size);
            setSelectedFont(robotoFont);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
        return selectedFont;
    }

    //bold Roboto Font
    public Font boldRoboto(){
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(getClass().getResourceAsStream("/com/assets/font/Roboto-Bold.ttf")));
            Font robotoFont = font.deriveFont(Font.BOLD, (float) size);
            setSelectedFont(robotoFont);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
        return selectedFont;
    }

}
