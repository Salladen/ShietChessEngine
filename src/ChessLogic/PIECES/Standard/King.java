package ChessLogic.PIECES.Standard;

import ChessLogic.ENUMS.Colors;
import ChessLogic.ENUMS.Direction;
import ChessLogic.GAME_SYS.Standard.TileNode;
import ChessLogic.GAME_SYS.Standard.Move;

public class King extends Piece {
    boolean hasMoved = false;

    public King(Colors color, TileNode position) {
        super(color, position);
    }

    @Override
    public void updateLegalMoves() {
        Direction[] directions = {
                Direction.UP_LEFT, Direction.UP, Direction.UP_RIGHT,
                Direction.LEFT, Direction.RIGHT,
                Direction.DOWN_LEFT, Direction.DOWN, Direction.DOWN_RIGHT
        };

        for (Direction dir : directions) {
            TileNode pos = this.position.getNeighbour(dir);

            if (pos == null) {
                // This is out of bounds
                continue;
            }

            if (pos.occupant == null) {
                // This is an empty tile
                this.legalMoves.add(new Move(this.position, new Direction[]{dir}, null));
            } else if (pos.occupant.color != this.color) {
                // This is an enemy piece
                this.legalMoves.add(new Move(this.position, new Direction[]{dir}, pos.occupant));
            }
        }

        // The board handles cases where the king is in check, or a tile in the path is being threatened
        updateCastlingMoves();
    }

    public void updateCastlingMoves() {
        if (this.hasMoved) {
            return;
        }

        Direction[] directions = new Direction[]{Direction.LEFT, Direction.RIGHT};

        // Cast a ray in each direction
        for (Direction dir : directions) {
            TileNode pos = this.position;
            while (true) {
                pos = pos.getNeighbour(dir);

                if (pos == null) {
                    // This is out of bounds
                    break;
                }

                if (pos.occupant == null) {
                    // This is an empty tile
                    continue;
                } else if (pos.occupant.color != this.color) {
                    // This is an enemy piece
                    continue;
                } else {
                    // This is a friendly piece
                    if (pos.occupant instanceof Rook rook) {
                        if (!rook.hasMoved && !this.hasMoved) {
                            // This is a friendly rook that hasn't moved
                            // and this is a friendly king that hasn't moved
                            // so castling is possible
                            this.legalMoves.add(new Move(this.position, new Direction[]{dir}, pos.occupant));
                        }
                    }
                    break;
                }
            }
        }
    }
}
