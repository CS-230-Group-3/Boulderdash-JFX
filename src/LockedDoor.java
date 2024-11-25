public class LockedDoor {
    private boolean locked = true;

    public void unlock() {
        locked = false;
    }

    public void lock() {
        locked = true;
    }
}
