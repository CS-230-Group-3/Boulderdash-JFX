import java.util.ArrayList;

/**
 * Represents a yellow door in the application. This class extends the LockedDoor class
 * and is specific to yellow doors with a predefined image and attributes.
 *
 * @author: Ollie Jones, Bailey Cockett, Oscar Baggs
 */

public class YellowDoor extends LockedDoor {

    private static final String FILE_PATH = "resources/assets/dooryellow.png";

    /**
     * Represents whether the door is currently locked.
     * The door starts as locked.
     */
    public boolean isLocked = true;

    /**
     * Creates a new YellowDoor object. Initializes the door with its specific
     * image and a default GridPosition at (0, 0).
     */
    public YellowDoor() {
        super(FILE_PATH, new GridPosition(0, 0));
        this.type = "door";
    }

    /**
     * Unlocks the yellow door if the player's keychain contains a YellowKey.
     * Once unlocked, the door is added to the pending removals list in the provided Map.
     *
     * @param map      The map where the door is located.
     * @param keychain The list of keys available in the player's keychain.
     */
    public void unlock(Map map, ArrayList<Key> keychain) {
        for (Key key : keychain) {
            if (key instanceof YellowKey) {
                map.getPendingRemovals().add(this);
            }
        }
    }

}
