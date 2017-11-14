/**
 * Represents a Move class that is used by ChessGame
 *
 * @author aanand76
 * @version 1.0
 */
public class Move {
    private Ply whitePly, blackPly;

    /**
     * An argument constructor for Move
     * @param  whitePly a Ply representing the white move
     * @param  blackPly a Ply representing the black move
     */
    public Move(Ply whitePly, Ply blackPly) {
        this.whitePly = whitePly;
        this.blackPly = blackPly;
    }

    /**
     * returns the White Ply
     * @return a Ply representing white
     */
    public Ply getWhitePly() {
        return whitePly;
    }

    /**
     * returns the Black Ply
     * @return a Ply representing black
     */
    public Ply getBlackPly() {
        return blackPly;
    }
}
