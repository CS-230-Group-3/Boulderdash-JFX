/**
 * leftMoving has 50/50 chance of being true when butterfly instantiated
 * lastMovement tracks where the butterfly is "facing"
 * exact same as butterfly except it doesn't drop diamonds when it explodes
 */

import java.util.ArrayList;
import java.util.Random;

public class Firefly extends Enemy {

    private static final String FILE_PATH = "resources/assets/firefly.png";
    private boolean leftMoving;
    private Direction lastMovement = Direction.UP;

    public Firefly() {
        super(FILE_PATH, new GridPosition(0, 0));
        Random random = new Random();
        leftMoving = random.nextInt(2) == 0;
    }

    @Override
    public void update(Map map) {
        calculateNextMove(map);
    }

    /**
     * calculates how the butterfly should try to move using 90 degree rotations
     * @return list of directions to move in order of highest priority
     */
    public Direction[] calculateMovePriority() {
        if (this.leftMoving) {
            return new Direction[]{
                    lastMovement.antiClockwiseRotation(),
                    lastMovement,
                    lastMovement.clockwiseRotation(),
                    lastMovement.clockwiseRotation().clockwiseRotation()
            };
        } else {
            return new Direction[]{
                    lastMovement.clockwiseRotation(),
                    lastMovement,
                    lastMovement.antiClockwiseRotation(),
                    lastMovement.clockwiseRotation().clockwiseRotation()
            };
        }
    }

    /**
     * finds the first valid move in the priority list. does nothing if none found,
     * calls move otherwise
     * @param map the level map
     */
    public void calculateNextMove(Map map) {
        for (Direction direction : this.calculateMovePriority()) {
            GameObject neighbour = map.getNeighbourOf(this, direction);
            if (neighbour instanceof Path) {
                this.move(map, direction);
                this.lastMovement = direction;
            }
        }

    }

    /**
     * guess what this one does
     * @param map level map
     * @param dir the direction the butterfly is moving, passed from calculateNextMove
     */
    @Override
    public void move(Map map, Direction dir) {
        if (dir == Direction.UP) {
            this.setPosition(this.getPosition().add(new GridPosition(0, -1)));
        } else if (dir == Direction.DOWN) {
            this.setPosition(this.getPosition().add(new GridPosition(0, 1)));
        } else if (dir == Direction.LEFT) {
            this.setPosition(this.getPosition().add(new GridPosition(-1, 0)));
        } else if (dir == Direction.RIGHT) {
            this.setPosition(this.getPosition().add(new GridPosition(1, 0)));
        }
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
        //explode - destroy a 9x9 grid of tiles
    }

    @Override
    public ArrayList<int[]> findPath(Map map, GridPosition enemyPosition, GridPosition playerPosition) {
        return null;
    }
}
