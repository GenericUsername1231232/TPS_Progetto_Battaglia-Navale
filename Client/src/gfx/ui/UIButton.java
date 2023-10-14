package gfx.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.Game;

public class UIButton extends UIObject {

    private Game game;
    private boolean hovering;
    private BufferedImage[] images;
    private ClickListener clicker;

    public UIButton(Game game, int x, int y, int width, int height, boolean center, BufferedImage[] images, ClickListener clicker) {
        super(x, y, width, height);
        this.game = game;
        this.images = images;
        this.clicker = clicker;

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
            g.drawImage(images[1], bounds.x, bounds.y, bounds.width, bounds.height, null);
        } else {
            g.drawImage(images[0], bounds.x, bounds.y, bounds.width, bounds.height, null);
        }

    }

    private boolean checkHovering() {
        return bounds.contains(game.getMouseManager().mousePosition());
    }
    
}
