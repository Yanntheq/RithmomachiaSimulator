import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 * Class that represents the board state of the game.
 * It keeps track of all the pieces, and updates the board state according to movement and capturing.
 *
 * @author Yann Quinard
 */
public class Board implements BoardGame {
    private final int winCon;
    private final Piece[][] board;
    private int numBlackCaptured = 0;
    private int numWhiteCaptured = 0;

    /**
     * Creates a new game with the given board state.
     *
     * @param winCon The number of captures required to win the game
     * @param board  The given board state
     */
    public Board(int winCon, Piece[][] board) {
        this.winCon = winCon;
        this.board = board;
    }

    /**
     * Creates a new game with the default board state.
     *
     * @param winCon The number of captures required to win the game
     */
    public Board(int winCon) {
        board = new Piece[16][8];
        this.winCon = winCon;
        // Black Pieces:
        board[0][0] = new Piece(49, Piece.Shape.SQUARE, Piece.Color.BLACK, 0, 0);
        board[0][1] = new Piece(121, Piece.Shape.SQUARE, Piece.Color.BLACK, 0, 1);
        board[0][6] = new Piece(225, Piece.Shape.SQUARE, Piece.Color.BLACK, 0, 6);
        board[0][7] = new Piece(361, Piece.Shape.SQUARE, Piece.Color.BLACK, 0, 7);
        board[1][0] = new Piece(28, Piece.Shape.SQUARE, Piece.Color.BLACK, 1, 0);
        board[1][1] = new Piece(66, Piece.Shape.SQUARE, Piece.Color.BLACK, 1, 1);
        board[1][2] = new Piece(36, Piece.Shape.TRIANGLE, Piece.Color.BLACK, 1, 2);
        board[1][3] = new Piece(30, Piece.Shape.TRIANGLE, Piece.Color.BLACK, 1, 3);
        board[1][4] = new Piece(56, Piece.Shape.TRIANGLE, Piece.Color.BLACK, 1, 4);
        board[1][5] = new Piece(64, Piece.Shape.TRIANGLE, Piece.Color.BLACK, 1, 5);
        board[1][6] = new Piece(120, Piece.Shape.SQUARE, Piece.Color.BLACK, 1, 6);
        board[1][7] = new Perfecta(Piece.Color.BLACK, 1, 7);
        board[2][0] = new Piece(16, Piece.Shape.TRIANGLE, Piece.Color.BLACK, 2, 0);
        board[2][1] = new Piece(12, Piece.Shape.TRIANGLE, Piece.Color.BLACK, 2, 1);
        board[2][2] = new Piece(9, Piece.Shape.CIRCLE, Piece.Color.BLACK, 2, 2);
        board[2][3] = new Piece(25, Piece.Shape.CIRCLE, Piece.Color.BLACK, 2, 3);
        board[2][4] = new Piece(49, Piece.Shape.CIRCLE, Piece.Color.BLACK, 2, 4);
        board[2][5] = new Piece(81, Piece.Shape.CIRCLE, Piece.Color.BLACK, 2, 5);
        board[2][6] = new Piece(90, Piece.Shape.TRIANGLE, Piece.Color.BLACK, 2, 6);
        board[2][7] = new Piece(100, Piece.Shape.TRIANGLE, Piece.Color.BLACK, 2, 7);
        board[3][2] = new Piece(3, Piece.Shape.CIRCLE, Piece.Color.BLACK, 3, 2);
        board[3][3] = new Piece(5, Piece.Shape.CIRCLE, Piece.Color.BLACK, 3, 3);
        board[3][4] = new Piece(7, Piece.Shape.CIRCLE, Piece.Color.BLACK, 3, 4);
        board[3][5] = new Piece(9, Piece.Shape.CIRCLE, Piece.Color.BLACK, 3, 5);

        // White Pieces:
        board[15][0] = new Piece(289, Piece.Shape.SQUARE, Piece.Color.WHITE, 15, 0);
        board[15][1] = new Piece(169, Piece.Shape.SQUARE, Piece.Color.WHITE, 15, 1);
        board[15][6] = new Piece(81, Piece.Shape.SQUARE, Piece.Color.WHITE, 15, 6);
        board[15][7] = new Piece(25, Piece.Shape.SQUARE, Piece.Color.WHITE, 15, 7);
        board[14][0] = new Piece(153, Piece.Shape.SQUARE, Piece.Color.WHITE, 14, 0);
        board[14][1] = new Perfecta(Piece.Color.WHITE, 14, 1);
        board[14][2] = new Piece(49, Piece.Shape.TRIANGLE, Piece.Color.WHITE, 14, 2);
        board[14][3] = new Piece(42, Piece.Shape.TRIANGLE, Piece.Color.WHITE, 14, 3);
        board[14][4] = new Piece(20, Piece.Shape.TRIANGLE, Piece.Color.WHITE, 14, 4);
        board[14][5] = new Piece(25, Piece.Shape.TRIANGLE, Piece.Color.WHITE, 14, 5);
        board[14][6] = new Piece(45, Piece.Shape.SQUARE, Piece.Color.WHITE, 14, 6);
        board[14][7] = new Piece(15, Piece.Shape.SQUARE, Piece.Color.WHITE, 14, 7);
        board[13][0] = new Piece(81, Piece.Shape.TRIANGLE, Piece.Color.WHITE, 13, 0);
        board[13][1] = new Piece(72, Piece.Shape.TRIANGLE, Piece.Color.WHITE, 13, 1);
        board[13][2] = new Piece(64, Piece.Shape.CIRCLE, Piece.Color.WHITE, 13, 2);
        board[13][3] = new Piece(36, Piece.Shape.CIRCLE, Piece.Color.WHITE, 13, 3);
        board[13][4] = new Piece(16, Piece.Shape.CIRCLE, Piece.Color.WHITE, 13, 4);
        board[13][5] = new Piece(4, Piece.Shape.CIRCLE, Piece.Color.WHITE, 13, 5);
        board[13][6] = new Piece(6, Piece.Shape.TRIANGLE, Piece.Color.WHITE, 13, 6);
        board[13][7] = new Piece(9, Piece.Shape.TRIANGLE, Piece.Color.WHITE, 11, 5);
        board[12][2] = new Piece(8, Piece.Shape.CIRCLE, Piece.Color.WHITE, 12, 2);
        board[12][3] = new Piece(6, Piece.Shape.CIRCLE, Piece.Color.WHITE, 12, 3);
        board[12][4] = new Piece(4, Piece.Shape.CIRCLE, Piece.Color.WHITE, 12, 4);
        board[12][5] = new Piece(2, Piece.Shape.CIRCLE, Piece.Color.WHITE, 12, 5);
    }

    /**
     * Moves a piece by a certain amount, updating the board state.
     *
     * @param pieceX    The x-coordinate of the piece to be moved
     * @param pieceY    The y-coordinate of the piece to be moved
     * @param amount    The amount the piece should be moved by
     * @param direction The direction the piece should be moved in
     * @param color     The color the piece to be moved should be
     * @return {@code true} if the movement was successful and {@code false} if the move was invalid
     */
    public boolean move(int pieceX, int pieceY, int amount, BoardGame.Direction direction, Piece.Color color) {
        int x = pieceX - 1;
        int y = 15 - (pieceY - 1);
        if (y < 0 || y > 15) {
            System.out.println("Invalid move: Y coordinate is out of bounds!");
            return false;
        } else if (x < 0 || x > 7) {
            System.out.println("Invalid move: X coordinate is out of bounds!");
            return false;
        } else if (Objects.isNull(board[y][x])) {
            System.out.println("Invalid move: This piece does not exist!");
            return false;
        }
        Piece piece = board[y][x];
        if (piece.getColor() != color) {
            System.out.println("Invalid move: No Cheating!");
            return false;
        }

        if (!piece.validMovement(amount)) {
            System.out.println("Invalid move: " + piece + " cannot move " + amount + " spaces");
            return false;
        }
        switch (direction) {
            case UP -> {
                if (y - amount < 0) {
                    System.out.println("Invalid move: There is not enough board space!");
                    return false;
                }
                for (int distance = 1; distance <= amount; distance++) {
                    if (!Objects.isNull(board[y - distance][x])) {
                        System.out.println("Invalid move: The path is not clear!");
                        return false;
                    }
                }

                board[y][x] = null;
                board[y - amount][x] = piece;
                piece.setI(y - amount);
                return true;
            }
            case DOWN -> {
                if (y + amount > 15) {
                    System.out.println("Invalid move: There is not enough board space!");
                    return false;
                }
                for (int distance = 1; distance <= amount; distance++) {
                    if (!Objects.isNull(board[y + distance][x])) {
                        System.out.println("Invalid move: The path is not clear!");
                        return false;
                    }
                }
                board[y][x] = null;
                board[y + amount][x] = piece;
                piece.setI(y + amount);
                return true;
            }
            case RIGHT -> {
                if (x + amount > 7) {
                    System.out.println("Invalid move: There is not enough board space!");
                    return false;
                }
                for (int distance = 1; distance <= amount; distance++) {
                    if (!Objects.isNull(board[y][x + distance])) {
                        System.out.println("Invalid move: There is already a piece at this location!");
                        return false;
                    }
                }
                board[y][x] = null;
                board[y][x + amount] = piece;
                piece.setJ(x + amount);
                return true;
            }
            case LEFT -> {
                if (x - amount < 0) {
                    System.out.println("Invalid move: There is not enough board space!");
                    return false;
                }
                for (int distance = 1; distance <= amount; distance++) {
                    if (!Objects.isNull(board[y][x - distance])) {
                        System.out.println("Invalid move: There is already a piece at this location!");
                        return false;
                    }
                }
                board[y][x] = null;
                board[y][x - amount] = piece;
                piece.setJ(x - amount);
                return true;
            }
            case DIAGONALUPLEFT -> {
                if (y - amount < 0 || x - amount < 0) {
                    System.out.println("Invalid move: There is not enough board space!");
                    return false;
                }
                for (int distance = 1; distance <= amount; distance++) {
                    if (!Objects.isNull(board[y - distance][x - distance])) {
                        System.out.println("Invalid move: There is already a piece at this location!");
                        return false;
                    }
                }
                board[y][x] = null;
                board[y - amount][x - amount] = piece;
                piece.setI(y - amount);
                piece.setJ(x - amount);
                return true;
            }
            case DIAGONALUPRIGHT -> {
                if (y - amount < 0 || x + amount > 7) {
                    System.out.println("Invalid move: There is not enough board space!");
                    return false;
                }
                for (int distance = 1; distance <= amount; distance++) {
                    if (!Objects.isNull(board[y - distance][x + distance])) {
                        System.out.println("Invalid move: There is already a piece at this location!");
                        return false;
                    }
                }
                board[y][x] = null;
                board[y - amount][x + amount] = piece;
                piece.setI(y - amount);
                piece.setJ(x + amount);
                return true;
            }
            case DIAGONALDOWNLEFT -> {
                if (y + amount > 15 || x - amount < 0) {
                    System.out.println("Invalid move: There is not enough board space!");
                    return false;
                }
                for (int distance = 1; distance <= amount; distance++) {
                    if (!Objects.isNull(board[y + distance][x - distance])) {
                        System.out.println("Invalid move: There is already a piece at this location!");
                        return false;
                    }
                }
                board[y][x] = null;
                board[y + amount][x - amount] = piece;
                piece.setI(y + amount);
                piece.setJ(x - amount);
                return true;
            }
            case DIAGONALDOWNRIGHT -> {
                if (y + amount > 15 || x + amount > 7) {
                    System.out.println("Invalid move: There is not enough board space!");
                    return false;
                }
                for (int distance = 1; distance <= amount; distance++) {
                    if (!Objects.isNull(board[y + distance][x + distance])) {
                        System.out.println("Invalid move: There is already a piece at this location!");
                        return false;
                    }
                }
                board[y][x] = null;
                board[y + amount][x + amount] = piece;
                piece.setI(y + amount);
                piece.setJ(x + amount);
                return true;
            }
            default -> {
                return false;
            }
        }
    }

    public void incrementBlackCaptured() {
        this.numBlackCaptured++;
    }

    public void incrementWhiteCaptured() {
        this.numWhiteCaptured++;
    }

    /**
     * Check for valid capturing methods
     *
     * @param capturingPieces The pieces that are being used to capture
     * @param capturedPieceX  The x-coordinate of the captured piece
     * @param capturedPieceY  The y-coordinate of the captured piece
     * @param color           The color that should be used to capture with
     * @return {@code true} if the capture is valid and {@code false} otherwise
     */
    public boolean capture(ArrayList<Piece> capturingPieces, int capturedPieceX, int capturedPieceY, Piece.Color color) {
        if (capturingPieces.isEmpty()) {
            System.out.println("Invalid capture: You can't capture with nothing!");
            return false;
        }
        for (Piece piece : capturingPieces) {
            if (Objects.isNull(piece)) {
                System.out.println("Invalid capture: This piece does not exist!");
                return false;
            } else if (piece.getColor() != color) {
                System.out.println("Invalid capture: No cheating!");
                return false;
            }
        }
        Piece capturedPiece = this.getBoardPiece(capturedPieceX, capturedPieceY, true);
        if (Objects.isNull(capturedPiece)) {
            System.out.println("Invalid capture: This piece does not exist!");
        } else if (capturedPiece.getColor() == color) {
            System.out.println("Invalid capture: Are you trying to lose? You can't capture yourself!");
        }
        // Single Piece Captures:
        if (capturingPieces.size() == 1) {
            Piece capturingPiece = capturingPieces.get(0);
            if (!pathIsClear(capturedPiece, capturingPiece)) {
                System.out.println("Invalid capture: Path to target is not clear!");
                return false;
            }
            int encounterCheck = capturedPiece.checkEncounterCapture(capturingPiece);
            if (encounterCheck > -2) {
                this.removePiece(capturedPiece.getI(), capturedPiece.getJ(), encounterCheck);
                return true;
            }
            // Eruption
            int eruptionCheck = capturedPiece.checkEruptionCapture(capturingPiece);
            if (eruptionCheck > -2) {
                this.removePiece(capturedPiece.getI(), capturedPiece.getJ(), eruptionCheck);
                return true;
            }
        } else if (capturingPieces.size() == 2) {
            // Deceit
            int deceitCheck = capturedPiece.checkDeceitCapture(capturingPieces);
            if (deceitCheck > -2) {
                this.removePiece(capturedPiece.getI(), capturedPiece.getJ(), deceitCheck);
                return true;
            }
            // 2-Sided Siege
            int siegeCheck = capturedPiece.check2SidedSiegeCapture(capturingPieces);
            if (siegeCheck > -2) {
                this.removePiece(capturedPiece.getI(), capturedPiece.getJ(), siegeCheck);
                return true;
            }
        } else if (capturingPieces.size() == 3) {
            // 3-Sided Siege
            int siegeCheck = capturedPiece.check3SidedSiegeCapture(capturingPieces);
            if (siegeCheck > -2) {
                this.removePiece(capturedPiece.getI(), capturedPiece.getJ(), siegeCheck);
                return true;
            }
        } else if (capturingPieces.size() == 4) {
            // 4-Sided Siege
            ArrayList<Piece> found = capturingPieces;
            for (int i = capturedPiece.getI() - 1; i > 0 && i < 15 && i < capturedPiece.getI() + 2; i += 2) {
                for (int j = capturedPiece.getI() - 1; i > 0 && i < 15 && i < capturedPiece.getI() + 2; i += 2) {
                    for (int capPiece = 0; capPiece < capturingPieces.size(); capPiece++) {
                        if (capturingPieces.get(capPiece).equals(this.getBoardPiece(i, j, false))) {
                            found.remove(capPiece);
                            capPiece--;
                        }
                    }
                }
            }
            if (found.isEmpty()) {
                removePiece(capturedPiece.getI(), capturedPiece.getJ());
                return true;
            }
            found = capturingPieces;
            this.getBoardPiece(capturedPiece.getI(), capturedPiece.getJ(), false);
            for (int capPiece = 0; capPiece < capturingPieces.size(); capPiece++) {
                if (capturingPieces.get(capPiece).equals(this.getBoardPiece(capturedPiece.getI() - 1, capturedPiece.getJ(), false)) || capturingPieces.get(capPiece).equals(this.getBoardPiece(capturedPiece.getI() + 1, capturedPiece.getJ(), false)) || capturingPieces.get(capPiece).equals(this.getBoardPiece(capturedPiece.getI(), capturedPiece.getJ() - 1, false)) || capturingPieces.get(capPiece).equals(this.getBoardPiece(capturedPiece.getI(), capturedPiece.getJ() + 1, false))) {
                    found.remove(capPiece);
                    capPiece--;
                }
            }
            if (found.isEmpty()) {
                removePiece(capturedPiece.getI(), capturedPiece.getJ());
                return true;
            }
        } else {
            System.out.println("Invalid capture: Cannot capture with so many pieces!");
        }
        return false;
    }

    /**
     * Check whether the path between two pieces is clear on the board.
     *
     * @param startPiece The piece the path starts at
     * @param endPiece   The piece the path ends at
     * @return {@code true} if the path is clear and {@code false} if the path is not clear
     */
    private boolean pathIsClear(Piece startPiece, Piece endPiece) {
        int distanceI = endPiece.getI() - startPiece.getI();
        int distanceJ = endPiece.getJ() - startPiece.getJ();
        if (distanceJ == 0) {
            for (int i = (int) Math.signum(distanceI); Math.abs(i) <= distanceI; i++) {
                if (!Objects.isNull(this.getBoardPiece(startPiece.getI() + i, endPiece.getJ(), false))) {
                    return false;
                }
            }
        } else if (distanceI == 0) {
            for (int j = (int) Math.signum(distanceJ); Math.abs(j) <= distanceJ; j++) {
                if (!Objects.isNull(this.getBoardPiece(endPiece.getI(), startPiece.getJ() + j, false))) {
                    return false;
                }
            }
        } else {
            for (int i = (int) Math.signum(distanceI); Math.abs(i) <= distanceI; i++) {
                if (!Objects.isNull(this.getBoardPiece(startPiece.getJ() + i, startPiece.getJ() + i, false))) {
                    return false;
                }
            }
        }
        return true;
    }


    public boolean gameEnded() {
        if (this.numBlackCaptured >= winCon) {
            System.out.println("Black wins!");
            return true;
        } else if (this.numWhiteCaptured >= winCon) {
            System.out.println("White wins!");
            return true;
        }
        return false;
    }

    public String toString() {
        Perfecta whitePyramid = null;
        Perfecta blackPyramid = null;
        StringBuilder result = new StringBuilder("\n\n   ┌──────┬──────┬──────┬──────┬──────┬──────┬──────┬──────┐\n");
        for (int i = 0; i < 16; i++) {
            String repeat = " ".repeat(3 - String.valueOf(16 - i).length());
            result.append(16 - i).append(repeat);
            for (int j = 0; j < 8; j++) {
                if (Objects.isNull(board[i][j])) {
                    result.append("│      ");
                } else {
                    result.append("│").append(board[i][j]).append(" ".repeat((6 - board[i][j].toString().length())));
                    if (board[i][j].getShape() == Piece.Shape.PERFECTA) {
                        if (board[i][j].getColor() == Piece.Color.BLACK) {
                            blackPyramid = (Perfecta) board[i][j];
                        } else {
                            whitePyramid = (Perfecta) board[i][j];
                        }
                    }
                }
                if (j == 7) {
                    result.append("│");
                }
            }
            if (i == 15) {
                result.append(repeat).append("\n   └──────┴──────┴──────┴──────┴──────┴──────┴──────┴──────┘\n");
            } else if (i == 7) {
                result.append(repeat).append("\n   ╞══════╪══════╪══════╪══════╪══════╪══════╪══════╪══════╡\n");
            } else {
                result.append(repeat).append("\n   ├──────┼──────┼──────┼──────┼──────┼──────┼──────┼──────┤\n");
            }
        }
        result.append("       1      2      3      4      5      6      7      8\n");
        if (!Objects.isNull(whitePyramid)) {
            result.append("\nWhite Pyramid Pieces: ").append(whitePyramid.getPieces());
        }
        if (!Objects.isNull(blackPyramid)) {
            result.append("\nBlack Pyramid Pieces: ").append(blackPyramid.getPieces());
        }
        return result.toString();
    }

    /**
     * Removes the specified piece from the board.
     *
     * @param i                   The i index of the piece to be removed
     * @param j                   The j index of the piece to be removed
     * @param perfectaPieceNumber The number of the perfecta piece to be removed if this piece is a {@code Perfecta}
     */
    private void removePiece(int i, int j, int perfectaPieceNumber) {
        if (this.getBoardPiece(i, j, false).getShape() == Piece.Shape.PERFECTA && perfectaPieceNumber > -1) {
            System.out.println("Would you like to pay ransom for your Perfecta?");
            Scanner scan = new Scanner(System.in);
            String ans = scan.next();
            if (ans.contains("y")) {
                while (true) {
                    System.out.println("What piece would you like to ransom?");
                    int pieceX = scan.nextInt();
                    int pieceY = scan.nextInt();
                    int ransomJ = pieceX - 1;
                    int ransomI = 16 - pieceY;
                    if (ransomI < 0 || ransomI > 15 || ransomJ < 0 || ransomJ > 7 || Objects.isNull(this.getBoardPiece(ransomI, ransomJ, false))) {
                        System.out.println("Invalid ransom: Please enter a valid piece!");
                    } else {
                        this.board[ransomI][ransomJ] = null;
                        return;
                    }
                }
            } else {
                ((Perfecta) this.getBoardPiece(i, j, false)).capture(perfectaPieceNumber);
            }
        } else {
            this.board[i][j] = null;
        }
    }

    /**
     * Removes the specified piece from the board
     *
     * @param i The i index of the piece
     * @param j The j index of the piece
     */
    private void removePiece(int i, int j) {
        if (this.board[i][j].getShape() == Piece.Shape.PERFECTA) {
            System.out.println("Would you like to pay ransom for your Perfecta?");
            Scanner scan = new Scanner(System.in);
            String ans = scan.next();
            if (ans.contains("y")) {
                while (true) {
                    System.out.println("What piece would you like to ransom?");
                    int pieceX = scan.nextInt();
                    int pieceY = scan.nextInt();
                    int ransomJ = pieceX - 1;
                    int ransomI = 16 - pieceY;
                    if (ransomI < 0 || ransomI > 15 || ransomJ < 0 || ransomJ > 7 || Objects.isNull(this.getBoardPiece(ransomI, ransomJ, false))) {
                        System.out.println("Invalid ransom: Please enter a valid piece!");
                    } else {
                        this.board[ransomI][ransomJ] = null;
                        return;
                    }
                }

            }
        }
        this.board[i][j] = null;
    }

    /**
     * Get a piece from the board given the coordinates of its position
     *
     * @param pieceX  The x-coordinate of the piece
     * @param pieceY  The y-coordinate of the piece
     * @param convert Whether to convert the coordinates from human readable coordinates
     * @return The {@code Piece} at the specified position
     */
    public Piece getBoardPiece(int pieceX, int pieceY, boolean convert) {
        if (convert) {
            int x = pieceX - 1;
            int y = 16 - pieceY;

            if (y < 0 || y > 15) {
                System.out.println("Invalid move: Y coordinate is out of bounds!");
                return null;
            } else if (x < 0 || x > 7) {
                System.out.println("Invalid move: X coordinate is out of bounds!");
                return null;
            } else if (Objects.isNull(board[y][x])) {
                System.out.println("Invalid move: This piece does not exist!");
                return null;
            }
            return board[y][x];
        } else {
            if (pieceX < 0 || pieceX > 15) {
                System.out.println("Invalid move: Y coordinate is out of bounds!");
                return null;
            } else if (pieceY < 0 || pieceY > 7) {
                System.out.println("Invalid move: X coordinate is out of bounds!");
                return null;
            }
            return board[pieceX][pieceY];
        }
    }

}
