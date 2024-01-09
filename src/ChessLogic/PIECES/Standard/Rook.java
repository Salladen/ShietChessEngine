package ChessLogic.PIECES.Standard;

import ChessLogic.ENUMS.Colors;
import ChessLogic.ENUMS.Direction;
import ChessLogic.GAME_SYS.Standard.Move;
import ChessLogic.GAME_SYS.Standard.TileNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Rook extends Piece {
    boolean hasMoved = false;

    public Rook(Colors color, TileNode position) {
        super(color, position);
    }

    @Override
    public void updateLegalMoves() {
        this.legalMoves.clear(); // Some may be invalid now
        this.legalMoves.addAll(rookUpdate(this));
    }

    public static Set<Move> rookUpdate(Piece piece) {
        Set<Move> map = new HashSet<>();
        Direction[] directions = {Direction.UP, Direction.RIGHT, Direction.DOWN, Direction.LEFT};

        for (Direction dir : directions) {
            TileNode pos = piece.position;
            while (true) {
                pos = pos.getNeighbour(dir);

                if (pos == null) {
                    // This is out of bounds
                    break;
                }

                if (pos.occupant == null) {
                    // This is an empty tile
                    map.add(new Move(piece.position, new Direction[]{dir}, null));
                } else if (pos.occupant.color != piece.color) {
                    // This is an enemy piece
                    map.add(new Move(piece.position, new Direction[]{dir}, pos.occupant));
                    break;
                } else {
                    // This is a friendly piece
                    break;
                }
            }
        }

        return map;
    }
}
