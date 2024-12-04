import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Utility class for saving & loading map data.
 * @author Yuliia & Spas
 */
public class SaveLoadController {
    private static final String PATH_TO_DATA_CLASS_SAVE =
            "src/resources/data/data.bin";

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
        ArrayList<GameObject> objects = new ArrayList<>();

        try {
            Scanner reader = new Scanner(fileToRead);
            while (reader.hasNextLine()) {
                if (isMapSizeRead) {
                    String line = reader.nextLine();
                    if (!line.equals("-")) {
                        for (char c: line.toCharArray()) {
                            objects.add(getObjectFromChar(c));
                        }
                    } else {
                        Player playerRef = getPlayerFromList(objects);
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
        String outputFile = "src/resources/saves/LevelSave1.txt";
        try {
            PrintWriter writer = new PrintWriter(outputFile);
            int mapWidth = mapToSave.getMapWidth();
            int mapHeight = mapToSave.getMapHeight();

            writer.println(mapWidth + " " + mapHeight);

            int charsWritten = 0;
            for (GameObject gameObject: mapToSave.getObjects()) {
                writer.print(
                        getCharFromObject(gameObject));
                charsWritten++;
                if (charsWritten % mapWidth == 0) {
                    writer.println();
                }
            }
            //Player data parsing
            Player playerRef = mapToSave.getPlayerObjectReference();
            //TODO finalise whenever player is complete
            if (!playerRef.getKeyChain().isEmpty()
                    || playerRef.getDiamonds() > 0) {
                //Map & Player Run stats divider
                writer.println("-");
                String playerData = playerToDataString(playerRef);
                writer.println(playerData);
            }

            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves provided data into binary file, preserving it's state.
     * @param dataToSave data object to save
     */
    public static void saveData(Data dataToSave) throws IOException {
        FileOutputStream fileOutputStream =
                new FileOutputStream(PATH_TO_DATA_CLASS_SAVE);
        ObjectOutputStream objectOutputStream =
                new ObjectOutputStream(fileOutputStream);

        objectOutputStream.writeObject(dataToSave);

        fileOutputStream.close();
        objectOutputStream.close();
    }

    /**
     * Loads the Data class from file.
     * @return data with preserved state from last save
     */
    public static Data loadData() throws IOException, ClassNotFoundException {
        Data data = null;

        FileInputStream fileInputStream =
                new FileInputStream(PATH_TO_DATA_CLASS_SAVE);
        ObjectInputStream outputStream =
                new ObjectInputStream(fileInputStream);

        data = (Data) outputStream.readObject();

        fileInputStream.close();
        outputStream.close();

        return data;
    }

    //Loading helpers
    private Player getPlayerFromList(ArrayList<GameObject> objects) {
        for (GameObject object: objects) {
            if (object instanceof Player) {
                return (Player) object;
            }
        }
        return null;
    }
    private void populatePlayerItem(String item, int amount, Player player) {
        switch (item) {
            case "D":
            player.setDiamonds(amount);
            break;
            //TODO
//            case 'K':
//            player.setKeysCollected(amount);
//            break;
        }

    }
    private Map populateMapWithObjects(Map map, ArrayList<GameObject> objects) {
        if (map != null) {
            map.setAllObjectsTo(objects);
        }
        return map;
    }

    private GameObject getObjectFromChar(char c) {
        switch (c) {
            case 'P':
                return new Path();
            case 'W':
                return new Wall();
            case 'D':
                return new Dirt();
            case 'R':
                return new Player();
            case 'A':
                return new Water();
            case 'G':
                return new MagicWall();
            case 'T':
                return new TitaniumWall();
            case 'E':
                return new Exit();
            default:
                return new Path();
        }
    }

    private void parsePlayerData(Scanner r, Player player) {
        while (r.hasNextLine()) {
            String nextLine = r.nextLine();
            if (!nextLine.isEmpty()) {
                //Split the line by 'space'
                String[] parts = nextLine.split("\\s+");
                if (parts.length == 2) {
                    String itemToBeRead = parts[0];
                    int amountToAdd = Integer.parseInt(parts[1]);
                    populatePlayerItem(itemToBeRead, amountToAdd, player);
                }
            }

        }
    }

    //Saving Helpers
    //TODO Extend for each concrete GameObject
    private char getCharFromObject(GameObject object) {
        switch (object.getClass().getSimpleName()) {
            case "Path":
                return 'P';
            case "Wall":
                return 'W';
            case "MagicWall":
                return 'G';
            case "TitaniumWall":
                return 'T';
            case "Dirt":
                return 'D';
            case "Player":
                return 'R';
            case "Water":
                return 'A';
            case "Exit":
                return 'E';
            default:
                return '*';
        }
    }
    private String playerToDataString(Player player) {
        String output = "";
        if (player.getDiamonds() > 0) {
            output += "D " + player.getDiamonds() + "\n";
        }
        //TODO Extend so it describes which type of key is collected
//        if (!player.getKeyChain().isEmpty()) {
//            output += "K " + player.getKeyChain().size() + "\n";
//        }
        return output;
    }
}

