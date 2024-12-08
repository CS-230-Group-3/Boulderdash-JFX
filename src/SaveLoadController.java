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
        ArrayList<GameObject> tileLayer = new ArrayList<>();
        ArrayList<GameObject> entityLayer = new ArrayList<>();
        //Reader pares an empty String after reading map size
        //Initializing height to -1 fixes it
        int height = -1;
        int width = 0;

        try {
            Scanner reader = new Scanner(fileToRead);
            while (reader.hasNextLine()) {
                if (isMapSizeRead) {
                    String line = reader.nextLine();
                    if (!line.equals("-")) {
                        for (char c : line.toCharArray()) {
                            GameObject objectToAdd = getObjectFromChar(c);
                            GridPosition objectPosition =
                                    new GridPosition(width, height);
                            if (objectToAdd instanceof Tile) {
                                objectToAdd.setPosition(objectPosition);
                                tileLayer.add(objectToAdd);
                                width++;
                            } else if (objectToAdd instanceof Entity) {
                                objectToAdd.setPosition(objectPosition);

                                tileLayer.add(new Path(objectPosition));
                                entityLayer.add(objectToAdd);
                                width++;
                            }
                        }
                        width = 0;
                        height++;
                    } else {
                        Player playerRef = getPlayerFromList(entityLayer);
                        parsePlayerData(reader, playerRef);
                    }
                } else {
                    int mapWidth =  Integer.parseInt(reader.next());
                    int mapHeight = Integer.parseInt(reader.next());

                    map = new Map(mapWidth, mapHeight);
                    isMapSizeRead = true;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }
        return populateMapWithLayers(map, tileLayer, entityLayer);

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
            ArrayList<Character> charToWrite = writeObjectsFromMap(mapToSave);
            for (Character character: charToWrite) {
                writer.print(character);
                charsWritten++;
                if (charsWritten % mapWidth == 0) {
                    writer.println();
                }
            }
            //TODO finalise whenever player collectables are complete
            //Player data parsing
//            Player playerRef = mapToSave.getPlayerObjectReference();
//            if (!playerRef.getKeyChain().isEmpty()
//                    || playerRef.getDiamonds() > 0) {
//                //Map & Player Run stats divider
//                writer.println("-");
//                String playerData = playerToDataString(playerRef);
//                writer.println(playerData);
//            }
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

    private ArrayList<Character> writeObjectsFromMap(Map map) {
        ArrayList<Character> output = new ArrayList<>();
        for (GameObject object: map.getTileLayer()) {
            output.add(
                    getCharFromObject(object)
            );
        }
        for (GameObject object: map.getEntityLayer()) {
            int index = map.gridToIndex(object.getPosition());
            output.add(index, getCharFromObject(object));
            output.remove(index + 1);
        }
        return output;
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
    private Map populateMapWithLayers(Map map, ArrayList<GameObject> tileLayer, ArrayList<GameObject> entityLayer) {
        if (map != null) {
            map.setLayersTo(tileLayer, entityLayer);
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
            case 'B':
                return new Boulder();
            case 'U':
                return new Butterfly();
            case 'F':
                return new Frog();
            case 'O':
                return new Gem();
            case 'S':
                return new Firefly();
            case '@':
                return new AmoebaGroup();
            case '!':
                return new RedKey();
            case '"':
                return new BlueKey();
            case '£':
                return new YellowKey();
            case '$':
                return new PinkKey();
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
            case "Boulder":
                return 'B';
            case "Butterfly":
                return 'U';
            case "Gem":
                return 'O';
            case "Firefly":
                return 'S';
            case "RedKey":
                return '!';
            case "BlueKey":
                return '"';
            case "YellowKey":
                return '£';
             case "PinkKey":
                 return '$';
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

