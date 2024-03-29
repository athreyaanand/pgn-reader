import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Represents a ChessGame
 *
 * @author aanand76
 * @version 1.0
 */
public class ChessGame {

    private StringProperty event = new SimpleStringProperty(this, "NA");
    private StringProperty site = new SimpleStringProperty(this, "NA");
    private StringProperty date = new SimpleStringProperty(this, "NA");
    private StringProperty white = new SimpleStringProperty(this, "NA");
    private StringProperty black = new SimpleStringProperty(this, "NA");
    private StringProperty result = new SimpleStringProperty(this, "NA");
    private List<String> moves;

    /**
     * an arg constructor for ChessGame
     * @param  event name of event
     * @param  site location of game
     * @param  date date of game
     * @param  white white player name
     * @param  black black player name
     * @param  result final result of game
     */
    public ChessGame(String event, String site, String date,
                     String white, String black, String result) {
        this.event.set(event);
        this.site.set(site);
        this.date.set(date);
        this.white.set(white);
        this.black.set(black);
        this.result.set(result);
        moves = new ArrayList<>();
    }

    /**
     * adds move to ChessGame
     * @param move needed to be added
     */
    public void addMove(String move) {
        moves.add(move);
    }

    /**
     * gets move in a certain location
     * @param n number location within list of moves
     * @return a string with the move required
     */
    public String getMove(int n) {
        return moves.get(n - 1);
    }

    /**
     * gets the moves array in a String form
     * @return a string of moves
     */
    public String getMoves() {
        return moves.toString();
    }

    /**
     * gets Event of game
     * @return Event of game
     */
    public String getEvent() {
        return event.get();
    }

    /**
     * gets Site of game
     * @return Site of game
     */
    public String getSite() {
        return site.get();
    }

    /**
     * gets Date of game
     * @return Date of game
     */
    public String getDate() {
        return date.get();
    }

    /**
     * gets White player of game
     * @return White player of game
     */
    public String getWhite() {
        return white.get();
    }

    /**
     * gets Black player of game
     * @return gets Black player of game
     */
    public String getBlack() {
        return black.get();
    }

    /**
     * gets Result of game
     * @return Result of game
     */
    public String getResult() {
        return result.get();
    }

    /**
     * gets Opening of game
     * @return Opening of game
     */
    public String getOpening() {
        String moveStr = "1. " + getMove(1) + " 2. " + getMove(2)
                          + " 3. " + getMove(3);
        if (moveStr.contains("1. e4 e5 2. Nf3 Nc6 3. Bc4 Bc5")) {
            return ("Giuoco Piano");
        } else if (moveStr.contains("1. e4 e5 2. Nf3 Nc6 3. Bb5")) {
            return ("Ruy Lopez");
        } else if (moveStr.contains("1. e4 c5")) {
            return ("Sicilian Defence");
        } else if (moveStr.contains("1. d4 d5 2. c4")) {
            return ("Queen's Gambit");
        } else if (moveStr.contains("1. d4 Nf6")) {
            return ("Indian defence");
        } else if (moveStr.contains("1. e4 e5 2. Nf3 d6")) {
            return ("Philidor Defence");
        }
        return "N/A";
    }
}
