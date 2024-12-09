/**
 * Abstract Entity class derived from abstract GameObject class. Includes new abstract move method.
 *
 * @author Bailey Cockett, Ollie Jones
 * @version 1.0.2
 * Last changed: 25/11/2024
 */
import java.util.Objects;

public abstract class Key extends Item {

    private boolean waterSkip = true;


    public Key(String pathToSprite, GridPosition gridPosition) {
        super(pathToSprite, new GridPosition(0, 0));
        this.type = "key";
    }

    public void update(Map map) {
        if (map.getTileAt(this.getPosition()) instanceof Water && !waterSkip) {
            waterSkip = true;
        } else if (map.getTileAt(this.getPosition()) instanceof Water && waterSkip) {
            waterSkip = false;
            return;
        }
        this.fall(map);
    }



    public void delete() {
        //play key collection sound





    }
}