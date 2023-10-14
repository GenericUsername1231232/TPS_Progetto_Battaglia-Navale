package game.input;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import states.State;
import states.StateManager;

public class MouseManager implements MouseListener, MouseMotionListener {
    
    private boolean leftPressed, rightPressed;
    private State clickedState;
    private Point mousePos = new Point();

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            leftPressed = true;    
            clickedState = StateManager.getState();
        }
        if (e.getButton() == MouseEvent.BUTTON3)
            rightPressed = true;
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1)
            leftPressed = false;
        
        if (e.getButton() == MouseEvent.BUTTON3)
            rightPressed = false;

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mousePos = new Point(e.getX(), e.getY());
    }
    
    public boolean isLeftPressed(){
        return leftPressed && clickedState == StateManager.getState();
    }

        public boolean isRightPressed(){
        return rightPressed;
    }

    public Point mousePosition() {
        return mousePos;
    }

    //Unused MouseLister/MouseMotionListener methods

    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mouseDragged(MouseEvent e) {}
    
    
    
}
