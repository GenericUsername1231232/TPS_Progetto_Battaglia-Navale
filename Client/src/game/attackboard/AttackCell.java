package game.attackboard;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import game.Game;

public class AttackCell {

    public static final int DEFAULT_CELL_WIDTH = 60,
                            DEFAULT_CELL_HEIGHT = 60;

    private Game game;
    private boolean hovering;
    private Rectangle bounds;

    public AttackCell(Game game, int x, int y, int width, int height) {
        this.game = game;
        bounds = new Rectangle(x, y, width, height);
    }

    public void update() {
        hovering = checkHovering();
    }

    public boolean click(MouseEvent e) {
        return bounds.contains(e.getPoint());
    }

    public void render(Graphics g) {
        
        if (hovering) {        
            g.setColor(new Color(200, 200, 200, 200));
            g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
        }
        
        g.setColor(Color.BLACK);
        g.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);

    }

    private boolean checkHovering() {
        return bounds.contains(game.getMouseManager().mousePosition());
    }
}