import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class GraphicsController {

    /**
     * Draws the passed map on the provided canvas.
     * GameObjects are draw on the screen based on their position.
     * @param canvas canvas to draw to
     * @param mapToDraw map to draw to canvas
     */
    public void drawGame(Canvas canvas, Map mapToDraw) {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        //Render background
        for (GameObject object: mapToDraw.getTileLayer()) {
            gc.drawImage(object.getSprite(),
                    object.getPosition().getX()
                            * object.getSprite().getHeight(),
                    object.getPosition().getY()
                            * object.getSprite().getWidth());
        }

        for (GameObject object: mapToDraw.getEntityLayer()) {
            gc.drawImage(object.getSprite(),
                    object.getPosition().getX()
                            * object.getSprite().getHeight(),
                    object.getPosition().getY()
                            * object.getSprite().getWidth());
        }


    }

    /**
     * Updates each GameObject on the passed map.
     * @param map map to update
     */
    public void updateGameObjectsOnMap(Map map) throws InterruptedException {
        map.getPendingAdditions().clear();
        map.getPendingRemovals().clear();

        List<Entity> entitiesToRemove = new ArrayList<>();

        for (GameObject object: map.getEntityLayer()) {
            /* Update only of UpdateRate is defined
               & UpdateRate Matches TickCount */
            if (object.getUpdateRate() != 0
                    && (TimeController.getTickCount()
                        % object.getUpdateRate()) == 0) {
                object.update(map);
            }
        }

        for (GameObject object: map.getTileLayer()) {
            /* Update only of UpdateRate is defined
               & UpdateRate Matches TickCount */
            if (object.getUpdateRate() != 0
                    && (TimeController.getTickCount()
                    % object.getUpdateRate()) == 0) {
                object.update(map);
            }

        }
        for (GameObject object : map.getPendingRemovals()) {
            if (object instanceof Entity entity) {
                entitiesToRemove.add(entity);
            } else if (object instanceof Tile tile) {
                map.destroyTile(tile);
            }
        map.getEntityLayer().removeAll(entitiesToRemove);
        }
        for (GameObject object : map.getPendingAdditions()) {
            map.spawnGameObject(object);
        }
    }

    private void drawBackground(Canvas canvas, Map mapToCover) {
        final String pathToBackGround = "resources/assets/path.png";
        Image bgImage = new Image(pathToBackGround);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        int mapX = mapToCover.getMapWidth();
        int mapY = mapToCover.getMapHeight();

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
