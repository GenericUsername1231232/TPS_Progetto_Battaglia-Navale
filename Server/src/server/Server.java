package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

public class Server {

    private ServerSocket  serverSocket;
    private Socket socket;

    public void start() {
        try {
            serverSocket = new ServerSocket(25655);
            socket = serverSocket.accept();
            serverSocket.close();

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(JOptionPane.showInputDialog("SCRIVI!"));

            socket.close();
            out.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}