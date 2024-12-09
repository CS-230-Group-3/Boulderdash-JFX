//Node class to complete PathfindingEnemy
// NOT FINAL
public class Node {
    public int x;
    public int y;
    public int g;
    public int h;
    public int f;
    public Node parent;

    public Node(int x, int y, Node parent) {
        this.x = x;
        this.y = y;
        this.parent = parent;
    }

    public Node(int x, int y, int g, int h, Node parent) {
        this(x, y, parent);
        this.g = g;
        this.h = h;
    }

}
