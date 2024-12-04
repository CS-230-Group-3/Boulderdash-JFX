/**
 * <p>This Frog class is intended to hold information related to the Sprite, the position as well as an Update,
 * Delete and 3 Collision functions.</p>
 *
 * @author Joseph Parish.
 * @version 1.0.2
 * Last Changed: 30/11/24
 */
public class Frog extends PathfindingEnemy {

    /**
     * Constructor to create a new Frog instance with a given starting position.
     * @param position the initial grid position of the frog.
     */
    public Frog(GridPosition position) {
        // Call the superclass constructor with sprite path and initial position
        super("frog_sprite.png", position);
    }

    /**
     * Updates the frog's state. This method is called every tick to perform actions such as moving the frog.
     */
    @Override
    public void update() {
        move();
    }

    /**
     * Checks for potential collisions at a given position.
     * This method checks if the frog collides with the player or an impassable tile.
     *
     * @param position the position to check for a collision.
     * @return boolean indicating whether there is a collision.
     */
    @Override
    public boolean collisionCheck(int[] position) {
        //  if (position == Player.getPosition()) {
        //      // Player.setLivingState(False);
        //      return true;
        //  } else if (!GameController.getMap().getGameObjectAt(position).isWalkable()) {
        //      return true;
        //  } else {
        //      return false;
        //  }
    }

    /**
     * Moves the frog based on pathfinding logic. This method uses an algorithm called A* to find
     * a path towards the player and update the frog's position accordingly.
     */
    @Override
    public void move() {
        //List<int[]> path = AStarAlgorithm(GameController.getMap, pos[0], pos[1], Player.getPosition[0], Player.getPosition[1]);

        //if (path != null && !path.isEmpty())
        //{
        //    if(!collisionCheck(path.get(0)))
        //    {
        //          if(!collisionCheck(path.get(0))) {
        //              position = path.get(0);
        //          } else {
        //              System.out.println("Frog Collision");
        //          }
        //    }
        //}
    }

    /**
     * Deletes the frog object from the game.
     */
    @Override
    public void delete() {
        // Logic to remove the frog object (e.g., freeing resources) would go here
        System.out.println("Frog deleted");
    }
}
