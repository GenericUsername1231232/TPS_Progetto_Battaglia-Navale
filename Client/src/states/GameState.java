package states;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

import client.GameClient;
import game.Game;
import game.attackboard.AttackBoard;
import game.board.Board;

public class GameState extends State {

    private GameClient client;
    private AttackBoard attackBoard;
    private Board board;

    public GameState(Game game) {
        super(game);
        this.client = game.getClient();
        attackBoard = new AttackBoard(game);
        board = new Board(game);
    }

    @Override
    public void update() {
        attackBoard.update();
        if (client.isHitted()) {
            board.hit(client.getHitIndices());
        }
    }

    @Override
    public void click(MouseEvent e) {
        attackBoard.click(e);
    }

    @Override
    public void render(Graphics g) {
        attackBoard.render(g);
        board.render(g);
    }

    public void checkHit(int x, int y) {
        
    }

    public GameClient getCLient() {
        return client;
    }

    
}
