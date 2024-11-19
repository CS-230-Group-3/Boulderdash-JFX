package SaveLoadController;

import java.util.ArrayList;

/**
 * Representation of the game's map.
 * A map is constructed from squares.
 * @author Yuliia & Spas
 */


public class Map {
    private final int mapWidth;
    private final int mapHeight;

    private final ArrayList<Square> squares;

    /**
     * Create a new map with empty squares.
     * @param newMapWidth width of the map
     * @param newMapHeight height of the map
     */
    public Map(int newMapWidth, int newMapHeight) {
        squares = new ArrayList<>();
        this.mapWidth = newMapWidth;
        this.mapHeight = newMapHeight;

        placeEmptySquares();
    }

    //Populate the squares array with empty squares
    private void placeEmptySquares() {
        for (int hight = 0; hight < getMapHeight(); hight++) {
            for (int widht = 0; widht < getMapWidth(); widht++) {
                Square square =
                        new Square(
                                new GridPosition(widht, hight)
                        );
                getSquares().add(square);
            }
        }
    }

    /**
     * Sets a game object at the provided coordinate.
     * @param coordinate the square coordinate to set
     * @param gameObjectSLC teh object to set
     */
    public void setSquareAt(
            GridPosition coordinate, GameObjectSLC gameObjectSLC) {
        Square squareToSet = getSquareAt(coordinate);
        if (squareToSet != null) {
            squareToSet.setGameObject(gameObjectSLC);
        } else {
            //Not ideal error handling
            System.out.println(
                    "Invalid Coordinate: "
                            + coordinate);
        }

    }

    /**
     * Returns the square object at the provided coordinate.
     * @param coordinate the square coordinate to get
     * @return Square object at the coordinate
     */
    public Square getSquareAt(GridPosition coordinate) {
        int index = gridToIndex(coordinate);
        if (index == -1) {
            return null;
        }
        return getSquares().get(index);
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
     * Returns squares populating the map.
     * @return an ArrayList of squares
     */
    public ArrayList<Square> getSquares() {
        return squares;
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
    public PlayerObjectSLC getPlayerObjectReference() {
        for (Square square: getSquares()) {
            if (square.getGameObject() instanceof PlayerObjectSLC) {
                return (PlayerObjectSLC) square.getGameObject();
            }
        }
        return null;
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
