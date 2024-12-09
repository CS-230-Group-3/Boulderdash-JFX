import java.util.ArrayList;

public abstract class LockedDoor extends Tile {

    private boolean isLocked = true;
    protected KeyColour colour;

    public boolean isLocked() {
        return isLocked;
    }

    public LockedDoor(String pathToSprite, GridPosition gridPositionposition) {
        super(pathToSprite, new GridPosition(0,0));
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

    public abstract void unlock(Map map, ArrayList<Key> keychain);


    public KeyColour getColour() {
        return colour;
    }




}
