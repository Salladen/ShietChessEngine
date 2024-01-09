package ChessLogic.GAME_SYS.Standard;

import ChessLogic.ENUMS.Colors;
import ChessLogic.ENUMS.Direction;
import ChessLogic.PIECES.Standard.*;
import jdk.jshell.spi.ExecutionControl;

import java.io.Console;

public class Board {
    private final TileNode root;

    public Board() {
        root = new TileNode(new TileNode[4], Colors.WHITE);

        initBoard();
        initPieces();
    }

    private void initBoard() {
        // Standard chess board is 8x8
        TileNode current = root;
        TileNode rowRoot = root;

        for (int row = 0; row < 8; row++) {
            if (row != 0 && row < 7) {
                // Create, then set the down neighbour of the row root
                TileNode down = new TileNode(new TileNode[4], rowRoot.color == Colors.WHITE ? Colors.BLACK : Colors.WHITE);
                rowRoot.setNeighbour(Direction.DOWN, down);
                down.setNeighbour(Direction.UP, rowRoot);
                rowRoot = down;
                current = rowRoot;
            }

            for (int col = 0; col < 8; col++) {
                // Opposite color of the current tile
                Colors color = current.color == Colors.WHITE ? Colors.BLACK : Colors.WHITE;
                TileNode right = new TileNode(new TileNode[4], color);
                current.setNeighbour(Direction.RIGHT, right);
                right.setNeighbour(Direction.LEFT, current);

                if (row != 0) {
                    TileNode up = (TileNode) rowRoot.getNeighbour(Direction.UP);
                    current.setNeighbour(Direction.UP, up);
                    up.setNeighbour(Direction.DOWN, current);
                }

                current = (TileNode) current.getNeighbour(Direction.RIGHT);
            }
        }
    }

    private void initPieces() {
        setBoardFromFEN("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
    }

    private void setBoardFromFEN(String fen) {
        String[] parts = fen.split(" ");
        if (parts.length < 6) {
            throw new IllegalArgumentException("Invalid FEN string");
        }

        String board = parts[0];
        String activeColor = parts[1];
        String castling = parts[2];
        String enPassant = parts[3];
        String halfMoves = parts[4];
        String fullMoves = parts[5];

        // TODO: Implement other parts of FEN (like castling, en passant, etc.)

        String[] rows = board.split("/");
        if (rows.length != 8) {
            throw new IllegalArgumentException("Invalid FEN board layout");
        }

        TileNode currentRowStart = root;
        TileNode current;

        for (int i = 0; i < 8; i++) {
            current = currentRowStart;
            String row = rows[i];
            for (int j = 0; j < row.length(); j++) {
                char c = row.charAt(j);
                if (Character.isDigit(c)) {
                    int emptySquares = Character.getNumericValue(c);
                    for (int k = 0; k < emptySquares; k++) {
                        current.occupant = null;
                        current = (TileNode) current.getNeighbour(Direction.RIGHT);
                    }
                } else {
                    switch (c) {
                        case 'r' -> current.occupant = new Rook(Colors.BLACK, current);
                        case 'R' -> current.occupant = new Rook(Colors.WHITE, current);
                        case 'n' -> current.occupant = new Knight(Colors.BLACK, current);
                        case 'N' -> current.occupant = new Knight(Colors.WHITE, current);
                        case 'b' -> current.occupant = new Bishop(Colors.BLACK, current);
                        case 'B' -> current.occupant = new Bishop(Colors.WHITE, current);
                        case 'q' -> current.occupant = new Queen(Colors.BLACK, current);
                        case 'Q' -> current.occupant = new Queen(Colors.WHITE, current);
                        case 'k' -> current.occupant = new King(Colors.BLACK, current);
                        case 'K' -> current.occupant = new King(Colors.WHITE, current);
                        case 'p' -> current.occupant = new Pawn(Colors.BLACK, current);
                        case 'P' -> current.occupant = new Pawn(Colors.WHITE, current);
                        // TODO: Add other pieces here (king, queen, pawn, etc.)
                        default -> throw new IllegalArgumentException("Invalid character in FEN board layout");
                    }
                    System.out.printf("Placed %s at (%d, %d)\n", current.occupant.getClass().getSimpleName(), i, j);
                    System.out.println(current);
                    current = (TileNode) current.getNeighbour(Direction.RIGHT);
                }
            }
            currentRowStart = (TileNode) currentRowStart.getNeighbour(Direction.DOWN);
        }
    }
}


