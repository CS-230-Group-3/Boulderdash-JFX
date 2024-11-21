package SaveLoadController;

/**
 * Concrete representation of dirt object.
 * Minimal representation for SLC testing.
 * @author Yuliia & Spas
 */
public class DirtObjectSLC extends GameObjectSLC {
    private static final String FILE_PATH_TO_DIRT_TILE = "SaveLoadController/assets/dirt.png";

    @Override
    public String toString() {
        return " DirtObject";
    }

    /**
     * Creates new dirt object at position (0,0) and
     * with a predefined sprite.
     */
    public DirtObjectSLC() {
        super(FILE_PATH_TO_DIRT_TILE,
                new GridPosition(0, 0));
    }


    /**
     * Creates a new dirt object at the passed grid position and
     * with a predefined sprite.
     * @param gridPosition the position to set object to
     */
    public DirtObjectSLC(GridPosition gridPosition) {
        super(FILE_PATH_TO_DIRT_TILE, gridPosition);
    }
}
