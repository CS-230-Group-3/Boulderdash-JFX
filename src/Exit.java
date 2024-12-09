public class Exit extends Tile {

    private static final String FILE_PATH = "resources/assets/exit.png";

    public Exit() {
        super(FILE_PATH, new GridPosition(0, 0));
        this.walkable = true;
        this.destroyable = false;
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
}