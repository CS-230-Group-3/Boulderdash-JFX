public class PinkDoor extends LockedDoor{

    private static final String FILE_PATH = "resources/assets/door.png";

    KeyColour colour = KeyColour.PINK;
    public PinkDoor(String pathToSprite, GridPosition position, KeyColour colour) {
        super(pathToSprite, position, colour);
    }


    @Override
    public KeyColour getColour() {
        return colour;
    }


}
