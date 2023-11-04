package game.board;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import game.Game;
import game.attackboard.AttackCell;
import game.board.ships.ShipsManager;
import gfx.Text;

public class Board {
    
    private Game game;
    private ShipsManager shipsManager;

    private int x = 0, y = 0, cellWidth, cellHeight;
    private Cell[][] board = new Cell[10][10];

    public Board(Game game) {
        this.game = game;
        cellWidth = AttackCell.DEFAULT_CELL_WIDTH / 2;
        cellHeight = AttackCell.DEFAULT_CELL_HEIGHT / 2;
        x = AttackCell.DEFAULT_CELL_WIDTH * 11 + 100; 
        y = 3 * game.getResolution().height / 4 - cellHeight * 11 / 2 - 2 * cellWidth + 20;
        
        for (int col = 0; col < 10; col++) 
            for (int row = 0; row < 10; row++) 
                board[col][row] = new Cell(x + cellWidth + col * cellWidth, y + cellHeight + row * cellHeight, cellWidth, cellHeight);
                
        shipsManager = new ShipsManager(this);

    }

    public void update() {
        
    }

    public void render(Graphics g) {
        
        g.setColor(Color.BLACK);
        for (int i = 1; i < 11; i++) {      // Renderizza le celle delle lettere e numeri
            g.drawRect(x + i * cellWidth, y, cellWidth, cellHeight);
            Text.drawString(g, String.valueOf((char)('A' + i - 1)), x + i * cellWidth + cellWidth / 2,
            y + cellHeight / 2, true, Color.BLACK, Game.assets.font25);
            
            g.drawRect(x, y + i * cellHeight, cellWidth, cellHeight);
            Text.drawString(g, String.valueOf((char)('0' + i - 1)), x + cellWidth / 2,
                            y + i * cellHeight + cellHeight / 2, true, Color.BLACK, Game.assets.font25);
        }

        shipsManager.render(g);

        for (Cell[] row : board)
            for (Cell c : row) 
                c.render(g);

    }

    public int hit(Point hitIndices) {
        System.out.println("POSITION " + x + " " + y);
        return board[hitIndices.x][hitIndices.y].hit();
    }

    public Point getXY() {
        return new Point(x, y);
    }

    public Dimension getCellSize() {
        return new Dimension(cellWidth, cellHeight);
    }

    public Cell[][] getBoard() {
        return board;
    }

    public Game getGame() {
        return game;
    }

}
