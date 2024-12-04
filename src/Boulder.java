public class Boulder extends Entity {

    private static final String FILE_PATH = "resources/assets/boulder.png";
    private boolean falling = false;

    public Boulder() {
        super(FILE_PATH, new GridPosition(0, 0));
    }


    @Override
    public void update(Map map) {}

    public void update(Map map, Player player) {

        GameObject downNeighbour = map.getNeighbourOf(this, Direction.DOWN);
        GameObject rightNeighbour = map.getNeighbourOf(this, Direction.RIGHT);
        GameObject leftNeighbour = map.getNeighbourOf(this, Direction.LEFT);

        if (this.isFalling()) {
            //If path, just move down
            if (downNeighbour instanceof Path) {
                this.move(Direction.DOWN);

                //If player, kill player and move down
            } else if (downNeighbour instanceof Player) {
                player.die();
                this.move(Direction.DOWN);

                //If enemy, kill enemy and move down
            } else if (downNeighbour instanceof Enemy) {
                downNeighbour.delete();
                this.move(Direction.DOWN);

                //If magic wall, delete boulder and spawn gem
            } else if (downNeighbour instanceof MagicWall) {
                this.delete();
                //spawn gem below magic wall

                //If it's anything else, boulder lands and stops falling
            } else {
                this.setFalling(false);
            }
        }

        //pushing logic:
        //if player is pushing and there is an empty path to be pushed into, get pushed
        //
        //rolling logic:
        //if south neighour is round tile (wall, gem, boulder)
        //if east or west neighbours are paths and the tiles below them are paths
        //roll to the east or west
    }

    public void delete() {

    }

    public boolean collisionCheck() {
        return false;
    }

    @Override
    public void onCollision(GameObject collidingObject) {

    }

    public void onCollision() {

    }

    public boolean isFalling() {
        return this.falling;
    }

    public void setFalling(boolean fallingStatus) {
        this.falling = fallingStatus;
    }

    @Override
    public void move(Direction dir) {

    }
}
