public class Queen extends Piece {

    public Queen(Color color) {
        super(color);
    }

    public String algebraicName() {
        return "Q";
    }

    public String fenName() {
        return color.equals(Color.WHITE) ? "Q" : "q";
    }

    public Square[] movesFrom(Square square) {
        return new Square[1000];
    }
}
