package gameV2;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * Cell panels that can be occupied by a Occupant object.
 * 
 * @author Matthew Li
 * @version 1.0
 */
public class Cell extends JPanel {
    private final Point location;
    private final World world;
    private Occupant occupant;
    
    /**
     * Constructor for the cell class.
     * 
     * @param row the row where the cell is on the game frame.
     * @param col the column where the cell is on the game frame.
     * @param world the world object the cell exists on.
     */
    public Cell(final int row, final int col, final World world) {
        this.world = world;
        this.location = new Point(col, row);
        occupant = null;
        init();
    }

    /**
     * Sets up the cells by putting a border around them.
     */
    private void init() {
        setBorder(BorderFactory.createLineBorder(Color.black));
    }    
    
    /**
     * Creates a point object with the coordinates of the Cell
     * 
     * @param row the row the Cell is in.
     * @param col the column the Cell is in.
     * @return Returns a Point object with the coordinates of the Cell.
     */
    public Point getLocation() {
        return location;
    }

    /**
     * Puts an occupant in the desire cell.
     * 
     * @param space the Occupant object to place in the cell.
     */
    public void placeOccupant(final Occupant occupant) {
        this.occupant = occupant;
        this.occupant.setCell(this);
    }
    
    public void noMoves() {
        init();
    }

    /**
     * Returns the current Occupant object occupying the cell.
     * 
     * @return Returns the Occupant object occupying the cell.
     */
    public Occupant getOccupant() {
        return occupant;
    }
    
    /**
     * Herbivore in the cell dies, and is replaced by an Empty object
     * to signify there is nothing in the cell.
     */
    public void die() {
        placeOccupant(new Empty(this));
    }
    
    /**
     * Calls the Occupant object (a Herbivore) in current Cell to eat 
     * the Occupant object (a Plant) located in another cell.
     * 
     * @param moveTo Cell with the Space object to be eaten.
     */
    public void eat(final Cell moveTo) {
        moveTo.placeOccupant(this.getOccupant());
        moveTo.getOccupant().init();
        this.placeOccupant(new Empty(this));
    }
    
    /**
     * Calls the Occupant object (a Herbivore) in current cell, to move
     * to an Cell occupied by another space object (Empty) and swaps
     * the two Occupant object.
     * 
     * @param moveTo the Cell with the Space object to swap with
     */
    public void swapObjects(final Cell moveTo) {
        Occupant temp = moveTo.getOccupant();
        moveTo.placeOccupant(this.getOccupant());
        this.placeOccupant(temp);
        moveTo.getOccupant().init();
        this.getOccupant().init();
    }
    
    /**
     * Gets all adjacent Cell objects to current Cell.
     * 
     * @return returns ArrayList containing all adjacent Cells.
     */
    public ArrayList<Cell> getAdjacent() {
        ArrayList<Cell> adjacents = new ArrayList<Cell>();
        for (int row = (location.y - 1); row <= (location.y + 1); row++) {
            for (int col = (location.x - 1); col <= (location.x + 1); col++) {
                if ((col >= 0 && col <= (world.getColumnCount() - 1)) 
                        && (row >= 0 && row <= (world.getRowCount() - 1))) {
                    if (world.getCellAt(row, col) != this) {
                        adjacents.add(world.getCellAt(row,col));
                    }
                }
            }
        }
        return adjacents;
    }
}