import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

/**
 * The GraphicsController class is responsible for rendering game objects
 * on the provided canvas. It handles the drawing of both tile and entity layers,
 * updates game objects based on their update rate, and manages the addition/removal
 * of objects in the game world. Additionally, it includes functionality to draw
 * a background image that covers the entire map area.
 *
 * @author Yuliia Shubina
 * @Version 1.0.1
 */
public class GraphicsController {

    /**
     * Draws the passed map on the provided canvas.
     * GameObjects are drawn on the screen based on their position in the map.
     * This method clears the canvas and redraws both the tile and entity layers.
     *
     * @param canvas the canvas to draw the map on
     * @param mapToDraw the map containing the game objects to be drawn
     */
    public void drawGame(Canvas canvas, Map mapToDraw) {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Clear the canvas before drawing new content
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Render background tiles
        for (GameObject object : mapToDraw.getTileLayer()) {
            gc.drawImage(object.getSprite(),
                    object.getPosition().getX()
                            * object.getSprite().getHeight(),
                    object.getPosition().getY()
                            * object.getSprite().getWidth());
        }

        // Render entities (players, items, etc.)
        for (GameObject object : mapToDraw.getEntityLayer()) {
            gc.drawImage(object.getSprite(),
                    object.getPosition().getX()
                            * object.getSprite().getHeight(),
                    object.getPosition().getY()
                            * object.getSprite().getWidth());
        }
    }

    /**
     * Updates each GameObject on the passed map by applying their respective
     * update logic. This includes removing and adding game objects, as well
     * as updating entities and tiles that meet the update rate criteria.
     *
     * @param map the map containing the game objects to update
     * @throws InterruptedException if the update process is interrupted
     */
    public void updateGameObjectsOnMap(Map map) throws InterruptedException {
        map.getPendingAdditions().clear();
        map.getPendingRemovals().removeIf(object -> !(object instanceof Gem || object instanceof Key ||
                object instanceof LockedDoor));

        List<Entity> entitiesToRemove = new ArrayList<>();

        // Update entities
        for (GameObject object : map.getEntityLayer()) {
            if (object.getUpdateRate() != 0 && (TimeController.getTickCount()
                    % object.getUpdateRate()) == 0) {
                object.update(map);
            }
        }

        // Update tiles
        for (GameObject object : map.getTileLayer()) {
            if (object.getUpdateRate() != 0 && (TimeController.getTickCount()
                    % object.getUpdateRate()) == 0) {
                object.update(map);
            }
        }

        // Remove objects marked for removal
        for (GameObject object : map.getPendingRemovals()) {
            if (object instanceof Entity entity) {
                entitiesToRemove.add(entity);
            } else if (object instanceof Tile tile) {
                map.destroyTile(tile);
            }
            map.getEntityLayer().removeAll(entitiesToRemove);
        }

        // Add new objects to the map
        for (GameObject object : map.getPendingAdditions()) {
            map.spawnGameObject(object);
        }

        // Clear the removal list after processing
        map.getPendingRemovals().clear();
    }

    /**
     * Draws the background image on the canvas, covering the entire map area.
     * This method is called to render the map's background with a repeating pattern.
     *
     * @param canvas the canvas to draw the background on
     * @param mapToCover the map whose size is used to determine how many times
     *                   to draw the background
     */
    private void drawBackground(Canvas canvas, Map mapToCover) {
        final String pathToBackGround = "resources/assets/path.png";
        Image bgImage = new Image(pathToBackGround);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        int mapX = mapToCover.getMapWidth();
        int mapY = mapToCover.getMapHeight();

        // Draw the background image across the entire map
        for (int width = 0; width < mapX; width++) {
            for (int height = 0; height < mapY; height++) {
                gc.drawImage(
                        bgImage,
                        width * bgImage.getWidth(),
                        height * bgImage.getHeight()
                );
            }
        }
    }
}
