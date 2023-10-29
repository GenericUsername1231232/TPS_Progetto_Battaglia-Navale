package gfx;

import java.awt.Font;
import java.awt.image.BufferedImage;

//  Classe in cui vengono salvate tutte le immagini e font utilizzate nel programma
public class Assets {
    
    public BufferedImage SUS;

    public Font font25, font50, font70;

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
        font25 = FontLoader.loadFont("res/fonts/JetBrainsMono.ttf", 25);
        font50 = FontLoader.loadFont("res/fonts/JetBrainsMono.ttf", 50);
        font70 = FontLoader.loadFont("res/fonts/JetBrainsMono.ttf", 70);
    }

}
