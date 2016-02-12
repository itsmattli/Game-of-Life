package gameV1;

import java.awt.Color;

/**
 * An abstract class that can be extended to 
 * create object that occupy cells.
 * 
 * @author Matthew Li
 * @version 1.0
 */
public abstract class Space {
    protected Cell cell;
    protected String type;
    protected Color typeColor;
    
    /**
     * Constructs a Space object and paints the Cell the appropriate color.
     * 
     * @param cell the Cell object the Space object resides in.
     */
    public Space(Cell cell, String type, Color typeColor) {
        this.cell = cell;
        this.type = type;
        this.typeColor = typeColor;
        init();
    }
    
    /**
     * Get the approriate color for the type of Space object.
     * 
     * @return the Color of the Space object.
     */
    public final Color getColor() {
        return typeColor;
    }
    
    /**
     * Returns a string representing the Space object.
     * 
     * @return returns a String representing the Space object,
     */
    public final String getType() {
        return type;
    }
    
    /**
     * Paints the Cell the appropriate typeColor.
     */
    public void init() {
        cell.setBackground(typeColor);
    }
    
    /**
     * Creates a reference to the Cell object that currently holds the
     * Space object.
     * 
     * @param cell the Cell object that the Space object is at.
     */
    public final void setCell(Cell cell) {
        this.cell = cell;
    }
}
