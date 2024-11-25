/**
 * Represents integer position pair.
 * @author Yuliia & Spas
 */
public class GridPosition {
    private final int x;
    private final int y;
    private static final String PRINTABLE_VECTOR_FORMAT =
        "(%d, %d)";


    /**
     * Create a new gird position.
     * @param x coordinate in map
     * @param y coordinate in map
     */
    public GridPosition(int x, int y) {
        this.x = x;
        this.y = y;

    }

    @Override
    public String toString() {
        return String.format(
                PRINTABLE_VECTOR_FORMAT,
                getX(),
                getY()
        );
    }

    /**
     * Returns the x coordinate.
     * @return x coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the y coordinate.
     * @return y coordinate
     */
    public int getY() {
        return y;
    }
}