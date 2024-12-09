/**
 * Represents a yellow key in the application. This class extends the Key class
 * and is specific to yellow keys with a predefined image and attributes.
 *
 * @author: Ollie Jones, Bailey Cockett, Oscar Baggs
 */

public class YellowKey extends Key {

    private static final String FILE_PATH = "resources/assets/keyyellow.png";
    private String colour = "Yellow";

    /**
     * Creates a new YellowKey object. Initialises the key with its specific
     * image and a default GridPosition at (0, 0).
     */
    public YellowKey() {
        super(FILE_PATH, new GridPosition(0, 0));
        this.type = "key";
    }

    /**
     * Returns a string representation of the yellow key.
     *
     * @return a String "Yellow Key"
     */
    @Override
    public String toString() {
        return "Yellow Key";
    }

}
