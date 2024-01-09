package ChessLogic.ENUMS;

import java.util.HashMap;

public enum Direction {
    UP(2),
    DOWN(3),
    LEFT(5),
    RIGHT(7),

    UP_LEFT(UP.value * LEFT.value),
    UP_RIGHT(UP.value * RIGHT.value),
    DOWN_LEFT(DOWN.value * LEFT.value),
    DOWN_RIGHT(DOWN.value * RIGHT.value);

    private final int value;
    private final HashMap<Integer, Direction> valueMap = new HashMap<>();

    Direction(int value) {
        this.value = value;
        valueMap.put(value, this);
    }

    public int getValue() {
        return value;
    }

    public Direction combine(Direction other) {
        return valueMap.get(this.value * other.value);
    }

    @Override
    public String toString() {
        return this.name();
    }
}
