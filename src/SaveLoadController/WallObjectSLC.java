package SaveLoadController;

/**
 * Concrete representation of wall object.
 * Minimal representation for SLC testing.
 * @author Yuliia & Spas
 */
public class WallObjectSLC extends GameObjectSLC {
    private static final String FILE_PATH_TO_WALL_TILE = "SaveLoadController/assets/wall.png";

    @Override
    public String toString() {
        return " WallObject";
    }

    /**
     * Creates a new wall object with predefined sprite.
     */
    public WallObjectSLC() {
        super(FILE_PATH_TO_WALL_TILE);
    }
}
