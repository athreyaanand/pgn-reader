public class Move {
    private Ply whitePly, blackPly;

    public Move(Ply whitePly, Ply blackPly) {
        this.whitePly = whitePly;
        this.blackPly = blackPly;
    }

    public Ply getWhitePly() {
        return whitePly;
    }

    public Ply getBlackPly() {
        return blackPly;
    }
}
