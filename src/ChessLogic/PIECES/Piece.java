package ChessLogic.PIECES;

import ChessLogic.ENUMS.Colors;
import ChessLogic.ENUMS.Direction;
import ChessLogic.TileNode;

import java.util.*;


public abstract class Piece <D extends Direction, T extends TileNode<D, C>, C extends Colors> {
    public T position;
    final C color;
    // This is a prime example of our generics getting out of hand, but it will simply be cryptic in docstrings in the future and not a pain to work with,
    // so yet again, we might as well make it easier for future us.
    protected final Set<Move<D, C, ? extends Piece<D, T, C>>> legalMoves;
    protected boolean isPinned = false;

    public Piece(C color, T position) {
        this.color = color;
        this.position = position;
        this.legalMoves = new HashSet<>(3);
    }

    // This should be only be subscribed to something that updates it when a RELEVANT piece moves
    // The board object is responsible for handling pinning and check so the piece does not need to know about it
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
        this.position.moveTo(destination);
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
