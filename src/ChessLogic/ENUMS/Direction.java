package ChessLogic.ENUMS;

public enum Direction {
    UP(1),
    DOWN(2),
    LEFT(3),
    RIGHT(4),
    //TODO: Shift to a different strategy for values that are linearly independent
    // (i.e. not a sum of two other values and therefore have no overlap)
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
