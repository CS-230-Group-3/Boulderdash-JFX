public class TitaniumWall extends Tile {

    private static final String FILE_PATH = "resources/assets/titaniumwall.png";

    public TitaniumWall() {
        super(FILE_PATH, new GridPosition(0, 0));
    }

    @Override
    public void update(Map map) {}

    @Override
    public boolean collisionCheck() {
        return false;
    }

    @Override
    public void onCollision(GameObject collidingObject) {}

    @Override
    public void delete() {}
}