import javafx.scene.image.Image;

/**
 * Abstract GameObject class that holds the sprite, centre of sprite, position, update rate and collision
 * status of game objects. Includes abstract methods update, collisionCheck, onCollision, and delete, as
 * well as getters and setters.
 *
 * @author Oscar and Ahmed.
 * @version 1.0.2
 * Last changed: 22/11/2024
 */

public abstract class GameObject {

    private final Image sprite;
    private GridPosition gridPosition;
    private int updateRate; // The rate at which the game object updates in ticks.
    private boolean isWalkable;
    private int collisionRate;

    private String type;

    /**
     * Creates a new game object.
     * @param pathToSprite path to the sprite
     * @param position initial position of object
     */
    public GameObject(String pathToSprite, GridPosition position) {
        this.sprite = new Image(pathToSprite);
        this.gridPosition = position;
    }


    /**
     * Updates the state of the game object.
     */
    public abstract void update(Map map);

    /**
     * Checks for collisions with other game objects.
     */
    public abstract boolean collisionCheck();

    /**
     * Removes the game object from the level.
     */
    public abstract void delete();

    /**
     * Returns the object's sprite.
     * @return javaFx Image instance.
     */
    public Image getSprite() {
        return sprite;
    }

    /**
     * Retrieves the current position of the game object.
     * @return the current position as a grid object.
     */
    protected GridPosition getPosition() {
        return this.gridPosition;
    }

    /**
     * Updates the position of the game object.
     * @param newPos the new position as an array [x, y].
     */
    public void setPosition(GridPosition newPos) {
        this.gridPosition = newPos;
    }


    /**
     * Retrieves the update rate of the game object.
     * @return the object's update rate.
     */
    protected int getUpdateRate() {
        return this.updateRate;
    }

    /**
     * Checks whether the game object is currently colliding.
     * @return true if the game object is colliding, false if not.
     */
    protected boolean isWalkable() {
        return this.isWalkable;
    }

    protected String getType()
    {
        return type;
    }
}
