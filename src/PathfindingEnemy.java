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
    
    private void initializeWalkableTiles(int[][] map) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (map[i][j] == 0) { 
                    walkableTiles.add(new Node(i, j, null));  
                }
            }
        }
    }
    
    public List<int[]> AStarAlgorithm(int[][] map, int startX, int startY, int endX, int endY) {
        rows = map.length;
        cols = map[0].length;
        initializeWalkableTiles(map);

        start = new Node(startX, startY, null);
        end = new Node(endX, endY, null);

        
        start.g = 0;
        start.h = calculateHeuristic(start.x, start.y, end.x, end.y);
        start.f = start.g + start.h;

        walkableTiles.add(start);

        while (!walkableTiles.isEmpty()) {
            Node current = walkableTiles.get(lowestF());  
            walkableTiles.remove(current);

            // If we reached the goal, reconstruct the path
            if (current.x == end.x && current.y == end.y) {
                List<int[]> path = new ArrayList<>();
                while (current != null) {
                    path.add(new int[]{current.x, current.y});  
                    current = current.parent;  
                }
                Collections.reverse(path);  
                return path;
            }

            walkedTiles.add(current);  

            // Explore neighbors
            for (int i = 0; i < 4; i++) {
                int newX = current.x + dx[i];
                int newY = current.y + dy[i];

                // If the new position is valid and walkable
                if (isValid(newX, newY, map) && !containsNode(walkedTiles, newX, newY)) {
                    int newG = current.g + 1;  
                    int newH = calculateHeuristic(newX, newY, end.x, end.y);  
                    Node neighbor = new Node(newX, newY, newG, newH, current);  
                    neighbor.f = neighbor.g + neighbor.h;

                    // If neighbor is not in the open list, add it
                    if (!containsNode(walkableTiles, newX, newY)) {
                        walkableTiles.add(neighbor);
                    }
                }
            }
        }

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

    private boolean isValid(int x, int y, int[][] map) {
        return x >= 0 && y >= 0 && x < rows && y < cols && map[x][y] == 0;
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

    // Abstract methods to be implemented by subclasses
    public abstract boolean collisionCheck(int[] position);

    public abstract void delete();
}
