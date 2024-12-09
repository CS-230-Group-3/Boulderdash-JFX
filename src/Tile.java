/**
 * Abstract Tile class derived from abstract GameObject class. Includes new walkable and destroyable
 * attributes as well as getters.
 *
 * @author Oscar and Ahmed.
 * @version 1.0.2
 * Last changed: 22/11/2024
 */

public abstract class Tile extends GameObject {

    protected boolean walkable;
    protected boolean destroyable;

    /**
     * Creates a new tile.
     * @param pathToSprite path to the sprite
     * @param position initial position of tile
     */
    public Tile(String pathToSprite, GridPosition position) {
        super(pathToSprite, position);
        this.type = "tile";
    }

    public void delete(Map map){
        map.destroyTile(this);
    }

    /**
     * Checks if the tile is walkable.
     * @return true if the tile is walkable, false if not.
     */
    protected boolean isWalkable() {
        return walkable;
    }

    /**
     * Checks if the tile is destroyable.
     * @return true if the tile is destroyable, false if not.
     */
    protected boolean isDestroyable() {
        return this.destroyable;
    }
}
