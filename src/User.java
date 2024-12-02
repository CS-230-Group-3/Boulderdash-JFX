import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private final String name;
    private final ArrayList<String> unlockedLevels;

    /**
     * Creates a new user with the provided name.
     * New users start with level 1 available to play.
     * @param name name of user
     */
    public User(String name) {
        this.name = name;
        unlockedLevels = new ArrayList<>();
        unlockedLevels.add("level 1.txt");
    }

    /**
     * @return name of user
     */
    public String getName() {
        return name;
    }

    /**
     * @return a list of all unlocked levels by user.
     */
    public ArrayList<String> getUnlockedLevels() {
        return unlockedLevels;
    }
}
