import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;

public class GameController {
    private final Canvas canvas;
    private final SaveLoadController saveLoadController;
    private final GraphicsController graphicsController;

    private Map map;

    private int currentScene; //Maybe of type Scene instead?

    /**
     * Creates a Game Controller,
     * responsible for displaying & handling logic for the game.
     * @param canvasToDisplayGame the canvas to display the game on
     */
    public GameController(Canvas canvasToDisplayGame) {
        this.canvas = canvasToDisplayGame;
        this.saveLoadController = new SaveLoadController();
        this.graphicsController = new GraphicsController();
    }

    /**
     * Creates a Game Controller,
     * responsible for displaying & handling logic for the game.
     * @param canvasToDisplayGame the canvas to display the game on
     * @param filePath file path to the level
     */
    public GameController(Canvas canvasToDisplayGame, String filePath) {
        this.canvas = canvasToDisplayGame;
        this.saveLoadController = new SaveLoadController();
        this.graphicsController = new GraphicsController();

        displayMapFromFilePth(filePath);
    }

    /**
     * Displays map from the provided file path, whenever
     * a map has not been assigned.
     * @param filePath file path to the level
     */
    public void displayMapFromFilePth(String filePath) {
        if (getMap() == null) {
            //Only create a TimeController if there is a map.
            new TimeController(this);
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
            case SPACE:
                graphicsController.updateGameObjectsOnMap(map);
                graphicsController.drawGame(canvas, map);
            default:
                // Do nothing for all other keys.
                break;
        }
        graphicsController.drawGame(canvas, getMap());

        event.consume();
    }

    /**
     * @return the current map
     */
    public Map getMap() {
        return map;
    }

    /**
     * Returns the canvas the games is displayed on.
     * @return canvas object containing the game
     */
    public Canvas getCanvas() {
        return canvas;
    }

    /**
     * Returns the graphics controller for game.
     * @return graphics controller instance
     */
    public GraphicsController getGraphicsController() {
        return graphicsController;
    }
}
