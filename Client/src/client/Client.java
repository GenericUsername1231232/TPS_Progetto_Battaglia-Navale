package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends Thread {
    
    private Socket socket = null;
    private BufferedReader in;
    private PrintWriter out;

    private volatile boolean connected = false,
                             connecting = true;

    public Client() {

    }

    public synchronized void run() {
        
        while (!connected) {
            try {
                connecting = true;
                socket = new Socket("localhost", 25655);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                connected = true;
            } catch (Exception e) {
                try {sleep(1300);} catch (InterruptedException e1) {e1.printStackTrace();}
                connecting = false;
                e.printStackTrace();
                try {wait();} catch (InterruptedException e1) {e1.printStackTrace();}
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
