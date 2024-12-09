import java.util.ArrayList;

/**
 * Abstract class representing a locked door on the game map.
 * This class extends the Tile class and handles the locking mechanism
 * and unlocking process based on keys in the player's keychain.
 * @author Ollie Jones
 * @version 1.0.2
 */
public abstract class LockedDoor extends Tile {

    private boolean isLocked = true;
    protected KeyColour colour;

    /**
     * Gets the locked status of the door.
     *
     * @return {@code true} if the door is locked, {@code false} otherwise.
     */
    public boolean isLocked() {
        return isLocked;
    }

    /**
     * Constructs a LockedDoor with a specified sprite and grid position.
     *
     * @param pathToSprite The file path to the sprite image representing the door.
     * @param gridPosition The grid position of the door on the map.
     */
    public LockedDoor(String pathToSprite, GridPosition gridPositionposition) {
        super(pathToSprite, new GridPosition(0, 0));
        this.type = "door";
    }

    /**
     * Updates the locked door on the map. This method may be overridden in subclasses,
     * but currently does nothing.
     *
     * @param map The map where the door is located.
     * @throws InterruptedException If the thread is interrupted during execution.
     */
    @Override
    public void update(Map map) throws InterruptedException {
        // Empty implementation for now.
    }

    /**
     * Abstract method to unlock the door. This method should be implemented by subclasses
     * to define how the door can be unlocked using the player's keychain.
     *
     * @param map The map where the door is located.
     * @param keychain The list of keys available in the player's keychain.
     */
    public abstract void unlock(Map map, ArrayList<Key> keychain);

    /**
     * Gets the color of the key required to unlock the door.
     *
     * @return The {@code KeyColour} representing the color of the key needed.
     */
    public KeyColour getColour() {
        return colour;
    }
}
