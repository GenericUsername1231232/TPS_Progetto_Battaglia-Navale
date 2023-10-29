package game.board;

import java.awt.Color;
import java.awt.Graphics;

import game.Game;
import gfx.Text;

public class Board {
    
    private Game game;
    private Ship[] ships;

    private int x = 0, y = 0, cellWidth, cellHeight;
    private boolean[] hit = new boolean[100];
    private boolean active;

    public Board(Game game) {
        this.game = game;

        ships = new Ship[4];
        ships[0] = new Ship(2, 0, Ship.ROTATION_EAST);
        ships[1] = new Ship(4, 33, Ship.ROTATION_NORTH);
        ships[2] = new Ship(3, 7, Ship.ROTATION_WEST);
        ships[3] = new Ship(5, 9, Ship.ROTATION_SOUTH);

    }

    public void update() {
        
    }

    public void render(Graphics g) {
        
        g.setColor(Color.BLACK);
        for (int i = 1; i < 11; i++) {
            g.drawRect(x + i * cellWidth, y, cellWidth, cellHeight);
            Text.drawString(g, String.valueOf((char)('A' + i - 1)), x + i * cellWidth + cellWidth / 2,
            y + cellHeight / 2, true, Color.BLACK, Game.assets.font25);
            
            g.drawRect(x, y + i * cellHeight, cellWidth, cellHeight);
            Text.drawString(g, String.valueOf((char)('0' + i - 1)), x + cellWidth / 2,
                            y + i * cellHeight + cellHeight / 2, true, Color.BLACK, Game.assets.font25);
        }
        
        for (int i = 0; i < 100; i++) {
            if (hit[i]) {
                g.setColor(Color.RED);
                g.fillRect(x + cellWidth + (i % 10) * cellWidth,
                y + cellHeight + (i / 10) * cellHeight, cellWidth, cellHeight);
            }
            else {
                g.setColor(Color.BLACK);
                g.drawRect(x + cellWidth + (i % 10) * cellWidth,
                y + cellHeight + (i / 10) * cellHeight, cellWidth, cellHeight);
            }
        }
        for (Ship s : ships)
            s.render(g);
    }

    public void setActive(boolean active) {
        this.active = active;
        if (!active) {
            cellWidth = Cell.DEFAULT_CELL_WIDTH / 2;
            cellHeight = Cell.DEFAULT_CELL_HEIGHT / 2;

            x = Cell.DEFAULT_CELL_WIDTH * 11 + 100; 
            y = 3 * game.getResolution().height / 4 - cellHeight * 11 / 2 - 2 * cellWidth + 20;
        } else {
            cellWidth = Cell.DEFAULT_CELL_WIDTH;
            cellHeight = Cell.DEFAULT_CELL_HEIGHT;

            x = 25;
            y = game.getResolution().height / 2 - cellHeight * 11 / 2;
        }
        for (Ship s : ships)
            s.setActive(active, x, y, cellWidth, cellHeight);
    }

    public boolean isActive() {
        return active;
    }

}
