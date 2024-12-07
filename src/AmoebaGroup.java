/**
 * holds a list of amoeba objects. can find available space to grow and add new amoebas to the group.
 * its not a game object so im not 100% sure how thats gonna work. it wont be a problem for collision
 * because entities will identify the individual amoebas rather than the whole group.
 * @author Oscar
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AmoebaGroup {
    private List<Amoeba> amoebas;
    int amoebaGrowthLimit = 10; //temporary variable, the growth limit will be specified in level file

    public AmoebaGroup() {
        amoebas = new ArrayList<>();
    }

    public void update(Map map) {
        if (this.isLimitReached(amoebaGrowthLimit)) {
            this.die(true);
        } else {
            this.findSpace(map);
        }
    }

    /**
     * add an amoeba to the group
     * @param amoeba amoeba to be added
     */
    public void addAmoeba(Amoeba amoeba) {
        amoebas.add(amoeba);
    }

    /**
     * gets orthogonal neighbours of every amoeba in the group and checks if they are a path or dirt.
     * if so, add to freeSpaces list. theres probably a more efficient way to do this. a position can
     * be added to the list more than once if it has multiple amoeba neighbours but this gives that
     * position a higher chance of being selected for growth which i think makes sense.
     * @param map the level map
     */
    public void findSpace(Map map) {
        List<GridPosition> freeSpaces = new ArrayList<>();

        for (Amoeba amoeba : amoebas) {
            List<GameObject> neighbours = new ArrayList<>();
            neighbours.add(map.getNeighbourOf(amoeba, Direction.UP));
            neighbours.add(map.getNeighbourOf(amoeba, Direction.LEFT));
            neighbours.add(map.getNeighbourOf(amoeba, Direction.RIGHT));
            neighbours.add(map.getNeighbourOf(amoeba, Direction.DOWN));
            for (GameObject neighbour : neighbours) {
                if (neighbour instanceof Path || neighbour instanceof Dirt) {
                    freeSpaces.add(neighbour.getPosition());
                }
            }
        }
        if (!freeSpaces.isEmpty()) {
            this.grow(freeSpaces);
        } else {
            this.die(false);
        }
    }

    /**
     * randomly selects one of the free spaces found in findSpace and instantiates a new amoeba there
     * @param freeSpaces
     */
    public void grow(List<GridPosition> freeSpaces) {
        Random random = new Random();
        int randomIndex = random.nextInt(freeSpaces.size());
        //create amoeba at chosen grid position. call amoeba.setGroup(this)
    }

    /**
     * not 100% sure if this is done by a controller or not, deletes each amoeba and replaces with
     * a diamond one by one
     * @param limitReached flag to mark if growth limit reached
     */
    public void die(boolean limitReached) {
        for (Amoeba amoeba : amoebas) {
            //get position and replace with boulders if limit reached, diamond if not
        }
    }

    /**
     * checks if growth limit reached
     * @param amoebaGrowthLimit the growth limit for each level
     * @return boolean - true if limit reached, false if not
     */
    public boolean isLimitReached(int amoebaGrowthLimit) {
        int amoebaSize = amoebas.size();
        return amoebaSize >= amoebaGrowthLimit;
    }

    public String toString() {
        return amoebas.toString();
    }
}
