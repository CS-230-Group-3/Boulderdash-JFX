/**
 * <p>This Abstract Entity class extends GameObject and is intended to hold information related to its living state
 * as well as hold an abstract movement method.</p>
 *
 * @author Joseph Parish.
 * @version 1.0.0
 * Last Changed: 18/11/24
 */
public abstract class Entity extends GameObject
{

    private boolean livingState; // Living state

    public abstract void move(); // Movement
}
