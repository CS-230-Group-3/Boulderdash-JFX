package SaveLoadController;

/**
 * Concrete representation of wall object.
 * Minimal representation for SLC testing.
 * @author Yuliia & Spas
 */
public class WallObjectSLC extends GameObjectSLC {
    private static final String FILE_PATH_TO_WALL_TILE = "SaveLoadController/assets/wall.png";

    /**
     * Creates new wall object at position (0,0) and
     * with a predefined sprite.
     */
    public WallObjectSLC() {
        super(FILE_PATH_TO_WALL_TILE,
                new GridPosition(0, 0));
    }

    /**
     * Creates a new wall object at the passed grid position and
     * with a predefined sprite.
     * @param gridPosition the position to set object to
     */
    public WallObjectSLC(GridPosition gridPosition) {
        super(FILE_PATH_TO_WALL_TILE,
                gridPosition);
    }
}
