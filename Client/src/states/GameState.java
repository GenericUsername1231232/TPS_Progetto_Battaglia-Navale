package states;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;

import client.GameClient;
import game.EnemyShips;
import game.Game;
import game.attackboard.AttackBoard;
import game.board.Board;

public class GameState extends State {
    
    public static volatile boolean canAttack = false;
    public static volatile EnemyShips enemyShips;
    
    private GameClient client;
    private AttackBoard attackBoard;
    private Board board;

    public GameState(Game game) {
        super(game);
        this.client = game.getClient();
        attackBoard = new AttackBoard(game);
        board = new Board(game);
        enemyShips = new EnemyShips();
    }

    @Override
    public void update() {
        if (!client.isConnected()) {        // Se l'avversario di disconnette resetta le tabelle di gioco e ritorna nel WaitingState
            reset();
            StateManager.setState(Game.waitingState);
        }
        
        if (canAttack)                      // Se può attaccare abilita la tabella per l'attacco
            attackBoard.update();

        if (client.isHitted()) {            // Quando l'avversario invia il proprio colpo gli invia le informazione di quel colpo 
            String message = board.hit(client.getHitIndices());
            client.sendMessage(message);
            canAttack = true;
            synchronized (client) {
                client.notify();
            }
        }
    }

    @Override
    public void click(MouseEvent e) {       // Se può attaccare controlla dove si è fatto click sulla tabella di attacco
        if (!canAttack)
            return;

        Point attackIndicies = attackBoard.click(e);
        if (attackIndicies != null) {
            canAttack = false;
            sendMessageToServer(attackIndicies.x, attackIndicies.y);
        }  
    }

    @Override
    public void render(Graphics g) {
        attackBoard.render(g);
        board.render(g);
        enemyShips.render(g);
    }

    private void sendMessageToServer(int col, int row) {        // Invia le coordinate dell'attacco inviate come "x y"
        GameClient client = game.getClient();
        String message = col + " " + row;
        client.sendMessage(message);
    }

    public GameClient getCLient() {
        return client;
    }

    public void reset() {
        board.reset();
        attackBoard.reset();
    }
}
