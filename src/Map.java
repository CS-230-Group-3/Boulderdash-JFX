import java.util.ArrayList;

/**
 * Representation of the game's map.
 * A map is constructed from squares.
 * @author Yuliia & Spas
 */


public class Map {
    private final int mapWidth;
    private final int mapHeight;

    private ArrayList<GameObject> tileLayer;
    private ArrayList<GameObject> entityLayer;

    /**
     * Create a new map with empty game objects.
     * @param newMapWidth width of the map
     * @param newMapHeight height of the map
     */
    public Map(int newMapWidth, int newMapHeight) {
        tileLayer = new ArrayList<>();
        entityLayer = new ArrayList<>();
        this.mapWidth = newMapWidth;
        this.mapHeight = newMapHeight;
    }

    /**
     * Assigns the entity and tile layers of the Map.
     * @param tileLayer tileLayer to assign
     * @param entityLayer entityLayer to assign
     */
    public void setLayersTo(
            ArrayList<GameObject> tileLayer,
            ArrayList<GameObject> entityLayer) {
        this.tileLayer = tileLayer;
        this.entityLayer = entityLayer;
    }

    /**
     * Returns the object at the provided coordinate.
     * @param coordinate coordinate to get object at
     * @return Object at the coordinate
     */
    public GameObject getObjectAt(GridPosition coordinate) {
        for (GameObject object: entityLayer) {
            if (object.getPosition().equals(coordinate)) {
                return object;
            }
        }
        int index = gridToIndex(coordinate);
        if (index == -1) {
            return new TitaniumWall();
        }
        return tileLayer.get(index);
    }

    /**
     * Returns a grid position from a passes list index.
     * Determines x & y coordinated based on the map's width.
     * @param index the list index.
     * @return GridPosition coordinate at index
     */
    //Uses inverse Row major order index
    public GridPosition indexToGrid(int index) {
        return new GridPosition(
                index % getMapWidth(),
                index / getMapWidth()
        );
    }

    /**
     * Spawns the provided GameObject according to its position and type.
     * New tiles can only be spawned on Path tiles.
     * New entities can only be spawned on Paths or in Water.
     * @param gameObject object to spawn
     */
    public void spawnGameObject(GameObject gameObject) {
        if (gameObject instanceof Entity) {
            handleEntitySpawn(gameObject);
        } else if (gameObject instanceof Tile) {
            handleTileSpawn(gameObject);
        }
    }

    /**
     * Destroys the passed tile.
     * Places a path on it's position
     * @param tileToDestroy tile to be destroyed
     */
    public void destroyTile(Tile tileToDestroy) {
        int tileIndex = gridToIndex(tileToDestroy.getPosition());
        tileLayer.add(tileIndex,
                new Path(tileToDestroy.getPosition())
                );
        tileLayer.remove(tileIndex + 1);
    }

    /**
     * Removes the passed item from the Map.
     * @param itemToRemove item to be removed
     */
    public void removeItem(Item itemToRemove) {
        for (GameObject object: entityLayer) {
            if (object.getPosition().equals(
                    itemToRemove.getPosition())) {
                entityLayer.remove(itemToRemove);
            }
        }
    }

    /**
     * Returns a references to player object on the map.
     * @return first instance of player object if one exists, null otherwise
     */
    public Player getPlayerObjectReference() {
        for (GameObject object: entityLayer) {
            if (object instanceof Player) {
                return (Player) object;
            }
        }
        return null;
    }

    /**
     * @return first instance of exit object if one exists, null otherwise
     */
    public Exit getExitObjectReference() {
        for (GameObject object: getTileLayer()) {
            if (object instanceof Exit) {
                return (Exit) object;
            }
        }
        return null;
    }

    /**
     * Returns the passed object's neighbour at the provided location,
     * if one exists, null otherwise.
     * @param object object to get neighbour of
     * @param direction direction relative to object to check
     * @return the neighbouring game object, if one exists, null otherwise
     */
    public GameObject getNeighbourOf(GameObject object, Direction direction) {
        GridPosition offset = directionToOffset(direction);
        GridPosition positionOfNeighbour = new GridPosition(
                object.getPosition().getX() + offset.getX(),
                object.getPosition().getY() + offset.getY()
        );
        if (!isInBounds(positionOfNeighbour)) {
            return null;
        } else {
            return getObjectAt(positionOfNeighbour);
        }
    }

    /**
     * Maps the index at the passed grid position
     * to linear array index.
     * @param mapCoordinate coordinate to return the index of
     * @return an int representing array index based on the size of the map.
     */
    public int gridToIndex(GridPosition mapCoordinate) {
        if (!isInBounds(mapCoordinate)) {
            return -1;
        }
        return mapCoordinate.getY() * getMapWidth() + mapCoordinate.getX();
    }

    /**
     * Returns the map's width.
     * @return an integer representing map width
     */
    public int getMapWidth() {
        return mapWidth;
    }
    /**
     * Returns the map's height.
     * @return an integer representing map height
     */
    public int getMapHeight() {
        return mapHeight;
    }

    /**
     * @return the Map's Tile Layer
     */
    public ArrayList<GameObject> getTileLayer() {
        return tileLayer;
    }

    /**
     * @return the Map's Entity Layer
     */
    public ArrayList<GameObject> getEntityLayer() {
        return entityLayer;
    }

    private GridPosition directionToOffset(Direction dir) {
        return switch (dir) {
            case RIGHT -> new GridPosition(1, 0);
            case LEFT -> new GridPosition(-1, 0);
            case UP -> new GridPosition(0, -1);
            //DOWN
            default -> new GridPosition(0, 1);
        };
    }

    private boolean isInBounds(GridPosition coordinate) {
        return 0 <= coordinate.getX()
                && coordinate.getX() < getMapWidth()
                && 0 <= coordinate.getY()
                && coordinate.getY() < getMapHeight();
    }

    private void handleTileSpawn(GameObject gameObject) {
        GridPosition objectPosition = gameObject.getPosition();
        boolean isValidPosition = true;

        int locationIndex = gridToIndex(objectPosition);
        GameObject location = tileLayer.get(
                locationIndex
        );

        if (!(location instanceof Path)) {
            isValidPosition = false;
        }

        if (isValidPosition) {
            tileLayer.add(locationIndex, gameObject);
            tileLayer.remove(locationIndex + 1);
        }
    }

    private void handleEntitySpawn(GameObject gameObject) {
        GridPosition objectPosition = gameObject.getPosition();
        boolean isValidPosition = true;

        for (GameObject object: entityLayer) {
            if (object.getPosition().equals(objectPosition)) {
                isValidPosition = false;
            }
        }

        GameObject tileAtPosition = tileLayer.get(
                gridToIndex(objectPosition)
        );

        if (!(tileAtPosition instanceof Path)
                && !(tileAtPosition instanceof Water)) {
            isValidPosition = false;
        }

        if (isValidPosition && isInBounds(objectPosition)) {
            entityLayer.add(gameObject);
        }
    }
}
