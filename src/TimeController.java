import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class TimeController {
    final GameController gameController;
    private static final int MILLIS_BETWEEN_TICK = 200;

    /**
     * Creates a new time controller, the game from the passed
     * GameController is automatically updated.
     * @param gameController the game controller to update game for
     */
    public TimeController(GameController gameController) {
        this.gameController = gameController;

        Timeline tickTimeline = new Timeline(
                new KeyFrame(Duration.millis(MILLIS_BETWEEN_TICK),
                        event -> handleTick())
        );
        tickTimeline.setCycleCount(Animation.INDEFINITE);
        tickTimeline.play();
    }

    private void handleTick() {
        //💀
        //TODO keep track of performed ticks
        gameController.getGraphicsController().updateGameObjectsOnMap(
                gameController.getMap()
        );
        gameController.getGraphicsController().drawGame(
                gameController.getCanvas(),
                gameController.getMap()
        );

    }
}
