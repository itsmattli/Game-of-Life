package ca.bcit.comp2526.a2b;

import java.awt.Color;
import java.util.ArrayList;

/**
 * An abstract class that specifics methods for object that are mobile
 * and are physically able to move.
 * 
 * @author Matthew Li
 *
 */
public abstract class Mobile extends Movable {
    
    /**
     * Constructor for the Mobile object.
     * 
     * @param cell the Cell object the Space object resides in.
     * @param type the Enumerated type that the Occupant object is.
     * @param typeColor the color associated with the type of object.
     */
    public Mobile(final Cell cell, final Identifier type, final Color typeColor) {
        super(cell, type, typeColor);
    }
    
    /**
     * Takes a turn or move on the game board, and calls the walk() method
     * for object of the Mobile class.
     * 
     */
    public void makeMove() {
        boolean moved;
        ArrayList<Cell> possMoves = cell.getAdjacent();
        if (life == 0) {
            cell.die();
        } else if (possMoveCheck(possMoves)) {
            do {
                moved = walk(possMoves.get(RandomGenerator.nextNumber(possMoves.size())));
            } while (!moved);
        } else {
            life--;
            setMoveStatus(true);
            cell.noMoves();
        }
    }
    
    /**
     * Make the objects move based on the class's requirements for movement.
     * 
     * @param moveTo the cell to move to
     * @return boolean if move was successful
     */
    public abstract boolean walk(final Cell moveTo);
    
    /**
     * Checks to see if the ArrayList contains a valid move.
     * @param possMoves the Arraylist of adjacent cells containing possible moves.
     * @return Returns boolean, true if ArrayList contains a valid move.
     */
    private boolean possMoveCheck(final ArrayList<Cell> possMoves) {
        int herbCounter = 0;
        int emptyCounter = 0;
        int plantCounter = 0;
        int carnCounter = 0;
        for (int i = 0; i < possMoves.size(); i++) {
            switch (possMoves.get(i).getOccupant().getType()) {
              case HERBIVORE:
                  herbCounter++;  
                  break;
              case PLANT:
                  plantCounter++;
                  break;
              case CARNIVORE:
                  carnCounter++;
                  break;
              case EMPTY:
                  emptyCounter++;
                  break;
              default: break;
            }
        }
        return typeCountCondition(herbCounter, plantCounter, carnCounter, emptyCounter);
    } 
    
    /**
     * Compares the counts of specific occupants in an array 
     * to match what is a valid move for the type.
     * @param herbCounter number of herbivores in the adjacent cells
     * @param plantCounter number of plants in adjacent cells
     * @param carnCounter number of carnivores in adjacent cells
     * @param emptyCounter number of empties in adjacent cells
     * @return Returns boolean, true if there is the right number 
     *     of occupants around for the current occupant to make a valid move
     */
    public abstract boolean typeCountCondition(final int herbCounter, 
            final int plantCounter, final int carnCounter, final int emptyCounter);
}
