/**
 * Represents a RedKey in the game, extending from the Key class.
 * It has a sprite which is cool
 * @author Ollie Jones
 * @version 1.0
 */
public class RedKey extends Key {

    private static final String FILE_PATH = "resources/assets/keyred.png";

    /**
     * Constructor for the RedKey.
     * Initializes the RedKey with its file path and grid position.
     */
    public RedKey() {
        super(FILE_PATH, new GridPosition(0, 0));
        this.type = "key";
    }

    /**
     * Returns a string representation of the RedKey.
     *
     * @return A string indicating the RedKey's name.
     */
    @Override
    public String toString() {
        return "Red Key";
    }
}
