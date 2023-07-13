package ChessLogic.PIECES;

import ChessLogic.ENUMS.Colors;
import ChessLogic.ENUMS.Direction;
import ChessLogic.TileNode;

public class Knight extends Piece<Direction, TileNode<Direction, Colors>, Colors> {
    public Knight(Colors color, TileNode<Direction, Colors> position) {
        super(color, position);
    }

    @Override
    public void updateLegalMoves() {
        this.legalMoves.clear();

        // These are short and well-defined enough to be hardcoded
        Direction[][] moves = new Direction[][]{
                {Direction.UP, Direction.UP, Direction.LEFT},
                {Direction.UP, Direction.UP, Direction.RIGHT},
                {Direction.DOWN, Direction.DOWN, Direction.LEFT},
                {Direction.DOWN, Direction.DOWN, Direction.RIGHT},
                {Direction.LEFT, Direction.LEFT, Direction.UP},
                {Direction.LEFT, Direction.LEFT, Direction.DOWN},
                {Direction.RIGHT, Direction.RIGHT, Direction.UP},
                {Direction.RIGHT, Direction.RIGHT, Direction.DOWN}
        };

        moveIter:
        for (Direction[] dirs : moves) {
            TileNode<Direction, Colors> pos = this.position;
            for (Direction d : dirs) {
                pos = pos.getNeighbour(d);

                if (pos == null) {
                    continue moveIter; // Move is illegal
                }
            }

            if (pos.occupant == null || pos.occupant.color != this.color) {
                this.legalMoves.add(new Move<>(dirs, pos.occupant));
            }
        }
    }
}
