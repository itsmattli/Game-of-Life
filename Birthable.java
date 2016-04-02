package gameV2;

import java.util.ArrayList;

/**
 * Interface that specifies if the class can reproduce
 * and contains methods on reproduction.
 * 
 * @author Matthew Li
 * @version 2.0
 */
public interface Birthable {
    
    /**
     * Checks if the object is eligible for birth.
     */
    boolean birthCheck(final ArrayList<Cell> adjacents);
    
    /**
     * Creates an offspring in a cell from the ArrayList passed in.
     * 
     * @param possMoves ArrayList containing the adjacent cells to the object.
     */
    void birth();
}
