import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

public class Data implements Serializable {

    private static final String PATH_TO_LEVELS = "src/resources/levels";
    private static User currentUser;
    private final ArrayList<User> users;
    private final ArrayList<Level> availableLevels;

    /**
     * Creates a new Data object.
     * Assigns the stored used and highs scores to empty.
     */
    public Data() {
        users = new ArrayList<>();
        this.availableLevels = new ArrayList<>();
    }

    /**
     * Creates a new Data object, assign the user to the passed array.
     * @param users users to assign.
     */
    public Data(ArrayList<User> users) {
        this.users = users;
        this.availableLevels = new ArrayList<>();
    }

    //TODO make it return bool to confirm if user was added?
    public void addNewUser(String name) {
        User newUser = new User(name);
        users.indexOf(newUser);
        if (!users.contains(newUser)) {
            newUser.getUnlockedLevels().
                    add(availableLevels.getFirst());
            users.add(newUser);
        }
    }

    public void setLevelsFromDirectory() {
        File dir = new File(PATH_TO_LEVELS);
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            for (File file: files) {
                availableLevels.add(
                        new Level(file.getName())
                );
            }
        }
    }

    public static void addScoreForCurrentUser(int score) {
        if (currentUser != null
                && currentUser.getCurrentLevel() != null) {
            //🍝, but it would do for now
            currentUser.
                    getCurrentLevel().
                    addUserScore(currentUser, score);
        }
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

    public ArrayList<Level> getAvailableLevels() {
        return availableLevels;
    }

}
