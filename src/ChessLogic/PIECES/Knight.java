package ChessLogic.PIECES;

import ChessLogic.ENUMS.Colors;
import ChessLogic.ENUMS.Direction;
import ChessLogic.TileNode;

//TODO: These generics are getting out of hand, but should not be set to Piece here, but propagated from Knight
// These will be a massive pain to work with, but it will help when initializing different boards.
// Architecture is (a pain but) hard to change after all, so we might as well make it easier for future us.
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

        //TODO: Refactor this to be more readable, maybe utilize streams
        moveIter:
        for (Direction[] dirs : moves) {
            //TODO:
            // We should just have an attribute that is the final position of the move.

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
