/**
 * Abstract Entity class derived from abstract GameObject class. Includes new abstract move method.
 *
 * @author Bailey Cockett, Ollie Jones
 * @version 1.0.2
 * Last changed: 25/11/2024
 */
import java.util.Objects;

public class Key extends Item {

    private static final String FILE_PATH = "resources/assets/key.png";
    private final int id;

    private final KeyColour colour;

    public Key(KeyColour colour) {
        super(FILE_PATH, new GridPosition(0, 0));
        this.id = Objects.hash(this); // Generate a unique hash for this key
        this.colour = colour;
    }

    public void update(Map map) {
        this.fall(map);
    }

    /**
     * Gets the ID of the key.
     *
     * @return the unique hash ID
     */
    public int getId() {
        return id;
    }

    public void delete() {
        //play key collection sound
    }
}