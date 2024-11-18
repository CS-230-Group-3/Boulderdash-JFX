/**
 * <p>This Abstract GameObject class is intended to hold information related to the Sprite, the update and collision
 * check rate as well as a abstract update function.</p>
 *
 * @author Joseph Parish.
 * @version 1.0.0
 * Last Changed: 18/11/24
 */
public abstract class GameObject
{
    private String pathToSprite; // Sprite

    private int[] centerSprite; // Center of Sprite

    public abstract void update(); // Updates itself

    private int updateRate; // Update rate

    private int collisionRate;// Collision Check rate
}
