public class BlueKey extends Key{

    private static final String FILE_PATH = "resources/assets/keyblue.png";

    public BlueKey() {
        super(FILE_PATH, new GridPosition(0, 0));
        this.type = "key";
    }

    @Override
    public String toString(){
        return "Blue Key";
    }
}
