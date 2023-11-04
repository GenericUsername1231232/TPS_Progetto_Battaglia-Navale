package game.board.ships;

import java.awt.Graphics;
import java.awt.Point;

import game.board.Board;

public class ShipsManager {
    
    public static final int SHIP_1 = 2,
                            SHIP_2 = 3,
                            SHIP_3 = 3,
                            SHIP_4 = 1,
                            TOTAL_SHIPS = SHIP_4 + SHIP_3 + SHIP_2 + SHIP_1;
        
    private int[] setup = {1, 1, 2, 2, 2, 3, 3, 3, 4};
    private Ship[] ships = new Ship[TOTAL_SHIPS];
    private Board board;

    public ShipsManager(Board board) {
        this.board = board;
        for (int i = 0; i < TOTAL_SHIPS; i++) {
            ships[i] = new Ship(board, setup[i], new Point(i, 0), 
                                board.getXY().x + (i + 1) * board.getCellSize().width + board.getCellSize().width / 2 - Ship.DEFAULT_SHIP_HEIGHT / 2, 
                                board.getXY().y + board.getCellSize().height + board.getCellSize().height / 2 - Ship.DEFAULT_SHIP_HEIGHT / 2, 
                                Ship.ROTATION_SOUTH, board.getCellSize().width, board.getCellSize().height);
        }
    }

    public void render(Graphics g) {
        for (Ship s : ships)
            s.render(g);
    }

    public Ship[] getShips() {
        return ships;
    }

    public Board getBoard() {
        return board;
    }

}
