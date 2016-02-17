package gameV1;

import java.awt.GridLayout;
import javax.swing.JFrame;

/**
 * Frame for the Game of Life.
 * 
 * @author Matthew Li
 * @version 1.0
 */
public class GameFrame extends JFrame {

    private final World world;
    
    /**
     * Creates a GameFrame
     * 
     * @param world the World that will occupy the GameFrame.
     */
    public GameFrame(final World world) {
        this.world = world;
    }
    
    /**
     * Initializes the layout for the game.
     */
    public void init() {
        setTitle("Matthew Li's Game of Life");
        setLayout(new GridLayout(world.getRowCount(), world.getColumnCount()));

        for (int row = 0; row < world.getRowCount(); row++) {
            for (int col = 0; col < world.getColumnCount(); col++) {
                add(world.getCellAt(row, col));
            }
        }
        addMouseListener(new TurnListener(this));
    }
    
    /**
     * Takes a turn when the mouse is clicked.
     */
    public void takeTurn() {
        world.takeTurn();
        repaint();
    }
}
