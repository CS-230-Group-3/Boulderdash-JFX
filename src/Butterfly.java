public class Butterfly extends Entity {

    private static final String FILE_PATH = "resources/assets/butterfly.png";

    public Butterfly() {
        super(FILE_PATH, new GridPosition(0, 0));
    }

    @Override
    public void move(Direction dir) {

    }

    @Override
    public boolean collisionCheck(Direction dir) {
        return false;
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
        //explode
    }
}
