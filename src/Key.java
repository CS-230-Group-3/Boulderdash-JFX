/**
 * Abstract Entity class derived from abstract GameObject class. Includes new abstract move method.
 *
 * @author Bailey Cockett, Ollie Jones
 * @version 1.0.2
 * Last changed: 25/11/2024
 */

import java.util.Objects;

public class Key {
    private final int id;

    public Key() {
        this.id = Objects.hash(this); // Generate a unique hash for this key
    }

    /**
     * Gets the ID of the key.
     *
     * @return the unique hash ID
     */
    public int getId() {
        return id;
    }

    /**
     * Formats information about the key into a string and returns it
     *
     * @return a string representation of e
     */
    @Override
    public String toString() {
        return "Key{id=" + id + "}";
    }
}