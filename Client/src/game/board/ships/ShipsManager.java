package game.board.ships;

import java.awt.Graphics;
import java.awt.Point;

import game.board.Board;

public class ShipsManager {
    
    /*
     * SHIP_1, SHIP_2, SHIP_3, e SHIP_4 sono costanti enumerative che rappresentano diversi tipi di nave.
     * Il costruttore ShipType(int length) viene chiamato ogni volta che viene definita una nuova costatne enumerativa, impostanto la lunghezza di quella costante.
     * il metodo getLength() permette di prendere la lunghezza della costante enumerativa.
     */
    private enum ShipType {
        SHIP_1(1), SHIP_2(2), SHIP_3(3), SHIP_4(4);

        private final int length;

        ShipType(int length) {
            this.length = length;
        }

        public int getLength() {
            return length;
        }
    }
                                    // Le navi con cui il giocatore inizia
    private final ShipType[] setup = { ShipType.SHIP_1, ShipType.SHIP_1, ShipType.SHIP_2, ShipType.SHIP_2, ShipType.SHIP_2, ShipType.SHIP_3, ShipType.SHIP_3, ShipType.SHIP_3, ShipType.SHIP_4 };
    private Ship[] ships = new Ship[setup.length];
    private Board board;

    public ShipsManager(Board board) {
        this.board = board;
        for (int i = 0; i < setup.length; i++) {    
            ShipType shipType = setup[i];
            int shipX = board.getXOffset() + (i + 1) * board.getCellSize().width + board.getCellSize().width / 2 - Ship.DEFAULT_SHIP_HEIGHT / 2;
            int shipY = board.getYOffset() + board.getCellSize().height + board.getCellSize().height / 2 - Ship.DEFAULT_SHIP_HEIGHT / 2;
            ships[i] = new Ship(board, shipType.getLength(), new Point(i, 0), shipX, shipY, Ship.ROTATION_SOUTH, board.getCellSize().width, board.getCellSize().height);
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
