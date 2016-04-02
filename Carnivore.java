package gameV2;

import java.awt.Color;
import java.util.ArrayList;

/**
 * A Carnivore object that can occupy a Cell.
 * 
 * @author Matthew Li
 * @version 1.0
 */
public class Carnivore extends Mobile implements OmniEdible { 
    
    /**
     * The lifespan of an Carnivore born in the game and after it has eaten.
     */
    public static final int CARNLIFESPAN = 3;
    /**
     * The number of mates adjacent required for the Omnivore to give birth.
     */
    public static final int CARNMATECONDITION = 1;
    
    /**
     * The number of empties adjacent required for the Omnivore to give birth.
     */
    public static final int CARNEMPTYCONDITION = 3;
    
    /**
     * The number of empties adjacent required for the Omnivore to give birth.
     */
    public static final int CARNFOODCONDITION = 3;
    
    /**
     * Creates a Carnivore object.
     * 
     * @param cell the Cell that the carnivore will occupy.
     */
    public Carnivore(final Cell cell) {
        super(cell, Identifier.CARNIVORE, Color.magenta);
        life = CARNLIFESPAN;
    }
    
    /**
     * Checks to see if current Carnivore is eligible for reproduction, and calls birth if eligible.
     */
    public boolean birthCheck(final ArrayList<Cell> adjacents) {
        int emptyCounter = 0;
        int mateCounter = 0;
        int foodCounter = 0;
        for (int i = 0; i < adjacents.size(); i++) {
            if (adjacents.get(i).getOccupant().getType() == Identifier.EMPTY) {
                emptyCounter++;
            } else if (adjacents.get(i).getOccupant() instanceof CarnEdible) {
                foodCounter++;
            } else if (adjacents.get(i).getOccupant().getType() == Identifier.CARNIVORE) {
                mateCounter++;
            } 
        }
        return (emptyCounter >= CARNEMPTYCONDITION && foodCounter >= CARNFOODCONDITION 
                && mateCounter >= CARNMATECONDITION);
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
        return (emptyCounter + herbCounter != 0);   
    }

    /**
     * Attempt for the Carnivore object to walk to a particular Cell
     * and the subsequent action depending on what is currently in
     * the Cell the Carnivore is walking towards.
     * 
     * @param moveTo the Cell for the Mobile object to walk to.
     * @return returns Boolean, true if move was successful.
     */
    public boolean walk(final Cell moveTo) {
        boolean success = false;
        if (moveTo.getOccupant() instanceof CarnEdible) {
            life = CARNLIFESPAN;
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
    
