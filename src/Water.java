public class Water extends Tile {

    private static final String FILE_PATH = "resources/assets/water.png";

    public Water() {
        super(FILE_PATH, new GridPosition(0, 0));
        this.walkable = true;
        this.destroyable = false;
        this.updateRate = 5;
    }

    @Override
    public void update(Map map) {
        GameObject downNeighbour = map.getTileNeighbourOf(this, Direction.DOWN);
        GameObject leftNeighbour = map.getTileNeighbourOf(this, Direction.LEFT);
        GameObject rightNeighbour = map.getTileNeighbourOf(this, Direction.RIGHT);

        if (downNeighbour instanceof Path) {
            this.flow(map, downNeighbour);
        }
        if (leftNeighbour instanceof Path) {
            this.flow(map, leftNeighbour);
        }
        if (rightNeighbour instanceof Path) {
            this.flow(map, rightNeighbour);
        }
    }

    public void flow(Map map, GameObject tile) {
        GridPosition newWaterPos = tile.getPosition();
        Water water = new Water();
        water.setPosition(newWaterPos);
        map.getPendingObjects().add(water);
        System.out.println("Flowing");
        //play flowing sound
    }

    @Override
    public boolean collisionCheck() {
        return false;
    }

    @Override
    public void delete() {}
}