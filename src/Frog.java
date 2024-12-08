import java.util.ArrayList;

/**
 * <p>This Frog class is intended to hold information related to the Sprite, the position as well as an Update,
 * Delete and 3 Collision functions.</p>
 *
 * @author Joseph Parish.
 * @version 1.0.5
 * Last Changed: 30/11/24
 */
public class Frog extends PathfindingEnemy {

    private static final String FILE_PATH = "resources/assets/frog.png";
    /**
     * Constructor to create a new Frog instance with a given starting position.
     */
    public Frog() {
        super(FILE_PATH, new GridPosition(0,0));
        this.updateRate = 5;
    }

    /**
     * Updates the frog's state. This method is called every tick to perform actions such as moving the frog.
     */
    @Override
    public void update(Map map) {
        move(map, Direction.UP);
    }

    @Override
    public boolean collisionCheck() {
        return false;
    }

    @Override
    public boolean collisionCheck(Map map, Direction dir) {
        return false;
    }

    /**
     * Checks for potential collisions at a given position.
     * This method checks if the frog collides with the player or an impassable tile.
     *
     * @param position the position to check for a collision.
     * @return boolean indicating whether there is a collision.
     */
    public boolean collisionCheck(Map map, GridPosition position) {
        GameObject objectAt = map.getObjectAt(position);
        if (objectAt == null) {
            return true;
        } else if (position == map.getPlayerObjectReference().getPosition()) {
            map.getPlayerObjectReference().die();
            return true;
        } else if (objectAt.isWalkable()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Moves the frog based on pathfinding logic. This method uses an algorithm called A* to find
     * a path towards the player and update the frog's position accordingly.
     */
    public void move(Map map, final Direction dir) {
        ArrayList<int[]> path = AStarAlgorithm(map, this.getPosition(), map.getPlayerObjectReference().getPosition());;
        if (path != null)
        {
            if(collisionCheck(map, new GridPosition(path.get(1)[0], path.get(1)[1])))
            {
                System.out.println("Frog moving");
                this.setPosition(new GridPosition(path.get(1)[0], path.get(1)[1]));
            } else {
                System.out.println("Frog Collision");
            }
        }
    }

    /**
     * Deletes the Frog object from the game.
     */
    @Override
    public void delete() {
        System.out.println("Player object removed from the game.");
        // Add logic to remove the Frog object from the game world
    }
}

