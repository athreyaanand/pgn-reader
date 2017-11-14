/**
 * Represents a Rook that extends Piece
 *
 * @author aanand76
 * @version 1.0
 */
public class Rook extends Piece {

    /**
     * arg constructor for rook
     * @param  c represents color
     */
    public Rook(Color c) {
        super(c);
    }

    /**
     * gets the algebraic name
     * @return returns the algebraic name
     */
    public String algebraicName() {
        return "R";
    }

    /**
     * gets the FEN name
     * @return returns the FEN name
     */
    public String fenName() {
        return getColor() == Color.WHITE ? "R" : "r";
    }

    /**
     * a method that returns squares that could move from
     * @param  square show square that piece is situated on
     * @return an array containing possible squarea
     */
    public Square[] movesFrom(Square square) {
        Square[] sq = new Square[27];
        int counter = 0;
        char rank = square.getRank();
        char file = square.getFile();
        for (int i = 1; i <= 8; i++) {
            char[] ranks = new char[]{(char) (rank + i), (char) (rank - i)};
            char[] files = new char[]{(char) (file + i), (char) (file - i)};
            if (isInBoard(files[0], rank)) {
                sq[counter++] = new Square(files[0], rank);
            }
            if (isInBoard(files[1], rank)) {
                sq[counter++] = new Square(files[1], rank);
            }
            if (isInBoard(file, ranks[0])) {
                sq[counter++] = new Square(file, ranks[0]);
            }
            if (isInBoard(file, ranks[1])) {
                sq[counter++] = new Square(file, ranks[1]);
            }
        }

        Square[] full = new Square[counter];
        for (int i = 0; i < counter; i++) {
            full[i] = sq[i];
        }

        return full;
    }
}
