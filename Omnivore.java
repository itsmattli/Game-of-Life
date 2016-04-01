package ca.bcit.comp2526.a2b;

import java.awt.Color;
import java.util.ArrayList;

/**
 * A Omnivore object that can occupy a Cell.
 * 
 * @author Matthew Li
 * @version 1.0
 */
public class Omnivore extends Mobile implements OmniEdible {
    
    /**
     * The number of mates adjacent required for the Omnivore to give birth.
     */
    public static final int OMNIMATECONDITION = 1;
    
    /**
     * The number of empties adjacent required for the Omnivore to give birth.
     */
    public static final int OMNIEMPTYCONDITION = 3;
    
    /**
     * The number of empties adjacent required for the Omnivore to give birth.
     */
    public static final int OMNIFOODCONDITION = 3;
    
    /**
     * The lifespan of an Omnivore born in the game and after it has eaten.
     */
    public static final int OMNILIFESPAN = 2;
    
    /**
     * Creates an Omnivore object.
     * 
     * @param cell the Cell that the omnivore will occupy.
     */
    public Omnivore(final Cell cell) {
        super(cell, Identifier.OMNIVORE, Color.blue);
        life = OMNILIFESPAN;
    }
    
    /**
     * Checks if the Omnivore object is eligible for reproduction, and calls birth if eligible.
     */
    public boolean birthCheck(final ArrayList<Cell> adjacents) {
        int emptyCounter = 0;
        int mateCounter = 0;
        int foodCounter = 0;
        for (int i = 0; i < adjacents.size(); i++) {
            if (adjacents.get(i).getOccupant().getType() == Identifier.EMPTY) {
                emptyCounter++;
            } else if (adjacents.get(i).getOccupant() instanceof OmniEdible) {
                foodCounter++;
            } else if (adjacents.get(i).getOccupant().getType() == Identifier.OMNIVORE) {
                mateCounter++;
            } 
        }
        if (emptyCounter >= OMNIEMPTYCONDITION && foodCounter >= OMNIFOODCONDITION 
                && mateCounter >= OMNIMATECONDITION) {
            return true;
        }
        return false;
    }
    
    /**
     * Creates a new Movable object in an adjacent cell.
     */
    public void birth() {
        ArrayList<Cell> adjacents = cell.getAdjacent();
        if (birthCheck(adjacents)) {
            boolean grown = false;
            Cell toGrow;
            do {
                toGrow = adjacents.get(RandomGenerator.nextNumber(adjacents.size()));
                if (toGrow.getOccupant().getType() == Identifier.EMPTY) {
                    toGrow.placeOccupant(new Omnivore(toGrow));
                    grown = true;
                    setBirthStatus(true);
                }
            } while (!grown);
        }
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
    public boolean typeCountCondition(final int herbCounter, 
            final int plantCounter, final int carnCounter, final int emptyCounter) {
        return (emptyCounter + plantCounter + herbCounter + carnCounter != 0);
    }
    
    /**
     * Attempt for the Omnivore object to walk to a particular Cell
     * and the subsequent action depending on what is currently in
     * the Cell the Omnivore is walking towards.
     * 
     * @param moveTo the Cell for the Mobile object to walk to.
     * @return returns Boolean, true if move was successful.
     */
    public boolean walk(final Cell moveTo) {
        boolean success = false;
        if (moveTo.getOccupant() instanceof OmniEdible) {
            life = OMNILIFESPAN;
            cell.eat(moveTo);
            setMoveStatus(true);
            success = true;
        } else if (moveTo.getOccupant().getType() == Identifier.EMPTY) {
            cell.swapObjects(moveTo);
            life--;
            setMoveStatus(true);
            success = true;
        } else {
            success = false;
        }
        return success;
    }
}
