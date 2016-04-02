package gameV2;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Plant object that can occupy a Cell.
 * 
 * @author Matthew Li
 * @version 2.0
 */
public class Plant extends Movable implements HerbEdible, OmniEdible {
    
    /**
     * The number of mates adjacent required for the plant to give birth.
     */
    public static final int PLANTMATECONDITION = 3;
    
    /**
     * The number of empties adjacent required for the plant to give birth.
     */
    public static final int PLANTEMPTYCONDITION = 1;
    
    /**
     * The lifespan of an Plant.
     */
    public static final int PLANTLIFESPAN = 10;
    
    /**
     * Creates a Plant object.
     * 
     * @param cell the Cell that the plant will occupy.
     */
    public Plant(final Cell cell) {
        super(cell, Identifier.PLANT, Color.green);
        life = PLANTLIFESPAN;
    }

    /**
     * Called when the mouse is clicked, decrements the life of a plant.
     */
    public void makeMove() {
        if (life == 0) {
            cell.die();
        } else {
            life--;
            setMoveStatus(true);
            init();
        }
    }

    /**
     * Checks to see if current Plant is eligible for reproduction, and calls birth if eligible.
     */
    public boolean birthCheck(final ArrayList<Cell> adjacents) {
        int emptyCounter = 0;
        int mateCounter = 0;
        for (int i = 0; i < adjacents.size(); i++) {
            if (adjacents.get(i).getOccupant().getType() == Identifier.EMPTY) {
                emptyCounter++;
            } else if (adjacents.get(i).getOccupant().getType() == Identifier.PLANT) {
                mateCounter++;
            } 
        }
        return (emptyCounter >= PLANTEMPTYCONDITION 
                && mateCounter >= PLANTMATECONDITION); 
    }
    
    /**
     * Creates a new Plant object in an adjacent cell.
     */
    public void birth() {
        ArrayList<Cell> adjacents = cell.getAdjacent();
        if (birthCheck(adjacents)) {
            boolean grown = false;
            Cell toGrow;
            do {
                toGrow = adjacents.get(RandomGenerator.nextNumber(adjacents.size()));
                if (toGrow.getOccupant().getType() == Identifier.EMPTY) {
                    toGrow.placeOccupant(new Plant(toGrow));
                    grown = true;
                    setBirthStatus(true);
                }
            } while (!grown);
        }
    }
}
