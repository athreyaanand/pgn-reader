/**
 * Represents an exception class fo invalid squares
 *
 * @author aanand76
 * @version 1.0
 */
public class InvalidSquareException extends RuntimeException {

    /**
     * an arg constructor for InvalidSquareException
     * @param square string that shows name
     */
    public InvalidSquareException(String square) {
        super(square);
    }
}
