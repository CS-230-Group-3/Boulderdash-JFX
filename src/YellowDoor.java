public class YellowDoor extends LockedDoor{

    KeyColour colour = KeyColour.YELLOW;

    private static final String FILE_PATH = "resources/assets/door.png";


    public YellowDoor(String pathToSprite, GridPosition position, KeyColour colour) {
        super(pathToSprite, position,colour);
    }


    @Override
    public KeyColour getColour() {
        return colour;
    }
}
