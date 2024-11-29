/**
 * <p>This Frog class is intended to hold information related to the Sprite, the position as well as an Update,
 * Delete and 3 Collision functions.</p>
 *
 * @author Joseph Parish.
 * @version 1.0.1
 * Last Changed: 26/11/24
 */
public class Frog extends PathfindingEnemy
{
    private int[] pos;
    private String pathToSprite;

    public Frog(int[] pos)
    {
        this.pos = pos;
        this.pathToSprite = "frog_sprite.png";
    }

    /**
     * Retrieves the current position of the frog.
     *
     * @return an array of integers representing the frog's position (e.g., [x, y])
     */
    public int[] getPosition()
    {
        return this.pos;
    }

    /**
     * Retrieves the sprite path of the frog.
     *
     * @return an array of char representing the frog's position (e.g., [x, y])
     */
    public String getPathToSprite()
    {
        return this.pathToSprite;
    }

    @Override
    public void update(Map map)
    {
        move();
    }

    @Override
    public boolean collisionCheck()
    {}

    @Override
    public void onCollision(GameObject collidingObject)
    {
        // Handle frog collision (to be implemented)
    }

    @Override
    public void playerCollisionCheck(boolean isColliding)
    {
        // Check if the frog collides with player (to be implemented)
        if (isColliding)
        {
            if (pos[0] == Player.getPosition()[0] && pos[1] == Player.getPosition()[1])
            {
                onPlayerCollision();
            }
        }
    }

    @Override
    public void onPlayerCollision()
    {
        // Player.setLivingState(False);
    }

    @Override
    public void delete()
    {
        // Delete the frog object (to be implemented)
    }

    @Override
    public void move()
    {
        //List<int[]> path = AStarAlgorithm(GameController.getMap, pos[0], pos[1], Player.getPosition[0], Player.getPosition[1]);

        //if (path != null && !path.isEmpty())
        //{
        //    if(!collisionCheck(path.get(0)))
        //    {
        //          position = path.get(0);
        //    }
        //}
    }
}
