public class BlueDoor extends LockedDoor{

    KeyColour colour = KeyColour.BLUE;

    private static final String FILE_PATH = "resources/assets/door.png";



    public BlueDoor(String pathToSprite, GridPosition position,KeyColour colour) {
        super(pathToSprite, position, colour);

    }

    @Override
    public KeyColour getColour() {
        return colour;
    }
}
