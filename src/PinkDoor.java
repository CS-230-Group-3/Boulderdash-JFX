import java.util.ArrayList;

public class PinkDoor extends LockedDoor {

    private static final String FILE_PATH = "resources/assets/doorpink.png";
    public boolean isLocked = true;

    public PinkDoor() {
        super(FILE_PATH, new GridPosition(0, 0));
        this.type = "door";
    }

    public void unlock(Map map, ArrayList<Key> keychain) {
        for (Key key : keychain) {
            if (key instanceof PinkKey) {
                map.getPendingRemovals().add(this);
            }
        }
    }

}
