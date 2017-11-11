public class Bishop extends Piece {

    public Bishop(Color color) {
        super(color);
    }

    public String algebraicName() {
        return "B";
    }

    public String fenName() {
        return color.equals(Color.WHITE) ? "B" : "b";
    }

    public Square[] movesFrom(Square square) {
        return new Square[1000];
    }
}
