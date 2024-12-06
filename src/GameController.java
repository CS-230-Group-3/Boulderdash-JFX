import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * GameController class is responsible for managing the core logic of the game. It
 * renders the game on the canvas, handles player input, calls for saving, loading,
 * graphics and rendering
 * @author Spas and Bailey (and likely others remember to edit)
 * @version 1.0.1
 * Last changed: 03/12/2024N
 */

public class GameController {
    private final Canvas canvas;
    private final SaveLoadController saveLoadController;
    private final GraphicsController graphicsController;
    private TimeController timeController;

    private Map map; //Check w spas

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
            this.timeController = new TimeController(this);
            this.map = saveLoadController.loadFromFile(filePath);
            graphicsController.drawGame(canvas, getMap());
        }
    }

    //Todo tmp here, will abstract to InputController
    public void handleEvent(KeyEvent event) {
        if (!timeController.isPaused()) {
            Player player = getMap().getPlayerObjectReference();
            switch (event.getCode()) {
                case RIGHT:
                case D:
                    player.move(Direction.RIGHT, getMap());
                    break;
                case LEFT:
                case A:
                    player.move(Direction.LEFT, getMap());
                    break;
                case UP:
                case W:
                    player.move(Direction.UP, getMap());
                    break;
                case DOWN:
                case S:
                    player.move(Direction.DOWN, getMap());
                    break;
                case SPACE:
                    map.getPlayerObjectReference().die();
                    break;
                case ESCAPE:
                    timeController.handlePause();
                default:
                    // Do nothing for all other keys.
                    break;
            }
            graphicsController.drawGame(canvas, getMap());

            event.consume();
        } else {
            if (event.getCode() == KeyCode.ESCAPE) {
                timeController.handlePause();
            }
            event.consume();
        }
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
