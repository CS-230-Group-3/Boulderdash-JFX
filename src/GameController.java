import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;

public class GameController {
    private final Canvas canvas;
    private final SaveLoadController saveLoadController;
    private final GraphicsController graphicsController;

    private Map map; //Check w spas

    private int currentScene; //Maybe of type Scene instead?


    public GameController(Canvas canvasToDisplayGame) {
        this.canvas = canvasToDisplayGame;
        this.saveLoadController = new SaveLoadController();
        this.graphicsController = new GraphicsController();
    }

    public GameController(Canvas canvasToDisplayGame, String filePath) {
        this.canvas = canvasToDisplayGame;
        this.saveLoadController = new SaveLoadController();
        this.graphicsController = new GraphicsController();

        displayMapFromFilePth(filePath);
    }

    //TODO name change or decouple
    public void displayMapFromFilePth(String filePath) {
        if (getMap() == null) {
            this.map = saveLoadController.loadFromFile(filePath);
            graphicsController.drawGame(canvas, getMap());
        }
    }

    //Todo tmp here, will abstract to InputController
    public void handleEvent(KeyEvent event) {
        Player player = getMap().getPlayerObjectReference();
        switch (event.getCode()) {
            case RIGHT:
            case D:
                player.move(Direction.RIGHT);
                break;
            case LEFT:
            case A:
                player.move(Direction.LEFT);
                break;
            case UP:
            case W:
                player.move(Direction.UP);
                break;
            case DOWN:
            case S:
                player.move(Direction.DOWN);
                break;
            default:
                // Do nothing for all other keys.
                break;
        }
        graphicsController.drawGame(canvas, getMap());

        event.consume();
    }

    public Map getMap() {
        return map;
    }

}
