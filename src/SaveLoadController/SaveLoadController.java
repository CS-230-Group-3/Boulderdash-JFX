package SaveLoadController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Utility class for saving & loading map data.
 * @author Yuliia & Spas
 */
public class SaveLoadController {

    /**
     * Loads map data from file.
     * Creates and returns a new map object
     * @param filePath path to level file
     * @return a Map object populated with data form the level file
     */
    public Map loadFromFile(String filePath) {
        File fileToRead = new File(filePath);
        Map map = null;
        boolean isMapSizeRead = false;
        ArrayList<GameObjectSLC> objects = new ArrayList<>();

        try {
            Scanner reader = new Scanner(fileToRead);
            while (reader.hasNextLine()) {
                if (isMapSizeRead) {
                    String data = reader.nextLine();
                    if (!data.equals("-")) {
                        for (char c: data.toCharArray()) {
                            objects.add(getObjectFromChar(c));
                        }
                    } else {
                        PlayerObjectSLC playerRef = getPlayerFromList(objects);
                        parsePlayerData(reader, playerRef);
                    }

                } else {
                    int width =  Integer.parseInt(reader.next());
                    int height = Integer.parseInt(reader.next());

                    map = new Map(width, height);
                    isMapSizeRead = true;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return populateMapWithObjects(map, objects);
    }

    /**
     * Creates a new save file from Map object.
     * The file is created in ...
     * @param mapToSave the Map object to save`
     */
    public void saveMapToFile(Map mapToSave) {
        //TODO give the file name a more robust name (date + time maybe?)
        String outputFile = "src/SaveLoadController/LevelSave1.txt";
        try {
            PrintWriter writer = new PrintWriter(outputFile);
            int mapWidth = mapToSave.getMapWidth();
            int mapHeight = mapToSave.getMapHeight();

            writer.println(mapWidth + " " + mapHeight);

            int charsWritten = 0;
            for (Square square: mapToSave.getSquares()) {
                writer.print(
                        getCharFromObject(square.getGameObject()));
                charsWritten++;
                if (charsWritten % mapWidth == 0) {
                    writer.println();
                }
            }
            //Player data parsing
            PlayerObjectSLC playerRef = mapToSave.getPlayerObjectReference();
            if (playerRef.getKeysCollected() > 0
                    || playerRef.getDiamondsCollected() > 0) {
                //Map & Player Run stats divider
                writer.println("-");
                String playerData = playerToDataString(playerRef);
                writer.println(playerData);
            }

            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //save the w & h as the first lines of the file
        //go over each object in map.getSquares.getGO
        // make a new line every width number of chars
    }

    //Loading helpers
    private PlayerObjectSLC getPlayerFromList(ArrayList<GameObjectSLC> objects) {
        for (GameObjectSLC object: objects) {
            if (object instanceof PlayerObjectSLC) {
                return (PlayerObjectSLC) object;
            }
        }
        return null;
    }

    private void populatePlayerItem(char item, int amount, PlayerObjectSLC player) {
        switch (item) {
            case 'D':
                player.setDiamondsCollected(amount);
                break;
            case 'K':
                player.setKeysCollected(amount);
                break;
        }
    }
    private Map populateMapWithObjects(Map map, ArrayList<GameObjectSLC> objects) {
        for (int i = 0; i < objects.size(); i++) {
            GameObjectSLC objectToAdd = objects.get(i);
            GridPosition objectCoordinate = map.indexToGrid(i);
            map.setSquareAt(objectCoordinate, objectToAdd);
        }
        return map;
    }

    private GameObjectSLC getObjectFromChar(char c) {
        switch (c) {
            case 'P':
                return new PathObjectSLC();
            case 'W':
                return new WallObjectSLC();
            case 'D':
                return new DirtObjectSLC();
            case 'R':
                return new PlayerObjectSLC();
            default:
                new PathObjectSLC();
        }
        //Not ideal but will do for now
        return new PathObjectSLC();
    }

    private void parsePlayerData(Scanner r, PlayerObjectSLC player) {
        while (r.hasNextLine()) {
            char itemToBeRead = r.next().toCharArray()[0];
            int amountToAdd = Integer.parseInt(r.next());

            populatePlayerItem(itemToBeRead, amountToAdd, player);
        }
    }

    //Saving Helpers
    private char getCharFromObject(GameObjectSLC object) {
        switch (object.getClass().getSimpleName()) {
            case  "PathObject":
                return 'P';
            case "WallObject":
                return 'W';
            case "DirtObject":
                return 'D';
            case "PlayerObject":
                return 'R';
            default:
                return '*';
        }
    }
    private String playerToDataString(PlayerObjectSLC player) {
        String output = "";
        if (player.getDiamondsCollected() > 0) {
            output += "D " + player.getDiamondsCollected() + "\n";
        }
        if (player.getKeysCollected() > 0) {
            output += "K " + player.getKeysCollected() + "\n";
        }
        return output;
    }
}
