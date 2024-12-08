public class RedDoor extends LockedDoor {


    private static final String FILE_PATH = "resources/assets/door.png";

    KeyColour colour = KeyColour.RED;
    public RedDoor(String pathToSprite, GridPosition position, KeyColour colour) {
        super(pathToSprite, position, colour);
    }


    @Override
    public KeyColour getColour() {
        return colour;
    }
}
