/**
 * <p>This Abstract Enemy class extends Entity and is intended to hold 2 abstract functions to detect if the player has
 * touched the enemy and what to do.</p>
 *
 * @author Joseph Parish.
 * @version 1.0.1
 * Last Changed: 18/11/24
 */
public abstract class Enemy extends Entity
{
    private int[][] path; // Path of future movement
    
    public abstract int[][] findPath(int[][] map, int[] enemyPos, int[] playerPos); // Finds path
    public abstract void onPlayerCollision(); // Reacts to players touch
    public abstract boolean playerCollisionCheck(boolean Collision); // Checks if touching player
}
