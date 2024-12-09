import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * <p>This Goblin class is intended to hold information related to the Sprite, the position as well as an Update,
 * Delete and 3 Collision functions.</p>
 *
 * @author Diya Patel.
 * @version 1.0.5
 * Last Changed: 30/11/24
 */
public class Goblin extends PathfindingEnemy {

    private static final String FILE_PATH = "resources/assets/goblin.png";
    /**
     * Constructor to create a new Goblin instance with a given starting position.
     */
    public Goblin() {
        super(FILE_PATH, new GridPosition(0,0));
        this.updateRate = 5;
        this.type = "goblin";
    }

    /**
     * Updates the goblin's state. This method is called every tick to perform actions such as moving the goblin.
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
     * This method checks if the goblin collides with the player or an impassable tile.
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
     * Moves the goblin based on pathfinding logic. This method uses an algorithm called A* to find
     * a path towards the player and update the goblin's position accordingly.
     */
    public void move(Map map, final Direction dir) {
        ArrayList<int[]> path = AStarAlgorithm(map, this.getPosition(), map.getPlayerObjectReference().getPosition());
        if (path != null)
        {
            if(collisionCheck(map, new GridPosition(path.get(1)[0], path.get(1)[1])))
            {
                this.setPosition(new GridPosition(path.get(1)[0], path.get(1)[1]));
            } else {
                this.steal(map.getPlayerObjectReference());
                map.getPendingRemovals().add(this);
                Explosion explosion = new Explosion();
                explosion.setPosition(this.getPosition());
                map.getPendingAdditions().add(explosion);
            }
        } else {
            Random rand = new Random();
            List<GameObject> neighbours = new ArrayList<>();

            neighbours.add(map.getNeighbourOf(this, Direction.UP));
            neighbours.add(map.getNeighbourOf(this, Direction.LEFT));
            neighbours.add(map.getNeighbourOf(this, Direction.RIGHT));
            neighbours.add(map.getNeighbourOf(this, Direction.DOWN));

            List<GameObject> pathNeighbours = new ArrayList<>();
            for (GameObject neighbour : neighbours) {
                if (neighbour instanceof Path) {
                    pathNeighbours.add(neighbour);
                }
            }
            int randindex = rand.nextInt(pathNeighbours.size());
            this.setPosition(pathNeighbours.get(randindex).getPosition());
        }
    }

    /**
     * Deletes the Goblin object from the game.
     */
    @Override
    public void delete() {
        // Add logic to remove the Frog object from the game world
    }

    public void steal(Player player) {
        player.setDiamonds(player.getDiamonds() / 2);
    }
}

