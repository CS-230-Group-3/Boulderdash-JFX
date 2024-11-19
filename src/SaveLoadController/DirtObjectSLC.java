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
     * Creates new dirt object with predefined sprite.
     */
    public DirtObjectSLC() {
        super(FILE_PATH_TO_DIRT_TILE);
    }
}
