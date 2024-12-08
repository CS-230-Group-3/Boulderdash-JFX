import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class Data implements Serializable {

    private static final String PATH_TO_LEVELS = "src/resources/levels";
    private static final String PATH_TO_DATA_CLASS_SAVE =
            "src/resources/data/data.bin";
    private static Data data;
    private static User currentUser;
    private final ArrayList<User> users;
    private final ArrayList<Level> availableLevels;

    /**
     * Creates a new Data object.
     * Assigns the stored used and highs scores to empty.
     */
    private Data() {
        users = new ArrayList<>();
        currentUser = null;
        this.availableLevels = setLevelsFromDirectory();
    }

    public static Data getInstance() {
        if (data == null) {
            data = load();
        }

        return data;
    }

    public void save() {
        try {
            FileOutputStream fileOutputStream =
                    new FileOutputStream(PATH_TO_DATA_CLASS_SAVE);
            ObjectOutputStream objectOutputStream =
                    new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(this);

            fileOutputStream.close();
            objectOutputStream.close();
        } catch (IOException e) {
            System.out.println("Could not save data :C");
            e.printStackTrace();
        }
    }

    private static Data load() {
        Data dataToLoad = null;

        try {
            FileInputStream fin = new FileInputStream(PATH_TO_DATA_CLASS_SAVE);
            ObjectInputStream ois = new ObjectInputStream(fin);

            dataToLoad = (Data) ois.readObject();

            ois.close();
            fin.close();
        } catch (IOException | ClassNotFoundException e) {

            System.out.println("Data class mismatch: Resetting stored Data "
                    + "and rebuilding save file");
            Data newData = new Data();
            newData.save();

            e.printStackTrace();
            return newData;
        }
        return dataToLoad;
    }

    //TODO make it return bool to confirm if user was added?
    public void addNewUser(User user) {
        if (!users.contains(user)) {
            if (user.getUnlockedLevels().isEmpty()) {
                user.getUnlockedLevels().
                        add(availableLevels.getFirst());
                users.add(user);
            }
        }
        save();
    }

    private ArrayList<Level> setLevelsFromDirectory() {
        File dir = new File(PATH_TO_LEVELS);
        ArrayList<Level> levels = new ArrayList<>();

        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            for (File file: files) {
                levels.add(
                        new Level(file.getName())
                );
            }
        }
        levels.sort(Comparator.comparing(Level::getLevelName));
        save();
        return levels;
    }

    public void addScoreForCurrentUser(int score) {
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
        this.save();
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
