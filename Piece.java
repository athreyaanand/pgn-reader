public abstract class Piece {
    protected Color color;

    public Piece(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public abstract String algebraicName();

    public abstract String fenName();

    public abstract Square[] movesFrom(Square square);
}
