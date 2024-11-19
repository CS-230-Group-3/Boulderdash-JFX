package SaveLoadController;

/**
 * Represent individual square on the map.
 * Each square holds its position and a reference to
 * the game object that occupies it.
 * @author Yuliia & Spas
 */
public class Square {
    private GameObjectSLC gameObjectSLC;
    private final GridPosition position;

    /**
     * Create a new empty square.
     * The game object is set to null.
     * @param position the position of the square.
     */
    public Square(GridPosition position) {
        this.position = position;
        this.gameObjectSLC = null;
    }

    /**
     * Create a new square.
     * @param position the position of teh square.
     * @param object the object that occupies the square.
     */
    public Square(GridPosition position, GameObjectSLC object) {
        this.position = position;
        this.gameObjectSLC = object;
    }

    @Override
    public String toString() {
        return position.toString() + gameObjectSLC;
    }

    /**
     * Returns the position of the square.
     * @return GridPosition object
     */
    public GridPosition getPosition() {
        return position;
    }

    /**
     * Returns game object occupying the square.
     * @return game object
     */
    public GameObjectSLC getGameObject() {
        return gameObjectSLC;
    }

    /**
     * Sets the game object to occupy the square.
     * @param gameObjectSLCToSet game object
     */
    public void setGameObject(GameObjectSLC gameObjectSLCToSet) {
        this.gameObjectSLC = gameObjectSLCToSet;
    }
}
