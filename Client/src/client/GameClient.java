package client;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GameClient extends Thread {                    // Utilizza un thread per non bloccare il programma durante la connessione
    
    private static final int PORT = 25655;

    private Socket socket = null;
    private BufferedReader in;
    private PrintWriter out;

    private volatile boolean connected = false;         // Variabili usate per l'animazione nel WaitingState
    private volatile boolean connecting = true;
                            
    private volatile Point hitIndices = null;
    private volatile boolean hitted = false;

    public synchronized void run() {
        connect();

        while (true) {
            waitMove();
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
                    try {sleep(1300);} catch (InterruptedException e1) {e1.printStackTrace();}  // Solo per estetica
                    connecting = false;
                    System.err.println("CONNECTION FAILED!");
                    try {wait();} catch (InterruptedException e1) {e1.printStackTrace();}               // Aspetta che venga cliccato il bottone per riprovare la connessione
                }
          
            
        }
    }

    private void waitMove() {
        try {
            String response = in.readLine();
            if (response == null)   
                return;
            String[] indices = response.split(" ");
            int x = Integer.parseInt(indices[0]);
            int y = Integer.parseInt(indices[1]);
            System.out.printf("%d %d", x, y);
            hitIndices = new Point(x, y);
            hitted = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        out.println(message);
    }

    public String getResponse() {
        try {
            return in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "ERROR";
    }

    public void close() {
        try {
            socket.close();
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isConnected() {
        return connected;
    }

    public boolean isConnecting() {
        return connecting;
    }

    public Socket getSocket() {
        return socket;
    }

    public PrintWriter getOutput() {
        return out;
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
