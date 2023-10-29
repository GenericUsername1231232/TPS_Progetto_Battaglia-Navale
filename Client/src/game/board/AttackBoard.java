package game.board;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import game.Game;
import gfx.Text;

public class AttackBoard {
    
    private int x = 0, y = 0;

    private Game game;
    private Cell[][] board = new Cell[10][10];

    public AttackBoard(Game game) {
        this.game = game;
 
        x = 25;                                                                         // Imposta la posizione sullo schermo
        y = game.getResolution().height / 2 - Cell.DEFAULT_CELL_HEIGHT * 11 / 2;

        for (int col = 0; col < 10; col++) {
            for (int row = 0; row < 10; row++)          // Crea tutte le celle in modo tale che il lati si tocchino e non si sovrappongono
                board[col][row] = new Cell(game, 1 + x + col * (Cell.DEFAULT_CELL_WIDTH + 1) + Cell.DEFAULT_CELL_WIDTH, 
                1 + y + row * (Cell.DEFAULT_CELL_HEIGHT + 1) + Cell.DEFAULT_CELL_HEIGHT, Cell.DEFAULT_CELL_WIDTH, Cell.DEFAULT_CELL_HEIGHT);
            
        }
    }

    public void update() {
        for (Cell[] col : board)
            for (Cell c : col)
                c.update();
        
    }

    public void click(MouseEvent e) {
        for (Cell[] col : board)
            for (Cell c : col)
                c.click(e);
    }

    public void render(Graphics g) {

        g.setColor(Color.BLACK);
        for (int i = 1; i < 11; i++) {      // Renderizza le celle dei numeri e delle lettere
            g.drawRect(x + i * (Cell.DEFAULT_CELL_WIDTH + 1), y, Cell.DEFAULT_CELL_WIDTH, Cell.DEFAULT_CELL_HEIGHT);
            Text.drawString(g, String.valueOf((char)('A' + i - 1)), x + i * (Cell.DEFAULT_CELL_WIDTH + 1) + Cell.DEFAULT_CELL_WIDTH / 2,
                            y + Cell.DEFAULT_CELL_HEIGHT / 2, true, Color.BLACK, Game.assets.font50);


            g.drawRect(x, y + i * (Cell.DEFAULT_CELL_HEIGHT + 1), Cell.DEFAULT_CELL_WIDTH, Cell.DEFAULT_CELL_HEIGHT);
            Text.drawString(g, String.valueOf((char)('0' + i - 1)), x + Cell.DEFAULT_CELL_WIDTH / 2,
                            y + i * (Cell.DEFAULT_CELL_HEIGHT + 1) + Cell.DEFAULT_CELL_HEIGHT / 2, true, Color.BLACK, Game.assets.font50);
        }

        for (Cell[] col : board)
            for (Cell c : col)
                c.render(g);
    }

    public Game getGame() {
        return game;
    }
}
