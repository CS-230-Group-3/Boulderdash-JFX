import java.util.ArrayList;

/**
 * A class representing a locked pink door in the game.
 * The door is initially locked and can be unlocked by a PinkKey.
 * Once unlocked, the door is removed from the map.
 *
 * @author Ollie Jones
 * @version 1.0
 */
public class PinkDoor extends LockedDoor {

    private static final String FILE_PATH = "resources/assets/doorpink.png";
    public boolean isLocked = true;

    /**
     * Constructs a new PinkDoor with the specified file path and default position.
     * The door is initially locked.
     */
    public PinkDoor() {
        super(FILE_PATH, new GridPosition(0, 0));
        this.type = "door";
    }

    /**
     * Unlocks the door if the player has a PinkKey in their keychain.
     * If the door is unlocked, it is added to the list of removals from the map.
     *
     * @param map the game map that contains the door
     * @param keychain the player's collection of keys
     */
    public void unlock(Map map, ArrayList<Key> keychain) {
        for (Key key : keychain) {
            if (key instanceof PinkKey) {
                map.getPendingRemovals().add(this);
            }
        }
    }
}
