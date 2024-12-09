import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * A singleton class responsible for managing user data and game levels.
 * It provides functionality for loading, saving, and managing user data,
 * including their scores and unlocked levels. It also handles the creation
 * of new users and retrieval of available levels from the filesystem.
 *
 * @author Spas Dikov
 * @version 1.0.1
 */
public class Data implements Serializable {

    private static final String PATH_TO_LEVELS = "src/resources/levels";
    private static final String PATH_TO_DATA_CLASS_SAVE = "src/resources/data/data.bin";
    private static Data data;
    private static User currentUser;
    private final ArrayList<User> users;
    private final ArrayList<Level> availableLevels;

    /**
     * Private constructor to initialize the Data object.
     * Initializes empty lists for users and levels, and sets up available levels
     * from the filesystem.
     */
    private Data() {
        users = new ArrayList<>();
        currentUser = null;
        this.availableLevels = setLevelsFromDirectory();
    }

    /**
     * Retrieves the singleton instance of the Data class.
     * If no instance exists, it loads the data from a file.
     *
     * @return the singleton Data instance
     */
    public static Data getInstance() {
        if (data == null) {
            data = load();
        }
        return data;
    }

    /**
     * Saves the current state of the Data object to a file.
     * Serializes the Data object to the specified file path.
     */
    public void save() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(PATH_TO_DATA_CLASS_SAVE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(this);
            fileOutputStream.close();
            objectOutputStream.close();
        } catch (IOException e) {
            System.out.println("Could not save data :C");
            e.printStackTrace();
        }
    }

    /**
     * Loads the Data object from a file.
     * If the file cannot be read, it resets the saved data and creates a new save file.
     *
     * @return the loaded Data object
     */
    private static Data load() {
        Data dataToLoad = null;

        try {
            FileInputStream fin = new FileInputStream(PATH_TO_DATA_CLASS_SAVE);
            ObjectInputStream ois = new ObjectInputStream(fin);
            dataToLoad = (Data) ois.readObject();
            ois.close();
            fin.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Data class mismatch: Resetting stored Data and rebuilding save file");
            Data newData = new Data();
            newData.save();
            e.printStackTrace();
            return newData;
        }
        return dataToLoad;
    }

    /**
     * Adds a new user to the list of users if they are not already present.
     * Also assigns the first available level to the new user.
     *
     * @param user the new user to add
     */
    public void addNewUser(User user) {
        if (!users.contains(user)) {
            if (user.getUnlockedLevels().isEmpty()) {
                user.getUnlockedLevels().add(availableLevels.get(0));
                users.add(user);
            }
        }
        save();
    }

    /**
     * Sets up the list of levels from the filesystem by reading the files
     * in the specified directory. Levels are sorted by their name.
     *
     * @return the sorted list of available levels
     */
    private ArrayList<Level> setLevelsFromDirectory() {
        File dir = new File(PATH_TO_LEVELS);
        ArrayList<Level> levels = new ArrayList<>();

        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            for (File file : files) {
                levels.add(new Level(file.getName()));
            }
        }
        levels.sort(Comparator.comparing(Level::getLevelName));
        save();
        return levels;
    }

    /**
     * Adds a score for the current user to their current level.
     *
     * @param score the score to add for the current user
     */
    public void addScoreForCurrentUser(int score) {
        if (currentUser != null && currentUser.getCurrentLevel() != null) {
            currentUser.getCurrentLevel().addUserScore(currentUser, score);
        }
    }

    /**
     * Retrieves the currently assigned user.
     *
     * @return the current user
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Sets the current user to the specified user.
     *
     * @param newUser the user to set as the current user
     */
    public void setCurrentUser(User newUser) {
        currentUser = newUser;
        this.save();
    }

    /**
     * Retrieves the list of all users.
     *
     * @return the list of users
     */
    public ArrayList<User> getUsers() {
        return users;
    }

    /**
     * Retrieves the list of available levels.
     *
     * @return the list of available levels
     */
    public ArrayList<Level> getAvailableLevels() {
        return availableLevels;
    }

}
