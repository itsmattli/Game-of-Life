package gameV2;

import java.util.Random;

/**
 * RandomGenerator has methods to generate a sequence of numbers.
 * 
 * @author Matthew Li
 * @version 1.0
 */
public final class RandomGenerator {
    /**
     * Constant for the RandomGenerator class representing
     * the Random object used to generate Random numbers.
     */
    private static final Random RANDOM = new Random();
    
    /**
     * Default constructor for RandomGenerator class.
     */
    private RandomGenerator() {
        
    }
    
    /**
     * Returns the next number in the sequence within the bounds specified by the parameters.
     * 
     * @param max The number of values that can be generated, with a max value of max).
     * @return Returns an int between 0 and max
     */
    public static int nextNumber(final int max) {
        return RANDOM.nextInt(max);
    }
}
