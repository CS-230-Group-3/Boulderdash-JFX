/**
 * Represents a wall tile in the application. This class extends the Tile class
 * and defines a non-walkable, destroyable obstacle in the game world.
 *
 * @author: Spas Dikov.
 */

public class Wall extends Tile {

    private static final String FILE_PATH = "resources/assets/wall.png";

    /**
     * Creates a new Wall object. Initializes the tile with its specific
     * image, default GridPosition at (0, 0), and specific attributes:
     * Walkable: false, Destroyable: true.
     */
    public Wall() {
        super(FILE_PATH, new GridPosition(0, 0));
        this.walkable = false;
        this.destroyable = true;
    }

    /**
     * Updates the state of the wall tile. This implementation is empty because
     * walls do not have any active behavior during updates.
     *
     * @param map the Map object that manages the game world
     *            and tile relationships.
     */
    @Override
    public void update(Map map) {

    }
}
