package ca.bcit.comp2526.a2b;

import java.awt.Color;
import java.util.ArrayList;

/**
 * An abstract class that specifies the methods for a class that 
 * is able to take a turn in the game when the mouse is clicked.
 * 
 * @author Matthew Li
 * @version 1.0
 */
public abstract class Movable extends Occupant implements Birthable {
    protected int life;
    private boolean hasMoved = false;
    private boolean hasBirthed = true;
    
    /**
     * Constructor for Movable object.
     * 
     * @param cell the Cell object the Space object resides in.
     * @param type the Enumerated type that the Occupant object is.
     * @param typeColor the color associated with the type of object.
     */
    public Movable(final Cell cell, final Identifier type, final Color typeColor) {
        super(cell, type, typeColor);

    }
    
    /**
     * Takes a turn or move on the game board.
     */
    public abstract void makeMove();
    
    /**
     * Sets the moved status of the Movable object.
     * 
     * @param hasMoved boolean, true if the Movable has moved this turn.
     */
    public final void setMoveStatus(final boolean hasMoved) {
        this.hasMoved = hasMoved;
    }
    
    /**
     * Checks to see whether the Movable object has moved this turn.
     * 
     * @return Returns a boolean, true if the Movable has moved this turn.
     */
    public final boolean getMoveStatus() {
        return hasMoved;
    }
    
    /**
     * Sets the birth status of a Movable object.
     * 
     * @param hasBirthed boolean, true if the Movable object has given birth this turn.
     */
    public final void setBirthStatus(final boolean hasBirthed) {
        this.hasBirthed = hasBirthed;
    }
    
    /**
     * Sets the life of a movable object.
     */
    public final void setLife(final int life) {
        this.life = life;
    }
    
    /**
     * Gets the current life of a movable object.
     */
    public final int getLife() {
        return life;
    }
    
    /**
     * Checks to see whether the Movable object has given birth this turn.
     * 
     * @return Returns a boolean, true if the Movable object has given birth this turn.
     */
    public final boolean getBirthStatus() {
        return hasBirthed;
    }
    
    /**
     * Checks if the Movable object is eligible for reproduction.
     */
    public abstract boolean birthCheck(final ArrayList<Cell> adjacents);
    
    /**
     * Creates a new Movable object in an adjacent cell.
     */
    public abstract void birth();
}
