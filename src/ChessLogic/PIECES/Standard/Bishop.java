package ChessLogic.PIECES.Standard;

import ChessLogic.ENUMS.Colors;
import ChessLogic.ENUMS.Direction;
import ChessLogic.GAME_SYS.Standard.TileNode;
import ChessLogic.GAME_SYS.Standard.Move;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Bishop extends Piece {
    public Bishop(Colors color, TileNode position) {
        super(color, position);
    }

    @Override
    public void updateLegalMoves() {
        this.legalMoves.clear();
        bishopUpdate(this);
    }

    public static Set<Move> bishopUpdate(Piece piece){
        Set<Move> map = new HashSet<>();
        Direction[] directions = {Direction.UP_LEFT, Direction.UP_RIGHT, Direction.DOWN_LEFT, Direction.DOWN_RIGHT};


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
                    map.add(new Move(piece.position, new Direction[]{dir}, (Piece) pos.occupant));
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
