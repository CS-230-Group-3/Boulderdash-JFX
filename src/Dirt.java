public class Dirt extends Tile {

    private static final String FILE_PATH = "resources/assets/dirt.png";

    public Dirt() {
        super(FILE_PATH, new GridPosition(0, 0));
    }

    @Override
    public void update(Map map) {

    }

    @Override
    public boolean collisionCheck() {
        return false;
    }

    @Override
    public void onCollision() {

    }

    @Override
    public void delete() {

    }
}
