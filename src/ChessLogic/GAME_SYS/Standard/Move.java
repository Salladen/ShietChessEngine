package ChessLogic.GAME_SYS.Standard;

import ChessLogic.ENUMS.Colors;
import ChessLogic.ENUMS.Direction;
import ChessLogic.PIECES.Standard.Piece;

public class Move extends ChessLogic.GAME_SYS.Move<Direction, Colors, Piece> {
    public final TileNode source = (TileNode) super.source;
    public Move(TileNode source, Direction[] direction, Piece capturedPiece) {
        super(source, direction, capturedPiece);
    }
}
