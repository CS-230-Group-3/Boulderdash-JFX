public class Gem extends Item {

    private static final String FILE_PATH = "resources/assets/diamondblue.png";
    private boolean waterSkip = true;

    public Gem() {
        super(FILE_PATH, new GridPosition(0, 0));
        this.updateRate = 1;
        this.type = "gem";
    }

    public void update(Map map) {
        if (map.getTileAt(this.getPosition()) instanceof Water && !waterSkip) {
            waterSkip = true;
        } else if (map.getTileAt(this.getPosition()) instanceof Water && waterSkip) {
            waterSkip = false;
            return;
        }
        GameObject downNeighbour = map.getNeighbourOf(this, Direction.DOWN);

        if (!this.fall(map)) {
            this.roll(map);
        }
        if (downNeighbour instanceof MagicWall) {
            map.getPendingRemovals().add(this);
            GameObject belowMagicWall = map.getNeighbourOf(downNeighbour, Direction.DOWN);
            Boulder boulder = new Boulder();
            boulder.setPosition(belowMagicWall.getPosition());
            map.getPendingAdditions().add(boulder);
        }
    }
    public void delete() {
        //play gem collection sound
    }
}
