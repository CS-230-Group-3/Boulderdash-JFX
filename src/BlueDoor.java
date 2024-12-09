import java.util.ArrayList;

public class BlueDoor extends LockedDoor{

    private static final String FILE_PATH = "resources/assets/keyblue.png";

    public BlueDoor() {
        super(FILE_PATH, new GridPosition(0,0));
        this.type = "door";
    }

    public void unlock(Map map, ArrayList<Key> keychain) {
        for (Key key : keychain) {
            if (key instanceof BlueKey) {
                map.getPendingRemovals().add(this);
            }
        }
    }

}

