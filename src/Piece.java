import java.util.ArrayList;

/**
 * Represents a {@code Board} piece.
 * Holds a number, a {@code Shape}, and a {@code Color}
 *
 * @author Yann Quinard
 */
public class Piece {
    private final Shape shape;
    private final Color color;
    private final String symbol;
    private int number;
    private int i;
    private int j;
    private int movement;

    /**
     * Creates a new piece.
     *
     * @param number The value of the piece
     * @param shape  The {@code Shape} of the piece
     * @param color  The color of the piece
     * @param i      The i index of the piece
     * @param j      The j index of the piece
     */
    public Piece(int number, Shape shape, Color color, int i, int j) {
        this.number = number;
        this.shape = shape;
        this.color = color;
        this.i = i;
        this.j = j;
        switch (shape) {
            case CIRCLE -> {
                this.movement = 1;
                this.symbol = this.color.equals(Color.WHITE) ? "●" + this.number : "○" + this.number;
            }
            case TRIANGLE -> {
                this.movement = 2;
                this.symbol = this.color.equals(Color.WHITE) ? "▲" + this.number : "△" + this.number;
            }
            case SQUARE -> {
                this.movement = 3;
                this.symbol = this.color.equals(Color.WHITE) ? "■" + this.number : "□" + this.number;
            }
            case PERFECTA -> {
                this.movement = 3;
                this.symbol = switch (this.color) {
                    case BLACK -> "BP";
                    case WHITE -> "WP";
                };
            }
            default -> this.symbol = "";
        }
    }

    /**
     * Check whether {@code this} piece can be captured by Encounter.
     *
     * @param capturingPiece The piece to capture {@code this} piece with
     * @return -2 if the capture is invalid, -1 or the index of the perfecta piece to be captured if the capture was successful
     */
    public int checkEncounterCapture(Piece capturingPiece) {
        if (capturingPiece.getShape() == Shape.PERFECTA && this.getShape() == Shape.PERFECTA) {
            ArrayList<Piece> capturingPiecePerfectaPieces = ((Perfecta) capturingPiece).getPieces();
            ArrayList<Piece> thisPerfectaPieces = ((Perfecta) this).getPieces();
            for (Piece capturingPiecePerfectaPiece : capturingPiecePerfectaPieces) {
                for (int thisPerfectaPieceIndex = 0; thisPerfectaPieceIndex < thisPerfectaPieces.size(); thisPerfectaPieceIndex++) {
                    if (capturingPiecePerfectaPiece.getNumber() == thisPerfectaPieces.get(thisPerfectaPieceIndex).getNumber()) {
                        return thisPerfectaPieceIndex;
                    }
                }
            }
        } else if (capturingPiece.getShape() == Shape.PERFECTA) {
            ArrayList<Piece> capturingPiecePerfectaPieces = ((Perfecta) capturingPiece).getPieces();
            for (Piece capturingPiecePerfectaPiece : capturingPiecePerfectaPieces) {
                if (capturingPiecePerfectaPiece.getNumber() == this.getNumber()) {
                    return -1;
                }
            }
        } else if (this.getShape() == Shape.PERFECTA) {
            ArrayList<Piece> thisPerfectaPieces = ((Perfecta) this).getPieces();
            for (int thisPerfectaPieceIndex = 0; thisPerfectaPieceIndex < thisPerfectaPieces.size(); thisPerfectaPieceIndex++) {
                if (thisPerfectaPieces.get(thisPerfectaPieceIndex).getNumber() == capturingPiece.getNumber()) {
                    return thisPerfectaPieceIndex;
                }
            }
        } else {
            if (this.getNumber() == capturingPiece.getNumber()) {
                return -1;
            }
        }
        return -2;
    }

    /**
     * Check whether {@code this} piece can be captured by Eruption.
     *
     * @param capturingPiece The piece to capture {@code this} piece with
     * @return -2 if the capture is invalid, -1 or the index of the perfecta piece to be captured if the capture was successful
     */
    public int checkEruptionCapture(Piece capturingPiece) {
        int spacesI = Math.abs(capturingPiece.getI() - this.getI()) + 1;
        int spacesJ = Math.abs(capturingPiece.getJ() - this.getJ()) + 1;
        if (capturingPiece.getShape() == Shape.PERFECTA && this.getShape() == Shape.PERFECTA) {
            ArrayList<Piece> capturingPiecePerfectaPieces = ((Perfecta) capturingPiece).getPieces();
            ArrayList<Piece> thisPerfectaPieces = ((Perfecta) this).getPieces();
            for (Piece capturingPiecePerfectaPiece : capturingPiecePerfectaPieces) {
                for (int thisPerfectaPieceIndex = 0; thisPerfectaPieceIndex < thisPerfectaPieces.size(); thisPerfectaPieceIndex++) {
                    int capPiece = capturingPiecePerfectaPiece.getNumber();
                    int thisPiece = thisPerfectaPieces.get(thisPerfectaPieceIndex).getNumber();
                    if (capPiece * spacesI == thisPiece || capPiece * spacesJ == thisPiece || (double) capPiece / spacesI == thisPiece || (double) spacesI / capPiece == thisPiece || (double) capPiece / spacesJ == thisPiece || (double) spacesJ / capPiece == thisPiece) {
                        return thisPerfectaPieceIndex;
                    }
                }
            }
        } else if (capturingPiece.getShape() == Shape.PERFECTA) {
            ArrayList<Piece> capturingPiecePerfectaPieces = ((Perfecta) capturingPiece).getPieces();
            for (Piece capturingPiecePerfectaPiece : capturingPiecePerfectaPieces) {
                int capPiece = capturingPiecePerfectaPiece.getNumber();
                int thisPiece = this.getNumber();
                if (capPiece * spacesI == thisPiece || capPiece * spacesJ == thisPiece || (double) capPiece / spacesI == thisPiece || (double) spacesI / capPiece == thisPiece || (double) capPiece / spacesJ == thisPiece || (double) spacesJ / capPiece == thisPiece) {
                    return -1;
                }
            }
        } else if (this.getShape() == Shape.PERFECTA) {
            ArrayList<Piece> thisPerfectaPieces = ((Perfecta) this).getPieces();
            for (int thisPerfectaPieceIndex = 0; thisPerfectaPieceIndex < thisPerfectaPieces.size(); thisPerfectaPieceIndex++) {
                int capPiece = capturingPiece.getNumber();
                int thisPiece = thisPerfectaPieces.get(thisPerfectaPieceIndex).getNumber();
                if (capPiece * spacesI == thisPiece || capPiece * spacesJ == thisPiece || (double) capPiece / spacesI == thisPiece || (double) spacesI / capPiece == thisPiece || (double) capPiece / spacesJ == thisPiece || (double) spacesJ / capPiece == thisPiece) {
                    return thisPerfectaPieceIndex;
                }
            }
        } else {
            int capPiece = capturingPiece.getNumber();
            int thisPiece = this.getNumber();
            if (capPiece * spacesI == thisPiece || capPiece * spacesJ == thisPiece || (double) capPiece / spacesI == thisPiece || (double) spacesI / capPiece == thisPiece || (double) capPiece / spacesJ == thisPiece || (double) spacesJ / capPiece == thisPiece) {
                return -1;
            }
        }
        return -2;
    }

    /**
     * Checks whether {@code this} piece can be captured by Deceit.
     *
     * @param capturingPieces The pieces to capture {@code this} piece with
     * @return -2 if the capture is invalid, -1 or the index of the perfecta piece to be captured if the capture was successful
     */
    public int checkDeceitCapture(ArrayList<Piece> capturingPieces) {
        Piece capPiece1 = capturingPieces.get(0);
        Piece capPiece2 = capturingPieces.get(1);
        int distanceI1 = Math.abs(capPiece1.getI() - this.getI());
        int distanceJ1 = Math.abs(capPiece1.getJ() - this.getJ());
        int distanceI2 = Math.abs(capPiece2.getI() - this.getI());
        int distanceJ2 = Math.abs(capPiece2.getJ() - this.getJ());
        boolean adjacentCheck = (distanceI1 == 1 && distanceI2 == 1 && distanceJ1 == 0 && distanceJ2 == 0) || (distanceI1 == 1 && distanceI2 == 1 && distanceJ1 == 1 && distanceJ2 == 1) || (distanceI1 == 0 && distanceI2 == 0 && distanceJ1 == 1 && distanceJ2 == 1);
        if (capPiece1.getShape() == Shape.PERFECTA && capPiece2.getShape() == Shape.PERFECTA && this.getShape() == Shape.PERFECTA) {
            for (Piece capPiece1PerfectaPiece : ((Perfecta) capPiece1).getPieces()) {
                for (Piece capPiece2PerfectaPiece : ((Perfecta) capPiece2).getPieces()) {
                    ArrayList<Piece> thisPerfectaPieces = ((Perfecta) this).getPieces();
                    for (int thisPerfectaPieceIndex = 0; thisPerfectaPieceIndex < thisPerfectaPieces.size(); thisPerfectaPieceIndex++) {
                        if (capPiece1PerfectaPiece.getNumber() + capPiece2PerfectaPiece.getNumber() == thisPerfectaPieces.get(thisPerfectaPieceIndex).getNumber() && adjacentCheck) {
                            return thisPerfectaPieceIndex;
                        }
                    }
                }
            }
        } else if (capPiece1.getShape() == Shape.PERFECTA && capPiece2.getShape() == Shape.PERFECTA) {
            for (Piece capPiece1PerfectaPiece : ((Perfecta) capPiece1).getPieces()) {
                for (Piece capPiece2PerfectaPiece : ((Perfecta) capPiece2).getPieces()) {
                    if (capPiece1PerfectaPiece.getNumber() + capPiece2PerfectaPiece.getNumber() == this.getNumber() && adjacentCheck) {
                        return -1;
                    }
                }
            }
        } else if (capPiece1.getShape() == Shape.PERFECTA && this.getShape() == Shape.PERFECTA) {
            for (Piece capPiece1PerfectaPiece : ((Perfecta) capPiece1).getPieces()) {
                ArrayList<Piece> thisPerfectaPieces = ((Perfecta) this).getPieces();
                for (int thisPerfectaPieceIndex = 0; thisPerfectaPieceIndex < thisPerfectaPieces.size(); thisPerfectaPieceIndex++) {
                    if (capPiece1PerfectaPiece.getNumber() + capPiece2.getNumber() == thisPerfectaPieces.get(thisPerfectaPieceIndex).getNumber() && adjacentCheck) {
                        return thisPerfectaPieceIndex;
                    }
                }
            }
        } else if (capPiece2.getShape() == Shape.PERFECTA && this.getShape() == Shape.PERFECTA) {
            for (Piece capPiece2PerfectaPiece : ((Perfecta) capPiece2).getPieces()) {
                ArrayList<Piece> thisPerfectaPieces = ((Perfecta) this).getPieces();
                for (int thisPerfectaPieceIndex = 0; thisPerfectaPieceIndex < thisPerfectaPieces.size(); thisPerfectaPieceIndex++) {
                    if (capPiece1.getNumber() + capPiece2PerfectaPiece.getNumber() == thisPerfectaPieces.get(thisPerfectaPieceIndex).getNumber() && adjacentCheck) {
                        return thisPerfectaPieceIndex;
                    }
                }
            }
        } else if (capPiece1.getShape() == Shape.PERFECTA) {
            for (Piece capPiece1PerfectaPiece : ((Perfecta) capPiece1).getPieces()) {
                if (capPiece1PerfectaPiece.getNumber() + capPiece2.getNumber() == this.getNumber() && adjacentCheck) {
                    return -1;
                }
            }
        } else if (capPiece2.getShape() == Shape.PERFECTA) {
            for (Piece capPiece2PerfectaPiece : ((Perfecta) capPiece2).getPieces()) {
                if (capPiece1.getNumber() + capPiece2PerfectaPiece.getNumber() == this.getNumber() && adjacentCheck) {
                    return -1;
                }
            }
        } else if (this.getShape() == Shape.PERFECTA) {
            ArrayList<Piece> thisPerfectaPieces = ((Perfecta) this).getPieces();
            for (int thisPerfectaPieceIndex = 0; thisPerfectaPieceIndex < thisPerfectaPieces.size(); thisPerfectaPieceIndex++) {
                if (capPiece1.getNumber() + capPiece1.getNumber() == thisPerfectaPieces.get(thisPerfectaPieceIndex).getNumber() && (distanceI1 == 1 && distanceI2 == 1 && distanceJ1 == 0 && distanceJ2 == 0) || (distanceI1 == 1 && distanceI2 == 1 && distanceJ1 == 1 && distanceJ2 == 1) || (distanceI1 == 0 && distanceI2 == 0 && distanceJ1 == 1 && distanceJ2 == 1)) {
                    return thisPerfectaPieceIndex;
                }
            }
        } else {
            if (((capPiece1.getNumber() + capPiece2.getNumber()) == this.getNumber()) && adjacentCheck) {
                return -1;
            }
        }
        return -2;
    }

    /**
     * Check whether {@code this} piece can be captured by 2-Sided Siege.
     *
     * @param capturingPieces The pieces to capture {@code this} piece with
     * @return -2 if the capture is invalid, -1 or the index of the perfecta piece to be captured if the capture was successful
     */
    public int check2SidedSiegeCapture(ArrayList<Piece> capturingPieces) {
        Piece capPiece1 = capturingPieces.get(0);
        Piece capPiece2 = capturingPieces.get(1);
        int distanceI1 = Math.abs(capPiece1.getI() - this.getI());
        int distanceJ1 = Math.abs(capPiece1.getJ() - this.getJ());
        int distanceI2 = Math.abs(capPiece2.getI() - this.getI());
        int distanceJ2 = Math.abs(capPiece2.getJ() - this.getJ());
        if (this.getI() == 0 && this.getJ() == 0 || this.getI() == 0 && this.getJ() == 7 || this.getI() == 15 && this.getJ() == 0 || this.getI() == 15 && this.getJ() == 7) {
            if (distanceI1 == 0 && distanceJ1 == 1 && distanceJ2 == 0 && distanceI2 == 1 || distanceJ1 == 0 && distanceI1 == 1 && distanceI2 == 0 && distanceJ2 == 1) {
                return -1;
            }
        }
        return -2;
    }

    /**
     * Check whether {@code this} piece can be captured by 3-Sided Siege.
     *
     * @param capturingPieces The pieces to capture {@code this} piece with
     * @return -2 if the capture is invalid, -1 or the index of the perfecta piece to be captured if the capture was successful
     */
    public int check3SidedSiegeCapture(ArrayList<Piece> capturingPieces) {
        Piece capPiece1 = capturingPieces.get(0);
        Piece capPiece2 = capturingPieces.get(1);
        Piece capPiece3 = capturingPieces.get(2);
        int distanceI1 = Math.abs(capPiece1.getI() - this.getI());
        int distanceJ1 = Math.abs(capPiece1.getJ() - this.getJ());
        int distanceJ2 = Math.abs(capPiece2.getJ() - this.getJ());
        int distanceI2 = Math.abs(capPiece2.getI() - this.getI());
        int distanceJ3 = Math.abs(capPiece3.getJ() - this.getJ());
        int distanceI3 = Math.abs(capPiece3.getI() - this.getI());
        if ((this.getI() == 0 || this.getI() == 15) && this.getJ() != 0 && this.getJ() != 7 || (this.getJ() == 0 || this.getJ() == 7) && this.getI() != 0 && this.getI() != 15) {
            if (distanceI1 + distanceJ1 + distanceI2 + distanceJ2 + distanceI3 + distanceJ3 <= 6 && distanceI1 == 1 ^ distanceJ1 == 1 && distanceI2 == 1 ^ distanceJ2 == 1 && distanceI3 == 1 ^ distanceJ3 == 1) {
                return -1;
            }
        }
        return -2;
    }

    public int getNumber() {
        return number;
    }

    protected void setNumber(int number) {
        this.number = number;
    }

    public Shape getShape() {
        return shape;
    }

    public Color getColor() {
        return color;
    }

    public int getMovement() {
        return movement;
    }

    public String toString() {
        return symbol;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    /**
     * Check whether {@code this} piece can move by {@code amount}.
     *
     * @param amount The amount {@code this} piece is trying to be moved by
     * @return {@code true} if the piece can be moved by {@code amount} or false otherwise
     */
    public boolean validMovement(int amount) {
        return movement == amount;
    }

    /**
     * Represents the Shapes that a piece can have.
     */
    public enum Shape {
        CIRCLE, SQUARE, TRIANGLE, PERFECTA
    }

    /**
     * Represents the Color that a piece is.
     */
    enum Color {
        WHITE, BLACK,
    }

}
