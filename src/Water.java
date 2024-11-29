public class Water extends Tile {

    private static final String FILE_PATH = "resources/assets/water.png";

    public Water() {
        super(FILE_PATH, new GridPosition(0, 0));
    }

    @Override
    public void update(Map map) {
        //get east, south, west neighbours
        //if there's a path tile, game controller spawns a new water tile there
        //play sound
    }

    @Override
    public boolean collisionCheck() {
        return false;
    }

    @Override
    public void onCollision(GameObject collidingObject) {}

    @Override
    public void delete() {}
}