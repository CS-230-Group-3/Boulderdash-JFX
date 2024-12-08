public class LockedDoor extends Tile {

    private final KeyColour colour;

    public LockedDoor(String pathToSprite, GridPosition position, KeyColour colour) {
        super(pathToSprite, position);
        this.colour = colour;
        this.type = "tile";
    }

    @Override
    public void update(Map map) throws InterruptedException {

    }

    @Override
    public boolean collisionCheck() {
        return false;
    }

    @Override
    public void delete() {

    }

    public boolean unlock(Key key) {
        return true;
    }


  public KeyColour getColour() {
       return colour;
    }
}
