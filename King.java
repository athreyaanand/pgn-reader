/**
 * Represents a King that extends Piece
 *
 * @author aanand76
 * @version 1.0
 */
public class King extends Piece {

  /**
   * arg constructor for king
   * @param  c represents color
   */
    public King(Color c) {
        super(c);
    }

    /**
     * gets the algebraic name
     * @return returns the algebraic name
     */
    public String algebraicName() {
        return "K";
    }

    /**
     * gets the FEN name
     * @return returns the FEN name
     */
    public String fenName() {
        return getColor() == Color.WHITE ? "K" : "k";
    }

    /**
     * a method that returns squares that could move from
     * @param  square show square that piece is situated on
     * @return an array containing possible squarea
     */
    public Square[] movesFrom(Square square) {
        Square[] sq = new Square[8];
        int counter = 0;
        char rank = square.getRank();
        char file = square.getFile();
        for (int r = -1; r <= 1; r++) {
            for (int c = -1; c <= 1; c++) {
                if (r == 0 && c == 0) {
                    continue;
                }
                if (isInBoard((char) (file + c), (char) (rank + r))) {
                    sq[counter++] = new Square((char) (file + c),
                                               (char) (rank + r));
                }
            }
        }

        Square[] full = new Square[counter];
        for (int i = 0; i < counter; i++) {
            full[i] = sq[i];
        }

        return full;
    }
}
