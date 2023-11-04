package game.attackboard;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import game.Game;
import gfx.Text;

public class AttackBoard {
    
    private int x = 0, y = 0;

    private Game game;
    private AttackCell[][] board = new AttackCell[10][10];

    public AttackBoard(Game game) {
        this.game = game;
 
        x = 25;                                                                         // Imposta la posizione sullo schermo
        y = game.getResolution().height / 2 - AttackCell.DEFAULT_CELL_HEIGHT * 11 / 2;

        for (int col = 0; col < 10; col++) {
            for (int row = 0; row < 10; row++)          // Crea tutte le celle in modo tale che il lati si tocchino e non si sovrappongono
                board[col][row] = new AttackCell(game, 1 + x + col * (AttackCell.DEFAULT_CELL_WIDTH + 1) + AttackCell.DEFAULT_CELL_WIDTH, 
                1 + y + row * (AttackCell.DEFAULT_CELL_HEIGHT + 1) + AttackCell.DEFAULT_CELL_HEIGHT, AttackCell.DEFAULT_CELL_WIDTH, AttackCell.DEFAULT_CELL_HEIGHT);
            
        }
    }

    public void update() {
        for (AttackCell[] col : board)
            for (AttackCell c : col)
                c.update();
        
    }

    public void click(MouseEvent e) {
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++)
                if (board[col][row].click(e)) {
                    String message = Integer.toString(col) + " " + Integer.toString(row);
                    game.getClient().getOutput().println(message);
                    return;
                }
                    
        }
    }

    public void render(Graphics g) {

        g.setColor(Color.BLACK);
        for (int i = 1; i < 11; i++) {      // Renderizza le celle dei numeri e delle lettere
            g.drawRect(x + i * (AttackCell.DEFAULT_CELL_WIDTH + 1), y, AttackCell.DEFAULT_CELL_WIDTH, AttackCell.DEFAULT_CELL_HEIGHT);
            Text.drawString(g, String.valueOf((char)('A' + i - 1)), x + i * (AttackCell.DEFAULT_CELL_WIDTH + 1) + AttackCell.DEFAULT_CELL_WIDTH / 2,
                            y + AttackCell.DEFAULT_CELL_HEIGHT / 2, true, Color.BLACK, Game.assets.font50);


            g.drawRect(x, y + i * (AttackCell.DEFAULT_CELL_HEIGHT + 1), AttackCell.DEFAULT_CELL_WIDTH, AttackCell.DEFAULT_CELL_HEIGHT);
            Text.drawString(g, String.valueOf((char)('0' + i - 1)), x + AttackCell.DEFAULT_CELL_WIDTH / 2,
                            y + i * (AttackCell.DEFAULT_CELL_HEIGHT + 1) + AttackCell.DEFAULT_CELL_HEIGHT / 2, true, Color.BLACK, Game.assets.font50);
        }

        for (AttackCell[] col : board)
            for (AttackCell c : col)
                c.render(g);
    }

    public Game getGame() {
        return game;
    }
}
