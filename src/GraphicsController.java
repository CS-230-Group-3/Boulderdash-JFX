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

        //Render background
        drawBackground(canvas, mapToDraw);

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
