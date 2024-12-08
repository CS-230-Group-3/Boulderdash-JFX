/**
 * holds a list of amoeba objects. can find available space to grow and add new amoebas to the group.
 * its not a game object so im not 100% sure how thats gonna work. it wont be a problem for collision
 * because entities will identify the individual amoebas rather than the whole group.
 * Other than general clean up there is a redundancy issue in that initFirstAmoeba is called every
 * update when it only needs to be called once but idk how to do it
 * @author Oscar
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AmoebaGroup extends Entity {
    private List<Amoeba> amoebas;
    int amoebaGrowthLimit = 10; //temporary variable, the growth limit will be specified in level file
    private static final String FILE_PATH = "resources/assets/amoeba.png";
    private Amoeba firstAmoeba;

    public AmoebaGroup() {
        super(FILE_PATH, new GridPosition(0, 0));
        this.updateRate = 15; //amoebaGrowthLimit in level file should specify this

        amoebas = new ArrayList<>();

        this.firstAmoeba = new Amoeba();
        this.addAmoeba(firstAmoeba);
    }

    public void initFirstAmoeba() {
        firstAmoeba.setPosition(this.getPosition());
    }

    public void update(Map map) {
        initFirstAmoeba();
        if (this.isLimitReached(amoebaGrowthLimit)) {
            this.die(map, true);
        } else {
            this.findSpace(map);
        }
    }

    @Override
    public boolean collisionCheck() {
        return false;
    }

    @Override
    public void delete() {

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
            this.grow(map, freeSpaces);
        } else {
            this.die(map, false);
        }
    }

    /**
     * randomly selects one of the free spaces found in findSpace and instantiates a new amoeba there
     * @param freeSpaces
     */
    public void grow(Map map, List<GridPosition> freeSpaces) {
        Random random = new Random();
        int randomIndex = random.nextInt(freeSpaces.size());
        Amoeba newAmoeba = new Amoeba();
        GridPosition amoebaTile = freeSpaces.get(randomIndex);
        newAmoeba.setPosition(amoebaTile);
        this.addAmoeba(newAmoeba);

        GameObject grownOver = map.getObjectAt(amoebaTile);
        map.getPendingRemovals().add(grownOver);
        map.getPendingAdditions().add(newAmoeba);
    }

    /**
     * deletes each amoeba and replaces with
     * a diamond one by one
     * @param limitReached flag to mark if growth limit reached
     */
    public void die(Map map, boolean limitReached) {
        for (Amoeba amoeba : amoebas) {
            GridPosition amoebaTile = amoeba.getPosition();
            if (limitReached) {
                map.getPendingRemovals().add(amoeba);
                Boulder boulder = new Boulder();
                boulder.setPosition(amoebaTile);
                map.getPendingAdditions().add(boulder);
            } else {
                map.getPendingRemovals().add(amoeba);
                Gem diamond = new Gem();
                diamond.setPosition(amoebaTile);
                map.getPendingAdditions().add(diamond);
            }
        }
        map.getPendingRemovals().add(this);
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

    @Override
    public void move(Map map, Direction dir) {

    }

    @Override
    public boolean collisionCheck(Map map, GridPosition position) {
        return false;
    }

    @Override
    public boolean collisionCheck(Map map, Direction dir) {
        return false;
    }

    public void setGrowthLimit(int limit) {
        this.amoebaGrowthLimit = limit;
    }

    public void setGrowthRate(int rate) {
        this.updateRate = rate;
    }
}
