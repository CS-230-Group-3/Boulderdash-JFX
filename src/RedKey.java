public class RedKey extends Key {

    private static final String FILE_PATH = "resources/assets/keyred.png";


    public RedKey() {
        super(FILE_PATH, new GridPosition(0, 0));

    }

    @Override
    public String toString(){
        return "Red Key";
    }



}
