public class LockedDoor {
    private final int keyId; // The ID of the key that unlocks this door
    private boolean isLocked;
    private Key key;

    public LockedDoor() {
        this.key = new Key();
        this.keyId = key.getId();
        this.isLocked = true;
    }

    /**
     * Unlocks the door if the correct key is provided.
     *
     * @param key the key to unlock the door
     * @return true if the door was successfully unlocked, false otherwise
     */
    public boolean unlock(Key key) {
        if (key.getId() == this.keyId) {
            this.isLocked = false;
            System.out.println("Door unlocked successfully.");
            return true;
        } else {
            System.out.println("Incorrect key. Door remains locked.");
            return false;
        }
    }

    /**
     * Checks if the door is locked.
     *
     * @return true if the door is locked, false otherwise
     */
    public boolean isLocked() {
        return isLocked;
    }

    @Override
    public String toString() {
        return "LockedDoor{keyId=" + keyId + ", isLocked=" + isLocked + "}";
    }
}