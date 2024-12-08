public class YellowDoor extends LockedDoor{

    KeyColour colour = KeyColour.YELLOW;

    private static final String FILE_PATH = "resources/assets/door.png";


    public YellowDoor() {
        super(FILE_PATH,new GridPosition(0,0));

    }




}
