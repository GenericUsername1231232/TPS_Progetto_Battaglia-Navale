package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import client.Client;
import display.Display;
import states.State;
import states.*;

public class Game {
    
    public static boolean running = true;
    public static State waitingState;

    private String title;
    private int width, height;
    private Display display;
    private BufferStrategy bs;
    private Graphics g;

    private Client client;
    
    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
    }
    
    private void init() {
        display = new Display(title, width, height);
        display.getCanvas().createBufferStrategy(2);    //Crea una strategia di buffer del canvas
        bs = display.getCanvas().getBufferStrategy();              //Assegna la strategia di buffer
        
        client = new Client();
        waitingState = new WaitingState(this, client);

        StateManager.setState(waitingState);
    }

    public void start() {
        init();
        
        int fps = 24;
        float timePerTick = 1000000000 / fps;
        float delta = 0;
        long now;
        long lastTime = System.nanoTime();

        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            lastTime = now;

            if (delta >= 1) {       //entra nell'if $fps volte al secondo
                delta = 0;
                
                update();
                render();
            }
        }
    }

    private void update() {

        if (StateManager.getCurrentState() != null)
            StateManager.getCurrentState().update();

    }
    
    private void render() {                
        
        g = bs.getDrawGraphics();                                  //Assegna l'oggetto per disegnare sul canvas
        g.clearRect(0, 0 , width, height);

        // DRAW HERE

        g.setColor(Color.GREEN);
        g.fillRect(0, 0, width, height);

        g.setColor(Color.BLUE);
        g.fillRect(0, 0, width / 2, height / 2);

        //

        bs.show();
        g.dispose();
    }

}
