package game.board;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;

import game.Game;
import game.board.ships.Segment;
import game.board.ships.Ship;

public class Cell {
    
    private Rectangle bounds;
    private boolean hit = false;
    private Segment segment = null;     // Alcune celle contengono il segmento della nave che gli è stata posizionata sopra

    public Cell(int x, int y, int width, int height) {
        bounds = new Rectangle(x, y, width, height);
    }

    public void render(Graphics g) {
        if (hit) 
            g.drawImage(Game.assets.hitSymbol, bounds.x, bounds.y, bounds.width, bounds.height, null);
        g.setColor(Color.BLACK);
        g.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);
    }

    public String hit() {       // Ritorna le informazione del colpo dell'avversario
        hit = true;
        if (segment != null) 
            return segment.getShip().hit(segment);
        
        return Ship.SHIP_MISSED;
    }

    public void reset() {
        hit = false;
        segment = null;
    }

    public void setSegment(Segment segment) {
        this.segment = segment;
    }
}
