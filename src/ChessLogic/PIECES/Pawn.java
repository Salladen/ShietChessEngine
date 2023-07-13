package ChessLogic.PIECES;

import ChessLogic.ENUMS.Colors;
import ChessLogic.ENUMS.Direction;
import ChessLogic.TileNode;
import static ChessLogic.ENUMS.Direction.*;

//TODO: These generics are getting out of hand, but should not be set to Piece here, but propagated from Knight
// These will be a massive pain to work with, but it will help when initializing different boards.
// Architecture is (a pain but) hard to change after all, so we might as well make it easier for future us.
public class Pawn extends Piece<Direction, TileNode<Direction, Colors>, Colors> {
    boolean passantAble = false;
    boolean startingPosition = true;
    boolean promotion = false;

    public Pawn(Colors color, TileNode<Direction, Colors> position) {
        super(color, position);
    }

    @Override
    public boolean move(TileNode<Direction, Colors> destination) {
        if (!this.canMove(destination)){
            return false;
        }

        // Assumes that the move is legal
        this.position.moveTo(destination);
        this.startingPosition = false;
        if (destination.getNeighbour(UP).occupant != null){
            this.promotion = true;
        }

        return true;
    }

    @Override
    public boolean capture(TileNode<Direction, Colors> destination) {
        if (!this.canCapture(destination)){
            return false;
        }

        // Assumes that the move is legal
        destination.occupant = null;
        // stealFrom is essentially just the same as moveTo
        // The sole reason for it existing is to contrast capture from move so that the code is differentiable
        destination.stealFrom(this.position);
        this.startingPosition = false;
        if (destination.getNeighbour(UP).occupant != null){
            this.promotion = true;
        }

        return true;
    }

    @Override
    public void updateLegalMoves() {
        this.legalMoves.clear(); // Some may be invalid now
        if (this.position.getNeighbour(UP).occupant == null){
            this.legalMoves.add(new Move<>(new Direction[]{UP}, null)); // Single move
        }

        if (this.position.getNeighbour(UP).getNeighbour(UP).occupant == null && this.startingPosition){
            this.legalMoves.add(new Move<>(new Direction[]{UP, UP}, null)); // Double move
        }

        Direction[] captureMoves = {UP_LEFT, UP_RIGHT};
        for (Direction d : captureMoves){
            TileNode<Direction, Colors> dest = this.position.getNeighbour(d);
            if (dest == null){
                continue; // Skip if the destination is out of bounds
            }

            if (dest.occupant != null && dest.occupant.color != this.color){
                this.legalMoves.add(new Move<>(new Direction[]{d}, dest.occupant)); // Capture
            }

            //TODO: This en passant check should when not in proximity to applicable rows
            // Distance from pawn to outermost row is 2 so that could be used
            if (dest.occupant instanceof Pawn possiblePassant && ((Pawn) dest.occupant).passantAble){
                this.legalMoves.add(new Move<>(new Direction[]{d}, possiblePassant)); // En passant
            }
        }
    }
}
