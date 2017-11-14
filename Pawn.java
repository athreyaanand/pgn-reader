/**
 * Represents a Pawn that extends Piece
 *
 * @author aanand76
 * @version 1.0
 */
public class Pawn extends Piece {

    /**
     * arg constructor for pawn
     * @param  c represents color
     */
    public Pawn(Color c) {
        super(c);
    }

    /**
     * gets the algebraic name
     * @return returns the algebraic name
     */
    public String algebraicName() {
        return "";
    }

    /**
     * gets the FEN name
     * @return returns the FEN name
     */
    public String fenName() {
        return getColor() == Color.WHITE ? "P" : "p";
    }

    /**
     * a method that returns squares that could move from
     * @param  square show square that piece is situated on
     * @return an array containing possible squarea
     */
    public Square[] movesFrom(Square square) {
        char rank = square.getRank();
        char file = square.getFile();
        if (getColor() == Color.WHITE) {
            if (rank == '8') {
                return new Square[0];
            } else if (rank == '2') {
                return new Square[]{new Square(file, '4'),
                                    new Square(file, '3')};
            } else {
                return new Square[]{new Square(file, (char) (rank + 1))};
            }
        } else {
            if (rank == '1') {
                return new Square[0];
            } else if (rank == '7') {
                return new Square[]{new Square(file, '5'),
                                    new Square(file, '6')};
            } else {
                return new Square[]{new Square(file, (char) (rank - 1))};
            }
        }
    }
}
