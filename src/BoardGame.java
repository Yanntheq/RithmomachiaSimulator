import java.util.ArrayList;

public interface BoardGame {
    boolean move(int pieceX, int pieceY, int amount, Direction direction, Piece.Color color);

    boolean capture(ArrayList<Piece> capturingPieces, int capturedPieceX, int capturedPieceY, Piece.Color color);

    boolean gameEnded();

    enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT,
        DIAGONALUPLEFT,
        DIAGONALUPRIGHT,
        DIAGONALDOWNLEFT,
        DIAGONALDOWNRIGHT
    }
}
