/**
 * <p>This Abstract Enemy class extends Entity and is intended to hold information related to its living state
 * as well as 2 abstract functions to detect if the player has touched the enemy and what to do.</p>
 *
 * @author Joseph Parish.
 * @version 1.0.0
 * Last Changed: 18/11/24
 */
public abstract class Enemy extends Entity
{
    private boolean livingState; // Living state (!! I think this makes more sense to be in Entity as its in player too)

    public abstract void onPlayerCollision(); // Reacts to players touch
    public abstract boolean playerCollisionCheck(boolean Collision); // Checks if touching player
}
