package game.board.ships;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Segmento della nave.
 * Viene automaticamente inserito nelle coordinate della tabella in base alla posizione e rotazione della nave e numero del segmento
 */
public class Segment {
    
    private Ship ship;
    private boolean hit = false;
    private Rectangle bounds;

    public Segment(Ship ship, int x, int y, int width, int height) {
        this.ship = ship;
        bounds = new Rectangle(x, y, width, height);
    }

    public void render(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
    }

    public void hit() {
        hit = true;
    }

    public void reset() {
        hit = false;
    }

    public boolean isHit() {
        return hit;
    }

    public Ship getShip() {
        return ship;
    }
}
