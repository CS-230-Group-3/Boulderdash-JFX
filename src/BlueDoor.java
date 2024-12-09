import java.util.ArrayList;

/**
 * Represents a locked door that can only be unlocked with a BlueKey.
 * BlueDoor is a specific type of LockedDoor that checks the player's keychain
 * for the correct key and unlocks if the key is found.
 *
 * @author [Your Name]
 * @version 1.0
 */
public class BlueDoor extends LockedDoor {

    /**
     * The file path to the image asset for the Blue Door.
     */
    private static final String FILE_PATH = "resources/assets/doorblue.png";

    /**
     * Constructs a new BlueDoor at the default position (0, 0) with the "door" type.
     */
    public BlueDoor() {
        super(FILE_PATH, new GridPosition(0, 0));
        this.type = "door";
    }

    /**
     * Attempts to unlock the door using the player's keychain.
     * If a BlueKey is found in the keychain, the door is removed from the map's pending removals.
     *
     * @param map the game map
     * @param keychain the player's collection of keys
     */
    public void unlock(Map map, ArrayList<Key> keychain) {
        for (Key key : keychain) {
            if (key instanceof BlueKey) {
                map.getPendingRemovals().add(this);
                break; // Door unlocked, stop checking further keys
            }
        }
    }
}
