package game.board.ships;

import java.awt.Graphics;
import java.awt.Point;

import game.board.Board;

public class Ship {
    
    public static final int ROTATION_EAST = 1,
                            ROTATION_NORTH = 10,
                            ROTATION_WEST = -1,
                            ROTATION_SOUTH = -10;
    
    public static final int SHIP_DESTROYED = 2,
                            SHIP_HITTED = 1,
                            SHIP_MISSED = -1; 

    public static final int DEFAULT_SHIP_WIDTH = 20,
                            DEFAULT_SHIP_HEIGHT = 20;

    private int rotation;
    private Segment[] segments;
    private Board board;
    private int length;
    private Point indices;

    public Ship(Board board, int length, Point position, int x, int y, int rotation, int cellWidth, int cellHeight) {
        this.board = board;
        this.length = length;
        this.rotation = rotation;
        this.indices = position;
        segments = new Segment[length];

        for (int i = 0; i < length; i++) 
            segments[i] = createSegment(x, y, cellWidth, cellHeight, i);
    }

    private Segment createSegment(int x, int y, int cellWidth, int cellHeight, int i) {
        int tempX = 0, tempY = 0;
        switch (rotation) {
            case ROTATION_EAST:
                x = x + cellWidth * i;
                tempX = i;
                break;
            case ROTATION_NORTH:
                y = y - cellHeight * i;
                tempY = -i;
                break;
            case ROTATION_WEST:
                x = x - cellWidth * i;
                tempX = -i;
                break;
            case ROTATION_SOUTH:
                y = y + cellHeight * i;
                tempY = i;
                break;
        }

        Segment s = new Segment(this, x, y, DEFAULT_SHIP_WIDTH, DEFAULT_SHIP_HEIGHT);
        board.getBoard()[indices.x + tempX][indices.y + tempY].setSegment(s);
        return s;
    }

    public int hit(Segment segment) {
        segment.hit();
        if (isDestroyed())
            return SHIP_DESTROYED;
        return SHIP_HITTED;
    }

    private boolean isDestroyed() {
        for (int i = 0; i < length; i++)
            if (!segments[i].isHit())
                return false;
        return true;
    }

    public void render(Graphics g) {
        for (Segment s : segments)
            s.render(g);

    }

}
