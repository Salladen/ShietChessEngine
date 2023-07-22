package ChessLogic.PIECES;

import ChessLogic.ENUMS.Colors;
import ChessLogic.ENUMS.Direction;
import ChessLogic.TileNode;

// Here we can grab the Rook and Bishop classes and combine them into a Queen class
public class Queen extends Piece<Direction, TileNode<Direction, Colors>, Colors>{
    public Queen(Colors color, TileNode<Direction, Colors> position) {
        super(color, position);
    }

    @Override
    public void updateLegalMoves() {
        this.legalMoves.clear();
        bishopUpdate();
        rookUpdate();
    }

    private void rookUpdate() {
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

    private void bishopUpdate() {
        Direction[] directions = {Direction.UP_LEFT, Direction.UP_RIGHT, Direction.DOWN_LEFT, Direction.DOWN_RIGHT};

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
