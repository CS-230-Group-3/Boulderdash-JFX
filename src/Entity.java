/**
 * Abstract Entity class derived from abstract GameObject class. Includes new abstract move method.
 *
 * @author Oscar and Ahmed.
 * @version 1.0.2
 * Last changed: 22/11/2024
 */

public abstract class Entity extends GameObject {

    /**
     * Creates a new entity.
     *
     * @param pathToSprite path to the sprite
     * @param position     initial position of object
     */
    public Entity(String pathToSprite, GridPosition position) {
        super(pathToSprite, position);
    }

    public void delete(Map map){
        map.destroyEntity(this);
    }

    /**
     * Changes the entity's position.
     */
    public abstract void move(Map map, Direction dir);

    public abstract boolean collisionCheck(Map map, GridPosition position);

    public abstract boolean collisionCheck(Map map, Direction dir);
}
