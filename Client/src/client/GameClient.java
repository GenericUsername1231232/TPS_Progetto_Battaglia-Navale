package client;

import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.awt.Point;

import states.GameState;

public class GameClient extends Thread {                    // Utilizza un thread per non bloccare il programma durante la connessione
    
    private static final int PORT = 25655;

    private Socket socket = null;
    private BufferedReader in;
    private PrintWriter out;

    private volatile boolean connected = false;         // Variabili per la connessione
    private volatile boolean connecting = true;
                            
    private volatile Point hitIndices = null;           // Variabili per ricevere la mossa dell'avversario
    private volatile boolean hitted = false;

    public synchronized void run() {
        while (true) {
            if (!connected)
                connect();

            if (GameState.canAttack)
                waitAttackInfo();       //Se si ha colpito acqua, nave/distrutto nave
            else
                waitEnemyAttackInfo();  //Le coordinate di dove l'avversario ha colpito
        }
    }

    private void connect() {
        while (!connected) {
         
                connecting = true;
                try {
                    socket = new Socket("localhost", PORT);                                                                  
                    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    out = new PrintWriter(socket.getOutputStream(), true);
                    connected = true;           
                } catch (IOException e) {
                    try {sleep(1300);} catch (InterruptedException e1) {e1.printStackTrace();}   // Solo per estetica
                    connecting = false;
                    System.err.println("CONNECTION FAILED!");
                    try {wait();} catch (InterruptedException e1) {e1.printStackTrace();}               // Aspetta che venga cliccato il bottone per riprovare la connessione
                }
          
            
        }
    }

    private void waitAttackInfo() {
        try {
            String hitInfo = in.readLine();
            GameState.enemyShips.update(hitInfo);
            System.out.println(hitInfo);
        } catch (IOException e) {
            restart();
        }
    }

    private void waitEnemyAttackInfo() {
        try {
            System.out.println("(GameClient) WAITING FOR MOVE"); // Coordinate attacco nemico ricevute come "x y"
            String hitIndicies = in.readLine();
            if (hitIndicies == null) {
                restart();
                return;
            }  
            String[] indices = hitIndicies.split(" ");   // Spara le coordinate divise dallo spazio e le mette in una variabile int
            int x = Integer.parseInt(indices[0]);
            int y = Integer.parseInt(indices[1]);
            hitIndices = new Point(x, y);
            hitted = true;
            try {wait();} catch (InterruptedException e) {e.printStackTrace();}     // Aspetta che il thread di GameState prenda le informazioni e setti canAttack a true
        } catch (IOException e) {
            restart();
        }
    }

    private void restart() {        // Nel caso che l'avversario si disconnette termina la comunicazione,
        try {                       // poi nel GameState verrà settato come stato attuale il WaitingState per attendere un nuovo avversario
            hitted = false; 
            hitIndices = null;
            connected = false;
            socket.close();
            in.close();
            out.close();
        } catch (IOException e) {
            System.exit(1);
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {   // Invia un messaggio al server
        out.println(message);
    }

    public String getResponse() {               // Riceve un messaggio dal server, nel caso di errore nella ricezione ritorna "ERROR"
        try {
            return in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "ERROR";
    }

    public boolean isConnected() {
        return connected;
    }

    public boolean isConnecting() {
        return connecting;
    }

    public BufferedReader getInput() {
        return in;
    }

    public boolean isHitted() {
        return hitted;
    }

    public Point getHitIndices() {
        Point indices = hitIndices;
        hitIndices = null;
        hitted = false;
        return indices;
    }
}
