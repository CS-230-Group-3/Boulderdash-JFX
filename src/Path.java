public class Path extends Tile {

    private static final String FILE_PATH = "resources/assets/path.png";

    public Path() {
        super(FILE_PATH, new GridPosition(0, 0));
    }

    @Override
    public void update() {

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
