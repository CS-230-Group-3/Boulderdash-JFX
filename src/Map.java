import java.util.ArrayList;

/**
 * Representation of the game's map.
 * A map is constructed from squares.
 * @author Yuliia & Spas
 */


public class Map {
    private final int mapWidth;
    private final int mapHeight;

    //TODO remove and fix all problems that come up;
    private ArrayList<GameObject> objects;
    private ArrayList<GameObject> tileLayer;
    private ArrayList<GameObject> entityLayer;

    /**
     * Create a new map with empty game objects.
     * @param newMapWidth width of the map
     * @param newMapHeight height of the map
     */
    public Map(int newMapWidth, int newMapHeight) {
        objects = new ArrayList<>();
        this.mapWidth = newMapWidth;
        this.mapHeight = newMapHeight;

        populateWithEmptyObjects();
    }

    /**
     * Sets the map's objects to the passed list,
     * assigning appropriate grid position for each game object.
     * @param objectsToSet the object to set
     */
    //TODO fix when you reach in SLC
    public void setAllObjectsTo(ArrayList<GameObject> objectsToSet) {
        //Simple error check
        if (getObjects().size() == objectsToSet.size()) {
            //Change positions of objects based on their index in list
            for (int i = 0; i < objectsToSet.size(); i++) {
                objectsToSet.get(i).setPosition(
                        indexToGrid(i)
                );
            }
            setObjects(objectsToSet);
        }
    }

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
            return null;
        }
        return tileLayer.get(index);
    }

    /**
     * Place game object on the map, based on its position.
     * @param gameObject object to place
     */
    public void placeObjectOnMap(GameObject gameObject) {
        int index = gridToIndex(gameObject.getPosition());
        getObjects().set(index, gameObject);
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
     * Returns list of game objects on the map.
     * @return list of game objects
     */
    public ArrayList<GameObject> getObjects() {
        return objects;
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
        for (GameObject object: getObjects()) {
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
        //TODO FIX
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

    public ArrayList<GameObject> getTileLayer() {
        return tileLayer;
    }

    public ArrayList<GameObject> getEntityLayer() {
        return entityLayer;
    }

    private GridPosition directionToOffset(Direction dir) {
        return switch (dir) {
            case RIGHT -> new GridPosition(1, 0);
            case LEFT -> new GridPosition(-1, 0);
            case UP -> new GridPosition(0, -1);
            //For DOWN
            default -> new GridPosition(0, 1);
        };

    }

    private void populateWithEmptyObjects() {
        for (int maxMapPositions = 0;
             maxMapPositions < getMapHeight() * getMapWidth();
             maxMapPositions++) {
            getObjects().add(null);
        }

    }

    private void setObjects(ArrayList<GameObject> objects) {
        this.objects = objects;
    }

    private boolean isInBounds(GridPosition coordinate) {
        return 0 <= coordinate.getX()
                && coordinate.getX() < getMapWidth()
                && 0 <= coordinate.getY()
                && coordinate.getY() < getMapHeight();
    }
}
