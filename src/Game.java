import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 * Class that takes in user input and updates the {@link Board} object accordingly.
 *
 * @author Yann Quinard
 */
public class Game {
    public static void main(String[] args) {
        System.out.println("Starting new game! How many pieces would you like to play up to?");
        Scanner scan = new Scanner(System.in);
        int winCon = 5;
        if (scan.hasNextInt()) {
            winCon = scan.nextInt();
        } else {
            System.out.println("Please enter a valid number!");
            System.exit(1);
        }
        Board board = new Board(winCon);
        Piece.Color turn = Piece.Color.BLACK;

        while (!board.gameEnded()) {
            System.out.println(board);
            System.out.println("It is " + turn + "'s turn to play.");
            System.out.println("Please enter you move in the form: [x] [y] [number of spaces] [direction]. Direction must be either \"up\", \"down\", \"left\", \"right\", \"diagonalupleft\", \"diagonalupright\", \"diagonaldownleft\", or \"diagonaldownright\".");
            boolean moved;
            try {
                moved = board.move(scan.nextInt(), scan.nextInt(), scan.nextInt(), BoardGame.Direction.valueOf(scan.next().toUpperCase()), turn);
                System.out.println("Would you like to capture?");
                String capture = scan.next().toLowerCase();
                if (capture.equals("yes") || capture.equals("y")) {
                    boolean captured = false;
                    while (!scan.next().contains("cancel") && !captured) {
                        System.out.println("Please enter you capture in the form: [number of pieces to capture with] [capturing piece #1 x] [capturing piece #1 y] [capturing piece #1 x] [capturing piece #2 y]...[captured piece x] [captured piece y].");
                        ArrayList<Piece> capturingPieces = new ArrayList<>();
                        int numPieces = scan.nextInt();
                        for (int i = 0; i < numPieces; i++) {
                            Piece piece = board.getBoardPiece(scan.nextInt(), scan.nextInt(), true);
                            if (Objects.isNull(piece)) {
                                System.out.println("Invalid format!");
                                continue;
                            }
                            capturingPieces.add(piece);
                        }
                        captured = board.capture(capturingPieces, scan.nextInt(), scan.nextInt(), turn);
                        if (moved) {
                            if (turn == Piece.Color.WHITE) {
                                board.incrementWhiteCaptured();
                            } else {
                                board.incrementBlackCaptured();
                            }
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Invalid format: " + e.getMessage() + " Caused by " + e.getCause());
                continue;
            }
            if (moved) {
                if (turn == Piece.Color.BLACK) {
                    turn = Piece.Color.WHITE;
                } else {
                    turn = Piece.Color.BLACK;
                }
            }
        }
        scan.close();
    }
}
