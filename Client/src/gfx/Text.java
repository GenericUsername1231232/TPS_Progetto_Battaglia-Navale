package gfx;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class Text {

	public static void drawString(Graphics g, String text, int xPos, int yPos, boolean center, Color c, Font font){
		g.setColor(c);
		g.setFont(font);
		int x = xPos;
		int y = yPos;

        String[] lines = text.split("\n");

        for (String line : lines) {
            if (center) {
                FontMetrics fm = g.getFontMetrics(font);
                x = xPos - fm.stringWidth(line) / 2;
                y = yPos - fm.getHeight() / 2 + fm.getAscent();
            }
            g.drawString(line, x, y);
            yPos += g.getFontMetrics(font).getHeight();
        }

		
	}

}
