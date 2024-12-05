public class Gem extends Item {

    private static final String FILE_PATH = "resources/assets/gem.png";

    public Gem() {
        super(FILE_PATH, new GridPosition(0, 0));
    }

    public void update(Map map) {
        GameObject downNeighbour = map.getNeighbourOf(this, Direction.DOWN);

        this.fall(map);
        this.roll(map);

        if (downNeighbour instanceof MagicWall) {
            this.delete();
            //spawn boulder under magic wall
        }
    }

    public void delete() {
        //play gem collection sound
    }
}
