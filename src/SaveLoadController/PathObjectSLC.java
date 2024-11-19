package SaveLoadController;

/**
 * Concrete representation of path object.
 * Minimal representation for SLC testing.
 * @author Yuliia & Spas
 */
public class PathObjectSLC extends GameObjectSLC {
    private static final String FILE_PATH_TO_PATH_TILE = "SaveLoadController/assets/path.png";

    @Override
    public String toString() {
        return " PathObject";
    }

    /**
     * Creates a new path object with predefined path.
     */
    public PathObjectSLC() {
        super(FILE_PATH_TO_PATH_TILE);
    }
}

