import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ChessGame {

    private StringProperty event = new SimpleStringProperty(this, "NA");
    private StringProperty site = new SimpleStringProperty(this, "NA");
    private StringProperty date = new SimpleStringProperty(this, "NA");
    private StringProperty white = new SimpleStringProperty(this, "NA");
    private StringProperty black = new SimpleStringProperty(this, "NA");
    private StringProperty result = new SimpleStringProperty(this, "NA");
    private List<String> moves;

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

    public void addMove(String move) {
        moves.add(move);
    }

    public String getMove(int n) {
        return moves.get(n - 1);
    }

    public String getMoves() {
        return moves.toString();
    }

    public String getEvent() {
        return event.get();
    }

    public String getSite() {
        return site.get();
    }

    public String getDate() {
        return date.get();
    }

    public String getWhite() {
        return white.get();
    }

    public String getBlack() {
        return black.get();
    }

    public String getResult() {
        return result.get();
    }

    public String getOpening() {
        String moveStr = "1. " + getMove(1) + " 2. " + getMove(2) + " 3. "+getMove(3);
        System.out.println(moveStr);
        if (moveStr.contains("1. e4 e5 2. Nf3 Nc6 3. Bc4 Bc5")) {
            return("Giuoco Piano");
        } else if (moveStr.contains("1. e4 e5 2. Nf3 Nc6 3. Bb5")) {
            return("Ruy Lopez");
        } else if (moveStr.contains("1. e4 c5")) {
            return("Sicilian Defence");
        } else if (moveStr.contains("1. d4 d5 2. c4")) {
            return("Queen's Gambit");
        } else if (moveStr.contains("1. d4 Nf6")) {
            return("Indian defence");
        } else if (moveStr.contains("1. e4 e5 2. Nf3 d6")) {
            return("Philidor Defence");
        }
        return "N/A";
    }
}
