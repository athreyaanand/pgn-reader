import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

/**
 * This is a tester class for the fall 2017 CS1331 HW5 assignment
 * It tests for all points that can be gained or lost
 * The test cases are not that exhaustive. I may update it in the
 * future. If you add your own feel free to post an updated file
 *
 * It is based on the HW2 assignment tester in order to ensure that it still
 * works from that version
 *
 * @author ssavelyev3
 * @version 5
 */
public class HW5Tester {
    private static boolean passedAllTests = true;

    /**
     * Main method
     *
     * @param args - program arguments (unused)
     */
    public static void main(String[] args) {
        //HW2 Stuffs (this should all pass as the solution was given to us)
        testColor();
        testSquare(); //This is a HW4 Thing
        testPiece();
        testKing();
        testQueen();
        testBishop();
        testKnight();
        testRook();
        testPawn();

        //HW5 Stuffs
        testPly();
        testMove();
        testChessGame();

        if (passedAllTests) {
            System.out.println("Passed All Tests");
        }
    }

    private static void testPly() {
        Ply ply1 = new Ply(new Queen(Color.WHITE), new Square("a1"),
                          new Square("a8"), Optional.empty());
        Ply ply2 = new Ply(new King(Color.BLACK), new Square("c4"),
                          new Square("d4"), Optional.of("KBc4d4"));
        Ply ply3 = new Ply(new Knight(Color.WHITE), new Square("a2"),
                          new Square("b4"), Optional.of("ply3ply3"));

        checkB(ply1.getPiece() instanceof Queen
               && ply2.getPiece() instanceof King
               && ply3.getPiece() instanceof Knight, "Ply.getPiece()");
        checkB(ply1.getFrom().equals(new Square("a1"))
               && ply2.getFrom().equals(new Square("c4"))
               && ply3.getFrom().equals(new Square("a2")), "Ply.getFrom()");
        checkB(ply1.getTo().equals(new Square("a8"))
               && ply2.getTo().equals(new Square("d4"))
               && ply3.getTo().equals(new Square("b4")), "Ply.getTo()");
        checkB(!ply1.getComment().isPresent()
               && ply2.getComment().isPresent()
               && ply2.getComment().get().equals("KBc4d4")
               && ply3.getComment().isPresent()
               && ply3.getComment().get().equals("ply3ply3"),
               "Ply.getComment()");
    }

    private static void testMove() {
        Ply ply1 = new Ply(new Queen(Color.WHITE), new Square("a1"),
                          new Square("a8"), Optional.empty());
        Ply ply2 = new Ply(new King(Color.BLACK), new Square("c4"),
                          new Square("d4"), Optional.of("KBc4d4"));
        Ply ply3 = new Ply(new Knight(Color.WHITE), new Square("a2"),
                          new Square("b4"), Optional.of("ply3ply3"));
        Ply ply4 = new Ply(new Bishop(Color.BLACK), new Square("a5"),
                          new Square("d8"), Optional.empty());

        Move move1 = new Move(ply1, ply2);
        Move move2 = new Move(ply3, ply4);

        //Already verified plys so i'll just do an == here because you should
        //just be storing them
        checkB(move1.getWhitePly() == ply1
               && move2.getWhitePly() == ply3, "Move.getWhitePly()");
        checkB(move1.getBlackPly() == ply2
               && move2.getBlackPly() == ply4, "Move.getBlackPly()");
    }

    private static void testChessGame() {
        Ply ply1 = new Ply(new Queen(Color.WHITE), new Square("a1"),
                          new Square("a8"), Optional.empty());
        Ply ply2 = new Ply(new King(Color.BLACK), new Square("c4"),
                          new Square("d4"), Optional.of("KBc4d4"));
        Ply ply3 = new Ply(new Knight(Color.WHITE), new Square("a2"),
                          new Square("b4"), Optional.of("ply3ply3"));
        Ply ply4 = new Ply(new Bishop(Color.BLACK), new Square("a5"),
                          new Square("d8"), Optional.empty());

        Move move1 = new Move(ply1, ply4);
        Move move2 = new Move(ply3, ply2);
        Move move3 = new Move(ply1, ply2);

        List<Move> moves = new ArrayList<>();
        moves.add(move1);
        moves.add(move2);
        moves.add(move3);

        ChessGame cg = new ChessGame(moves);

        checkB(cg.getMoves().equals(moves), "ChessGame.getMoves()");
        checkB(cg.getMove(0) == move1
               && cg.getMove(1) == move2, "ChessGame.getMove(int)");
        checkB(cg.filter(it -> true).equals(moves)
               && cg.filter(it -> false).isEmpty()
               && cg.filter(it -> it == move1).size() == 1,
               "ChessGame.filter(Predicate<Move>)");
        checkB(cg.getMoves().equals(moves),
               "ChessGame#filter doesn't modify moves");
        checkB(cg.getMovesWithComment().size() == 2
               && cg.getMovesWithComment().contains(move2)
               && cg.getMovesWithComment().contains(move3),
               "ChessGame.getMovesWithComment()");
        checkB(cg.getMoves().equals(moves),
               "ChessGame.getMovesWithComment() doesn't modify moves");
        checkB(cg.getMovesWithoutComment().size() == 1
               && cg.getMovesWithoutComment().get(0) == move1,
               "ChessGame.getMovesWithoutComment()");
        checkB(cg.getMoves().equals(moves),
               "ChessGame.getMovesWithoutComment() doesn't modify moves");
        checkB(cg.getMovesWithPiece(new Queen(Color.WHITE)).size() == 2
               && cg.getMovesWithPiece(new Queen(Color.WHITE)).contains(move1)
               && cg.getMovesWithPiece(new Queen(Color.WHITE)).contains(move3)
               && cg.getMovesWithPiece(new Knight(Color.WHITE)).size() == 1
               && cg.getMovesWithPiece(new Knight(Color.WHITE)).contains(move2)
               && cg.getMovesWithPiece(new Bishop(Color.WHITE)).size() == 1
               && cg.getMovesWithPiece(new Bishop(Color.WHITE)).contains(move1),
               "ChessGame.getMovesWithPiece(Piece)");
    }

    private static void testColor() {
        //5 points for Color existing
        Color whiteColor = Color.WHITE;
        Color blackColor = Color.BLACK;
    }

    private static void testSquare() {
        //5 points for char constructor
        Square a1 = new Square('a', '1');
        checkB(a1.toString().equals("a1"), "Square.toString()");
        try {
            Object a1Str = "a1";
            checkB(!a1.equals(a1Str), "Square.equals(String)");
        } catch (ClassCastException e) {
            System.err.println("Failed test: Square.equals(String)");
            System.err.println("\tReason: improperly written equals method");
            System.err.println("\thttp://cs1331.gatech.edu/slides/"
                               + "object-superclass.pdf");
            e.printStackTrace();
        }

        //5 points for String constructor
        Object a1String = new Square("a1");
        checkB(a1.equals(a1String), "Square.equals(Square)");
    }

    private static void testPiece() {
        //This is just checking for the ability to compile
        //It also checks that Piece is storing the color and not the other
        //classes
        Piece w = new Piece(Color.WHITE) {
                @Override
                public String algebraicName() {
                    return "algebraicName";
                }

                @Override
                public String fenName() {
                    return "fenName";
                }

                @Override
                public Square[] movesFrom(Square square) {
                    return new Square[0];
                }
            };
        Piece b = new Piece(Color.BLACK) {
                @Override
                    public String algebraicName() {
                    return "algebraicName";
                }

                @Override
                    public String fenName() {
                    return "fenName";
                }

                @Override
                    public Square[] movesFrom(Square square) {
                    return new Square[0];
                }
            };

        checkB(w.getColor() == Color.WHITE && b.getColor() == Color.BLACK,
               "Piece.getColor()");
        //These must compile
        w.algebraicName();
        w.fenName();
        w.movesFrom(new Square("a1"));
    }

    private static void testKing() {
        Piece kingW = new King(Color.WHITE);
        Piece kingB = new King(Color.BLACK);

        checkB(kingW.getColor() == Color.WHITE
               && kingB.getColor() == Color.BLACK , "King.getColor()");

        checkB(kingW.algebraicName().equals("K")
               && kingB.algebraicName().equals("K"),
               "King.algebraicName()");
        checkB(kingW.fenName().equals("K")
               && kingB.fenName().equals("k"), "King.fenName()");

        checkB(arraysContainSame(kingW.movesFrom(new Square("a1")),
                                 new Square[] {
                                     new Square("a2"),
                                     new Square("b1"),
                                     new Square("b2")
                                 })
               && arraysContainSame(kingW.movesFrom(new Square("h1")),
                                 new Square[] {
                                     new Square("h2"),
                                     new Square("g1"),
                                     new Square("g2")
                                 })
               && arraysContainSame(kingW.movesFrom(new Square("a8")),
                                 new Square[] {
                                     new Square("a7"),
                                     new Square("b8"),
                                     new Square("b7")
                                 })
               && arraysContainSame(kingW.movesFrom(new Square("b2")),
                                 new Square[] {
                                     new Square("a1"),
                                     new Square("a2"),
                                     new Square("a3"),
                                     new Square("b1"),
                                     new Square("b3"),
                                     new Square("c1"),
                                     new Square("c2"),
                                     new Square("c3")
                                 })
               && arraysContainSame(kingW.movesFrom(new Square("h8")),
                                 new Square[] {
                                     new Square("h7"),
                                     new Square("g8"),
                                     new Square("g7")
                                 }),
               "King.movesFrom(Square)");
    }

    private static void testQueen() {
        Piece w = new Queen(Color.WHITE);
        Piece b = new Queen(Color.BLACK);

        checkB(w.getColor() == Color.WHITE
               && b.getColor() == Color.BLACK , "Queen.getColor()");

        checkB(w.algebraicName().equals("Q")
               && b.algebraicName().equals("Q"),
               "Queen.algebraicName()");
        checkB(w.fenName().equals("Q")
               && b.fenName().equals("q"), "Queen.fenName()");

        checkB(arraysContainSame(w.movesFrom(new Square("a1")),
                                 new Square[] {
                                     new Square("a2"),
                                     new Square("a3"),
                                     new Square("a4"),
                                     new Square("a5"),
                                     new Square("a6"),
                                     new Square("a7"),
                                     new Square("a8"),
                                     new Square("b1"),
                                     new Square("c1"),
                                     new Square("d1"),
                                     new Square("e1"),
                                     new Square("f1"),
                                     new Square("g1"),
                                     new Square("h1"),
                                     new Square("b2"),
                                     new Square("c3"),
                                     new Square("d4"),
                                     new Square("e5"),
                                     new Square("f6"),
                                     new Square("g7"),
                                     new Square("h8")
                                 })
               && arraysContainSame(w.movesFrom(new Square("d3")),
                                 new Square[] {
                                     new Square("a3"),
                                     new Square("b3"),
                                     new Square("c3"),
                                     new Square("e3"),
                                     new Square("f3"),
                                     new Square("g3"),
                                     new Square("h3"),
                                     new Square("d1"),
                                     new Square("d2"),
                                     new Square("d4"),
                                     new Square("d5"),
                                     new Square("d6"),
                                     new Square("d7"),
                                     new Square("d8"),
                                     new Square("b1"),
                                     new Square("c2"),
                                     new Square("e4"),
                                     new Square("f5"),
                                     new Square("g6"),
                                     new Square("h7"),
                                     new Square("f1"),
                                     new Square("e2"),
                                     new Square("c4"),
                                     new Square("b5"),
                                     new Square("a6")
                                 }),
               "Queen.movesFrom(Square)");
    }

    private static void testBishop() {
        Piece w = new Bishop(Color.WHITE);
        Piece b = new Bishop(Color.BLACK);

        checkB(w.getColor() == Color.WHITE
               && b.getColor() == Color.BLACK , "Bishop.getColor()");

        checkB(w.algebraicName().equals("B")
               && b.algebraicName().equals("B"),
               "Bishop.algebraicName()");
        checkB(w.fenName().equals("B")
               && b.fenName().equals("b"), "Bishop.fenName()");

        checkB(arraysContainSame(w.movesFrom(new Square("a1")),
                                 new Square[] {
                                     new Square("b2"),
                                     new Square("c3"),
                                     new Square("d4"),
                                     new Square("e5"),
                                     new Square("f6"),
                                     new Square("g7"),
                                     new Square("h8")
                                 })
               && arraysContainSame(w.movesFrom(new Square("d3")),
                                 new Square[] {
                                     new Square("b1"),
                                     new Square("c2"),
                                     new Square("e4"),
                                     new Square("f5"),
                                     new Square("g6"),
                                     new Square("h7"),
                                     new Square("f1"),
                                     new Square("e2"),
                                     new Square("c4"),
                                     new Square("b5"),
                                     new Square("a6")
                                 }),
               "Bishop.movesFrom(Square)");
    }

    private static void testKnight() {
        Piece w = new Knight(Color.WHITE);
        Piece b = new Knight(Color.BLACK);

        checkB(w.getColor() == Color.WHITE
               && b.getColor() == Color.BLACK , "Knight.getColor()");

        checkB(w.algebraicName().equals("N")
               && b.algebraicName().equals("N"),
               "Knight.algebraicName()");
        checkB(w.fenName().equals("N")
               && b.fenName().equals("n"), "Knight.fenName()");

        checkB(arraysContainSame(w.movesFrom(new Square("a1")),
                                 new Square[] {
                                     new Square("b3"),
                                     new Square("c2")
                                 })
               && arraysContainSame(w.movesFrom(new Square("a8")),
                                 new Square[] {
                                     new Square("b6"),
                                     new Square("c7")
                                 })
               && arraysContainSame(w.movesFrom(new Square("h1")),
                                 new Square[] {
                                     new Square("g3"),
                                     new Square("f2")
                                 })
               && arraysContainSame(w.movesFrom(new Square("h8")),
                                 new Square[] {
                                     new Square("g6"),
                                     new Square("f7")
                                 })
               && arraysContainSame(w.movesFrom(new Square("d3")),
                                 new Square[] {
                                     new Square("c1"),
                                     new Square("e1"),
                                     new Square("b2"),
                                     new Square("f2"),
                                     new Square("b4"),
                                     new Square("f4"),
                                     new Square("c5"),
                                     new Square("e5")
                                 }),
               "Knight.movesFrom(Square)");
    }

    private static void testRook() {
        Piece w = new Rook(Color.WHITE);
        Piece b = new Rook(Color.BLACK);

        checkB(w.getColor() == Color.WHITE
               && b.getColor() == Color.BLACK , "Rook.getColor()");

        checkB(w.algebraicName().equals("R")
               && b.algebraicName().equals("R"),
               "Rook.algebraicName()");
        checkB(w.fenName().equals("R")
               && b.fenName().equals("r"), "Rook.fenName()");

        checkB(arraysContainSame(w.movesFrom(new Square("a1")),
                                 new Square[] {
                                     new Square("a2"),
                                     new Square("a3"),
                                     new Square("a4"),
                                     new Square("a5"),
                                     new Square("a6"),
                                     new Square("a7"),
                                     new Square("a8"),
                                     new Square("b1"),
                                     new Square("c1"),
                                     new Square("d1"),
                                     new Square("e1"),
                                     new Square("f1"),
                                     new Square("g1"),
                                     new Square("h1")
                                 })
               && arraysContainSame(w.movesFrom(new Square("d3")),
                                 new Square[] {
                                     new Square("a3"),
                                     new Square("b3"),
                                     new Square("c3"),
                                     new Square("e3"),
                                     new Square("f3"),
                                     new Square("g3"),
                                     new Square("h3"),
                                     new Square("d1"),
                                     new Square("d2"),
                                     new Square("d4"),
                                     new Square("d5"),
                                     new Square("d6"),
                                     new Square("d7"),
                                     new Square("d8")
                                 }),
               "Rook.movesFrom(Square)");
    }

    private static void testPawn() {
        Piece w = new Pawn(Color.WHITE);
        Piece b = new Pawn(Color.BLACK);

        checkB(w.getColor() == Color.WHITE
               && b.getColor() == Color.BLACK , "Pawn.getColor()");

        checkB(w.algebraicName().equals("")
               && b.algebraicName().equals(""),
               "Pawn.algebraicName()");
        checkB(w.fenName().equals("P")
               && b.fenName().equals("p"), "Pawn.fenName()");

        checkB(arraysContainSame(w.movesFrom(new Square("a2")),
                                 new Square[] {
                                     new Square("a3"),
                                     new Square("a4")
                                 })
               && arraysContainSame(b.movesFrom(new Square("a7")),
                                 new Square[] {
                                     new Square("a6"),
                                     new Square("a5")
                                 })
               && arraysContainSame(w.movesFrom(new Square("d3")),
                                 new Square[] {
                                     new Square("d4")
                                 })
               && arraysContainSame(w.movesFrom(new Square("a8")),
                                    new Square[0])
               && arraysContainSame(b.movesFrom(new Square("d6")),
                                 new Square[] {
                                     new Square("d5")
                                 }),
               "Pawn.movesFrom(Square)");
    }

    private static void checkB(boolean result, String testName) {
        if (!result) {
            //If we passed a test we don't care
            //System.out.println("Passed test: " + testName);
            //} else {
            System.err.println("Failed test: " + testName);
            passedAllTests = false;
        }
    }

    private static boolean arraysContainSame(Square[] a1, Square[] a2) {
        Comparator<Square> comp = (s1, s2) -> {
            return s1.toString().compareTo(s2.toString());
        };
        Arrays.sort(a1, comp);
        Arrays.sort(a2, comp);
        boolean output = Arrays.deepEquals(a1, a2);
        if (!output) {
            System.err.println("Arrays not equal: " + Arrays.deepToString(a1)
                               + " " + Arrays.deepToString(a2));
        }
        return output;
    }
}
