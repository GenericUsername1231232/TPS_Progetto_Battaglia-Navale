package game.board;

import java.awt.Color;
import java.awt.Graphics;

public class Ship {
    
    public static final int ROTATION_EAST = 1,
                            ROTATION_NORTH = 10,
                            ROTATION_WEST = -1,
                            ROTATION_SOUTH = -10;

    public static final int DEFAULT_SHIP_WIDTH = 40,
                            DEFAULT_SHIP_HEIGHT = 40;

    private int length;
    private int headPosition;
    private int rotation;
    private boolean[] hitted;

    private int x, y, width, height;
    private int cellWidth, cellHeight;

    public Ship(int length, int headPosition, int rotation) {
        this.length = length;
        this.headPosition = headPosition;
        this.rotation = rotation;
        hitted = new boolean[length];
        for (int i = 0; i < length; i++)
            hitted[i] = false;
    }

    public int checkHit(int hit) {
        for (int i = 0; i < length; i++)
            if (hit == (headPosition + rotation * i)) {
                if (isDestroyed())
                    return 2;
                return 1;
            }
        
        return 0;
    }

    private boolean isDestroyed() {
        for (int i = 0; i < length; i++)
            if (!hitted[i])
                return false;
        return true;
    }

    public void render(Graphics g) {
        int tempX = 0, tempY = 0;
        for (int i = 0; i < length; i++) {
            switch (rotation) {
                case ROTATION_EAST:
                    tempX = x + cellWidth * i;
                    tempY = y;
                    break;
                case ROTATION_NORTH:
                    tempX = x;
                    tempY = y - cellHeight * i;
                    break;
                case ROTATION_WEST:
                    tempX = x - cellWidth * i;
                    tempY = y;
                    break;
                case ROTATION_SOUTH:
                    tempX = x;
                    tempY = y + cellHeight * i;
                    break;
            }
            
            g.setColor(Color.GRAY);
            g.fillRect(tempX, tempY, width, height);
        }

    }

    public void setActive(boolean active, int boardX, int boardY, int cellWidth, int cellHeight) {
        if (active) {
            width = DEFAULT_SHIP_WIDTH;
            height = DEFAULT_SHIP_HEIGHT;
            
        } else {
            width = DEFAULT_SHIP_WIDTH / 2;
            height = DEFAULT_SHIP_HEIGHT / 2;
        }
        
        this.cellWidth = cellWidth;
        this.cellHeight = cellHeight;
        x = boardX + (headPosition % 10 + 1) * cellWidth + cellWidth / 2 - width / 2;
        y = boardY + (headPosition / 10 + 1) * cellHeight + cellHeight / 2 - height / 2;
    }

}
