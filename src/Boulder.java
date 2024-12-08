public class Boulder extends Item {

    private static final String FILE_PATH = "resources/assets/boulder.png";
    private boolean waterSkip = true;

    public Boulder() {
        super(FILE_PATH, new GridPosition(0, 0));
        this.type = "boulder";
        this.updateRate = 3;
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
            Gem diamond = new Gem();
            diamond.setPosition(belowMagicWall.getPosition());
            map.getPendingAdditions().add(diamond);
        }
    }

    public void push(Map map, Direction direction) {
        GameObject rightNeighbour = map.getNeighbourOf(this, Direction.RIGHT);
        GameObject leftNeighbour = map.getNeighbourOf(this, Direction.LEFT);

        if (direction == Direction.RIGHT) {
            if (rightNeighbour instanceof Path || rightNeighbour instanceof Water) {
                this.move(map, Direction.RIGHT);
            }
        } else if (direction == Direction.LEFT) {
            if (leftNeighbour instanceof Path || leftNeighbour instanceof Water) {
                this.move(map, Direction.LEFT);
            }
        }
    }

    public void delete() {
        //play transformation sound
    }
}
