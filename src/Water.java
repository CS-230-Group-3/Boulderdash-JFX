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
        GameObject downNeighbour = map.getNeighbourOf(this, Direction.DOWN);
        GameObject leftNeighbour = map.getNeighbourOf(this, Direction.LEFT);
        GameObject rightNeighbour = map.getNeighbourOf(this, Direction.RIGHT);

        if (downNeighbour instanceof Path || downNeighbour instanceof Entity) {
            this.flow(map, downNeighbour);
        }
        if (leftNeighbour instanceof Path || leftNeighbour instanceof Entity) {
            this.flow(map, leftNeighbour);
        }
        if (rightNeighbour instanceof Path || rightNeighbour instanceof Entity) {
            this.flow(map, rightNeighbour);
        }
    }

    public void flow(Map map, GameObject tile) {
        GridPosition newWaterPos = tile.getPosition();
        Water water = new Water();
        water.setPosition(newWaterPos);
        map.spawnGameObject(water);
        //play flowing sound
    }

    @Override
    public boolean collisionCheck() {
        return false;
    }

    @Override
    public void delete() {}
}