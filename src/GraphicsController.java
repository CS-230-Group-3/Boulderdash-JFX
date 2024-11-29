import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


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
        for (GameObject object: mapToDraw.getObjects()) {
            if (!(object instanceof Player)) {
                gc.drawImage(object.getSprite(),
                        object.getPosition().getX()
                                * object.getSprite().getHeight(),
                        object.getPosition().getY()
                                * object.getSprite().getWidth());
            }
        }
        //Render player last to prevent Z-ordering issues
        Player player = mapToDraw.getPlayerObjectReference();
        Image playerSprite = player.getSprite();
        gc.drawImage(playerSprite,
                player.getPosition().getX()
                        * playerSprite.getHeight(),
                player.getPosition().getY()
                        * playerSprite.getWidth());
    }

    /**
     * Updates each GameObject on the passed map.
     * @param map map to update
     */
    public void updateGameObjectsOnMap(Map map) {
        for (GameObject object: map.getObjects()) {
            object.update(map);
        }
    }
}
