package ChessLogic.PIECES;

import ChessLogic.ENUMS.Colors;
import ChessLogic.ENUMS.Direction;
import ChessLogic.TileNode;

public class Rook extends Piece<Direction, TileNode<Direction, Colors>, Colors> {
    boolean hasMoved = false;

    public Rook(Colors color, TileNode<Direction, Colors> position) {
        super(color, position);
    }

    @Override
    public void updateLegalMoves() {
        Direction[] directions = {Direction.UP, Direction.RIGHT, Direction.DOWN, Direction.LEFT};

        for (Direction dir : directions){
            TileNode<Direction, Colors> pos = this.position;
            while (true){
                pos = pos.getNeighbour(dir);

                if (pos == null){
                    // This is out of bounds
                    break;
                }

                if (pos.occupant == null){
                    // This is an empty tile
                    this.legalMoves.add(new Move<>(new Direction[]{dir}, null));
                }
                else if (pos.occupant.color != this.color){
                    // This is an enemy piece
                    this.legalMoves.add(new Move<>(new Direction[]{dir}, pos.occupant));
                    break;
                }
                else {
                    // This is a friendly piece
                    break;
                }
            }
        }
    }
}
