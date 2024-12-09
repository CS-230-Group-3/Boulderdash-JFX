import java.util.ArrayList;

public class RedDoor extends LockedDoor {

    private static final String FILE_PATH = "resources/assets/keyred.png";
    public boolean isLocked = true;

    KeyColour colour = KeyColour.RED;
    public RedDoor() {
        super(FILE_PATH, new GridPosition(0,0));
        this.type = "door";
    }

    public void unlock(ArrayList<Key> keychain) {
        for (Key key : keychain) {
            if (key instanceof PinkKey) {
                isLocked = false;
                System.out.println("DOOR UNLOCKED SKIBIDI OHIO");
            }
        }
    }
}
