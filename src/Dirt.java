public class Dirt extends Tile {

    private static final String FILE_PATH = "resources/assets/dirt.png";

    public Dirt() {
        super(FILE_PATH, new GridPosition(0, 0));
        this.walkable = true;
        this.destroyable = true;
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
