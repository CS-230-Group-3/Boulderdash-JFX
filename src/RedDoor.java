public class RedDoor extends LockedDoor {


    private static final String FILE_PATH = "resources/assets/door.png";

    KeyColour colour = KeyColour.RED;
    public RedDoor() {
        super(FILE_PATH, new GridPosition(0,0));
    }




}
