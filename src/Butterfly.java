/**
 * The Butterfly class represents a specific type of enemy with unique movement patterns.
 * Its movement involves a priority-based system with 90-degree rotations.
 * The direction in which it "faces" is tracked by {@code lastMovement}, and
 * {@code leftMoving} determines whether it prefers anti-clockwise or clockwise rotations.
 *
 * @author: Oscar Baggs, Joseph Paris, Diya Patel
 * @version 1.0.1
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Butterfly extends PatrollingEnemy {

    private static final String FILE_PATH = "resources/assets/butterfly.png";
    private boolean isleftMoving;
    private Direction lastMovement = Direction.UP;

    /**
     * Butterfly Constructor
     * its movement preference (left or right) randomly with a 50/50 chance.
     */
    public Butterfly() {
        super(FILE_PATH, new GridPosition(0, 0));
        Random random = new Random();
        isleftMoving = random.nextInt(2) == 0;
        this.updateRate = 3;
        this.type = "enemy";
    }

    /**
     * Updates the butterfly's state. This method calculates and executes its next move.
     * @param map the current level map.
     */
    @Override
    public void update(Map map) {
        calculateNextMove(map);
    }

    /**
     * Checks for collisions at a specific position on the map.
     * @param map      the current level map.
     * @param position the position to check for collisions.
     * @return true if a collision is detected, false otherwise.
     */
    @Override
    public boolean collisionCheck(Map map, GridPosition position) {
        return false;
    }

    /**
     * Checks for collisions in the specified direction on the map.
     * @param map the current level map.
     * @param dir the direction to check for collisions.
     * @return true if a collision is detected, false otherwise.
     */
    @Override
    public boolean collisionCheck(Map map, Direction dir) {
        return false;
    }

    /**
     * Checks for collisions at a specific position, including interactions with the player.
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
     * Destroies destroyable tiles in 9x9 grid and spawns gems
     * @param map
     */
    public void destroy(Map map) {
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

    /**
     * Gets instances of objects to be destroyed in 9x9 grid
     * @param map
     */
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
}