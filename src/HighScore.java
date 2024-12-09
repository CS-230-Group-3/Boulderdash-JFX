import java.io.Serializable;

/**
 * The HighScore class represents a high score achieved by a user. It stores the user's name
 * and the score they achieved. The class implements Serializable for saving and loading high scores.
 * @author Yuliia Shubina, Spas Dikov
 */
public class HighScore implements Serializable {

    private final int score;
    private final String userName;

    /**
     * Creates a high score with the specified user name and score.
     *
     * @param userName name of the user who achieved the score
     * @param score    the score achieved by the user
     */
    public HighScore(String userName, int score) {
        this.userName = userName;
        this.score = score;
    }

    /**
     * Returns the score of the high score.
     *
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * Returns the user name associated with the high score.
     *
     * @return the name of the user
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Returns a string representation of the high score.
     * The string is in the format "User: [userName] scored: [score]".
     *
     * @return string representation of the high score
     */
    @Override
    public String toString() {
        return "User: " + userName + " scored: " + score;
    }
}
