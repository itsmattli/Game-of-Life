package gameV2;

/**
 * World that initializes the cells and occupants of the game.
 * 
 * @author Matthew Li
 * @version 2.0
 */
public class World {
    
    /**
     * An int representing the max value to be passed to the 
     * static nextNumber method of the RandomGenerator class 
     * so that it will return value between 0 and 99 inclusive.
     */
    public static final int GENERATORMAX = 99;
    
    /**
     * The number representing the lower bound that will 
     * generate things at a 20% chance based on GENERATORMAX.
     */
    public static final int EIGHTY = 80;
    
    /**
     * The number representing the lower bound that will 
     * generate things at a 20% chance based on GENERATORMAX
     * and EIGHTY.
     */
    public static final int SIXTY = 60;
    
    /**
     * The number representing the lower bound that will 
     * generate things at a 10% chance based on GENERATORMAX
     * and SIXTY.
     */
    public static final int FIFTY = 50;
    
    /**
     * The number representing the lower bound that will 
     * generate things at a 10% chance based on GENERATORMAX
     * and FIFTY.
     */
    public static final int FORTY = 40;
    
    private final Cell[][] allCells;
    private int rows;
    private int cols;
    
    /**
     * Constructs the world containing the cells in the game frame.
     * 
     * @param rows Number of rows on the game frame.
     * @param cols Number of columns on the game frame.
     */
    public World(final int rows, final int cols) {
        this.allCells = new Cell[rows][cols];
        this.rows = rows;
        this.cols = cols;
    }
    
    /**
     * Gets the number of rows for the game.
     * 
     * @return Returns int representing number of rows.
     */
    public int getRowCount() {
        return rows;
    }

    /**
     * Gets the number of columns for the game.
     * 
     * @return Returns int representing number of columns.
     */
    public int getColumnCount() {
        return cols;
    }
    
    /**
     * Creates the cells and places them on the game frame.
     * Also places a random Space object in each of the Cells
     * determined by the RandomGenerator.
     */
    public void init() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                allCells[row][col] = new Cell(row, col, this);
            }
        }
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                randomSpace(allCells[row][col]);
            }
        }    
    }
    
    /**
     * Creates a random Occupant object to occupy a Cell
     * based on the RandomGenerator value.
     * 
     * @param cell the Cell that will contain the Space object.
     * @return Space object to be stored in the cell.
     */
    private void randomSpace(final Cell cell) {
        Occupant space;
        int gen = RandomGenerator.nextNumber(GENERATORMAX);
        if (gen >= EIGHTY) {
            space = new Herbivore(cell);
        } else if  (gen >= SIXTY) {
            space = new Plant(cell);
        } else if (gen >= FIFTY) {
            space = new Carnivore(cell);
        } else if (gen >= FORTY) {
            space = new Omnivore(cell);
        } else {
            space = new Empty(cell);
        }
        cell.placeOccupant(space);
    }
    
    /**
     * Returns the cell at the row and column specified.
     * 
     * @param row the row of the cell.
     * @param col the column of the cell.
     * @return Returns a Cell object at a specified row and column.
     */
    public Cell getCellAt(final int row, final int col) {
        return allCells[row][col];
    }
    
    /**
     * Checks all the Cells for Movable objects and gets them to move if
     * they haven't already moved on this mouse click. Then calls resetMoves
     * to allow them to move on the next turn.
     */
    public void takeTurn() {
        for (Cell[] innerArray: allCells) {
            for (Cell element: innerArray) {
                if ((element.getOccupant() instanceof Movable) 
                        && !((Movable) element.getOccupant()).getMoveStatus()) {
                    ((Movable) element.getOccupant()).makeMove();
                }
            }
        }
        growNew();
        resetMoves();
    }
    
    /** 
     * Calls methods to check if the occupant in a Cell is eligible for reproduction.
     */
    private void growNew() {  
        for (Cell[] innerArray: allCells) {
            for (Cell element: innerArray) {
                if ((element.getOccupant() instanceof Birthable) 
                        && !((Movable) element.getOccupant()).getBirthStatus()) {
                    ((Movable) element.getOccupant()).birth();
                }
            }
        }
    }
       
    /**
     * Resets the move & birthing status of all Movable objects objects 
     * contained in the Cells on the game board at the end of a turn.
     */
    private void resetMoves() {
        for (Cell[] innerArray: allCells) {
            for (Cell element: innerArray) {
                if (!(element.getOccupant().getType() == Identifier.EMPTY)) {
                    ((Movable) element.getOccupant()).setMoveStatus(false);
                    ((Movable) element.getOccupant()).setBirthStatus(false);
                }
            }
        }
    }
}