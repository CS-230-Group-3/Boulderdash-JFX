/**
 * can create groups (when the level is loaded) and be assigned to groups
 * ALL THE IMPORTANT LOGIC IS IN AMOEBAGROUP
 * @author Oscar Anthony Baggs
 */

import java.util.ArrayList;

public class Amoeba extends Entity {

    private static final String FILE_PATH = "resources/assets/amoeba.png";

    public Amoeba() {
        super(FILE_PATH, new GridPosition(0, 0));
        this.type = "amoeba";
    }

    @Override
    public void update(Map map) {
    }

    @Override
    public void move(Map map, Direction dir) {
    }

    @Override
    public boolean collisionCheck(Map map, GridPosition position) {
        return false;
    }

    @Override
    public boolean collisionCheck(Map map, Direction dir) {
        return false;
    }
}
