package gameV1;

import java.awt.Color;

/**
 * An abstract class that can be extended to 
 * create object that occupy cells.
 * 
 * @author Matthew Li
 * @version 1.0
 */
public abstract class Occupant {
    protected Cell cell;
    protected Identifier type;
    protected Color typeColor;
    
    /*
    public enum Identifier {
        HERBIVORE, PLANT, EMPTY
    }
    */
    
    /**
     * Constructs a Occupant object and paints the Cell the appropriate color.
     * 
     * @param cell the Cell object the Space object resides in.
     */
    public Occupant(Cell cell, Identifier type, Color typeColor) {
        this.cell = cell;
        this.type = type;
        this.typeColor = typeColor;
        init();
    }
    
    /**
     * Get the appropriate color for the type of Space object.
     * 
     * @return the Color of the Space object.
     */
    public final Color getColor() {
        return typeColor;
    }
    
    /**
     * Returns what enumerated type the Occupant is.
     * @return Returns Identifier enumerated type of object.
     */
    public Identifier getType() {
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
