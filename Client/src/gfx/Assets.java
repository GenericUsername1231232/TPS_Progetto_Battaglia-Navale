package gfx;

import java.awt.Font;
import java.awt.image.BufferedImage;

public class Assets {
    
    public Font font30, font50, font70;

    public Assets(){
        loadUI();
        loadTextures();
        loadFonts();
    }

    private void loadTextures() {
        
    }

    private void loadUI(){
        
    }

    private void loadFonts() {
        font30 = FontLoader.loadFont("res/fonts/JetBrainsMono.ttf", 30);
        font50 = FontLoader.loadFont("res/fonts/JetBrainsMono.ttf", 50);
        font70 = FontLoader.loadFont("res/fonts/JetBrainsMono.ttf", 70);
    }

}
