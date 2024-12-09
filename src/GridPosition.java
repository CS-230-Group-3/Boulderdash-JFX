/**
 * Represents integer position pair.
 *
 * @author Yuliia & Spas
 */
public class GridPosition {
    private int x;
    private int y;
    private static final String PRINTABLE_VECTOR_FORMAT =
            "(%d, %d)";


    /**
     * Create a new gird position.
     *
     * @param x coordinate in map
     * @param y coordinate in map
     */
    public GridPosition(int x, int y) {
        this.x = x;
        this.y = y;

    }

    /**
     * Increases the positions x & y
     * based on the passed positions x & y.
     *
     * @param offset grid position to add
     * @return position after the increase
     */
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

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GridPosition posToCompare)) {
            return false;
        }

        return this.getX() == posToCompare.getX()
                && this.getY() == posToCompare.getY();
    }

    /**
     * Returns the x coordinate.
     *
     * @return x coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the y coordinate.
     *
     * @return y coordinate
     */
    public int getY() {
        return y;
    }
}