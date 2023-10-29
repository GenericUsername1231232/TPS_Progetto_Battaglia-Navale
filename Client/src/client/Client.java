package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends Thread {                    // Utilizza un thread per non bloccare il programma durante la connessione
    
    private Socket socket = null;
    private BufferedReader in;
    private PrintWriter out;

    private volatile boolean connected = false,         // Variabili usate per l'animazione nel WaitingState
                             connecting = true;

    public synchronized void run() {
        
        while (!connected) {
            try {
                connecting = true;
                socket = new Socket("localhost", 25655);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                connected = true;           
            } catch (Exception e) {
                try {sleep(1300);} catch (InterruptedException e1) {e1.printStackTrace();}  // Solo per estetica
                connecting = false;
                System.err.println("CONNECTION FAILED!");
                try {wait();} catch (InterruptedException e1) {e1.printStackTrace();}               // Aspetta che venga cliccato il bottone per riprovare la connessione
            }

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
}
