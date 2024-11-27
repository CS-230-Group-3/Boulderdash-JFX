/**
 * Represents integer position pair.
 * @author Yuliia & Spas
 */
public class GridPosition {
    private int x;
    private int y;
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

    public GridPosition add(GridPosition offset) {
        this.x = getX() + offset.getX();
        this.y = getY() + offset.getY();

        return this;
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