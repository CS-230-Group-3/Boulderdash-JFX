public class YellowKey extends Key {

    private static final String FILE_PATH = "resources/assets/keyyellow.png";
    private String colour = "Yellow";

    public YellowKey() {
        super(FILE_PATH, new GridPosition(0, 0));
        this.type = "key";
    }

    @Override
    public String toString() {
        return "Yellow Key";
    }

}
