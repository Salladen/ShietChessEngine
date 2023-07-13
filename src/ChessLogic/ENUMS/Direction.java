package ChessLogic.ENUMS;

public enum Direction {
    UP(1),
    DOWN(2),
    LEFT(3),
    RIGHT(4),
    UP_LEFT(UP.value + LEFT.value),
    UP_RIGHT(UP.value + RIGHT.value),
    DOWN_LEFT(DOWN.value + LEFT.value),
    DOWN_RIGHT(DOWN.value + RIGHT.value);

    private final int value;

    Direction(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
