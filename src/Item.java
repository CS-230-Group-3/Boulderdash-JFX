/**
 * The Item class represents an entity that can fall and roll in the game.
 * It extends the Entity class and provides functionality for falling,
 * rolling, and handling collisions with various other game objects.
 * @author Oscar Baggs
 * @version 1.0.3
 */
public class Item extends Entity {

    protected boolean isFalling = false;

    /**
     * Creates an Item object with the specified sprite path and position.
     *
     * @param pathToSprite the path to the sprite image for the item
     * @param position     the position of the item in the game world
     */
    public Item(String pathToSprite, GridPosition position) {
        super(pathToSprite, position);
        this.type = "item";
    }

    /**
     * Makes the item fall down the map, interacting with the game objects it encounters.
     * The item falls until it hits a solid object or reaches the bottom of the map.
     * If the item falls onto a player, enemy, butterfly, or water, special interactions occur.
     *
     * @param map the map on which the item is falling
     * @return true if the item successfully falls, false otherwise
     */
    public boolean fall(Map map) {
        GameObject downNeighbour = map.getNeighbourOf(this, Direction.DOWN);

        switch (downNeighbour) {
            case Path _, Water _ -> {
                this.move(map, Direction.DOWN);
                this.isFalling = true;
                return true;
            }
            // Kill player if gem or boulder falls on them
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

    /**
     * Makes the item roll in the game world, interacting with the environment based on the
     * neighboring game objects. The item rolls left or right if there is a valid path.
     *
     * @param map the map where the item is rolling
     */
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

    /**
     * Updates the item. In this class, no update behavior is defined.
     *
     * @param map the map on which to update the item
     */
    @Override
    public void update(Map map) {
    }

    /**
     * Moves the item in the specified direction on the map.
     * The movement is restricted to UP, DOWN, LEFT, or RIGHT directions.
     *
     * @param map the map on which the item is moving
     * @param dir the direction in which to move the item
     */
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

    /**
     * Performs a collision check for the item at the specified position on the map.
     *
     * @param map      the map to check for collisions
     * @param position the position to check for collisions
     * @return false as collisions are not handled in this method
     */
    @Override
    public boolean collisionCheck(Map map, GridPosition position) {
        return false;
    }

    /**
     * Performs a collision check for the item in the specified direction.
     *
     * @param map the map to check for collisions
     * @param dir the direction to check for collisions
     * @return false as collisions are not handled in this method
     */
    @Override
    public boolean collisionCheck(Map map, Direction dir) {
        return false;
    }
}
