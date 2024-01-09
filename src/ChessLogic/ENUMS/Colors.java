package ChessLogic.ENUMS;

public enum Colors {
    WHITE,
    BLACK;

    public Colors getOpposite() {
        return this == WHITE ? BLACK : WHITE;
    }
}
