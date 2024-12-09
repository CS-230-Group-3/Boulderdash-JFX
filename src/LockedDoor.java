public class LockedDoor extends Tile {

    private boolean isLocked = true;
    protected KeyColour colour;

    public boolean isLocked() {
        return isLocked;
    }

    public LockedDoor(String pathToSprite, GridPosition position) {
        super(pathToSprite, position);
        this.type = "door";
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
