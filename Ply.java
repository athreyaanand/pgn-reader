import java.util.Optional;

/**
 * Represents a Ply class used by Move
 *
 * @author aanand76
 * @version 1.0
 */
public class Ply {
    private Piece piece;
    private Square from, to;
    private Optional<String> comment;

    /**
     * Argument constructor for Ply
     * @param piece on Square
     * @param from is initial Square
     * @param to is intended Square
     * @param comment is an optional that may contain a comment
     */
    public Ply(Piece piece, Square from, Square to, Optional<String> comment) {
        this.piece = piece;
        this.from = from;
        this.to = to;
        this.comment = comment;
    }

    /**
     * returns Piece global variable
     * @return Piece global var
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     * returns From Square
     * @return from square
     */
    public Square getFrom() {
        return from;
    }

    /**
     * returns To Square
     * @return to square
     */
    public Square getTo() {
        return to;
    }

    /**
     * returns the Comment Optional variable
     * @return optional comment
     */
    public Optional<String> getComment() {
        return comment;
    }
}
