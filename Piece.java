/**
 * Represents a parent class for pieces
 *
 * @author aanand76
 * @version 1.0
 */
public abstract class Piece {
    private Color color;

    /**
     * arg constructor for piece
     * @param  c represents color
     */
    public Piece(Color c) {
        color = c;
    }

    /**
     * gets the color of the piece
     * @return a color enum
     */
    public Color getColor() {
        return color;
    }

    /**
     * [isInBoard description]
     * @param  file of piece
     * @param  rank of piece
     * @return a boolean if the piece is on the board
     */
    public boolean isInBoard(char file, char rank) {
        return file >= 'a' && file <= 'h' && rank >= '1' && rank <= '8';
    }

    /**
     * gets the algebraic name
     * @return returns the algebraic name
     */
    public abstract String algebraicName();

    /**
     * gets the FEN name
     * @return returns the FEN name
     */
    public abstract String fenName();

    /**
     * a method that returns squares that could move from
     * @param  square show square that piece is situated on
     * @return an array containing possible squarea
     */
    public abstract Square[] movesFrom(Square square);

    /**
     * converts the piece to a string value
     * @return a string of the piece (color+class)
     */
    public String toString() {
        return color.toString() + " " + this.getClass();
    }
}
