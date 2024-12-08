public class BlueDoor extends LockedDoor{

    KeyColour colour = KeyColour.BLUE;

    private static final String FILE_PATH = "resources/assets/door.png";



    public BlueDoor() {
        super(FILE_PATH, new GridPosition(0,0));
    }



}
