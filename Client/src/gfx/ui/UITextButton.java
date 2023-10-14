package gfx.ui;

import java.awt.Color;
import java.awt.Graphics;

import game.Game;
import gfx.Text;

public class UITextButton extends UIObject {

    private Game game;
    private boolean hovering;
    private String text;
    private ClickListener clicker;

    public UITextButton(Game game, int x, int y, int width, int height, boolean center, String text, ClickListener clicker) {
        super(x, y, width, height);
        this.game = game;
        this.clicker = clicker;
        this.text = text;


        if (center){
            bounds.x -= width / 2;
            bounds.y -= height / 2;
        }
    }

    @Override
    public void update() {

        hovering = checkHovering();

        if (hovering && game.getMouseManager().isLeftPressed())
            clicker.onClick();

    }

    @Override
    public void render(Graphics g) {

        if (hovering) {
            g.setColor(Color.RED);
        } else {
            g.setColor(Color.BLACK);
        }

        for (int thickness = 0; thickness < 7; thickness++) 
            g.drawRect(bounds.x + thickness, bounds.y + thickness, bounds.width - thickness * 2, bounds.height - thickness * 2);
        Text.drawString(g, text, bounds.x + 7, bounds.y + bounds.height - 15, false, Color.BLACK, Game.assets.font30);

    }

    private boolean checkHovering() {
        return bounds.contains(game.getMouseManager().mousePosition());
    }
    
}
