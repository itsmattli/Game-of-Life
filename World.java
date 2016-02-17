package gameV1;

/**
 * World that initializes the cells and occupants of the game.
 * 
 * @author Matthew Li
 * @version 1.0
 */
public class World {
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
     * Creates a random Space object to occupy a Cell
     * based on the RandomGenerator value.
     * 
     * @param cell the Cell that will contain the Space object.
     * @return Space object to be stored in the cell.
     */
    private void randomSpace(final Cell cell) {
        Occupant space;
        int gen = (int) Math.floor(Math.random() * 11);
        if (gen == 0) {
            space = new Herbivore(cell);
        } else if (gen > 3) {
            space = new Empty(cell);
        } else {
            space = new Plant(cell);
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
     * Checks all the Cells for Herbivore objects and gets them to move if
     * they haven't already moved on this mouse click. Then calls resetMoves
     * to allow them to move on the next turn.
     */
    public void takeTurn() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (allCells[row][col].getOccupant().getType() == Identifier.HERBIVORE 
                        && !((Herbivore) allCells[row][col].getOccupant()).getMoveStatus()) {
                    ((Herbivore) allCells[row][col].getOccupant()).move();
                }
            }
        }
        resetMoves();
        growNew();
    }
    
    /* 
     * Creates one Plant object at a 10% probability in a Cell with an Empty object or
     * creates one Herbivore object at a 30% probability in a Cell with an Empty object per turn
     */
    private void growNew() {
        int growNew = (int) Math.floor(Math.random() * 11);
        Cell check;
        boolean created = false;
        if (growNew == 0) {
            do {
                check = getCellAt((int) Math.floor(Math.random() * getRowCount()), 
                        (int) Math.floor(Math.random() * getColumnCount()));
                if (check.getOccupant().getType() == Identifier.EMPTY) {
                    check.placeOccupant(new Herbivore(check));
                    ((Herbivore)check.getOccupant()).setRegrowthLife();
                    created = true;
                }                
            } while (!created);
        } else if (growNew < 4 && growNew > 0) {
            do {
                check = getCellAt((int) Math.floor(Math.random() * getRowCount()), 
                        (int) Math.floor(Math.random() * getColumnCount()));
                if (check.getOccupant().getType() == Identifier.EMPTY) {
                    check.placeOccupant(new Plant(check));
                    created = true;
                }                
            } while (!created);
        }
    }
       
    /**
     * Resets the move status of all  Herbivore objects 
     * contained in the Cells on the game board at the end of a turn.
     */
    public void resetMoves() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (allCells[row][col].getOccupant().getType() == Identifier.HERBIVORE) {
                    ((Herbivore) allCells[row][col]
                            .getOccupant()).setMoveStatus(false);              
                }
            }
        }
    }
}