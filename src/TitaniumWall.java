/**
 * Represents an indestructible wall in the game that cannot be walked through or destroyed.
 * This wall is unbreakable and impassable.
 *
 * @author Spas Dikov, Yuliia Shubina
 * @version 1.2
 */
public class TitaniumWall extends Tile {

    private static final String FILE_PATH = "resources/assets/titaniumwall.png";

    /**
     * Constructs a new TitaniumWall with the specified sprite image and position.
     * The wall is set to be non-walkable and non-destroyable.
     */
    public TitaniumWall() {
        super(FILE_PATH, new GridPosition(0, 0));
        this.walkable = false;
        this.destroyable = false;
    }

    /**
     * Updates the TitaniumWall.
     * As the wall is indestructible and does not change, this method does nothing.
     *
     * @param map the current game map, passed for completeness, but not used in this class
     */
    @Override
    public void update(Map map) {
    }
}
