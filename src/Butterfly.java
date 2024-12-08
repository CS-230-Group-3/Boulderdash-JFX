/**
 * The Butterfly class represents a specific type of enemy with unique movement patterns.
 * Its movement involves a priority-based system with 90-degree rotations.
 * The direction in which it "faces" is tracked by {@code lastMovement}, and
 * {@code leftMoving} determines whether it prefers anti-clockwise or clockwise rotations.
 */

import java.util.ArrayList;
import java.util.Random;

public class Butterfly extends PatrollingEnemy {

    /** The file path to the butterfly's sprite image. */
    private static final String FILE_PATH = "resources/assets/butterfly.png";

    /**
     * Constructs a Butterfly object, initialising its position and determining
     * its movement preference (left or right) randomly with a 50/50 chance.
     */
    public Butterfly() {
        super(FILE_PATH, new GridPosition(0, 0));
        Random random = new Random();
        this.leftMoving = random.nextInt(2) == 0;
        this.updateRate = 2;
    }

    /**
     * Updates the butterfly's state. This method calculates and executes its next move.
     *
     * @param map the current level map.
     */
    @Override
    public void update(Map map) {
        super.update(map);
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
        return super.collisionCheck(map, position);
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
        return super.collisionCheck(map, dir);
    }

    /**
     * Checks for collisions without specifying a direction or position.
     *
     * @return true if a collision is detected, false otherwise.
     */
    @Override
    public boolean collisionCheck() {
        return super.collisionCheck();
    }

    /**
     * Deletes the butterfly from the game, triggering its destruction sequence.
     * The destruction includes exploding a 9x9 grid of tiles and spawning diamonds in their place.
     */
    @Override
    public void delete() {
        //explode - destroy a 9x9 grid of tiles and spawn diamonds in their places.
    }
}