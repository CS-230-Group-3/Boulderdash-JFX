package SaveLoadController;

import javafx.scene.image.Image;

/**
 * Abstract class to represent every game object.
 * Holds its sprite information.
 * @author Yuliia & Spas
 */
public abstract class GameObjectSLC {
    private final Image sprite;
    private GridPosition gridPosition;

    /**
     * Create a new object with the specified sprite.
     * @param spritePath file path to sprite
     */
    public GameObjectSLC(String spritePath, GridPosition gridPosition) {
        this.sprite = new Image(spritePath);
        this.gridPosition = gridPosition;
    }

    /**
     * Returns the object's sprite.
     * @return javaFx Image instance.
     */
    public Image getSprite() {
        return sprite;
    }

    /**
     * Returns the object's grid position.
     * @return a grid position object
     */
    public GridPosition getGridPosition() {
        return gridPosition;
    }

    /**
     * Set the grid position.
     * @param gridPosition the position to set
     */
    public void setGridPosition(GridPosition gridPosition) {
        this.gridPosition = gridPosition;
    }

}
