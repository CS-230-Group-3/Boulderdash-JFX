public class Water extends Tile {

    private static final String FILE_PATH = "resources/assets/water.png";

    public Water() {
        super(FILE_PATH, new GridPosition(0, 0));
    }

    @Override
    public void update(Map map) {
        if (map.getNeighbourOf(this, Direction.DOWN) instanceof Path) {
            this.flow(map.getNeighbourOf(this, Direction.DOWN));
        }

        if (map.getNeighbourOf(this, Direction.LEFT) instanceof Path) {
            this.flow(map.getNeighbourOf(this, Direction.LEFT));
        }

        if (map.getNeighbourOf(this, Direction.RIGHT) instanceof Path) {
            this.flow(map.getNeighbourOf(this, Direction.RIGHT));
        }

        //play sound
    }

    @Override
    public boolean collisionCheck() {
        return false;
    }

    @Override
    public void onCollision(GameObject collidingObject) {}

    @Override
    public void delete() {}

    public void flow(GameObject tile) {
        //spawn water at specified tile
    }

}