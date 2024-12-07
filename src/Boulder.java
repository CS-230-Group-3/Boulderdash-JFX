public class Boulder extends Item {

    private static final String FILE_PATH = "resources/assets/boulder.png";

    public Boulder() {
        super(FILE_PATH, new GridPosition(0, 0));
        this.type = "boulder";
        this.updateRate = 5;
    }

    public void update(Map map) {
        GameObject downNeighbour = map.getNeighbourOf(this, Direction.DOWN);

        if (!this.fall(map)) {
            this.roll(map);
        }
        if (downNeighbour instanceof MagicWall) {
            this.delete();
            //spawn gem under magic wall
        }
    }

    public void push(Map map, Direction direction) {
        System.out.println("Push done");
        GameObject rightNeighbour = map.getNeighbourOf(this, Direction.RIGHT);
        GameObject leftNeighbour = map.getNeighbourOf(this, Direction.LEFT);

        if (direction == Direction.RIGHT) {
            if (rightNeighbour instanceof Path || rightNeighbour instanceof Water) {
                System.out.println("Moving right");
                this.move(map, Direction.RIGHT);
            }
        } else if (direction == Direction.LEFT) {
            if (leftNeighbour instanceof Path || leftNeighbour instanceof Water) {
                System.out.println("Moving left");
                this.move(map, Direction.LEFT);
            }
        }
    }

    public void delete() {
        //play transformation sound
    }
}
