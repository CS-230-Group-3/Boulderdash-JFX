import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Data implements Serializable {

    private User currentUser;
    private final ArrayList<User> users;
    private final HashMap<String, ArrayList<HighScore>> highScorePerLevel;

    /**
     * Creates a new Data object.
     * Assigns the stored used and highs scores to empty.
     */
    public Data() {
        users = new ArrayList<>();
        highScorePerLevel = new HashMap<>();
    }

    /**
     * Creates a new Data object, assign the user to the passed array.
     * @param users users to assign.
     */
    public Data(ArrayList<User> users) {
        this.users = users;
        highScorePerLevel = new HashMap<>();
    }


    /**
     * @return the currently assigned user
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * @param newUser user to replace current user.
     */
    public void setCurrentUser(User newUser) {
        currentUser = newUser;
    }


    /**
     * @return the list representing all users.
     */
    public ArrayList<User> getUsers() {
        return users;
    }

    public HashMap<String, ArrayList<HighScore>> getHighScorePerLevel() {
        return highScorePerLevel;
    }
}
