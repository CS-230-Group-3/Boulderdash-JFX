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

    @Override
    public String toString() {
        return "Key{id=" + id + "}";
    }
}