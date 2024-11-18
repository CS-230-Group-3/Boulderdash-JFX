/**
 * <p>This Abstract GameObject class is intended to hold information related to the Sprite, the update rate and collision
 * check result as well as an abstract Update, Delete and 2 Collision functions.</p>
 *
 * @author Joseph Parish.
 * @version 1.0.1
 * Last Changed: 18/11/24
 */
public abstract class GameObject
{
    private String pathToSprite; // Sprite
    private int[] centerSprite; // Center of Sprite
    private int[] position; // Position
    private boolean isColliding; // Collision
    private int updateRate; // Update rate

    public abstract void update(); // Updates itself
    public abstract boolean collisionCheck(); // Checks for collision 
    public abstract void onCollision(); // Reaction to collision 
    public abstract void delete(); // Destroys game object
}
