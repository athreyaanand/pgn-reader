import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.nio.file.Files;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Represents a database for ChessGames
 *
 * @author aanand76
 * @version 1.0
 */
public class ChessDb {

    private List<ChessGame> games;

    /**
     * A no arg constructor for ChessDb
     */
    public ChessDb() {
        games = new ArrayList<>();
        games.add(morphyIsouard());
        games.add(talFischer());
        addPgns(games);
    }

    /**
     * Gets a list of all ChessGames available
     * @return list of ChessGames
     */
    public List<ChessGame> getGames() {
        return games;
    }

    /**
     * A ChessGame for A Night at the Opera
     * @return ChessGame for A Night at the Opera
     */
    private ChessGame morphyIsouard() {
        ChessGame game = new ChessGame(
            "A Night at the Opera",
            "Paris Opera House",
            "1958.01.01",
            "Morphy, Paul",
            "Comte Isouard de Vauvenargues and Karl II, Duke of Brunswick",
            "1-0"
        );
        game.addMove("e4 e5");
        game.addMove("Nf3 d6");
        game.addMove("d4 Bg4");
        game.addMove("dxe5 Bxf3");
        game.addMove("Qxf3 dxe5");
        game.addMove("Bc4 Nf6");
        game.addMove("Qb3 Qe7");
        game.addMove("Nc3 c6");
        game.addMove("Bg5 b5");
        game.addMove("Nxb5 cxb5");
        game.addMove("Bxb5+ Nbd7");
        game.addMove("O-O-O Rd8");
        game.addMove("Rxd7 Rxd7");
        game.addMove("Rd1 Qe6");
        game.addMove("Bxd7+ Nxd7");
        game.addMove("Qb8+ Nxb8");
        game.addMove("Rd8#");
        return game;
    }

    /**
     * A ChessGame for Tal Fischer
     * @return ChessGame for Tal Fischer
     */
    private ChessGame talFischer() {
        ChessGame game = new ChessGame(
            "Bled-Zagreb-Belgrade Candidates",
            "Bled, Zagreb & Belgrade YUG",
            "1959.10.11",
            "Tal, Mikhail",
            "Fischer, Robert James",
            "1-0"
        );
        game.addMove("d4 Nf6");
        game.addMove("c4 g6");
        game.addMove("Nc3 Bg7");
        game.addMove("e4 d6");
        game.addMove("Be2 O-O");
        game.addMove("Nf3 e5");
        game.addMove("d5 Nbd7");
        game.addMove("Bg5 h6");
        game.addMove("Bh4 a6");
        game.addMove("O-O Qe8");
        game.addMove("Nd2 Nh7");
        game.addMove("b4 Bf6");
        game.addMove("Bxf6 Nhxf6");
        game.addMove("Nb3 Qe7");
        game.addMove("Qd2 Kh7");
        game.addMove("Qe3 Ng8");
        game.addMove("c5 f5");
        game.addMove("exf5 gxf5");
        game.addMove("f4 exf4");
        game.addMove("Qxf4 dxc5");
        game.addMove("Bd3 cxb4");
        game.addMove("Rae1 Qf6");
        game.addMove("Re6 Qxc3");
        game.addMove("Bxf5+ Rxf5");
        game.addMove("Qxf5+ Kh8");
        game.addMove("Rf3 Qb2");
        game.addMove("Re8 Nf6");
        game.addMove("Qxf6+ Qxf6");
        game.addMove("Rxf6 Kg7");
        game.addMove("Rff8 Ne7");
        game.addMove("Na5 h5");
        game.addMove("h4 Rb8");
        game.addMove("Nc4 b5");
        game.addMove("Ne5 1-0");
        return game;
    }

    /**
     * Adds PGN files as ChessGames to DB
     * @param chessGames is the current list of ChessGames that we add to
     */
    private void addPgns(List chessGames) {
        List<String> textFiles = new ArrayList<String>();
        File dir = new File(".");
        for (File file : dir.listFiles()) {
            if (file.getName().endsWith((".pgn"))) {
                textFiles.add(file.getPath());
            }
        }

        for (String game : textFiles) {
            chessGames.add(getGameFromPath(game));
        }
    }

    /**
     * Creates a ChessGame from the path of the pgn file
     * @param  path is path of pgn file
     * @return a ChessGame based off the date inside the pgn file
     */
    private ChessGame getGameFromPath(String path) {
        Path file = Paths.get(path);
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = Files.newBufferedReader(file)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                // Add the \n that's removed by readline()
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
            System.exit(1);
        }
        String game = sb.toString();

        String event = tagValue("Event", game);
        String site = tagValue("Site", game);
        String date = tagValue("Date", game);
        String white = tagValue("White", game);
        String black = tagValue("Black", game);
        String result = tagValue("Result", game);

        ChessGame cg = new ChessGame(event, site, date, white, black, result);
        addMoves(cg, game.substring(game.lastIndexOf(']')+3)
                                        .replaceAll("[\r\n]+", " "));
        return cg;
    }

    /**
     * Adds moves to the ChessGame we get from the PGN file
     * @param cg the ChessGame we add moves to
     * @param game the list of moves formatted with turn numbers
     */
    private void addMoves(ChessGame cg, String game) {
        int turn = 1;
        while (!turnValue(turn + ". ", game).equals("INVALID")) {
            cg.addMove(turnValue(turn + ". ", game));
            turn++;
        }
    }

    /**
     * used to obtain metadata from PGN file
     * @param  tagName our desired tag
     * @param  game string in which tag is located within
     * @return a string associated with tag
     */
    public static String tagValue(String tagName, String game) {
        String tagVal  = "NOT GIVEN";
        int index = game.indexOf(tagName);

        if (index > -1) {
            int index2 = game.indexOf("\"", index);
            tagVal = game.substring(index2 + 1, game.indexOf("\"", index2 + 1));
        }

        return (tagVal);
    }

    /**
     * used to obtain individual turns from the PGN FileSystemImpl
     * @param  turn is the number we look for within string
     * @param  game in which turns are stored
     * @return the desired move/turn
     */
    public static String turnValue(String turn, String game) {
        String turnVal  = "INVALID";
        int index = game.indexOf(turn);

        if (index > -1) {
            turnVal = game.substring(index + 3, game.indexOf(" ", index + 4));
        }

        return (turnVal.trim());
    }
}
