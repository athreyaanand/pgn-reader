public class Pawn extends Piece {

    public Pawn(Color color) {
        super(color);
    }

    public String algebraicName() {
        return "";
    }

    public String fenName() {
        return color.equals(Color.WHITE) ? "P" : "p";
    }

    public Square[] movesFrom(Square square) {
        return new Square[1000];
    }
}
