package gameV2;

import java.awt.Color;

/**
 * Empty Occupant object that can occupy a Cell. 
 * Representing a Cell that is empty.
 * 
 * @author Matthew Li
 * @version 2.0
 */
public class Empty extends Occupant {
    
    /**
     * Creates an Empty object to be stored in a Cell.
     * 
     * @param cell the Cell that the empty object will be in
     */
    public Empty(final Cell cell) {
        super(cell, Identifier.EMPTY, Color.white);
    }
}