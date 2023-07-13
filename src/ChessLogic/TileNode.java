package ChessLogic;

import ChessLogic.ENUMS.Colors;
import ChessLogic.ENUMS.Direction;
import ChessLogic.PIECES.Piece;

public class TileNode <D extends Direction, C extends Colors> {
    private final TileNode<D, C>[] neighbours;
    public Piece<D, TileNode<D, C>, C> occupant;

    public TileNode(TileNode<D, C>[] neighbours) {
        this.neighbours = neighbours;
    }

    public TileNode<D, C> getNeighbour (D direction) {
        return this.neighbours[direction.ordinal()];
    }

    public void setNeighbour (Direction direction, TileNode<D, C> neighbour) {
        this.neighbours[direction.ordinal()] = neighbour;
    }

    public boolean moveTo(TileNode<D, C> destination) {
        if (destination.occupant != null) {
            return false;
        }

        destination.occupant = this.occupant;
        destination.occupant.position = destination;
        this.occupant = null;
        return true;
    }

    public boolean stealFrom(TileNode<D, C> destination) {
        //TODO: Remove this method and replace it with moveTo

        if (this.occupant != null) {
            return false;
        }

        this.occupant = destination.occupant;
        destination.occupant = null;
        return true;
    }
}
