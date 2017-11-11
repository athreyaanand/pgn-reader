public class King extends Piece {

    public King(Color color) {
        super(color);
    }

    public String algebraicName() {
        return "K";
    }

    public String fenName() {
        return color.equals(Color.WHITE) ? "K" : "k";
    }

    public Square[] movesFrom(Square square) {
        int squareCount = 0;
        int[][] offsets = {{1, 0}, {0, 1}, {-1, 0}, {0, -1},
                           {1, 1}, {-1, 1}, {-1, -1}, {1, -1}};
        int[] pRanks = new int[offsets.length];
        int[] pFiles = new int[offsets.length];
        for (int i = 0; i < offsets.length; i++) {
            if (isRankFileValid((int) square.getRank() + offsets[i][0],
                                (int) square.getFile() + offsets[i][1])) {
                pRanks[i] = (int) square.getRank() + offsets[i][0];
                pFiles[i] = (int) square.getFile() + offsets[i][1];
                squareCount++;
            }
        }
        Square[] moveSquares = new Square[squareCount];
        for (int j = 0; j < pRanks.length; j++) {
            if (pRanks[j] != 0) {
                moveSquares[squareCount - 1] = new Square((char) pFiles[j],
                                                        (char) pRanks[j]);
            }
        }
        return moveSquares;
    }

    private boolean isRankFileValid(int file, int rank) {
        if ((rank > 96 && rank < 105) && (file > 48 && file < 57)) {
            return true;
        }
        return false;
    }
}
