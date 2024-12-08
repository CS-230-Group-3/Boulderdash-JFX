import java.util.ArrayList;
import java.util.List;

/**
 * <p>This Goblin class represents a pathfinding enemy character in the game. It includes logic
 * for movement, collision handling, and interactions with the player or the game environment.</p>
 * @author Diya Patel
 */
public abstract class Goblin extends PathfindingEnemy {

    /**
     * Constructor to create a new Goblin instance with a given starting position.
     *
     * @param position the initial grid position of the goblin.
     */
    public Goblin(GridPosition position) {
        super("goblin_sprite.png", position);
    }

    /**
     * Updates the goblin's state on every game tick. This includes logic to move the goblin.
     *
     * @param gameController the game controller that manages the game state and entities.
     */
    public void update(GameController gameController) {
        move(gameController, Direction.UP);
    }

    /**
     * Checks for potential collisions at a given position.
     * If the goblin collides with the player, the player's score is reduced, and the player is moved to a safe position.
     *
     * @param gameController the game controller managing the game world.
     * @param position the position to check for a collision.
     * @return true if a collision occurs, false otherwise.
     */
    public boolean collisionCheck(GameController gameController, GridPosition position) {
        Player player = gameController.getMap().getPlayerObjectReference();

        if (position.equals(player.getPosition())) {
            int penalty = 10; // Example penalty value
            player.setDiamonds(player.getDiamonds() - penalty);
            System.out.println("Player collided with Goblin! Score reduced by " + penalty);

            GridPosition safePosition = findSafePosition(gameController.getMap());
            if (safePosition != null) {
                player.setPosition(safePosition);
                System.out.println("Player moved to a safe position: " + safePosition);
            } else {
                System.out.println("No safe position found for the player!");
            }

            return true;
        } else if (!gameController.getMap().getObjectAt(position).isWalkable()) {
            return true; // Collision with an impassable tile
        }

        return false;
    }


    /**
     * Finds a safe position for the player on the map.
     * A safe position is defined as a walkable tile that does not contain an enemy.
     *
     * @param map the game map to search for a safe position.
     * @return a {@link GridPosition} representing a safe position on the map,
     *         or {@code null} if no such position is found.
     */
    private GridPosition findSafePosition(Map map) {
        for (int x = 0; x < map.getMapWidth(); x++) { // Changed getWidth to getMapWidth
            for (int y = 0; y < map.getMapHeight(); y++) { // Changed getHeight to getMapHeight
                GridPosition position = new GridPosition(x, y);
                GameObject object = map.getObjectAt(position);

                if (object != null && object.isWalkable() && !(object instanceof Enemy)) {
                    return position;
                }
            }
        }
        return null;
    }

    /**
     * Moves the goblin based on pathfinding logic. This method uses the A* algorithm
     * to find a path towards the player and updates the goblin's position.
     *
     * @param gameController the game controller managing the game world.
     * @param dir the direction to initiate movement (not directly used in A* pathfinding).
     */
    public void move(GameController gameController, Direction dir) {
        ArrayList<int[]> path = findPath(
                gameController,
                this.getPosition(),
                gameController.getMap().getPlayerObjectReference().getPosition()
        );

        if (!path.isEmpty()) {
            int[] nextStep = path.getFirst();
            GridPosition nextPosition = new GridPosition(nextStep[0], nextStep[1]);
            if (!collisionCheck(gameController, nextPosition)) {
                this.setPosition(nextPosition);
            } else {
                System.out.println("Goblin Collision: Unable to move to " + nextPosition);
            }
        }
    }

    /**
     * Finds a path from the goblin's position to the player's position using the A* algorithm.
     *
     * @param gameController the game controller managing the game state.
     * @param enemyPosition  the goblin's current position.
     * @param playerPosition the player's current position.
     * @return an {@link ArrayList} of int arrays representing the path to the player.
     */
    public ArrayList<int[]> findPath(GameController gameController, GridPosition enemyPosition, GridPosition playerPosition) {
        return AStarAlgorithm(
                gameController.getMap(),
                enemyPosition,
                playerPosition
        );
    }

    /**
     * Deletes the Goblin object from the game.
     * This can include logic to remove the object from the game map and free up resources.
     */
    @Override
    public void delete() {
        System.out.println("Goblin removed from the game.");
    }
}


