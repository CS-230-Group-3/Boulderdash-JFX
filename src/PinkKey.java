/**
 * A class representing a pink key in the game.
 * This key can be used to unlock PinkDoors.
 *
 * @author Ollie Jones, Yuliia Shubina, Diya Patel
 * @version 1.0
 */
public class PinkKey extends Key {

    private static final String FILE_PATH = "resources/assets/keypink.png";

    /**
     * Constructs a new PinkKey with the specified file path and default position.
     */
    public PinkKey() {
        super(FILE_PATH, new GridPosition(0, 0));
        this.type = "key";
    }

    /**
     * Returns a string representation of the PinkKey.
     *
     * @return a string indicating the key type
     */
    @Override
    public String toString() {
        return "Pink Key";
    }
}
