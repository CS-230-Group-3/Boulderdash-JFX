public class Item extends Entity {

    private boolean falling = false;

    public Item(String pathToSprite, GridPosition position) {
        super(pathToSprite, position);
    }

    public void fall(Map map) {
        GameObject downNeighbour = map.getNeighbourOf(this, Direction.DOWN);

        if (this.isFalling()) {
            if (downNeighbour instanceof Path) {
                this.move(Direction.DOWN);

                //kill player if gem or boulder
            } else if (downNeighbour instanceof Player && this instanceof Gem ||
                    this instanceof Boulder) {
                map.getPlayerObjectReference().die();
                this.move(Direction.DOWN);

            } else if (downNeighbour instanceof Enemy) {
                downNeighbour.delete();
                this.move(Direction.DOWN);

            } else {
                this.setFalling(false);
            }
        }
    }

    public void roll(Map map) {
        GameObject downNeighbour = map.getNeighbourOf(this, Direction.DOWN);

        if (downNeighbour instanceof Wall || downNeighbour instanceof Boulder ||
                downNeighbour instanceof Gem) {
            if (map.getNeighbourOf(downNeighbour, Direction.LEFT) instanceof Path) {
                this.move(Direction.LEFT);
            } else if (map.getNeighbourOf(downNeighbour, Direction.RIGHT) instanceof Path) {
                this.move(Direction.RIGHT);
            }
        }
    }

    @Override
    public void update(Map map) {}

    @Override
    public boolean collisionCheck() {
        return false;
    }

    @Override
    public void onCollision(GameObject collidingObject) {}

    @Override
    public void delete() {}

    @Override
    public void move(Direction dir) {}

    public boolean isFalling() {
        return this.falling;
    }

    public void setFalling(boolean fallingStatus) {
        this.falling = fallingStatus;
    }
}
