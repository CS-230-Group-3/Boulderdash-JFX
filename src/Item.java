public class Item extends Entity {

    public int updateRate = 1;
    private boolean falling = false;

    public Item(String pathToSprite, GridPosition position) {
        super(pathToSprite, position);
        this.type = "item";
    }

    public void fall(Map map) {
        GameObject downNeighbour = map.getNeighbourOf(this, Direction.DOWN);

        if (this.isFalling()) {
            if (downNeighbour instanceof Path) {
                this.move(map, Direction.DOWN);

                //kill player if gem or boulder
            } else if (downNeighbour instanceof Player && this instanceof Gem ||
                    this instanceof Boulder) {
                map.getPlayerObjectReference().die();
                this.move(map, Direction.DOWN);

            } else if (downNeighbour instanceof Enemy) {
                downNeighbour.delete();
                this.move(map, Direction.DOWN);

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
                this.move(map, Direction.LEFT);
            } else if (map.getNeighbourOf(downNeighbour, Direction.RIGHT) instanceof Path) {
                this.move(map, Direction.RIGHT);
            }
        }
    }

    @Override
    public void update(Map map) {}
    @Override
    public void move(Map map, Direction dir) {
        if (dir == Direction.UP) {
            this.setPosition(this.getPosition().add(new GridPosition(0, -1)));
        } else if (dir == Direction.DOWN) {
            this.setPosition(this.getPosition().add(new GridPosition(0, 1)));
        } else if (dir == Direction.LEFT) {
            this.setPosition(this.getPosition().add(new GridPosition(-1, 0)));
        } else if (dir == Direction.RIGHT) {
            this.setPosition(this.getPosition().add(new GridPosition(1, 0)));
        }
    }

    @Override
    public boolean collisionCheck() {
        return false;
    }

    @Override
    public void delete() {}


    @Override
    public boolean collisionCheck(Map map, GridPosition position) {
        return false;
    }

    @Override
    public boolean collisionCheck(Map map, Direction dir) {
        return false;
    }

    public boolean isFalling() {
        return this.falling;
    }

    public void setFalling(boolean fallingStatus) {
        this.falling = fallingStatus;
    }
}
