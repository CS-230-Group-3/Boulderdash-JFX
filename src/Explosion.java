/**
 * Represents an Explosion tile in the game.
 * This tile triggers a removal action after one update cycle.
 * author: Oscar Baggs, Ahmed Zain
 * version: 1.0.2
 */
public class Explosion extends Tile {

    private static final String FILE_PATH = "resources/assets/explosion.png";
    private boolean deleteNextUpdate = false;

    /**
     * Constructs an Explosion tile with the specified file path and position.
     * The tile will be removed on the next update cycle.
     */
    public Explosion() {
        super(FILE_PATH, new GridPosition(0, 0));
        this.updateRate = 1;
    }

    /**
     * Updates the state of the Explosion tile.
     * If the flag `deleteNextUpdate` is set, the tile is scheduled for removal.
     *
     * @param map the game map
     * @throws InterruptedException if the thread is interrupted
     */
    @Override
    public void update(Map map) throws InterruptedException {
        if (deleteNextUpdate) {
            map.getPendingRemovals().add(this);
        }
        deleteNextUpdate = true;
    }
}
