package ChessLogic.PIECES.Standard;

import ChessLogic.ENUMS.Colors;
import ChessLogic.ENUMS.Direction;
import ChessLogic.GAME_SYS.Standard.Move;
import ChessLogic.GAME_SYS.Standard.TileNode;

import java.util.HashSet;
import java.util.Set;

public abstract class Piece extends ChessLogic.PIECES.Piece<Direction, TileNode, Colors> {
    public TileNode position;
    public final Colors color;
    protected Set<Move> pinnedBy;

    // This is a prime example of our generics getting out of hand, but it will simply be cryptic in docstrings in the future and not a pain to work with,
    // so yet again, we might as well make it easier for future us.
    protected final Set<Move> legalMoves;

    public Piece(Colors color, TileNode position) {
        super(color, position);
        this.color = color;
        this.position = position;

        this.legalMoves = new HashSet<>(3);
    }

    // This should be only be subscribed to something that updates it when a RELEVANT piece moves
    // The board object is responsible for handling pinning and check so the piece does not need to know about it
    public abstract void updateLegalMoves();

    @Override
    public boolean canMove(TileNode destination) {
        for (var move : legalMoves) {
            TileNode pos = this.position;

            //TODO:
            // We should just have an attribute that is the final position of the move.

            // Get the final position of the move
            for (var d : move.direction) {
                pos = this.position.getNeighbour(d); // Unchecked cast but <T> is an extension of TileNode<D, C> so it is safe
            }

            // Check if the final position is the destination and that the move does not capture a piece
            if (pos == destination && move.capturedPiece == null) {
                return true;
            }
        }

        return false;
    }

    public boolean move(TileNode destination) {
        if (!this.canMove(destination)) {
            return false;
        }

        // Assumes that the move is legal
        this.position.moveTo(destination);
        return true;
    }

    public boolean capture(TileNode destination) {
        if (!this.canCapture(destination)) {
            return false;
        }

        // Assumes that the move is legal
        destination.occupant = null;
        this.position.moveTo(destination);
        return true;
    }

    public boolean canCapture(TileNode destination) {
        for (var move : legalMoves) {
            TileNode pos = this.position;

            //TODO:
            // We should just have an attribute that is the final position of the move.

            // Get the final position of the move
            for (var d : move.direction) {
                // assert that T is an extension of TileNode<D, C>
                pos = this.position.getNeighbour(d); // Unchecked cast but <T> is an extension of TileNode<D, C> so it is safe
            }

            // Check if the final position is the destination and that the move captures a piece
            if (pos == destination && move.capturedPiece != null) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        return String.format("%s%s", this.color.toString().charAt(0), this.getClass().getSimpleName());
    }
}
