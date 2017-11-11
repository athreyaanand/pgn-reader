public class Rook extends Piece {

    public Rook(Color color) {
        super(color);
    }

    public String algebraicName() {
        return "R";
    }

    public String fenName() {
        return color.equals(Color.WHITE) ? "R" : "r";
    }

    public Square[] movesFrom(Square square) {
        int squareCount = 0;
        int[] pRanks = new int[16];
        int[] pFiles = new int[16];
        int iFile = (int) square.getFile() - 7;
        int iRank = (int) square.getRank() - 7;
        for (int i = 0; i < 8; i++) {
            if (isRankFileValid(iFile + i, (int) square.getRank())
                && !square.equals(new Square((char) (iFile + i),
                                             square.getRank()))) {
                pRanks[i] = (int) square.getRank();
                pFiles[i] = iFile + i;
                squareCount++;
            }

            if (isRankFileValid((int) square.getFile(), iRank + i)
                && !square.equals(new Square(square.getFile(),
                                            (char) (iRank + i)))) {
                pRanks[i * 2] = iRank + i;
                pFiles[i * 2] = (int) square.getFile();
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
