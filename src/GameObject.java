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

    private Sprite sprite;
    private int[] position;
    private int[] centreOfSprite;
    private int updateRate; // The rate at which the game object updates in ticks.
    private boolean isColliding;

    public GameObject(Sprite sprite, int[] position, int[] centreOfSprite, int updateRate) {
        this.sprite = sprite;
        this.position = position;
        this.centreOfSprite = centreOfSprite;
        this.updateRate = updateRate;
        this.isColliding = false;
    }

    /**
     * Updates the state of the game object.
     */
    public abstract void update();

    /**
     * Checks for collisions with other game objects.
     */
    public abstract boolean collisionCheck();

    /**
     * Determines what the game object does when it collides with another game object.
     */
    public abstract void onCollision();

    /**
     * Removes the game object from the level.
     */
    public abstract void delete();

    /**
     * Retrieves the current position of the game object.
     * @return the current position as an array [x, y].
     */
    protected int[] getPosition() {
        return this.position;
    }

    /**
     * Updates the position of the game object.
     * @param newPos the new position as an array [x, y].
     */
    protected void setPosition(int[] newPos) {
        this.position = newPos;
    }

    /**
     * Retrieves the centre of the game object's sprite.
     * @return the centre as an array [x, y].
     */
    protected int[] getCentreOfSprite() {
        return this.centreOfSprite;
    }

    /**
     * Updates the centre of the game object's sprite.
     * @param newCentre the new centre as an array [x, y].
     */
    protected void setCentreOfSprite(int[] newCentre) {
        this.centreOfSprite = newCentre;
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
    protected boolean isObjectColliding() {
        return this.isColliding;
    }

    /**
     * Updates the collision status of the game object.
     * @param collisionStatus true if the game object is colliding, false if not.
     */
    protected void setIsColliding(boolean collisionStatus) {
        this.isColliding = collisionStatus;
    }
}