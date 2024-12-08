public class YellowKey extends Key {

    private static final String FILE_PATH = "resources/assets/keyyellow.png";

    public YellowKey() {
        super(FILE_PATH, new GridPosition(0,0));
    }

    @Override
    public String toString(){
        return "Yellow Key";
    }

}
