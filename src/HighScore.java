import java.io.Serializable;

public class HighScore implements Serializable {

    private final int score;
    private final User user;

    /**
     * Creates a high score.
     * @param user user that archived the score
     * @param score score of the user
     */
    public HighScore(User user, int score) {
        this.user = user;
        this.score = score;
    }

    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * @return user object
     */
    public User getUser() {
        return user;
    }
}
