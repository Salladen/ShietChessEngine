package ChessLogic.PIECES;

import ChessLogic.ENUMS.Colors;
import ChessLogic.ENUMS.Direction;
import ChessLogic.TileNode;

import java.util.*;


public abstract class Piece <D extends Direction, T extends TileNode<D, C>, C extends Colors> {
    public T position;
    final C color;
    //TODO: This should be a set, not a collection.
    // This is a prime example of our generics getting out of hand, but it will simply be cryptic in docstrings in the future and not a pain to work with,
    // so yet again, we might as well make it easier for future us.
    protected final Collection<Move<D, C, ? extends Piece<D, T, C>>> legalMoves;

    public Piece(C color, T position) {
        this.color = color;
        this.position = position;
        this.legalMoves = new ArrayList<>(3);
    }

    public abstract void updateLegalMoves();

    public boolean canMove(T destination) {
        for (var move : legalMoves) {
            T pos = this.position;

            //TODO:
            // We should just have an attribute that is the final position of the move.

            // Get the final position of the move
            for (var d : move.direction){
                pos = (T) this.position.getNeighbour(d); // Unchecked cast but <T> is an extension of TileNode<D, C> so it is safe
            }

            // Check if the final position is the destination and that the move does not capture a piece
            if (pos == destination && move.capturedPiece == null){
                return true;
            }
        }

        return false;
    }

    public boolean move(T destination) {
        if (!this.canMove(destination)) {
            return false;
        }

        // Assumes that the move is legal
        this.position.moveTo(destination);
        return true;
    }

    public boolean capture(T destination) {
        if (!this.canCapture(destination)) {
            return false;
        }

        // Assumes that the move is legal
        destination.occupant = null;
        // stealFrom is essentially just the same as moveTo
        // The sole reason for it existing is to contrast capture from move so that the code is differentiable
        destination.stealFrom(this.position);
        return true;
    }

    public boolean canCapture(T destination) {
        for (var move : legalMoves) {
            T pos = this.position;

            //TODO:
            // We should just have an attribute that is the final position of the move.

            // Get the final position of the move
            for (var d : move.direction){
                pos = (T) this.position.getNeighbour(d); // Unchecked cast but <T> is an extension of TileNode<D, C> so it is safe
            }

            // Check if the final position is the destination and that the move captures a piece
            if (pos == destination && move.capturedPiece != null){
                return true;
            }
        }

        return false;
    }
}
