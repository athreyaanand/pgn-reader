import java.util.List;
import java.util.stream.Collectors;
import java.util.function.Predicate;

/**
 * Represents a class that uses Move and Ply to create ChessGame
 *
 * @author aanand76
 * @version 1.0
 */
public class ChessGame {
    private List<Move> moves;

    /**
     * an argument constructor for ChessGame
     * @param  moves is a list of moves in the game
     */
    public ChessGame(List<Move> moves) {
        this.moves = moves;
    }

    /**
     * Return all moves.
     * @return a list containing all moves of a game
     */
    public List<Move> getMoves() {
        return moves;
    }

    /**
     * Gets nth move.
     * @param  n tells which move
     * @return the move in the list associated with the nth move
     */
    public Move getMove(int n) {
        return moves.get(n);
    }

    /**
     * Returns a list filtered by the predicate. Must not change moves field.
     * @param filter is a predicate that filters the moves list
     * @return a filtered list
     */
    public List<Move> filter(Predicate<Move> filter) {
        return moves.stream().filter(filter).collect(Collectors.<Move>toList());
    }

    /**
     * Returns a list of moves with comments.
     * @return a list of moves with commented Plys
     */
    public List<Move> getMovesWithComment() {
        return filter((Move s) -> s.getBlackPly().getComment().isPresent()
                      || s.getBlackPly().getComment().isPresent());
    }

    /**
     * Returns a list of moves without comments.
     * @return a list of moves without commented Plys
     */
    public List<Move> getMovesWithoutComment() {
        return filter(new Predicate<Move>() {
            public boolean test(Move m) {
                return !m.getBlackPly().getComment().isPresent()
                       || !m.getBlackPly().getComment().isPresent();
            }
        });
    }

    /**
     * Returns a list of moves with a piece of this type;
     * @param p that reveals what piece the moves should contain
     * @return a list of plies with this move
     */
    public List<Move> getMovesWithPiece(Piece p) {
        class PieceContainer implements Predicate<Move> {
            public boolean test(Move m) {
                return p.algebraicName().equals(m.getWhitePly()
                        .getPiece().algebraicName()) || p.algebraicName()
                        .equals(m.getBlackPly().getPiece().algebraicName());
            }
        }
        return filter(new PieceContainer());
    }
}
