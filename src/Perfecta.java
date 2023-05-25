import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents a Perfecta Piece.
 *
 * @author Yann Quinard
 */
public class Perfecta extends Piece {
    private ArrayList<Piece> pieces;

    public Perfecta(Color color, int i, int j) {
        super(0, Shape.PERFECTA, color, i, j);
        if (color == Color.BLACK) {
            super.setNumber(190);
        } else {
            super.setNumber(91);
        }
        switch (color) {
            case BLACK ->
                    this.pieces = new ArrayList<>(Arrays.asList(new Piece(1, Shape.CIRCLE, Color.BLACK, i, j), new Piece(4, Shape.CIRCLE, Color.BLACK, i, j), new Piece(9, Shape.TRIANGLE, Color.BLACK, i, j), new Piece(16, Shape.TRIANGLE, Color.BLACK, i, j), new Piece(25, Shape.SQUARE, Color.BLACK, i, j), new Piece(36, Shape.SQUARE, Color.BLACK, i, j)));
            case WHITE ->
                    this.pieces = new ArrayList<>(Arrays.asList(new Piece(16, Shape.CIRCLE, Color.WHITE, i, j), new Piece(25, Shape.CIRCLE, Color.WHITE, i, j), new Piece(36, Shape.TRIANGLE, Color.WHITE, i, j), new Piece(49, Shape.TRIANGLE, Color.WHITE, i, j), new Piece(64, Shape.SQUARE, Color.WHITE, i, j)));
        }
    }

    public void updateNumber() {
        int sum = pieces.get(pieces.size() - 1).getNumber();
        if (pieces.size() == 6 && super.getColor() == Color.WHITE || pieces.size() == 5 && super.getColor() == Color.BLACK) {
            for (Piece piece : this.pieces) {
                sum += piece.getNumber();
            }
        }
        super.setNumber(sum);
    }

    /**
     * Check whether {@code this} piece can move by {@code amount}.
     *
     * @param amount The amount {@code this} piece is trying to be moved by
     * @return {@code true} if the piece can be moved by {@code amount} or false otherwise
     */
    public boolean validMovement(int amount) {
        for (Piece piece : pieces) {
            if (piece.getMovement() == amount) {
                return true;
            }
        }
        return false;
    }

    /**
     * Remove a {@link Piece} from the Perfecta while updating the value of the perfecta
     *
     * @param perfectaPieceNumber The index of the piece to be removed
     */
    public void capture(int perfectaPieceNumber) {
        this.pieces.remove(perfectaPieceNumber);
        this.updateNumber();
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
    }
}
