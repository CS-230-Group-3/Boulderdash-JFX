/**
 * Represents a MagicWall tile on the game map.
 * This tile is not walkable but can be destroyed. Transforms gems to boulders and vice versa.
 */
public class MagicWall extends Tile {

    private static final String FILE_PATH = "resources/assets/magicwall.png";

    /**
     * Constructs a MagicWall tile with the default sprite and grid position.
     * Sets the MagicWall as non-walkable and destroyable.
     */
    public MagicWall() {
        super(FILE_PATH, new GridPosition(0, 0));
        this.walkable = false;
        this.destroyable = true;
    }

    /**
     * Updates the state of the MagicWall tile. 
     * Currently, it does nothing.
     *
     * @param map The map where the MagicWall is located.
     */
    @Override
    public void update(Map map) {}
}
