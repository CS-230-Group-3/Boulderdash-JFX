public class MagicWall extends Tile {

    private static final String FILE_PATH = "resources/assets/magicwall.png";

    public MagicWall() {
        super(FILE_PATH, new GridPosition(0, 0));
        this.walkable = false;
        this.destroyable = true;
    }

    @Override
    public void update(Map map) {}
}