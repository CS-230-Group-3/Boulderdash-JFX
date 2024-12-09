public class Item extends Entity {

    protected boolean isFalling = false;

    public Item(String pathToSprite, GridPosition position) {
        super(pathToSprite, position);
        this.type = "item";
    }

    public boolean fall(Map map) {
        GameObject downNeighbour = map.getNeighbourOf(this, Direction.DOWN);

        switch (downNeighbour) {
            case Path _, Water _ -> {
                this.move(map, Direction.DOWN);
                this.isFalling = true;
                return true;
            }
            //kill player if gem or boulder
            case Player _ when ((this instanceof Boulder || this instanceof Gem) && this.isFalling) -> {
                map.getPlayerObjectReference().die();
                this.move(map, Direction.DOWN);
                this.isFalling = true;
                return true;
            }
            case Butterfly _ when (this instanceof Boulder || this instanceof Gem) -> {
                Butterfly downNeighbourButterfly = (Butterfly) downNeighbour;
                downNeighbourButterfly.destroy(map);
                map.getPendingRemovals().add(this);
                map.getPendingRemovals().add((downNeighbourButterfly));
                this.move(map, Direction.DOWN);
                this.isFalling = true;
                return true;
            }
            case Enemy _ when (this instanceof Boulder || this instanceof Gem) -> {
                Enemy downNeighbourEnemy = (Enemy) downNeighbour;
                downNeighbourEnemy.destroy(map);
                map.getPendingRemovals().add(this);
                map.getPendingRemovals().add((downNeighbourEnemy));
                this.move(map, Direction.DOWN);
                this.isFalling = true;
                return true;
            }
            case null, default -> {
                this.isFalling = false;
                return false;
            }
        }
    }

    public void roll(Map map) {
        GameObject downNeighbour = map.getNeighbourOf(this, Direction.DOWN);
        GameObject rightNeighbour = map.getNeighbourOf(this, Direction.RIGHT);
        GameObject leftNeighbour = map.getNeighbourOf(this, Direction.LEFT);

        if (downNeighbour instanceof Wall || downNeighbour instanceof Boulder ||
                downNeighbour instanceof Gem) {
            if (map.getNeighbourOf(downNeighbour, Direction.LEFT) instanceof Path &&
            leftNeighbour instanceof Path) {
                this.move(map, Direction.LEFT);
            } else if (map.getNeighbourOf(downNeighbour, Direction.RIGHT) instanceof Path &&
                    rightNeighbour instanceof Path) {
                this.move(map, Direction.RIGHT);
            }
        }
    }

    @Override
    public void update(Map map) {
    }

    @Override
    public void move(Map map, Direction dir) {
        if (dir == Direction.UP) {
            this.setPosition(new GridPosition(
                    this.getPosition().getX(),
                    this.getPosition().getY() - 1
            ));

        } else if (dir == Direction.DOWN) {
            this.setPosition(new GridPosition(
                    this.getPosition().getX(),
                    this.getPosition().getY() + 1
            ));
        } else if (dir == Direction.LEFT) {
            this.setPosition(new GridPosition(
                    this.getPosition().getX() - 1,
                    this.getPosition().getY()
            ));

        } else if (dir == Direction.RIGHT) {
            this.setPosition(new GridPosition(
                    this.getPosition().getX() + 1,
                    this.getPosition().getY()
            ));
        }
    }


    @Override
    public boolean collisionCheck(Map map, GridPosition position) {
        return false;
    }

    @Override
    public boolean collisionCheck(Map map, Direction dir) {
        return false;
    }
}
