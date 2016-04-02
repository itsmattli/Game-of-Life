package gameV2;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 * Driver class for the Game of Life.
 * 
 * @author Matthew Li
 * @version 1.0
 */
public final class Main {
    
    /**
     * Constant representing the size of the game board.
     */
    public static final int FRAMESIZE = 50;
    
    /**
     * Constant representing a Toolkit object.
     */
    private static final Toolkit TOOLKIT;
    
    /**
     * Static initialization block for the Toolkit.
     */
    static {
        TOOLKIT = Toolkit.getDefaultToolkit();
    }
    
    /**
     * The default constructor for main.
     */
    private Main() {
    }
    
    /**
     * The main method accepts command line arguments and drives the program.
     * 
     * @param argv the command line arguments
     */
    public static void main(final String[] argv) {
        final GameFrame frame;
        final World world;

        world = new World(FRAMESIZE, FRAMESIZE);
        world.init();
        frame = new GameFrame(world);
        position(frame);
        frame.init();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    /**
     * Sets the position of the frame based on the Point and Dimension objects generated
     * by centreOnScreen and calculateScreenArea.
     * @param frame the GameFrame to be positioned.
     */
    private static void position(final GameFrame frame) {
        final Dimension size;

        size = calculateScreenArea(0.80f, 0.80f);
        frame.setSize(size);
        frame.setLocation(centreOnScreen(size));
    }
    
    /**
     * Creates a Point representing the centre of the screen based on the 
     * size of the screen.
     * 
     * @param size Dimension object for size of the screen.
     * @return Point object representing the centre of the screen.
     */
    public static Point centreOnScreen(final Dimension size) {
        final Dimension screenSize;

        if (size == null) {
            throw new IllegalArgumentException("Size cannot be null");
        }

        screenSize = TOOLKIT.getScreenSize();

        return (new Point((screenSize.width - size.width) / 2, 
                (screenSize.height - size.height) / 2));
    }

    /**
     * Creates a Dimension object based on the size of the screen and a percentage based on the 
     * parameters passed into the method.
     * 
     * @param widthPercent the width percentage of the screen width.
     * @param heightPercent the height percentage of the screen width.
     * @return Returns a Dimension object representing the Dimensions of the game.
     */
    public static Dimension calculateScreenArea(
            final float widthPercent, final float heightPercent) {
        final Dimension screenSize;
        final Dimension area;
        final int width;
        final int height;
        final int size;

        if ((widthPercent <= 0.0f) || (widthPercent > 100.0f)) {
            throw new IllegalArgumentException(
                    "widthPercent cannot be " + "<= 0 or > 100 - got: " + widthPercent);
        }

        if ((heightPercent <= 0.0f) || (heightPercent > 100.0f)) {
            throw new IllegalArgumentException(
                    "heightPercent cannot be " + "<= 0 or > 100 - got: " + heightPercent);
        }

        screenSize = TOOLKIT.getScreenSize();
        width = (int) (screenSize.width * widthPercent);
        height = (int) (screenSize.height * heightPercent);
        size = Math.min(width, height);
        area = new Dimension(size, size);

        return (area);
    }
}
