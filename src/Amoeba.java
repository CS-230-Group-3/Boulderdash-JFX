/**
 * can create groups (when the level is loaded) and be assigned to groups
 * @author Oscar Anthony Baggs
 */

import java.util.ArrayList;

public class Amoeba extends Enemy {

    private static final String FILE_PATH = "resources/assets/amoeba.png";
    AmoebaGroup group;

    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};

    public Amoeba() {
        super(FILE_PATH, new GridPosition(0, 0));
        this.updateRate = 3;
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
        GameObject upNeighbour = map.getTileNeighbourOf(this, Direction.UP);
        GameObject downNeighbour = map.getTileNeighbourOf(this, Direction.DOWN);
        GameObject leftNeighbour = map.getTileNeighbourOf(this, Direction.LEFT);
        GameObject rightNeighbour = map.getTileNeighbourOf(this, Direction.RIGHT);

        if (upNeighbour instanceof Path) {
            this.move(map, Direction.UP);
        }
        if (downNeighbour instanceof Path) {
            this.move(map, Direction.DOWN);
        }
        if (leftNeighbour instanceof Path) {
            this.move(map, Direction.LEFT);
        }
        if (rightNeighbour instanceof Path) {
            this.move(map, Direction.RIGHT);
        }
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
        GridPosition newPos = this.getPosition();
        switch (dir) {
            case UP:
                newPos.add(new GridPosition(dx[3], dy[3]));
                break;
            case DOWN:
                newPos.add(new GridPosition(dx[2], dy[2]));
                break;
            case LEFT:
                newPos.add(new GridPosition(dx[0], dy[0]));
                break;
            case RIGHT:
                newPos.add(new GridPosition(dx[1], dy[1]));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + dir);
        }
        Amoeba amoeba = new Amoeba();
        amoeba.setPosition(newPos);
        map.getPendingAdditions().add(amoeba);
        System.out.println(newPos);
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
