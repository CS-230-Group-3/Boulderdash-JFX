package SaveLoadController;

/**
 * Concrete representation of path object.
 * Minimal representation for SLC testing.
 * @author Yuliia & Spas
 */
public class PathObjectSLC extends GameObjectSLC {
    private static final String FILE_PATH_TO_PATH_TILE = "SaveLoadController/assets/path.png";

    /**
     * Creates new path object at position (0,0) and
     * with a predefined sprite.
     */
    public PathObjectSLC() {
        super(FILE_PATH_TO_PATH_TILE,
                new GridPosition(0, 0));
    }

    /**
     * Creates a new path object at the passed grid position and
     * with a predefined sprite.
     * @param gridPosition the position to set object to
     */
    public PathObjectSLC(GridPosition gridPosition) {
        super(FILE_PATH_TO_PATH_TILE,
                gridPosition);
    }
}

