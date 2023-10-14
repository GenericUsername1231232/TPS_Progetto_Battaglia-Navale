package states;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Timer;

import client.Client;
import game.Game;
import gfx.Text;

public class WaitingState extends State {

    private Client client;
    private long timer = 0, now = 0, lastTime = System.currentTimeMillis();
    private String text = "Connecting";


    public WaitingState(Game game, Client client) {
        super(game);
        this.client = client;
        client.start();
    }

    @Override
    public void update() {
        now = System.currentTimeMillis();
        timer += now - lastTime;
        lastTime = now;

        if (timer >= 2000)
            timer = 0;

        if (client.getSocket() == null) 
            return;
        
        
    }

    @Override
    public void render(Graphics g) {

        if (client.getSocket() == null) {
            
            if (timer >= 1500)
                Text.drawString(g, "Connecting...", game.getResolution().width / 2, game.getResolution().height / 2, true, Color.BLACK, Game.assets.font50);
            else if (timer >= 1000)
                Text.drawString(g, "Connecting..", game.getResolution().width / 2, game.getResolution().height / 2, true, Color.BLACK, Game.assets.font50);
            else if (timer >= 500)
                Text.drawString(g, "Connecting.", game.getResolution().width / 2, game.getResolution().height / 2, true, Color.BLACK, Game.assets.font50);
            else 
                Text.drawString(g, "Connecting", game.getResolution().width / 2, game.getResolution().height / 2, true, Color.BLACK, Game.assets.font50);
        }

    }
    
}
