public class Path extends Tile {

    private static final String FILE_PATH = "resources/assets/path_transparent.png";

    public Path() {
        super(FILE_PATH, new GridPosition(0, 0));
        walkable = true;
        destroyable = false;
    }

    @Override
    public void update(Map map) {

    }

    @Override
    public boolean collisionCheck() {
        return false;
    }

    @Override
    public void onCollision(GameObject collidingObject) {

    }

    @Override
    public void delete() {

    }
}
