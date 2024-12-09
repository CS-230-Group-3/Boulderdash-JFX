import java.io.Serializable;

public class HighScore implements Serializable {

    private final int score;
    private final String userName;

    /**
     * Creates a high score.
     *
     * @param userName name of user that archived the score
     * @param score    score of the user
     */
    public HighScore(String userName, int score) {
        this.userName = userName;
        this.score = score;
    }

    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * @return name of the user
     */
    public String getUserName() {
        return userName;
    }

    @Override
    public String toString() {
        return "User: " + userName + " scored: " + score;
    }
}
