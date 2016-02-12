package gameV1;

import java.awt.Color;

/**
 * Empty Space object that can occupy a Cell. 
 * Representing a Cell that is empty.
 * 
 * @author Matthew Li
 * @version 1.0
 */
public class Empty extends Space {
    
    /**
     * Creates an Empty object to be stored in a Cell.
     * 
     * @param cell the Cell that the empty object will be in
     */
    public Empty(final Cell cell) {
        super(cell, "Empty", Color.white);
    }
}