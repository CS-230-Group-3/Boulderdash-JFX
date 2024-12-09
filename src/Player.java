/**
 * Abstract Entity class derived from abstract GameObject class. Includes new abstract move method.
 *
 * @author Bailey Cockett
 * @version 1.0.1
 * Last changed: 25/11/2024
 */

import java.util.ArrayList;
import java.util.Timer;

import static java.lang.Integer.MAX_VALUE;

public class Player extends Entity {
    private Boolean livingState;
    private static final String SPRITE_PATH = "resources/assets/player.png";
    private Boolean isUnderwater = false;
    private ArrayList<Key> keyChain = new ArrayList<>();
    private GridPosition position = new GridPosition(0, 0);
    private int diamonds;
    private int tickCounter = 0;
    private int waterEntryCounter;
    private int exitCounter;
    private Direction movingDirection;

    /**
     * Creates a new player object.
     * Position is set to (0,0)
     */
    public Player() {
        super(SPRITE_PATH, new GridPosition(0, 0));
        this.livingState = true;
        this.diamonds = 0;
        this.type = "player";
        this.keyChain = new ArrayList<>();
        this.updateRate = 1;
    }

    /**
     * Creates a new player object.
     *
     * @param position the position to set player to
     */
    public Player(GridPosition position) {
        super(SPRITE_PATH, position);
        this.livingState = true;
        this.isUnderwater = false;
        this.diamonds = 0;
        this.type = "player";
    }


    /**
     * Adds a key to the player's keychain and logs the action.
     *
     * @param key the key to be added to the keychain
     */
    public void takeKey(Key key) {
        System.out.println("Key " + key.toString() + " obtained.");
        keyChain.add(key);
    }

    public GridPosition getPosition() {
        return position;
    }

    /**
     * Sets the player's position.
     *
     * @param position the new position to set
     */
    public void setPosition(GridPosition position) {
        this.position = position;
    }

    /**
     * Moves the player in the direction specified by keyboard input by updating its position.
     *
     * @param dir the direction to move (UP, RIGHT, DOWN, or LEFT)
     */
    @Override
    public void move(Map map, final Direction dir) {
        GridPosition newPosition = new GridPosition(0, 0).add(position);
        GridPosition desiredPosition = switch (dir) {
            case UP -> {
                movingDirection = Direction.UP;
                yield newPosition.add(new GridPosition(0, -1));
            }
            case RIGHT -> {
                movingDirection = Direction.RIGHT;
                yield newPosition.add(new GridPosition(1, 0));
            }
            case DOWN -> {
                movingDirection = Direction.DOWN;
                yield newPosition.add(new GridPosition(0, 1));
            }
            case LEFT -> {
                movingDirection = Direction.LEFT;
                yield newPosition.add(new GridPosition(-1, 0));
            }
        };

        if (!collisionCheck(map, desiredPosition)) {
            compressDirt(map, desiredPosition);
            setPosition(desiredPosition);
        } else {
            System.out.println("Collision");
        }
    }

    private void compressDirt(Map map, GridPosition desiredPosition) {
        if (map.getObjectAt(desiredPosition) instanceof Dirt) {
            Tile dirtToSquish = (Tile) (map.getObjectAt(desiredPosition));
            map.destroyTile(dirtToSquish);
            System.out.println("Dirt compressed to path.");
        }
    }

    private void pushBoulder(Map map, Direction dir, Boulder boulder) {
        GameObject tilePushingInto = map.getNeighbourOf(boulder, dir);
        if ((dir == Direction.UP || dir == Direction.DOWN)) {
            return;
        }
        if (tilePushingInto instanceof Path || tilePushingInto instanceof Water) {
            boulder.push(map, dir);
            move(map, dir);
        }
    }

    @Override
    public boolean collisionCheck(Map map, GridPosition position) {
        GameObject gameObjectAt = map.getObjectAt(position);
        if (gameObjectAt.getType() == null) {
            return true;
        }
        switch (gameObjectAt.getType()) {
            case "amoeba":
                return true;
            case "goblin":
                return true;
            case "enemy":
                die();
            case "key":
                pickUpKey(map, (Key) gameObjectAt);
                return true;
            case "tile":
                return !gameObjectAt.isWalkable();
            case "boulder":
                Boulder boulderToPush = (Boulder) gameObjectAt;
                pushBoulder(map, movingDirection, boulderToPush);
                System.out.println("Boulder detected");
                return true;
            case "gem":
                map.getPendingRemovals().add(gameObjectAt);
                this.diamonds++;
                this.setPosition(gameObjectAt.getPosition());
                return true;
            case "door":
                LockedDoor doorObject = (LockedDoor) gameObjectAt;
                doorObject.unlock(map, keyChain);
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean collisionCheck(Map map, Direction dir) {
        return false;
    }

    /**
     * Updates the player's state based on input or game events.
     */
    @Override
    public void update(Map map) {
        tickCounter++;
        System.out.println("PLAYER UPDATING");
        checkForWater(map, position);
        System.out.println("Current tick: " + tickCounter);
        checkForDrowning();
        System.out.println(keyChain);
    }

    /**
     * Starts a countdown from x number of seconds, resulting in the
     * player drowning if they stay underwater too long.
     */
    public void underwaterCountDown() {
        int drowningTime = 5;
        exitCounter = waterEntryCounter + (5 * drowningTime);
        System.out.println("Countdown begun. Start: " + waterEntryCounter + " End: " + exitCounter);
    }

    private boolean checkForWater(Map map, GridPosition currentPosition) {
        if (map.getTileAt(currentPosition) instanceof Water) {
            if (!isUnderwater) {
                isUnderwater = true;
                waterEntryCounter = tickCounter;
                System.out.println("UNDERWATER DETECTED");
                underwaterCountDown();
                return true;
            }
        } else {
            if (isUnderwater) {
                System.out.println("Exiting water");
                System.out.println("Countdown end. Seconds remaining: " + getSecondsLeft());
                isUnderwater = false;
                exitCounter = 0;
            }
        }
        return false;
    }

    private void checkForDrowning() {
        if (tickCounter == exitCounter) {
            die();
        }
    }


    /**
     * Handles logic for a player picking up a key
     *
     * @param keyToPickUp the key the player should pick up
     */
    public void pickUpKey(Map map, final Key keyToPickUp) {
        keyChain.add(keyToPickUp);
        map.getPendingRemovals().add(keyToPickUp);
        this.setPosition(keyToPickUp.getPosition());
    }

    /**
     * Returns the players collection of keys.
     *
     * @return array list representing all player collected keys
     */
    public ArrayList<Key> getKeyChain() {
        return this.keyChain;
    }

    /**
     * Sets the player's living state to false and performs cleanup.
     */
    public void die() {
        this.livingState = false;
    }

    /**
     * Returns the diamonds collected by the player.
     *
     * @return amount of diamonds collected
     */
    public int getDiamonds() {
        return diamonds;
    }

    /**
     * Sets the diamonds collected for the player.
     *
     * @param diamondsCollected amount
     */
    public void setDiamonds(int diamondsCollected) {
        this.diamonds = diamondsCollected;
    }

    /**
     * @return the current state of player
     */
    public Boolean getLivingState() {
        return livingState;
    }

    public int getSecondsLeft() {
        return Math.floorDiv((exitCounter - tickCounter), 5);
    }
}