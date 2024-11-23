/**
 * Abstract Tile class derived from abstract GameObject class. Includes new walkable and destroyable
 * attributes as well as getters.
 *
 * @author Oscar and Ahmed.
 * @version 1.0.2
 * Last changed: 22/11/2024
 */

public abstract class Tile extends GameObject {

    private boolean walkable;
    private boolean destroyable;

    public Tile(String pathToSprite, int[] position, int[] centreOfSprite, int updateRate) {
        super(pathToSprite, position, centreOfSprite, updateRate);
    }

    /**
     * Checks if the tile is walkable.
     * @return true if the tile is walkable, false if not.
     */
    protected boolean isWalkable() {
        return this.walkable;
    }

    /**
     * Checks if the tile is destroyable.
     * @return true if the tile is destroyable, false if not.
     */
    protected boolean isDestroyable() {
        return this.destroyable;
    }
}
