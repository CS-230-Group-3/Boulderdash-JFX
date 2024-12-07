/**
 * Abstract Entity class derived from abstract GameObject class. Includes new abstract move method.
 *
 * @author Bailey Cockett
 * @version 1.0.1
 * Last changed: 25/11/2024
 */
import java.util.ArrayList;

public class Player extends Entity {
    private Boolean livingState;
    private int score; // high score perhaps
    private static final String SPRITE_PATH = "resources/assets/player.png";
    private Boolean isUnderwater;
    private ArrayList<Key> keyChain = new ArrayList<>();
    private GridPosition position = new GridPosition(0, 0);
    // Note, change above to be gridPosition later once it comes up.
    private int diamonds;

    /**
     * Creates a new player object.
     * Position is set to (0,0)
     */
    public Player() {
        super(SPRITE_PATH, new GridPosition(0, 0));
        this.livingState = true;
        this.keyChain = null;
        this.diamonds = 0;
        this.type = "player";
    }

    /**
     * Creates a new player object.
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
     * Starts a countdown from x number of seconds, resulting in the
     * player drowning if they stay underwater too long.
     *
     * @param number the number of seconds for the player to drown
     */
    public void underwaterCountDown(int number) throws InterruptedException {
        for (int i = number; i > 0; i--) {
            System.out.println("Time remaining underwater: " + i + " seconds");
            Thread.sleep(1000);
            if (!isUnderwater) {
                System.out.println("Player surfaced in time!");
                return;
            }
        }
        System.out.println("Player has drowned.");
        die();
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

    /**
     * Unlocks the given door if the player has the required key.
     *
     * @param door the locked door to be unlocked
     */
    public void unlockDoor(final LockedDoor door) {
        for (Key key : keyChain) {
            if (door.unlock(key)) {
                System.out.println("Door " + door + " unlocked.");
                return;
            }
        }
        System.out.println("You don't have the required key to unlock this door.");
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

   //public void move(final Direction dir, final Map map) {
   //    int x = position.getX();
   //    int y = position.getY();

   //    if (collissionCheck(map, dir)){
   //        System.out.println("Cannot move there, blocked.");
   //    } else {
   //        if (dir == Direction.UP) {
   //            System.out.println("Going Up");
   //            int[] delta = new int[]{x, y - 1}; // Flipped Y value. It hurts me too I know
   //            position = new GridPosition(delta[0], delta[1]);
   //            System.out.println("New position: " + position);
   //        } else if (dir == Direction.RIGHT) {
   //            System.out.println("Going Right");
   //            int[] delta = new int[] {x + 1, y};
   //            position = new GridPosition(delta[0], delta[1]);
   //            System.out.println("New position: " + position);


   //        } else if (dir == Direction.DOWN) {
   //            System.out.println("Going Down");
   //            int[] delta = new int[] {x, y+1}; // Flipped Y value. It hurts me too I know.
   //            position = new GridPosition(delta[0], delta[1]);
   //            System.out.println("New position: " + position);

   //        } else if (dir == Direction.LEFT) {
   //            System.out.println("Going Left");
   //            int[] delta = new int[] {x - 1, y};
   //            position = new GridPosition(delta[0], delta[1]);
   //            System.out.println("New position: " + position);
   //        }
   //    }


   //}
    @Override
    public void move(Map map, final Direction dir) {
        GridPosition newPosition = new GridPosition(0,0).add(position);
        GridPosition desiredPosition = switch (dir) {
            case UP -> newPosition.add(new GridPosition(0, -1));
            case RIGHT -> newPosition.add(new GridPosition(1, 0));
            case DOWN -> newPosition.add(new GridPosition(0, 1));
            case LEFT -> newPosition.add(new GridPosition(-1, 0));
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
            System.out.println("Dirt compressed to tile");
        }
    }

    @Override
    public boolean collisionCheck(Map map, GridPosition position) {
        GameObject gameObjectAt = map.getObjectAt(position);
        if (gameObjectAt.getType() == null) {
            return true;
        }
        switch (gameObjectAt.getType()) {
            case "enemy" -> die();
            case "tile" -> {if (!gameObjectAt.isWalkable()) {return true;}}
        }
        return false;
    }

    @Override
    public boolean collisionCheck(Map map, Direction dir) {
        return false;
    }


    @Override
    public boolean collisionCheck() {
        return false;
    }

    /**
     * Updates the player's state based on input or game events.
     */
    @Override
    public void update(Map map) {
        // Check if dead first, if dead then do endgame method in gameController
        // Example: Replace with actual keyboard input handling
        String buttonPressed = "X"; // Temporary placeholder
        if (buttonPressed.equals("W")) {
            move(map, Direction.UP);
        }
        collisionCheck();
    }


    /**
     * Handles logic for a player picking up a key
     * @param keyToPickUp the key the player should pick up
     */
    public void pickUpKey(final Key keyToPickUp) {
        keyChain.add(keyToPickUp);
        // BEFORE FINALISING - delete the key afterwards
    }

    /**
     * Deletes the player object from the game.
     */
    @Override
    public void delete() {
        System.out.println("Player object removed from the game.");
        // Add logic to remove the player object from the game world
    }

    /**
     * Returns the players collection of keys.
     * @return array list representing all player collected keys
     */
    public ArrayList<Key> getKeyChain() {
        return this.keyChain;
    }

    /**
     * Sets the player's living state to false and performs cleanup.
     */
    public void die() { //Set alive to false
        this.livingState = false;
    }

    /**
     * Returns the diamonds collected by the player.
     * @return amount of diamonds collected
     */
    public int getDiamonds() {
        return diamonds;
    }

    /**
     * Sets the diamonds collected for the player.
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
}