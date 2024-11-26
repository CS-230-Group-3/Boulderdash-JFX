import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;


public class GraphicsController {

    private final Canvas canvas;

    private final Map mapToDisplay;

    public GraphicsController(Canvas canvas, Map mapToDisplay) {
        this.canvas = canvas;
        this.mapToDisplay = mapToDisplay;
    }

    public void drawGame() {
        GraphicsContext gc = getCanvas().getGraphicsContext2D();

        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (GameObject object: getMapToDisplay().getObjects()) {
            if (!(object instanceof Player)) {
                gc.drawImage(object.getSprite(),
                        object.getPosition().getX()
                                * object.getSprite().getHeight(),
                        object.getPosition().getY()
                                * object.getSprite().getWidth());
            }
        }
        //Render player last to prevent Z-ordering issues
        Player player = getMapToDisplay().getPlayerObjectReference();
        Image playerSprite = player.getSprite();
        gc.drawImage(playerSprite,
                player.getPosition().getX()
                        * playerSprite.getHeight(),
                player.getPosition().getY()
                        * playerSprite.getWidth());
    }

    //Todo tmp here, will abstract to InputController
    public void handleEvent(KeyEvent event) {
        Player player = getMapToDisplay().getPlayerObjectReference();
        switch (event.getCode()) {
            case RIGHT:
            case D:
                player.move(new GridPosition(1, 0));
                break;
            case LEFT:
            case A:
                player.move(new GridPosition(-1, 0));
                break;
            case UP:
            case W:
                player.move(new GridPosition(0, -1));
                break;
            case DOWN:
            case S:
                player.move(new GridPosition(0, 1));
                break;
            default:
                // Do nothing for all other keys.
                break;
        }
        drawGame();

        event.consume();
    }

    public Canvas getCanvas() {
        return canvas;
    }
    public Map getMapToDisplay() {
        return mapToDisplay;
    }

}
