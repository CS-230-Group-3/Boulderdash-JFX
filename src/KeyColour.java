/**
 * Enum representing the various colors of keys in the game.
 * Each constant corresponds to a specific key color.
 * @author Ollie Jones
 * @version 1.0.0
 */
public enum KeyColour {

    PINK, YELLOW, BLUE, RED, GREEN;

    /**
     * Returns the color of the key.
     *
     * @return the current key color.
     */
    public KeyColour getKeyColour() {
        return this;
    }

    /**
     * Returns a string representation of the key color.
     * The string is prefixed with "Key Colour" to indicate its type.
     *
     * @return a string representation of the key color.
     */
    @Override
    public String toString() {
        return "Key Colour " + getKeyColour();
    }
}
