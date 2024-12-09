public class BlueDoor extends LockedDoor {

    KeyColour colour = KeyColour.BLUE;
    public boolean locked = true;
    private static final String FILE_PATH = "resources/assets/door.png";


    public BlueDoor() {
        super(FILE_PATH, new GridPosition(0, 0));
        this.type = "door";
    }


}
