import java.util.ArrayList;

public class YellowDoor extends LockedDoor{

    private static final String FILE_PATH = "resources/assets/keyyellow.png";
    public boolean isLocked = true;
    public YellowDoor() {
        super(FILE_PATH, new GridPosition(0,0));
        this.type = "door";
    }

    public void unlock(ArrayList<Key> keychain) {
        for (Key key : keychain) {
            if (key instanceof YellowKey) {
                isLocked = false;
            }
        }
    }

}
