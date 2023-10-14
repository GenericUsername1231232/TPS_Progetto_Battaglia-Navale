package states;

import java.awt.Graphics;

import client.Client;
import game.Game;

public class WaitingState extends State {

    private Client client;

    public WaitingState(Game game, Client client) {
        super(game);
        this.client = client;
        client.start();
    }

    @Override
    public void update() {
        if (client.getSocket() == null) {
            System.out.println("NO CONNECTION");
            return;
        }
        System.out.println("CONNECTION");
    }

    @Override
    public void render(Graphics g) {
    }
    
}
