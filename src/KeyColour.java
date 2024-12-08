public enum KeyColour {

    PINK, YELLOW, BLUE, RED;


    public KeyColour getKeyColour(){
        return this;
    }



    @Override
    public String toString(){
        return "Key Colour " + getKeyColour();
    }

}
