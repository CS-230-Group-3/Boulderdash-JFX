import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

public class Data implements Serializable {

    private User currentUser;
    private final ArrayList<User> users;
    private ArrayList<Level> availableLevels;

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
        }
    }

    /**
     * @return the currently assigned user
     */
    public User getCurrentUser() {
        return currentUser;
    }

    public void getLevelsFromDirectory() {
        File dir = new File("src/resources/levels");
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            for (File file: files) {
//                System.out.println(file.getName());
                availableLevels.add(
                        new Level(file.getName())
                );
            }
        }
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
