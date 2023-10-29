package display;

import javax.swing.JFrame;

import game.input.MouseManager;

import java.awt.Canvas;
import java.awt.Dimension;

public class Display {
    
    private String title;
    private int width, height;

    private JFrame frame;
    private Canvas canvas;

    public Display(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;

        createDisplay();
    }

    private void createDisplay() {
        frame = new JFrame(title);                                  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       //Quando viene premuta la X del frame si chiude
        frame.setSize(width, height);                               
        frame.setLocationRelativeTo(null);                        //La finistra compare al centro dello schermo
        frame.setResizable(false);                      
        frame.setVisible(true);

        canvas = new Canvas();                                      
        canvas.setMinimumSize(new Dimension(width, height));        //Imposta la dimensione del canvas
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setFocusable(false);

        frame.add(canvas);
        frame.pack();
    }

    public void addMouseManager(MouseManager mouseManager) {
        frame.addMouseListener(mouseManager);
        frame.addMouseMotionListener(mouseManager);
        canvas.addMouseListener(mouseManager);
        canvas.addMouseMotionListener(mouseManager);
    }

    public JFrame getFrame() {
        return frame;
    }

    public Canvas getCanvas() {
        return canvas;
    }

}