package ChessLogic.PIECES;

import ChessLogic.ENUMS.Colors;
import ChessLogic.ENUMS.Direction;
import ChessLogic.TileNode;

public class Move <D extends Direction, C extends Colors, P extends Piece<D, ? extends TileNode<D, C>, C>> {
    public final D[] direction;
    public final P capturedPiece;

    public Move(D[] direction, P capturedPiece) {
        this.direction = direction;
        this.capturedPiece = capturedPiece;
    }
}
