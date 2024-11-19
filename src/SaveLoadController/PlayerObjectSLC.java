package SaveLoadController;

/**
 * Concrete representation of player object.
 * Minimal representation for SLC testing.
 * @author Yuliia & Spas
 */
public class PlayerObjectSLC extends GameObjectSLC {
    private static final String FILE_PATH_TO_PLAYER_SPRITE =
            "SaveLoadController/assets/player.png";


    private int diamondsCollected;
    private int keysCollected;

    @Override
    public String toString() {
        return " PlayerObject";
    }

    /**
     * Creates a new player object with predefined path.
     * Assign its diamonds and keys to empty.
     */
    public PlayerObjectSLC() {
        super(FILE_PATH_TO_PLAYER_SPRITE);
        setDiamondsCollected(0);
        setKeysCollected(0);
    }

    /**
     * Returns the amount of diamonds.
     * @return an integer representing collected diamonds
     */
    public int getDiamondsCollected() {
        return diamondsCollected;
    }

    /**
     * Sets collected diamonds.
     * @param diamondsCollected diamonds
     */
    public void setDiamondsCollected(int diamondsCollected) {
        this.diamondsCollected = diamondsCollected;
    }

    /**
     * Returns collected keys.
     * @return an integer representing the keys collected
     */
    public int getKeysCollected() {
        return keysCollected;
    }

    /**
     * Sets collected keys.
     * @param keysCollected keys
     */
    public void setKeysCollected(int keysCollected) {
        this.keysCollected = keysCollected;
    }
}
