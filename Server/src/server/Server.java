package server;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {

    private ServerSocket  serverSocket;

    public void start() {
        try {
            serverSocket = new ServerSocket(25655);
            serverSocket.accept();
            serverSocket.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}