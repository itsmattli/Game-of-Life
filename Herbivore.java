package gameV1;

import java.awt.Color;
import java.util.ArrayList;

/**
 * A Herbivore object that can occupy a Cell.
 * 
 * @author Matthew Li
 * @version 1.0
 */
public class Herbivore extends Space {
    private int life;
    private boolean hasMoved;
    
    /**
     * Herbivore object that can occupy a Cell.
     * 
     * @param cell the Cell that the object can occupy.
     */
    public Herbivore(final Cell cell) {
        super(cell, "Herbivore", Color.yellow);
        life = 5;
        hasMoved = false;
    }
    
    /**
     * Sets the moved status of the Herbivore.
     * 
     * @param hasMoved boolean, true if the Herbivore has moved this turn.
     */
    public void setMoveStatus(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }
    
    /**
     * Checks to see whether the Herbivore has moved this turn
     * 
     * @return Returns a boolean, true if the Herbivore has moved this turn.
     */
    public boolean getMoveStatus() {
        return hasMoved;
    }
    
    
    /**
     * Attempt for the Herbivore to walk to a particular Cell
     * and the subsequent action depending on what is currently in
     * the Cell the Herbivore is walking towards.
     * 
     * @param moveTo the Cell for the Herbivore to walk to.
     * @return returns Boolean, true if move was successful.
     */
    private boolean walk(final Cell moveTo) {
        if (life == 0) {
            cell.die();
            return true;
        } else {
            if (moveTo.getSpace() instanceof Herbivore) {
                return false;
            } else if (moveTo.getSpace() instanceof Plant) {
                cell.eat(moveTo);
                life = 5;
                return true;
            } else {
                cell.swapObjects(moveTo);
                life--;
                return true;
            }
        }
    }
    
    /**
     * Uses the RandomGenerator to pick an adjacent square for the Herbivore to attempt to walk to.
     */
    public void move() {
        ArrayList<Cell> possMoves;
        boolean moved;
        possMoves = cell.getAdjacent();
        do {
            moved = walk(possMoves.get(RandomGenerator.nextNumber(possMoves.size())));
        } while (!moved);
    }
}
