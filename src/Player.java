import java.lang.reflect.Array;
import java.security.Key;
import java.util.Timer;
import java.util.ArrayList;
public class Player extends Entity {
    private Boolean livingState;
    private ArrayList<Key> keyChain = new ArrayList<>();

    public Player() {
        this.livingState = true;
        this.keyChain = null;
    }

    public void underwaterCountDown(int number){ // Play with timers here, but we should be coolin
        System.out.println(number);
    }

    public void move(Direction dir){
        if (dir == Direction.UP) {
            System.out.println("Going Up");
        } else if (dir == Direction.RIGHT) {
            System.out.println("Going Right");
        } else if (dir == Direction.DOWN) {
            System.out.println("Going Down");
        } else if (dir == Direction.LEFT) {
            System.out.println("Going Left");
        }
    }

    public void takeKey(Key key){
        System.out.println("Key" + key.toString() + "Obtained");
        keyChain.add(key);
    }

    public void unlockDoor(){
        System.out.println("Door Unlocked");
    }

    public int[] getPosition(){
        return new int[]{1,2}; //Weird but seems to be the way that works for these.
    }


    @Override
    public void move() {

    }

    @Override
    public void update() {

    }

    @Override
    public boolean collisionCheck() {
        return false;
    }

    @Override
    public void onCollision() {

    }

    @Override
    public void delete() {

    }
}
