import java.io.Serializable;
import java.util.ArrayList;

public class Level implements Serializable {
    private static final int MAX_HIGH_SCORE_ENTRIES = 10;
    private final String levelName;
    private final ArrayList<HighScore> highScores;


    public Level(String levelName) {
        this.levelName = formatLevelName(levelName);
        this.highScores = new ArrayList<>();
    }

    public String getFilePath() {
        return String.format(
                "src/resources/levels/%s.txt",
                levelName
        );
    }

    public void addUserScore(User user, int score) {
        HighScore newHighScore = new HighScore(user.getName(), score);
        int index = 0;

        /*  Insert score to list if the
            passed score is higher than any score stored.
            Removes lowest score, if limit is exceeded */
        while (index < highScores.size()
                && highScores.get(index).getScore() > score) {
            index++;
        }
        if (index != highScores.size() - 1) {
            highScores.add(index, newHighScore);

            if (highScores.size() > MAX_HIGH_SCORE_ENTRIES) {
                highScores.removeLast();
            }
        //Add score if array has room to grow
        } else if (index < MAX_HIGH_SCORE_ENTRIES - 1) {
            highScores.add(index, newHighScore);
        }

        Data.getInstance().save();
    }

    @Override
    public String toString() {
        return this.levelName + " High Scores: " + highScores;
    }

    private String formatLevelName(String name) {
        if (name.endsWith(".txt")) {
            return name.substring(
                    0, name.lastIndexOf(".")
            );
        }
        return name;
    }

    public ArrayList<HighScore> getHighScores() {
        return highScores;
    }
}
