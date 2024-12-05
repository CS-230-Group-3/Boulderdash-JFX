public class Butterfly extends Enemy {

    private static final String FILE_PATH = "resources/assets/butterfly.png";
    private boolean leftMoving;
    private Direction lastMovement = Direction.UP;

    public Butterfly() {
        super(FILE_PATH, new GridPosition(0, 0));
    }

    @Override
    public void update(Map map) {

    }

    @Override
    public void move(Direction dir) {

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

    @Override
    public int[][] findPath(int[][] map, int[] enemyPos, int[] playerPos) {
        return new int[0][];
    }

    public boolean isLeftMoving() {
        return this.leftMoving;
    }
}
