public class PinkKey extends Key {
    private static final String FILE_PATH = "resources/assets/keypink.png";

    public PinkKey() {
        super(FILE_PATH, new GridPosition(0,0));

    }

    @Override
    public String toString(){
        return "Pink Key";
    }
}
