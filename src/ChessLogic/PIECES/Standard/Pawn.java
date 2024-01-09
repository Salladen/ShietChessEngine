package ChessLogic.PIECES.Standard;

import ChessLogic.ENUMS.Colors;
import ChessLogic.ENUMS.Direction;
import ChessLogic.GAME_SYS.Standard.Move;
import ChessLogic.GAME_SYS.Standard.TileNode;

import static ChessLogic.ENUMS.Direction.*;

public class Pawn extends Piece {
    boolean passantAble = false;
    boolean startingPosition = true;
    boolean promotion = false;

    public Pawn(Colors color, TileNode position) {
        super(color, position);
    }

    @Override
    public boolean move(TileNode destination) {
        if (!this.canMove(destination)) {
            return false;
        }

        // Assumes that the move is legal
        this.position.moveTo(destination);
        this.startingPosition = false;
        if (destination.getNeighbour(UP) != null) {
            this.promotion = true;
        }

        return true;
    }

    @Override
    public boolean capture(TileNode destination) {
        if (!this.canCapture(destination)) {
            return false;
        }

        destination.occupant = null;
        this.position.moveTo(destination);
        this.startingPosition = false;
        if (destination.getNeighbour(UP).occupant != null) {
            this.promotion = true;
        }

        return true;
    }

    @Override
    public void updateLegalMoves() {
        this.legalMoves.clear(); // Some may be invalid now
        if (this.position.getNeighbour(UP).occupant == null) {
            this.legalMoves.add(new Move(this.position, new Direction[]{UP}, null)); // Single move
        }

        if (this.position.getNeighbour(UP).getNeighbour(UP).occupant == null && this.startingPosition) {
            this.legalMoves.add(new Move(this.position, new Direction[]{UP, UP}, null)); // Double move
        }

        Direction[] captureMoves = {UP_LEFT, UP_RIGHT};
        for (Direction d : captureMoves) {
            TileNode dest = this.position.getNeighbour(d);
            if (dest == null) {
                continue; // Skip if the destination is out of bounds
            }

            if (dest.occupant != null && dest.occupant.color != this.color) {
                this.legalMoves.add(new Move(this.position, new Direction[]{d}, dest.occupant)); // Capture
            }

            Direction[] path = {UP, UP, UP, UP}; // If this ends up in null, then it's close enough to the opponent starting row
            boolean enPassantFeasible;
            TileNode temp_dest = this.position;
            for (Direction dir : path) {
                temp_dest = temp_dest.getNeighbour(dir);
                if (temp_dest == null) {
                    // We've landed on the outermost row
                    break;
                }
            }
            enPassantFeasible = temp_dest == null; // If we've landed on the outermost row, then we can en passant

            if (enPassantFeasible && dest.occupant instanceof Pawn possiblePassant && possiblePassant.passantAble) {
                this.legalMoves.add(new Move(this.position, new Direction[]{d}, possiblePassant)); // En passant
            }
        }
    }
}
