import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents a level in the game, storing high scores and providing methods to
 * manage level data and user scores.
 * This class is responsible for adding high scores, managing file paths, and ensuring
 * the high score list does not exceed the maximum number of entries.
 * @author Spas Dikov
 * @version 1.0.2
 */
public class Level implements Serializable {

    private static final int MAX_HIGH_SCORE_ENTRIES = 10;
    private final String levelName;
    private final ArrayList<HighScore> highScores;

    /**
     * Constructs a new Level with the specified name.
     *
     * @param levelName the name of the level
     */
    public Level(String levelName) {
        this.levelName = formatLevelName(levelName);
        this.highScores = new ArrayList<>();
    }

    /**
     * Returns the file path of the level's data file.
     * The file is located under "src/resources/levels" with the level name as the file name.
     *
     * @return the file path of the level
     */
    public String getFilePath() {
        return String.format(
                "src/resources/levels/%s.txt",
                levelName
        );
    }

    /**
     * Returns the file path where the level's progress is saved.
     * The file is located under "src/resources/saves" with the level name as the file name.
     *
     * @return the file path for saving the level progress
     */
    public String getInProgressFilePath() {
        return String.format(
                "src/resources/saves/%s.txt",
                levelName
        );
    }

    /**
     * Adds a user's score to the high scores list, maintaining the list size limit.
     * If the list exceeds the maximum number of entries, the lowest score is removed.
     *
     * @param user the user whose score is to be added
     * @param score the score of the user
     */
    public void addUserScore(User user, int score) {
        HighScore newHighScore = new HighScore(user.getName(), score);
        int index = 0;

        // Insert score to list if the passed score is higher than any score stored.
        // Removes the lowest score if the list exceeds the max size.
        while (index < highScores.size() && highScores.get(index).getScore() > score) {
            index++;
        }
        if (index != highScores.size() - 1) {
            highScores.add(index, newHighScore);

            if (highScores.size() > MAX_HIGH_SCORE_ENTRIES) {
                highScores.remove(highScores.size() - 1); // Remove the last (lowest) score
            }
        } else if (index < MAX_HIGH_SCORE_ENTRIES - 1) {
            highScores.add(index, newHighScore);
        }

        // Save the updated high scores
        Data.getInstance().save();
    }

    /**
     * Returns a string representation of the level's name and its high scores.
     *
     * @return a string containing the level name and its high scores
     */
    @Override
    public String toString() {
        return this.levelName + " High Scores: " + highScores;
    }

    /**
     * Formats the level name by removing the file extension if present.
     *
     * @param name the name of the level
     * @return the formatted level name
     */
    private String formatLevelName(String name) {
        if (name.endsWith(".txt")) {
            return name.substring(0, name.lastIndexOf("."));
        }
        return name;
    }

    /**
     * Returns the list of high scores for this level.
     *
     * @return the list of high scores
     */
    public ArrayList<HighScore> getHighScores() {
        return highScores;
    }

    /**
     * Returns the name of the level.
     *
     * @return the name of the level
     */
    public String getLevelName() {
        return levelName;
    }
}
