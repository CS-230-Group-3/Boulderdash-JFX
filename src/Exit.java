/**
 * Represents an Exit tile in the game.
 * This tile is walkable but cannot be destroyed.
 * @author Oscar Baggs, Ahmed Zain
 * @version 1.0.0
 */
public class Exit extends Tile {

    private static final String FILE_PATH = "resources/assets/exit.png";

    /**
     * Constructs an Exit tile with the specified file path and position.
     * The Exit tile is walkable but not destroyable.
     */
    public Exit() {
        super(FILE_PATH, new GridPosition(0, 0));
        this.walkable = true;
        this.destroyable = false;
    }

    /**
     * Updates the state of the Exit tile.
     * This implementation currently does nothing.
     *
     * @param map the game map
     */
    @Override
    public void update(Map map) {
    }
}
