import java.util.ArrayList;

/**
 * Representation of the game's map.
 * A map is constructed from squares.
 * @author Yuliia & Spas
 */


public class Map {
    private final int mapWidth;
    private final int mapHeight;

    private ArrayList<GameObject> objects;

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

    /**
     * Returns the object at the provided coordinate.
     * @param coordinate coordinate to get object at
     * @return Object at the coordinate
     */
    public GameObject getObjectAt(GridPosition coordinate) {
        int index = gridToIndex(coordinate);
        if (index == -1) {
            return null;
        }
        return getObjects().get(index);
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
        for (GameObject object: getObjects()) {
            if (object instanceof Player) {
                return (Player) object;
            }
        }
        return null;
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

    //Uses Row major order index
    private int gridToIndex(GridPosition mapCoordinate) {
        if (!isInBounds(mapCoordinate)) {
            return -1;
        }
        return mapCoordinate.getY() * getMapWidth() + mapCoordinate.getX();
    }

    private boolean isInBounds(GridPosition coordinate) {
        return 0 <= coordinate.getX()
                && coordinate.getX() < getMapWidth()
                && 0 <= coordinate.getY()
                && coordinate.getY() < getMapHeight();
    }
}
