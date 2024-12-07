import java.util.*;

// DOES NOT WORK ATM

public abstract class PathfindingEnemy extends Enemy {
    Node start, end;
    ArrayList<Node> walkableTiles = new ArrayList<>();
    ArrayList<Node> walkedTiles = new ArrayList<>();

    int rows, cols;
    int[] dx = {-1, 1, 0, 0};  // Directions: left, right, up, down
    int[] dy = {0, 0, -1, 1};  // Directions: left, right, up, down

    public PathfindingEnemy(String pathToSprite, GridPosition position) {
        super(pathToSprite, position);
    }

    private void initializeWalkableTiles(Map map) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                GameObject objectAt = map.getObjectAt(new GridPosition(i, j));
                if (objectAt == null) {
                    walkableTiles.add(new Node(i, j, null));
                } else if (objectAt.isWalkable()) {
                    walkableTiles.add(new Node(i, j, null));
                }
            }
        }
    }

    public ArrayList<int[]> AStarAlgorithm(Map map, GridPosition enemyPos, GridPosition playerPos) {
        rows = map.getMapHeight();
        cols = map.getMapWidth();

        start = new Node(enemyPos.getX(), enemyPos.getY(), null);
        end = new Node(playerPos.getX(), playerPos.getY(), null);


        start.g = 0;
        start.h = calculateHeuristic(start.x, start.y, end.x, end.y);
        start.f = start.g + start.h;

        walkableTiles.add(start);
        System.out.println(enemyPos.getX() + " " + enemyPos.getY());
        System.out.println(playerPos.getX() + " " + playerPos.getY());

        while (!walkableTiles.isEmpty()) {
            Node current = walkableTiles.get(lowestF());
            System.out.println("CURRENT: " + current.x + " " + current.y);
            walkableTiles.remove(current);

            // If we reached the goal, reconstruct the path
            if (current.x == playerPos.getX() && current.y == playerPos.getY()) {
                System.out.println("found");
                ArrayList<int[]> path = new ArrayList<>();
                while (current != null) {
                    path.add(new int[]{current.x, current.y});
                    current = current.parent;
                }
                Collections.reverse(path);
                for (int[] i : path) {
                    System.out.println(i[0] + " " + i[1]);
                }
                return path;
            }

            walkedTiles.add(current);

            // Explore neighbors
            for (int i = 0; i < 4; i++) {
                int newX = current.x + dx[i];
                int newY = current.y + dy[i];

                System.out.println("try: " + newX + " " + newY + " " + current);
                // If the new position is valid and walkable
                if (isValid(newX, newY, map) && !containsNode(walkedTiles, newX, newY)) {
                    int newG = current.g + 1;
                    int newH = calculateHeuristic(newX, newY, end.x, end.y);
                    Node neighbor = new Node(newX, newY, newG, newH, current);
                    System.out.println("    VALID: " + newX + " " + newY + " " + current);
                    neighbor.f = neighbor.g + neighbor.h;

                    // If neighbor is not in the open list, add it
                    if (!containsNode(walkableTiles, newX, newY)) {
                        walkableTiles.add(neighbor);
                    }
                }
            }
        }
        System.out.println("null");
        return null;
    }

    private int lowestF() {
        int minFIndex = 0;
        for (int i = 1; i < walkableTiles.size(); i++) {
            if (walkableTiles.get(i).f < walkableTiles.get(minFIndex).f) {
                minFIndex = i;
            }
        }
        return minFIndex;
    }

    private boolean isValid(int x, int y, Map map) {
        if (x >= 0 && y >= 0 && x < rows && y < cols) {
            if (map.getObjectAt(new GridPosition(x, y)) == null) {
                return true;
            } else if (map.getObjectAt(new GridPosition(x, y)) == map.getPlayerObjectReference()) {
                return true;
            } else {
                return map.getObjectAt(new GridPosition(x, y)).isWalkable();
            }
        }
        return false;
    }

    private int calculateHeuristic(int x, int y, int goalX, int goalY) {
        return Math.abs(x - goalX) + Math.abs(y - goalY);
    }

    private boolean containsNode(List<Node> list, int x, int y) {
        for (Node node : list) {
            if (node.x == x && node.y == y) {
                return true;
            }
        }
        return false;
    }

    public abstract void delete();
}
