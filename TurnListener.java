package ca.bcit.comp2526.a2b;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Listener class for mouse clicks.
 * 
 * @author Matthew Li
 * @version 1.0
 */
public class TurnListener extends MouseAdapter {
    private GameFrame game;
    
    /**
     * Create the listeners.
     * 
     * @param game the JFrame that the listener will apply to.
     */
    public TurnListener(final GameFrame game) {
        this.game = game;
    }
    
    /**
     * Takes a turn when the mouse is clicked.
     */
    public void mouseClicked(MouseEvent arg0) {
        game.takeTurn();
    }
}
