import java.util.ArrayList;

public class BlueDoor extends LockedDoor{

    private static final String FILE_PATH = "resources/assets/keyblue.png";
    public boolean isLocked = true;

    public BlueDoor() {
        super(FILE_PATH, new GridPosition(0,0));
        this.type = "door";
    }

    public void unlock(ArrayList<Key> keychain) {
        for (Key key : keychain) {
            if (key instanceof BlueKey) {
                isLocked = false;
            }
        }
    }

}

