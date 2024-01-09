package ChessLogic.PIECES.Standard;

import ChessLogic.ENUMS.Colors;
import ChessLogic.ENUMS.Direction;
import ChessLogic.GAME_SYS.Standard.TileNode;
import ChessLogic.GAME_SYS.Standard.Move;

import java.util.Set;

public class Queen extends Piece {
    public Queen(Colors color, TileNode position) {
        super(color, position);
    }

    @Override
    public void updateLegalMoves() {
        this.legalMoves.clear();
        Set<Move> bishopMoves = Bishop.bishopUpdate(this);
        Set<Move> rookMoves = Rook.rookUpdate(this);

        this.legalMoves.addAll(bishopMoves);
        this.legalMoves.addAll(rookMoves);
    }
}
