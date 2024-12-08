/**
 * can create groups (when the level is loaded) and be assigned to groups
 * @author Oscar Anthony Baggs
 */

import java.util.ArrayList;

public class Amoeba extends Enemy {

    private static final String FILE_PATH = "resources/assets/amoeba.png";
    AmoebaGroup group;

    public Amoeba() {
        super(FILE_PATH, new GridPosition(0, 0));
        this.updateRate = 4;
    }

    public void createGroup() {
        AmoebaGroup group = new AmoebaGroup();
        this.setGroup(group);
    }

    public void setGroup(AmoebaGroup group) {
        group.addAmoeba(this);
        this.group = group;
    }

    public AmoebaGroup getGroup() {
        return this.group;
    }

    @Override
    public void update(Map map) {
    }

    @Override
    public boolean collisionCheck() {
        return false;
    }

    @Override
    public void delete() {
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
