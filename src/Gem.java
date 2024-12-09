/**
 * The Gem class represents a gem item that can be collected by the player.
 * It is a type of item that interacts with the map, including behaviors for
 * falling, rolling, and interacting with other game objects such as water
 * and magic walls.
 * @author Oscar Baggs
 * @version 1.0.2
 */
public class Gem extends Item {

    private static final String FILE_PATH = "resources/assets/diamondblue.png";
    private boolean waterSkip = true;

    /**
     * Constructs a new Gem object with the specified image file path and initial position.
     * The gem is placed at the (0, 0) grid position.
     */
    public Gem() {
        super(FILE_PATH, new GridPosition(0, 0));
        this.updateRate = 1;
        this.type = "gem";
    }

    /**
     * Updates the position and state of the Gem object on the map.
     * Handles interactions with water tiles and magic walls, including
     * the behavior of the gem falling or rolling across the map.
     *
     * @param map the map where the Gem is located, used for checking neighbors and tiles
     */
    public void update(Map map) {
        if (map.getTileAt(this.getPosition()) instanceof Water && !waterSkip) {
            waterSkip = true;
        } else if (map.getTileAt(this.getPosition()) instanceof Water && waterSkip) {
            waterSkip = false;
            return;
        }

        GameObject downNeighbour = map.getNeighbourOf(this, Direction.DOWN);

        if (!this.fall(map)) {
            this.roll(map);
        }

        if (downNeighbour instanceof MagicWall) {
            map.getPendingRemovals().add(this);
            GameObject belowMagicWall = map.getNeighbourOf(downNeighbour, Direction.DOWN);
            Boulder boulder = new Boulder();
            boulder.setPosition(belowMagicWall.getPosition());
            map.getPendingAdditions().add(boulder);
        }
    }

    /**
     * Handles the deletion of the Gem object, typically triggered when the gem is collected.
     */
    public void delete() {
        //play gem collection sound
    }
}
