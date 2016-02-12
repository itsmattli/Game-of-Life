package gameV1;

import java.awt.Color;

/**
 * Plant object that can occupy a Cell.
 * 
 * @author Matthew Li
 * @version 1.0
 *
 */
public class Plant extends Space {
    /**
     * Creates a Plant object.
     * 
     * @param cell the Cell that the plant will occupy.
     */
    public Plant(final Cell cell) {
        super(cell, "Plant", Color.green);
    }
}
