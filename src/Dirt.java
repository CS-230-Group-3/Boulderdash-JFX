/**
 * Represents a Dirt tile in the game. Inherits from Tile.
 * A Dirt tile is walkable and destroyable.
 * author: Ahmed Zain
 * version: 1.0.0
 */
public class Dirt extends Tile {

    private static final String FILE_PATH = "resources/assets/dirt.png";

    /**
     * Constructs a Dirt tile with the predefined image path and grid position.
     * Sets the tile as walkable and destroyable.
     */
    public Dirt() {
        super(FILE_PATH, new GridPosition(0, 0));
        this.walkable = true;
        this.destroyable = true;
    }

    /**
     * Updates the state of the Dirt tile.
     * This method is currently empty but can be extended for future behavior.
     *
     * @param map the game map to update
     */
    @Override
    public void update(Map map) {
        // No behavior defined for the Dirt tile in this update method.
    }
}
