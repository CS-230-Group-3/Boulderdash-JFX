import java.util.ArrayList;

/**
 * <p>This Abstract Enemy class extends Entity
 * and is intended to hold an abstract functions to find its future moves.</p>
 *
 * @author Joseph Parish.
 * @version 1.0.4
 * Last Changed: 30/11/24
 */
public abstract class Enemy extends Entity
{
    private Boolean livingState;
    private int[][] path;

    public Enemy(String pathToSprite, GridPosition position) {
        super(pathToSprite, position);
        this.type = "enemy";
    }
}
