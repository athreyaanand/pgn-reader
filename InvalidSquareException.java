/**
 *The exception is unchecked due to the
 *fact that the client can not do anything
 *to recover from the exception, its
 *better for the user to know at runtime that
 *a square is invalid. We also don't need to
 *add extra lines in the square class
 *
 * @author aanand76
 */
public class InvalidSquareException extends RuntimeException {
    public InvalidSquareException(String message) {
        super(message);
    }
}
