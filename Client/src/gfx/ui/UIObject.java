package gfx.ui;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class UIObject {
    
    protected Rectangle bounds;

    public UIObject(int x, int y, int width, int height){
        bounds = new Rectangle(x, y, width, height);
    }
    
    public abstract void update();
    public abstract void render(Graphics g);

}
