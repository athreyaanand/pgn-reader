public class ChessGame {
    List<Move> moves;

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
     * @param  int n tells which move
     * @return the move in the list associated with the nth move
     */
    public Move getMove(int n) {
        return moves.get(n - 1);
    }

    /**
     * Returns a list filtered by the predicate. Must not change moves field.
     * @param Predicate<Move> filter filters the moves list
     * @return a filtered list
     */
    public List<Move> filter(Predicate<Move> filter) {
      return moves.stream().filter(predicate)
             .collect(Collectors.<Move>toList());
    }

    /**
     * Returns a list of moves with comments.
     * @return a list of moves with commented Plys
     */
    public List<Move> getMovesWithComment() {
        return moves;
    }

    /**
     * Returns a list of moves without comments.
     * @return a list of moves without commented Plys
     */
    public List<Move> getMovesWithoutComment() {
        return moves;
    }

    /**
     * Returns a list of moves with a piece of this type;
     * @param Piece P that reveals what piece the moves should contain
     * @return a list of plies with this move
     */
    public List<Move> getMovesWithPiece(Piece P) {
        return moves;
    }
}
