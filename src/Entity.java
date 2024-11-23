/**
 * Abstract Entity class derived from abstract GameObject class. Includes new abstract move method.
 *
 * @author Oscar and Ahmed.
 * @version 1.0.2
 * Last changed: 22/11/2024
 */

public abstract class Entity extends GameObject {

    public Entity(Sprite sprite, int[] position, int[] centreOfSprite, int updateRate) {
        super(sprite, position, centreOfSprite, updateRate);
    }

    /**
     * Changes the entity's position.
     */
    public abstract void move();
}
