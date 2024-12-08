public class PatrollingEnemy extends Enemy
{
    protected boolean leftMoving;

    /** Tracks the direction the butterfly is currently "facing". */
    private Direction lastMovement = Direction.UP;

    public PatrollingEnemy(String pathToSprite, GridPosition position) {
        super(pathToSprite, position);
    }

    @Override
    public void update(Map map){
        this.calculateNextMove(map);
    }

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
     * Finds the first valid move in the priority list generated by {@code calculateMovePriority}.
     * Executes the move if found, updating the butterfly's position and direction.
     * If no valid move is found, the butterfly does nothing.
     *
     * @param map the current level map.
     */
    public void calculateNextMove(Map map) {
        for (Direction direction : this.calculateMovePriority()) {
            GameObject neighbour = map.getNeighbourOf(this, direction);
            if (neighbour instanceof Path) {
                this.move(map, direction);
                lastMovement = direction;
                return;
            } else if (neighbour instanceof Player) {
                map.getPlayerObjectReference().die();
                this.move(map, direction);
                lastMovement = direction;
                return;
            }
        }
    }


    /**
     * Moves the butterfly in the specified direction by updating its position on the map.
     * This method is called by {@code calculateNextMove}.
     *
     * @param map the current level map.
     * @param dir the direction in which the butterfly is moving.
     */
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

    }
}
