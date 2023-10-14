package game.input;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import states.State;
import states.StateManager;

public class MouseManager implements MouseListener, MouseMotionListener {
    
    private boolean leftPressed, rightPressed;
    private State clickedState;
    private Point mousePos = new Point();

    private Point clickPos = new Point(-100, -100);

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
        mousePos.x = e.getX();
        mousePos.y = e.getY();
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

    public boolean isClicked(Rectangle bounds) {
        if (bounds.contains(clickPos)) {
            clickPos.x = -100;
            clickPos.y = -100;
            return true;
        }
        return false;
    }

    //Unused MouseLister/MouseMotionListener methods

    @Override
    public void mouseClicked(MouseEvent e) {
        clickPos.x = e.getX();
        clickPos.y = e.getY();
    }
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mouseDragged(MouseEvent e) {}
    
    
    
}
