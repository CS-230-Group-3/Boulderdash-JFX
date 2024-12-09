import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private final String name;
    private boolean hasLevelInProgress;

    private final ArrayList<Level> unlockedLevels;
    private Level currentLevel;

    /**
     * Creates a new user with the provided name.
     * New users start with level 1 available to play.
     * @param name name of user
     */
    public User(String name) {
        this.name = name;
        unlockedLevels = new ArrayList<>();
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
    public ArrayList<Level> getUnlockedLevels() {
        return unlockedLevels;
    }

    public void unlockLevel(Level levelToUnlock) {
        unlockedLevels.add(levelToUnlock);
        Data.getInstance().save();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof User userToCompare)) {
            return false;
        }

        return this.getName().equals(userToCompare.getName());
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(Level selectedLevel) {
        this.currentLevel = selectedLevel;
        Data.getInstance().save();
    }

    public boolean hasLevelInProgress() {
        return hasLevelInProgress;
    }

    public void setHasLevelInProgress(boolean hasLevelInProgress) {
        this.hasLevelInProgress = hasLevelInProgress;
    }
}
