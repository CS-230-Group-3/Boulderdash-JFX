public class Boulder extends Item {

    private static final String FILE_PATH = "resources/assets/boulder.png";

    public Boulder() {
        super(FILE_PATH, new GridPosition(0, 0));
        this.type = "boulder";
    }

    public void update(Map map) {
        GameObject downNeighbour = map.getNeighbourOf(this, Direction.DOWN);

        this.fall(map);
        this.roll(map);

        if (downNeighbour instanceof MagicWall) {
            this.delete();
            //spawn gem under magic wall
        }
    }

    public void push(Map map, Direction direction) {
        GameObject rightNeighbour = map.getNeighbourOf(this, Direction.RIGHT);
        GameObject leftNeighbour = map.getNeighbourOf(this, Direction.LEFT);

        if (direction == Direction.RIGHT) {
            if (rightNeighbour instanceof Path) {
                this.move(Direction.RIGHT);
            }
        } else if (direction == Direction.LEFT) {
            if (leftNeighbour instanceof Path) {
                this.move(Direction.LEFT);
            }
        }
    }

    private void move(Direction direction)
    {
    }

    public void delete() {
        //play transformation sound
    }
}
