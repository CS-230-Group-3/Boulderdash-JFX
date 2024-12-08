/**
 * leftMoving has 50/50 chance of being true when butterfly instantiated
 * lastMovement tracks where the butterfly is "facing"
 * exact same as butterfly except it doesn't drop diamonds when it explodes
 */

import java.util.ArrayList;
import java.util.Random;

public class Firefly  extends PatrollingEnemy {

    private static final String FILE_PATH = "resources/assets/firefly.png";

    public Firefly() {
        super(FILE_PATH, new GridPosition(0, 0));
        Random random = new Random();
        leftMoving = random.nextInt(2) == 0;
    }

    @Override
    public void update(Map map) {
        super.calculateNextMove(map);
    }

    /**
     * guess what this one does
     * @param map level map
     * @param dir the direction the butterfly is moving, passed from calculateNextMove
     */
    @Override
    public void move(Map map, Direction dir) {
        super.move(map, dir);
    }

    @Override
    public boolean collisionCheck(Map map, GridPosition position) {
        return super.collisionCheck(map, position);
    }

    @Override
    public boolean collisionCheck(Map map, Direction dir) {
        return super.collisionCheck(map, dir);
    }

    @Override
    public boolean collisionCheck() {
        return super.collisionCheck();
    }

    @Override
    public void delete() {
        //explode - destroy a 9x9 grid of tiles
    }

}
