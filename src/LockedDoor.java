public class LockedDoor extends Tile {


    protected KeyColour colour;


    public LockedDoor(String pathToSprite, GridPosition position) {
        super(pathToSprite, position);
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
