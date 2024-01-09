package ChessLogic.GAME_SYS;

import ChessLogic.ENUMS.Colors;
import ChessLogic.ENUMS.Direction;
import ChessLogic.PIECES.Piece;

public class Move <D extends Direction, C extends Colors, P extends Piece<D, ? extends TileNode<D, C>, C>> {
    public final TileNode<D, C> source;
    public final D[] direction;
    public final P capturedPiece;

    public Move(TileNode<D, C> source, D[] direction, P capturedPiece) {
        this.source = source;
        this.direction = direction;
        this.capturedPiece = capturedPiece;
    }
}
