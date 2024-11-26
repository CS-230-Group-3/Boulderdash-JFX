import java.security.Key;
import java.util.ArrayList;
public class Player extends Entity {
    private Boolean livingState;
    private static final String SPRITE_PATH = "resources/assets/player.png";
    private ArrayList<Key> keyChain = new ArrayList<>();

    /**
     * Creates a new player object.
     * Position is set to (0,0)
     */
    public Player() {
        super(SPRITE_PATH, new GridPosition(0, 0));
        this.livingState = true;
        this.keyChain = null;
    }

    /**
     * Creates a new player object.
     * @param position the position to set player to
     */
    public Player(GridPosition position) {
        super(SPRITE_PATH, position);
        this.livingState = true;
        this.keyChain = null;
    }

    public void underwaterCountDown(int number) { // Play with timers here, but we should be coolin
        System.out.println(number);
    }

    /**
     * Adds a key to the player's keychain and logs the action.
     *
     * @param key the key to be added to the keychain
     */
    public void takeKey(Key key) {
        System.out.println("Key" + key.toString() + "Obtained");
        keyChain.add(key);
    }

    /**
     * Unlocks the given door.
     *
     * @param door the locked door to be unlocked
     */
    public void unlockDoor(LockedDoor door) {
        System.out.println("Door" + door.toString() + "unlocked");
        door.unlock();
    }

    @Override
    public void move(Direction dir) {
        if (dir == Direction.UP) {
            System.out.println("Going Up");
        } else if (dir == Direction.RIGHT) {
            System.out.println("Going Right");
        } else if (dir == Direction.DOWN) {
            System.out.println("Going Down");
        } else if (dir == Direction.LEFT) {
            System.out.println("Going Left");
        }
    }

    @Override
    public void update() {
//        move(); // Doing everything in
        collisionCheck();

    }

    @Override
    public boolean collisionCheck() {
        if (true) { // FIX LATER - If colliding
            onCollision();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onCollision() {

    }

    @Override
    public void delete() { // For graphics controller?
    }

    public ArrayList<Key> getKeyChain() {
        return this.keyChain;
    }

    private void die() {
        this.livingState = false;
        System.out.println("Player Died");
        this.delete();
    }
}
