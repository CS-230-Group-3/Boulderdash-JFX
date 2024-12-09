import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;
import javafx.scene.robot.Robot;
import javafx.util.Duration;

/**
 * Manages the timing and updates for the game, handling ticks and checking for game events such as
 * player death or victory.
 * @author Spas Dikov, Yuliia Shubina
 * @version 1.2
 */
public class TimeController {
    private static final int MILLIS_BETWEEN_TICKS = 200;
    private final GameController gameController;
    private final Timeline tickTimeline;

    private static boolean isPaused;
    private static int tickCount = 0;

    /**
     * Creates a new TimeController and automatically starts updating the game based on the provided GameController.
     *
     * @param gameController the GameController to update the game
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

    /**
     * Handles the actions that occur on each tick of the game.
     * Updates the game objects, checks for player death or victory, and handles game end conditions.
     *
     * @throws InterruptedException if the thread is interrupted during tick handling
     */
    private void handleTick() throws InterruptedException {
        // Track the number of ticks
        tickCount++;

        gameController.getGraphicsController().updateGameObjectsOnMap(
                gameController.getMap()
        );
        gameController.getGraphicsController().drawGame(
                gameController.getCanvas(),
                gameController.getMap()
        );

        // Check if the player is dead or on top of an exit
        Player player = gameController.getMap().getPlayerObjectReference();
        if ((tickCount / 5) >= gameController.getSecondsToBeatLevel()) {
            player.die();
        }

        if (!player.getLivingState()) {
            System.out.println("Dead lol");
            handleLose();
        } else {
            Exit exit = gameController.getMap().getExitObjectReference();
            if (exit != null && player.getPosition().equals(exit.getPosition())) {
                System.out.println("Wow you won, impressive :)");
                handleVictory();
            }
        }
    }

    /**
     * Handles the actions when the player loses the game, including stopping the game and showing the pause menu.
     */
    private void handleLose() {
        Data data = Data.getInstance();
        if (data.getCurrentUser().hasLevelInProgress()) {
            data.getCurrentUser().setHasLevelInProgress(false);
        }
        gameController.setGameIsRunning(false);
        tickCount = 0;
        showPauseMenu();
    }

    /**
     * Handles the actions when the player wins the game, including updating the score, unlocking levels, and showing the pause menu.
     */
    private void handleVictory() {
        int collectedGems = gameController.getGemsCollected();
        int secondsPassed = TimeController.getTickCount() / 5;
        int remainingSeconds = gameController.getSecondsToBeatLevel() - secondsPassed;
        int playerScore = collectedGems * remainingSeconds;

        Data data = Data.getInstance();
        data.addScoreForCurrentUser(playerScore);
        if (data.getCurrentUser().hasLevelInProgress()) {
            data.getCurrentUser().setHasLevelInProgress(false);
        }

        int userLevelsUnlocked = data.getCurrentUser().getUnlockedLevels().size();
        if (userLevelsUnlocked < data.getAvailableLevels().size()) {
            data.getCurrentUser().unlockLevel(
                    data.getAvailableLevels().get(userLevelsUnlocked)
            );
        }
        gameController.setGameIsRunning(false);
        tickCount = 0;
        showPauseMenu();
    }

    /**
     * Simulates pressing the ESCAPE key to show the pause menu.
     */
    private void showPauseMenu() {
        // Learned from https://stackoverflow.com/questions/24258995/how-to-programmatically-simulate-arrow-key-presses-in-java-fx
        Robot r = new Robot();
        r.keyPress(KeyCode.ESCAPE);
        r.keyRelease(KeyCode.ESCAPE);
    }

    /**
     * Pauses the game's ticks if active, or resumes otherwise.
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
     * Returns the number of ticks that have occurred since the game started.
     *
     * @return the number of ticks
     */
    public static int getTickCount() {
        return tickCount;
    }

    /**
     * Returns the current state of the pause (whether the game is paused or not).
     *
     * @return true if the game is paused, false otherwise
     */
    public boolean isPaused() {
        return isPaused;
    }

    /**
     * Resets the tick count to 0.
     */
    public static void resetTicks() {
        tickCount = 0;
    }
}
