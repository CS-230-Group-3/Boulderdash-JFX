import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents a user in the game with their name, unlocked levels,
 * and the current level in progress.
 *
 * This class allows for tracking the user's progress, including
 * the levels they have unlocked and the level they are currently playing.
 *
 * @author Yuulia Shubina, Spas Dikov, Diya Patel
 * @version 1.0
 */
public class User implements Serializable {
    private final String name;
    private boolean hasLevelInProgress;
    private final ArrayList<Level> unlockedLevels;
    private Level currentLevel;

    /**
     * Creates a new user with the provided name.
     * New users start with level 1 available to play.
     *
     * @param name name of the user
     */
    public User(String name) {
        this.name = name;
        unlockedLevels = new ArrayList<>();
    }

    /**
     * Gets the name of the user.
     *
     * @return name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the list of all levels that the user has unlocked.
     *
     * @return list of unlocked levels
     */
    public ArrayList<Level> getUnlockedLevels() {
        return unlockedLevels;
    }

    /**
     * Unlocks a new level for the user.
     * The level will be added to the user's unlocked levels list.
     *
     * @param levelToUnlock the level to unlock
     */
    public void unlockLevel(Level levelToUnlock) {
        unlockedLevels.add(levelToUnlock);
        Data.getInstance().save();
    }

    /**
     * Checks whether this user is the same as another user.
     *
     * @param obj the object to compare with this user
     * @return true if the users have the same name, false otherwise
     */
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

    /**
     * Gets the level that the user is currently playing.
     *
     * @return the current level the user is playing
     */
    public Level getCurrentLevel() {
        return currentLevel;
    }

    /**
     * Sets the current level for the user.
     * This will save the current state.
     *
     * @param selectedLevel the level to set as the current level
     */
    public void setCurrentLevel(Level selectedLevel) {
        this.currentLevel = selectedLevel;
        Data.getInstance().save();
    }

    /**
     * Checks whether the user has a level in progress.
     *
     * @return true if the user has a level in progress, false otherwise
     */
    public boolean hasLevelInProgress() {
        return hasLevelInProgress;
    }

    /**
     * Sets whether the user has a level in progress.
     * This will save the current state.
     *
     * @param hasLevelInProgress true if the user has a level in progress, false otherwise
     */
    public void setHasLevelInProgress(boolean hasLevelInProgress) {
        this.hasLevelInProgress = hasLevelInProgress;
        Data.getInstance().save();
    }
}
