import java.util.ArrayList;

public class GameTester {
    public static void main(String[] args) {
        testEncounterCapture();
        testPerfectaEncounterCapture();
        testEruptionCapture();
        testDeceitCaptureHorizontally();
        testDeceitCaptureVertically();
        testDeceitCaptureDiagonally();
        test2SidedSiegeCapture();
        test3SidedSiegeCapture();
        test4SidedSiegeCapture();
        testFailedCapture();
    }

    public static void testEncounterCapture() {
        System.out.println("Testing Encounter Countering");
        Piece[][] pieces = new Piece[16][8];
        // Black Piece:
        pieces[10][5] = new Piece(9, Piece.Shape.CIRCLE, Piece.Color.BLACK, 10, 5);

        // White Piece:
        pieces[11][5] = new Piece(9, Piece.Shape.TRIANGLE, Piece.Color.WHITE, 11, 5);

        Board board = new Board(1, pieces);
        System.out.println(board);
        ArrayList<Piece> capturingPieces = new ArrayList<>();
        capturingPieces.add(board.getBoardPiece(6, 6, true));
        System.out.println("Capturing " + board.getBoardPiece(6, 5, true) + " with " + capturingPieces);
        board.capture(capturingPieces, 6, 5, Piece.Color.BLACK);
        System.out.println(board);
    }

    public static void testPerfectaEncounterCapture() {
        System.out.println("Testing Perfecta Capturing by Encounter");
        Piece[][] pieces = new Piece[16][8];
        // Black Piece:
        pieces[10][5] = new Piece(25, Piece.Shape.CIRCLE, Piece.Color.BLACK, 10, 5);

        // White Piece:
        pieces[11][5] = new Perfecta(Piece.Color.WHITE, 11, 5);

        Board board = new Board(1, pieces);
        System.out.println(board);
        ArrayList<Piece> capturingPieces = new ArrayList<>();
        capturingPieces.add(board.getBoardPiece(6, 6, true));
        System.out.println("Capturing " + board.getBoardPiece(6, 5, true) + " with " + capturingPieces);
        board.capture(capturingPieces, 6, 5, Piece.Color.BLACK);
        System.out.println(board);
    }

    public static void testEruptionCapture() {
        System.out.println("Testing Eruption Capturing");
        Piece[][] pieces = new Piece[16][8];
        // Black Pieces:
        pieces[11][3] = new Piece(3, Piece.Shape.CIRCLE, Piece.Color.BLACK, 11, 3);

        // White Piece:
        pieces[12][3] = new Piece(6, Piece.Shape.TRIANGLE, Piece.Color.WHITE, 12, 3);
        Board board = new Board(1, pieces);
        System.out.println(board);
        ArrayList<Piece> capturingPieces = new ArrayList<>();
        capturingPieces.add(board.getBoardPiece(11, 3, false));
        System.out.println("Capturing " + board.getBoardPiece(4, 4, true) + " with " + capturingPieces);
        board.capture(capturingPieces, 4, 4, Piece.Color.BLACK);
        System.out.println(board);
    }

    public static void testFailedCapture() {
        System.out.println("Testing Invalid Capture");
        Piece[][] pieces = new Piece[16][8];
        // Black Pieces:
        pieces[10][3] = new Piece(3, Piece.Shape.CIRCLE, Piece.Color.BLACK, 11, 3);

        // White Piece:
        pieces[11][3] = new Piece(6, Piece.Shape.TRIANGLE, Piece.Color.WHITE, 12, 3);
        pieces[12][3] = new Piece(9, Piece.Shape.TRIANGLE, Piece.Color.WHITE, 12, 3);
        Board board = new Board(1, pieces);
        System.out.println(board);
        ArrayList<Piece> capturingPieces = new ArrayList<>();
        capturingPieces.add(board.getBoardPiece(10, 3, false));
        System.out.println("Capturing " + board.getBoardPiece(4, 4, true) + " with " + capturingPieces);
        board.capture(capturingPieces, 4, 4, Piece.Color.BLACK);
        System.out.println(board);
    }

    public static void testDeceitCaptureHorizontally() {
        System.out.println("Testing Deceit Capturing Horizontally");
        Piece[][] pieces = new Piece[16][8];
        // Black Pieces:
        pieces[0][1] = new Piece(3, Piece.Shape.CIRCLE, Piece.Color.BLACK, 0, 1);

        // White Piece:
        pieces[0][0] = new Piece(1, Piece.Shape.TRIANGLE, Piece.Color.WHITE, 0, 0);
        pieces[0][2] = new Piece(2, Piece.Shape.SQUARE, Piece.Color.WHITE, 0, 2);


        Board board = new Board(1, pieces);
        System.out.println(board);
        ArrayList<Piece> capturingPieces = new ArrayList<>();
        capturingPieces.add(board.getBoardPiece(0, 0, false));
        capturingPieces.add(board.getBoardPiece(0, 2, false));
        System.out.println("Capturing " + board.getBoardPiece(2, 16, true) + " with " + capturingPieces);
        board.capture(capturingPieces, 2, 16, Piece.Color.WHITE);
        System.out.println(board);
    }

    public static void testDeceitCaptureVertically() {
        System.out.println("Testing Deceit Capturing Vertically");
        Piece[][] pieces = new Piece[16][8];
        // Black Pieces:
        pieces[1][0] = new Piece(3, Piece.Shape.CIRCLE, Piece.Color.BLACK, 1, 0);

        // White Piece:
        pieces[0][0] = new Piece(1, Piece.Shape.TRIANGLE, Piece.Color.WHITE, 0, 0);
        pieces[2][0] = new Piece(2, Piece.Shape.SQUARE, Piece.Color.WHITE, 2, 0);


        Board board = new Board(1, pieces);
        System.out.println(board);
        ArrayList<Piece> capturingPieces = new ArrayList<>();
        capturingPieces.add(board.getBoardPiece(0, 0, false));
        capturingPieces.add(board.getBoardPiece(2, 0, false));
        System.out.println("Capturing " + board.getBoardPiece(1, 15, true) + " with " + capturingPieces);
        board.capture(capturingPieces, 1, 15, Piece.Color.WHITE);
        System.out.println(board);
    }

    public static void testDeceitCaptureDiagonally() {
        System.out.println("Testing Deceit Capturing Diagonally");
        Piece[][] pieces = new Piece[16][8];
        // Black Pieces:
        pieces[1][1] = new Piece(3, Piece.Shape.CIRCLE, Piece.Color.BLACK, 1, 1);

        // White Piece:
        pieces[0][0] = new Piece(1, Piece.Shape.TRIANGLE, Piece.Color.WHITE, 0, 0);
        pieces[2][2] = new Piece(2, Piece.Shape.SQUARE, Piece.Color.WHITE, 2, 2);


        Board board = new Board(1, pieces);
        System.out.println(board);
        ArrayList<Piece> capturingPieces = new ArrayList<>();
        capturingPieces.add(board.getBoardPiece(0, 0, false));
        capturingPieces.add(board.getBoardPiece(2, 2, false));
        System.out.println("Capturing " + board.getBoardPiece(2, 15, true) + " with " + capturingPieces);
        board.capture(capturingPieces, 2, 15, Piece.Color.WHITE);
        System.out.println(board);
    }

    public static void test2SidedSiegeCapture() {
        System.out.println("Testing 2-Sided Siege Capturing");
        Piece[][] pieces = new Piece[16][8];
        // Black Pieces:
        pieces[0][0] = new Piece(3, Piece.Shape.CIRCLE, Piece.Color.WHITE, 0, 0);

        // White Piece:
        pieces[0][1] = new Piece(1, Piece.Shape.TRIANGLE, Piece.Color.BLACK, 0, 1);
        pieces[1][0] = new Piece(2, Piece.Shape.SQUARE, Piece.Color.BLACK, 1, 0);


        Board board = new Board(1, pieces);
        System.out.println(board);
        ArrayList<Piece> capturingPieces = new ArrayList<>();
        capturingPieces.add(board.getBoardPiece(0, 1, false));
        capturingPieces.add(board.getBoardPiece(1, 0, false));
        System.out.println("Capturing " + board.getBoardPiece(1, 16, true) + " with " + capturingPieces);
        board.capture(capturingPieces, 1, 16, Piece.Color.BLACK);
        System.out.println(board);
    }

    public static void test3SidedSiegeCapture() {
        System.out.println("Testing 3-Sided Siege Capturing");
        Piece[][] pieces = new Piece[16][8];
        // Black Pieces:
        pieces[0][1] = new Piece(3, Piece.Shape.CIRCLE, Piece.Color.WHITE, 0, 1);

        // White Piece:
        pieces[0][0] = new Piece(1, Piece.Shape.TRIANGLE, Piece.Color.BLACK, 0, 0);
        pieces[0][2] = new Piece(2, Piece.Shape.SQUARE, Piece.Color.BLACK, 0, 2);
        pieces[1][1] = new Piece(2, Piece.Shape.CIRCLE, Piece.Color.BLACK, 1, 1);


        Board board = new Board(1, pieces);
        System.out.println(board);
        ArrayList<Piece> capturingPieces = new ArrayList<>();
        capturingPieces.add(board.getBoardPiece(0, 0, false));
        capturingPieces.add(board.getBoardPiece(0, 2, false));
        capturingPieces.add(board.getBoardPiece(1, 1, false));
        System.out.println("Capturing " + board.getBoardPiece(2, 16, true) + " with " + capturingPieces);
        board.capture(capturingPieces, 2, 16, Piece.Color.BLACK);
        System.out.println(board);
    }

    public static void test4SidedSiegeCapture() {
        System.out.println("Testing 4-Sided Siege Capturing");
        Piece[][] pieces = new Piece[16][8];
        // Black Pieces:
        pieces[3][3] = new Piece(3, Piece.Shape.CIRCLE, Piece.Color.WHITE, 3, 3);

        // White Piece:
        pieces[3][2] = new Piece(1, Piece.Shape.TRIANGLE, Piece.Color.BLACK, 3, 2);
        pieces[3][4] = new Piece(2, Piece.Shape.SQUARE, Piece.Color.BLACK, 3, 4);
        pieces[2][3] = new Piece(2, Piece.Shape.CIRCLE, Piece.Color.BLACK, 2, 3);
        pieces[4][3] = new Piece(2, Piece.Shape.CIRCLE, Piece.Color.BLACK, 4, 3);

        Board board = new Board(1, pieces);
        System.out.println(board);
        ArrayList<Piece> capturingPieces = new ArrayList<>();
        capturingPieces.add(board.getBoardPiece(3, 2, false));
        capturingPieces.add(board.getBoardPiece(3, 4, false));
        capturingPieces.add(board.getBoardPiece(2, 3, false));
        capturingPieces.add(board.getBoardPiece(4, 3, false));
        System.out.println("Capturing " + board.getBoardPiece(4, 13, true) + " with " + capturingPieces);
        board.capture(capturingPieces, 4, 13, Piece.Color.BLACK);
        System.out.println(board);
    }
}
