public class TitaniumWall extends Tile {

    private static final String FILE_PATH = "resources/assets/titaniumwall.png";

    public TitaniumWall() {
        super(FILE_PATH, new GridPosition(0, 0));
        this.walkable = false;
        this.destroyable = false;
    }

    @Override
    public void update(Map map) {
    }

    @Override
    public void delete() {}
}