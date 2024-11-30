import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class TimeController {
    private static final int MILLIS_BETWEEN_TICK = 200;
    private final GameController gameController;
    private final Timeline tickTimeline;
    private boolean isPaused;

    private int tickCount = 0;

    /**
     * Creates a new time controller, the game from the passed
     * GameController is automatically updated.
     * @param gameController the game controller to update game for
     */
    public TimeController(GameController gameController) {
        this.gameController = gameController;
        this.isPaused = false;

        tickTimeline = new Timeline(
                new KeyFrame(Duration.millis(MILLIS_BETWEEN_TICK),
                        event -> handleTick())
        );
        tickTimeline.setCycleCount(Animation.INDEFINITE);
        tickTimeline.play();
    }

    private void handleTick() {
        //keep track of performed ticks
        tickCount++;

        gameController.getGraphicsController().updateGameObjectsOnMap(
                gameController.getMap()
        );
        gameController.getGraphicsController().drawGame(
                gameController.getCanvas(),
                gameController.getMap()
        );
    }

    /**
     * Pauses the games ticks if active,
     * resumes otherwise.
     */
    public void handlePause() {
        if (isPaused) {
            tickTimeline.play();
            isPaused = false;
        } else {
            tickTimeline.pause();
            isPaused = true;
        }
    }

    /**
     * @return Numbers of ticks since game start.
     */
    public int getTickCount() {
        return tickCount;
    }
}
