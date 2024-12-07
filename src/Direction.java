public enum Direction {
    UP, DOWN, LEFT, RIGHT;

    public Direction clockwiseRotation() {
        switch (this) {
            case UP: return RIGHT;
            case RIGHT: return DOWN;
            case DOWN: return LEFT;
            case LEFT: return UP;
            default: throw new IllegalStateException("you silly goose");
        }
    }

    public Direction antiClockwiseRotation() {
        switch (this) {
            case UP: return LEFT;
            case LEFT: return DOWN;
            case DOWN: return RIGHT;
            case RIGHT: return UP;
            default: throw new IllegalStateException("you silly goose");
        }
    }
}
