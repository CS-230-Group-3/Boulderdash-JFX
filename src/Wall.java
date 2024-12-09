public class Wall extends Tile {

    private static final String FILE_PATH = "resources/assets/wall.png";

    public Wall() {
        super(FILE_PATH, new GridPosition(0, 0));
        this.walkable = false;
        this.destroyable = true;
    }

    @Override
    public void update(Map map) {

    }
}
