import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;
import javafx.scene.robot.Robot;
import javafx.util.Duration;

public class TimeController {
    private static final int MILLIS_BETWEEN_TICKS = 200;
    private final GameController gameController;
    private final Timeline tickTimeline;

    private static boolean isPaused;

    private static int tickCount = 0;

    /**
     * Creates a new time controller, the game from the passed
     * GameController is automatically updated.
     * @param gameController the game controller to update game for
     */
    public TimeController(GameController gameController) {
        this.gameController = gameController;
        isPaused = false;

        tickTimeline = new Timeline(
                new KeyFrame(Duration.millis(MILLIS_BETWEEN_TICKS),
                        event -> {
                            try {
                                handleTick();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        })
        );
        tickTimeline.setCycleCount(Animation.INDEFINITE);
        tickTimeline.play();
    }

    private void handleTick() throws InterruptedException {
        //keep track of performed ticks
        tickCount++;

        gameController.getGraphicsController().updateGameObjectsOnMap(
                gameController.getMap()
        );
        gameController.getGraphicsController().drawGame(
                gameController.getCanvas(),
                gameController.getMap()
        );

        //Check if player is dead or on top of an exit
        Player player = gameController.getMap().getPlayerObjectReference();
        if (!player.getLivingState()) {
            System.out.println("Dead lol");
            handleLose();
        } else {
            Exit exit = gameController.getMap().getExitObjectReference();
            if (exit != null
                    && player.getPosition().equals(exit.getPosition())) {
                System.out.println("Wow you won, impressive :)");
                handleVictory();
            }
        }
    }

    private void handleLose() {
        gameController.setGameIsRunning(false);
        showPauseMenu();
    }
    private void handleVictory() {
        int collectedGems = gameController.getGemsCollected();
        int secondsPassed = TimeController.getTickCount() / 5;
        int remainingSeconds =
                gameController.getSecondsToBeatLevel() - secondsPassed;
        int playerScore = collectedGems * remainingSeconds;

        Data data = Data.getInstance();
        data.addScoreForCurrentUser(playerScore);

        int userLevelsUnlocked = data.getCurrentUser().
                getUnlockedLevels().size();
        if (userLevelsUnlocked
                < data.getAvailableLevels().size()) {
            data.getCurrentUser().unlockLevel(
                    data.getAvailableLevels().get(userLevelsUnlocked)
            );
        }
        gameController.setGameIsRunning(false);
        showPauseMenu();
    }

    private void showPauseMenu() {
        //Would be part of the handle victory & da feet
        //Learned from https://stackoverflow.com/questions/24258995/how-to-programmatically-simulate-arrow-key-presses-in-java-fx
        Robot r = new Robot();
        r.keyPress(KeyCode.ESCAPE);
        r.keyRelease(KeyCode.ESCAPE);
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
    public static int getTickCount() {
        return tickCount;
    }

    /**
     * @return the state of the timeline
     */
    public boolean isPaused() {
        return isPaused;
    }
}
