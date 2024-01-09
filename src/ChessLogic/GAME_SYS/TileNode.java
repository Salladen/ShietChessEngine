package ChessLogic.GAME_SYS;

import ChessLogic.ENUMS.Colors;
import ChessLogic.ENUMS.Direction;
import ChessLogic.PIECES.Piece;

public class TileNode<D extends Direction, C extends Colors> {
    private final TileNode<D, C>[] neighbours;
    public Piece occupant;
    public final C color;

    public TileNode(TileNode<D, C>[] neighbours, C color) {
        this.neighbours = neighbours;
        this.color = color;
    }

    public TileNode<D, C> getNeighbour (D direction) {
        return this.neighbours[direction.ordinal()];
    }

    public void setNeighbour (D direction, TileNode<D, C> neighbour) {
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

    public String neighboursToString() {
        StringBuilder sb = new StringBuilder();
        boolean first = true;

        for (Direction dir : D.values()) {
            D d = (D) dir;

            if (this.getNeighbour(d) == null) {
                continue;
            }

            if (first) {
                first = false;
            } else {
                sb.append(", ");
            }

            if (this.getNeighbour(d).occupant != null) {
                sb.append(d).append("_").append(this.getNeighbour(d).occupant);
            } else {
                sb.append(d).append("_").append("null");
            }
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.occupant)
          .append("| ")
          .append(neighboursToString())
          .append("|\n");
        return sb.toString();
    }
}
