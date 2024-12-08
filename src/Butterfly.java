/**
 * The Butterfly class represents a specific type of enemy with unique movement patterns.
 * Its movement involves a priority-based system with 90-degree rotations.
 * The direction in which it "faces" is tracked by {@code lastMovement}, and
 * {@code leftMoving} determines whether it prefers anti-clockwise or clockwise rotations.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Butterfly extends Enemy {

    /** The file path to the butterfly's sprite image. */
    private static final String FILE_PATH = "resources/assets/butterfly.png";

    /** Indicates if the butterfly favours anti-clockwise movement. */
    private boolean leftMoving;

    /** Tracks the direction the butterfly is currently "facing". */
    private Direction lastMovement = Direction.UP;

    /**
     * Constructs a Butterfly object, initialising its position and determining
     * its movement preference (left or right) randomly with a 50/50 chance.
     */
    public Butterfly() {
        super(FILE_PATH, new GridPosition(0, 0));
        Random random = new Random();
        leftMoving = random.nextInt(2) == 0;
        this.updateRate = 3;
    }

    /**
     * Updates the butterfly's state. This method calculates and executes its next move.
     *
     * @param map the current level map.
     */
    @Override
    public void update(Map map) {
        calculateNextMove(map);
    }

    /**
     * Calculates the butterfly's move priority based on its movement preference.
     * If {@code leftMoving} is true, the priority starts with anti-clockwise rotation.
     * Otherwise, it starts with clockwise rotation.
     *
     * @return an array of {@link Direction} objects in order of movement priority.
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

    /**
     * Checks for collisions at a specific position on the map.
     *
     * @param map the current level map.
     * @param position the position to check for collisions.
     * @return true if a collision is detected, false otherwise.
     */
    @Override
    public boolean collisionCheck(Map map, GridPosition position) {
        return false;
    }

    /**
     * Checks for collisions in the specified direction on the map.
     *
     * @param map the current level map.
     * @param dir the direction to check for collisions.
     * @return true if a collision is detected, false otherwise.
     */
    @Override
    public boolean collisionCheck(Map map, Direction dir) {
        return false;
    }

    /**
     * Checks for collisions without specifying a direction or position.
     *
     * @return true if a collision is detected, false otherwise.
     */
    @Override
    public boolean collisionCheck() {
        return false; // Implement collision logic as needed.
    }

    /**
     * Checks for collisions at a specific position, including interactions with the player.
     *
     * @param gameController the game's controller containing map and player references.
     * @param position       the position to check for collisions.
     * @return true if a collision is detected, false otherwise.
     */
    public boolean collisionCheck(GameController gameController, GridPosition position) {
        if (position.equals(gameController.getMap().getPlayerObjectReference().getPosition())) {
            // Handle collision with the player
            gameController.getMap().getPlayerObjectReference().die();
            return true;
        } else if (!gameController.getMap().getObjectAt(position).isWalkable()) {
            // Handle collision with an unwalkable object
            return true;
        } else {
            return false;
        }
    }

    /**
     * Deletes the butterfly from the game, triggering its destruction sequence.
     * The destruction includes exploding a 9x9 grid of tiles and spawning diamonds in their place.
     */
    @Override
    public void delete() {
    }

    public void dostroi(Map map) {
        for (GameObject object : get9x9Grid(map)) {
            if (!(object instanceof TitaniumWall)) {
                map.getPendingRemovals().add(object);
                GridPosition diamondPos = object.getPosition();
                Gem diamond = new Gem();
                diamond.setPosition(diamondPos);
                map.getPendingAdditions().add(diamond);
            }
        }
    }

    public List<GameObject> get9x9Grid(Map map) {
        List<GameObject> destroyedObjects = new ArrayList<>();
        destroyedObjects.add(this);
        for (Direction direction : this.calculateMovePriority()) {
            GameObject orthogonalNeighbour = map.getTileNeighbourOf(this, direction);
            GameObject evilNonOrthogonalNeighbour = map.getTileNeighbourOf(orthogonalNeighbour, direction.clockwiseRotation());
            destroyedObjects.add(orthogonalNeighbour);
            destroyedObjects.add(evilNonOrthogonalNeighbour);
        }
        return destroyedObjects;
    }

    /**
     * Finds a path from the butterfly's position to the player's position using a pathfinding algorithm.
     *
     * @param map the current level map.
     * @param enemyPosition the butterfly's current position.
     * @param playerPosition the player's position.
     * @return an {@link ArrayList} of int arrays representing the path from enemy to player.
     */
}