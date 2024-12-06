import java.util.ArrayList;

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
    public void move(Map map, Direction dir) {

    }

    @Override
    public boolean collisionCheck(Map map, GridPosition position) {
        return false;
    }

    @Override
    public boolean collisionCheck(Map map, Direction dir) {
        return false;
    }

    @Override
    public boolean collisionCheck() {
        return false;
    }

    @Override
    public void delete() {
        //explode
    }

    public ArrayList<int[]> findPath(Map map, GridPosition enemyPosition, GridPosition playerPosition) {
        return null;
    }
    public boolean isLeftMoving() {
        return this.leftMoving;
    }
}
