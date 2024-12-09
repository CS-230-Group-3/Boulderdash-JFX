public class Explosion extends Tile {

    private static final String FILE_PATH = "resources/assets/explosion.png";
    boolean deleteNextUpdate = false;

    public Explosion() {
        super(FILE_PATH, new GridPosition(0, 0));
        this.updateRate = 1;
    }

    @Override
    public void update(Map map) throws InterruptedException {
        if (deleteNextUpdate) {
            map.getPendingRemovals().add(this);
        }
        deleteNextUpdate = true;
    }

    @Override
    public boolean collisionCheck() {
        return false;
    }

    @Override
    public void delete() {

    }
}
