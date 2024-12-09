/**
 * Enum representing the four cardinal directions: UP, DOWN, LEFT, and RIGHT.
 * Provides methods for rotating the directions clockwise and anti-clockwise.
 * @author Bailey Cockett, Oscar Baggs, Yuliia Shubina
 * @version 1.0.4
 */
public enum Direction {
    UP, DOWN, LEFT, RIGHT;

    /**
     * Rotates the direction clockwise (e.g., UP -> RIGHT, RIGHT -> DOWN, etc.).
     *
     * @return the direction resulting from a clockwise rotation
     * @throws IllegalStateException if the direction is invalid (should not happen)
     */
    public Direction clockwiseRotation() {
        switch (this) {
            case UP:
                return RIGHT;
            case RIGHT:
                return DOWN;
            case DOWN:
                return LEFT;
            case LEFT:
                return UP;
            default:
                throw new IllegalStateException("Unexpected value: " + this);
        }
    }

    /**
     * Rotates the direction anti-clockwise (e.g., UP -> LEFT, LEFT -> DOWN, etc.).
     *
     * @return the direction resulting from an anti-clockwise rotation
     * @throws IllegalStateException if the direction is invalid (should not happen)
     */
    public Direction antiClockwiseRotation() {
        switch (this) {
            case UP:
                return LEFT;
            case LEFT:
                return DOWN;
            case DOWN:
                return RIGHT;
            case RIGHT:
                return UP;
            default:
                throw new IllegalStateException("Unexpected value: " + this);
        }
    }
}
