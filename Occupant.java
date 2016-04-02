package gameV2;

import java.awt.Color;

/**
 * An abstract class that can be extended to 
 * create object that occupy cells.
 * 
 * @author Matthew Li
 * @version 2.0
 */
public abstract class Occupant {
    
    /**
     * The max value of an RGBa Channel.
     */
    public static final int RGBA_MAX = 255;
    protected Cell cell;
    private Identifier type;
    private Color typeColor;
    
    /**
     * Constructs a Occupant object and paints the Cell the appropriate color.
     * 
     * @param cell the Cell object the Space object resides in.
     * @param type the Enumerated type that the Occupant object is.
     * @param typeColor the color associated with the type of object.
     */
    public Occupant(final Cell cell, final Identifier type, final Color typeColor) {
        this.cell = cell;
        this.type = type;
        this.typeColor = typeColor;
        this.cell.setBackground(typeColor);
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
    public final Identifier getType() {
        return type;
    }
    
    /**
     * Paints the Cell the appropriate typeColor.
     */
    public final void init() {
        double maxLife = 10;
        if (!(getType() == Identifier.EMPTY)) {
            switch (getType()) {
              case PLANT:
                  maxLife = Plant.PLANTLIFESPAN;
                  break;
              case CARNIVORE:
                  maxLife = Carnivore.CARNLIFESPAN;
                  break;
              case OMNIVORE:
                  maxLife = Omnivore.OMNILIFESPAN;
                  break;
              case HERBIVORE:
                  maxLife = Herbivore.HERBLIFESPAN;
                  break;
              default: break;
                
            }
            int red = (int) (typeColor.getRed());
            int blue = (int) (typeColor.getBlue());
            int green = (int) (typeColor.getGreen());
            Color occupantColor = new Color(red, green, blue, 
                    (int)((((Movable)this).getLife() / maxLife) * RGBA_MAX));
            cell.setBackground(occupantColor);
        } else {
            cell.setBackground(typeColor);
        }
    }
    
    /**
     * Creates a reference to the Cell object that currently holds the
     * Space object.
     * 
     * @param cell the Cell object that the Space object is at.
     */
    public final void setCell(final Cell cell) {
        this.cell = cell;
    }
}
