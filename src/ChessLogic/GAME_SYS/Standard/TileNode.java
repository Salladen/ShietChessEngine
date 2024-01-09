package ChessLogic.GAME_SYS.Standard;

import ChessLogic.ENUMS.Colors;
import ChessLogic.ENUMS.Direction;
import ChessLogic.PIECES.Standard.Piece;

public class TileNode extends ChessLogic.GAME_SYS.TileNode<Direction, Colors> {
    public Piece occupant = (Piece) super.occupant;

    public TileNode(TileNode[] neighbours, Colors color) {
        super(neighbours, color);
    }

    @Override
    public TileNode getNeighbour(Direction direction) {
        return (TileNode) super.getNeighbour(direction);
    }

    @Override
    public void setNeighbour(Direction direction, ChessLogic.GAME_SYS.TileNode<Direction, Colors> neighbour) {
        super.setNeighbour(direction, neighbour);
    }

    @Override
    public boolean moveTo(ChessLogic.GAME_SYS.TileNode<Direction, Colors> destination) {
        return super.moveTo(destination);
    }

    @Override
    public String neighboursToString() {
        StringBuilder sb = new StringBuilder();
        boolean first = true;

        for (Direction dir : Direction.values()) {
            Direction d = (Direction) dir;
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
