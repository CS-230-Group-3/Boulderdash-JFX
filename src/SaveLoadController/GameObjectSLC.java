package SaveLoadController;

import javafx.scene.image.Image;

/**
 * Abstract class to represent every game object.
 * Holds its sprite information.
 * @author Yuliia & Spas
 */
public abstract class GameObjectSLC {
    private final Image sprite;

    /**
     * Create a new object with the specified sprite.
     * @param spritePath file path to sprite
     */
    public GameObjectSLC(String spritePath) {
        this.sprite = new Image(spritePath);
    }

    /**
     * Returns the object's sprite.
     * @return javaFx Image instance.
     */
    public Image getSprite() {
        return sprite;
    }

}
