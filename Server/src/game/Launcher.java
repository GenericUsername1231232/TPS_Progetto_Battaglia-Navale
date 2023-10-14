package game;

import java.io.IOException;

import server.Server;

public class Launcher {
    public static void main(String[] args) {
        new Server().start();
    }
}
