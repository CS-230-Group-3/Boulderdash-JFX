/**
 * Represents a BlueKey that can be used to unlock BlueDoor objects.
 * This is a specific type of Key with a unique appearance and behavior.
 *
 * @author Bailey Cockett
 * @version 1.0
 */
public class BlueKey extends Key {

    /**
     * The file path to the image asset for the Blue Key.
     */
    private static final String FILE_PATH = "resources/assets/keyblue.png";

    /**
     * Constructs a new BlueKey at the default position (0, 0) with the "key" type.
     */
    public BlueKey() {
        super(FILE_PATH, new GridPosition(0, 0));
        this.type = "key";
    }

    /**
     * Returns a string representation of the BlueKey.
     *
     * @return "Blue Key"
     */
    @Override
    public String toString() {
        return "Blue Key";
    }
}
