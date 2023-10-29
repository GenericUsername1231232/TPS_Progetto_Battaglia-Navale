package states;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

import client.Client;
import game.Game;
import game.board.AttackBoard;
import game.board.Board;

public class GameState extends State {

    private Client client;
    private AttackBoard attackBoard;
    private Board board;

    public GameState(Game game) {
        super(game);
        this.client = game.getClient();
        attackBoard = new AttackBoard(game);
        board = new Board(game);
        board.setActive(false);
    }

    @Override
    public void update() {
        attackBoard.update();
    }

    @Override
    public void click(MouseEvent e) {
        attackBoard.click(e);
    }

    @Override
    public void render(Graphics g) {
        board.render(g);
        if (!board.isActive())
            attackBoard.render(g);
    }

    public void checkHit(MouseEvent e) {
        
    }

    public Client getCLient() {
        return client;
    }

    
}
